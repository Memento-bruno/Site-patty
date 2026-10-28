/* Conferência automática de acessibilidade e de layout.

   Uso: npm run build && npm run a11y

   Roda o axe-core nas três páginas, procura rolagem horizontal em várias
   larguras (inclusive com zoom de 200%), confere o tamanho dos alvos de
   toque e testa o menu do celular pelo teclado. */
import { chromium } from 'playwright';
import { opcoesNavegador } from './navegador.mjs';
import { spawnSync } from 'node:child_process';
import { readFileSync } from 'node:fs';
import { setTimeout as espera } from 'node:timers/promises';

const PORTA = Number(process.env.PORTA_PREVIEW ?? 4399);
const BASE = (process.env.BASE_PATH ?? '/Site-patty').replace(/\/$/, '');
const PAGINAS = ['/', '/politica-de-privacidade', '/pagina-que-nao-existe'];

const axeFonte = readFileSync('node_modules/axe-core/axe.min.js', 'utf8');

const astro = (...a) => spawnSync('npx', ['astro', ...a], { stdio: 'ignore' });
astro('preview', 'stop');
astro('preview', '--port', String(PORTA), '--host', '127.0.0.1');

const endereco = (caminho) => `http://127.0.0.1:${PORTA}${BASE}${caminho}`;

let respondeu = false;
for (let i = 0; i < 120 && !respondeu; i += 1) {
  try { respondeu = (await fetch(endereco('/'))).ok; } catch { /* subindo */ }
  if (!respondeu) await espera(250);
}
if (!respondeu) { astro('preview', 'stop'); throw new Error('preview não subiu'); }

const problemas = [];
const navegador = await chromium.launch(opcoesNavegador);

try {
  /* ---------------------------------------------------- axe-core */
  for (const largura of [390, 1440]) {
    const contexto = await navegador.newContext({
      viewport: { width: largura, height: 900 },
      reducedMotion: 'reduce',
      locale: 'pt-BR',
    });
    const pagina = await contexto.newPage();

    for (const caminho of PAGINAS) {
      await pagina.goto(endereco(caminho), { waitUntil: 'networkidle', timeout: 60_000 });
      await pagina.addScriptTag({ content: axeFonte });
      const resultado = await pagina.evaluate(async () =>
        // @ts-expect-error axe é injetado acima
        await window.axe.run(document, {
          runOnly: { type: 'tag', values: ['wcag2a', 'wcag2aa', 'wcag21a', 'wcag21aa', 'wcag22aa', 'best-practice'] },
        }),
      );

      for (const violacao of resultado.violations) {
        problemas.push(
          `axe [${largura}px] ${caminho} — ${violacao.id} (${violacao.impact}): ${violacao.help}` +
            `\n      ${violacao.nodes.slice(0, 3).map((n) => n.target.join(' ')).join('\n      ')}`,
        );
      }
    }
    await contexto.close();
  }

  /* ------------------------------------- rolagem horizontal e zoom 200% */
  for (const largura of [320, 390, 768, 1024, 1440]) {
    const contexto = await navegador.newContext({
      viewport: { width: largura, height: 900 },
      reducedMotion: 'reduce',
    });
    const pagina = await contexto.newPage();
    for (const caminho of PAGINAS) {
      await pagina.goto(endereco(caminho), { waitUntil: 'networkidle', timeout: 60_000 });
      const excesso = await pagina.evaluate(
        () => document.documentElement.scrollWidth - document.documentElement.clientWidth,
      );
      if (excesso > 1) problemas.push(`rolagem horizontal [${largura}px] ${caminho}: +${excesso}px`);
    }
    await contexto.close();
  }

  /* zoom de 200% = metade da largura com a mesma escala de texto */
  for (const largura of [640, 720]) {
    const contexto = await navegador.newContext({ viewport: { width: largura, height: 512 }, deviceScaleFactor: 2 });
    const pagina = await contexto.newPage();
    await pagina.goto(endereco('/'), { waitUntil: 'networkidle', timeout: 60_000 });
    const excesso = await pagina.evaluate(
      () => document.documentElement.scrollWidth - document.documentElement.clientWidth,
    );
    if (excesso > 1) problemas.push(`zoom 200% [${largura}px] rolagem horizontal: +${excesso}px`);
    await contexto.close();
  }

  /* ------------------------------------------------ revelação ao rolar */
  /* O conteúdo só pode nascer escondido quando há JavaScript e a pessoa não
     pediu menos movimento. Se algum desses caminhos quebrar, parte da página
     fica invisível — por isso cada um é conferido aqui. */
  {
    const contar = (pagina) =>
      pagina.evaluate(() => {
        const blocos = [...document.querySelectorAll('[data-revelar]')].flatMap((el) =>
          el.hasAttribute('data-revelar-grupo') ? [...el.children] : [el],
        );
        const opacidades = blocos.map((el) => Number(getComputedStyle(el).opacity));
        return {
          total: blocos.length,
          visiveis: opacidades.filter((o) => o > 0.95).length,
        };
      });

    /* 1. Com movimento permitido, o que está fora da tela começa escondido
          e aparece ao rolar. */
    {
      const contexto = await navegador.newContext({ viewport: { width: 1440, height: 900 } });
      const pagina = await contexto.newPage();
      await pagina.goto(endereco('/'), { waitUntil: 'networkidle', timeout: 60_000 });
      await espera(400);
      const inicio = await contar(pagina);
      if (inicio.total === 0) problemas.push('nenhum bloco marcado com data-revelar foi encontrado');
      if (inicio.total > 0 && inicio.visiveis === inicio.total) {
        problemas.push('revelação: nada começou escondido — o efeito não está agindo');
      }
      await pagina.evaluate(() => window.scrollTo(0, document.body.scrollHeight));
      await espera(2500);
      const fim = await contar(pagina);
      if (fim.visiveis !== fim.total) {
        problemas.push(`revelação: ${fim.total - fim.visiveis} bloco(s) seguiram invisíveis depois de rolar a página`);
      }
      await contexto.close();
    }

    /* 2. Com "reduzir movimento", nada se esconde em momento nenhum. */
    {
      const contexto = await navegador.newContext({
        viewport: { width: 1440, height: 900 },
        reducedMotion: 'reduce',
      });
      const pagina = await contexto.newPage();
      await pagina.goto(endereco('/'), { waitUntil: 'networkidle', timeout: 60_000 });
      await espera(400);
      const r = await contar(pagina);
      if (r.visiveis !== r.total) {
        problemas.push(`revelação: com movimento reduzido, ${r.total - r.visiveis} bloco(s) ficaram invisíveis`);
      }
      await contexto.close();
    }

    /* 3. Sem JavaScript, a página aparece inteira. */
    {
      const contexto = await navegador.newContext({
        viewport: { width: 1440, height: 900 },
        javaScriptEnabled: false,
      });
      const pagina = await contexto.newPage();
      await pagina.goto(endereco('/'), { waitUntil: 'domcontentloaded', timeout: 60_000 });
      await espera(600);
      const r = await contar(pagina);
      if (r.visiveis !== r.total) {
        problemas.push(`revelação: sem JavaScript, ${r.total - r.visiveis} bloco(s) ficaram invisíveis`);
      }
      await contexto.close();
    }

    /* 4. Trava de segurança: se o script da revelação não rodar (erro de
          sintaxe num navegador antigo, por exemplo), a classe sai sozinha e
          o conteúdo aparece. O script é embutido no HTML pelo Astro, então
          aqui ele é removido da resposta para simular a falha. */
    {
      const contexto = await navegador.newContext({ viewport: { width: 1440, height: 900 } });
      const pagina = await contexto.newPage();
      let removeu = false;
      await pagina.route(endereco('/'), async (rota) => {
        const resposta = await rota.fetch();
        const original = await resposta.text();
        const html = original.replace(
          /* O (?:(?!<\/script>)[\s\S]) impede a busca de atravessar o fim de um
             script: sem isso ela começaria no primeiro <script type="module">
             da página (o do menu) e varreria tudo até achar o termo. */
          /<script type="module">(?:(?!<\/script>)[\s\S])*?IntersectionObserver(?:(?!<\/script>)[\s\S])*?<\/script>/,
          '',
        );
        removeu = html !== original;
        /* Cabeçalhos recriados sem content-encoding nem content-length: o
           corpo aqui já vem descomprimido, e reaproveitá-los entregaria ao
           navegador um texto que ele tentaria descomprimir. */
        const cabecalhos = { ...resposta.headers() };
        delete cabecalhos['content-encoding'];
        delete cabecalhos['content-length'];
        await rota.fulfill({ status: resposta.status(), headers: cabecalhos, body: html });
      });
      await pagina.goto(endereco('/'), { waitUntil: 'domcontentloaded', timeout: 60_000 });
      await espera(600);
      const antes = await contar(pagina);
      if (!removeu) {
        problemas.push('revelação: não achei o script no HTML para simular a falha — o teste da trava não valeu');
      } else if (antes.total === 0) {
        problemas.push('revelação: a página não carregou no teste da trava de segurança');
      } else if (antes.visiveis === antes.total) {
        problemas.push('revelação: sem o script, o conteúdo já nascia visível — a trava não foi exercitada');
      }
      await espera(3400);
      const depois = await contar(pagina);
      if (depois.visiveis !== depois.total) {
        problemas.push('revelação: a trava de segurança não revelou o conteúdo quando o script não rodou');
      }
      await contexto.close();
    }
  }

  /* ------------------------------------------ chegada e âncoras na URL */
  /* O site abre sempre no topo. Já quebrou uma vez: o scroll-behavior:
     smooth no html capturava o salto inicial da âncora e a página descia
     animada por dois segundos, parando no lugar errado no celular. */
  for (const largura of [1440, 390]) {
    const contexto = await navegador.newContext({ viewport: { width: largura, height: 900 } });
    const pagina = await contexto.newPage();

    /* Abrir com âncora na URL não pode tirar a página do topo. */
    const posicoes = [];
    await pagina.goto(`${endereco('/')}#agendar`, { waitUntil: 'commit', timeout: 60_000 });
    for (let i = 0; i < 14; i += 1) {
      try {
        posicoes.push(await pagina.evaluate(() => Math.round(window.scrollY)));
      } catch {
        /* navegação em curso */
      }
      await espera(110);
    }
    const maior = Math.max(...posicoes);
    if (maior > 5) {
      problemas.push(`chegada [${largura}px]: abrir com âncora tirou a página do topo (${maior}px)`);
    }
    if (pagina.url().includes('#')) {
      problemas.push(`chegada [${largura}px]: a âncora continuou na URL`);
    }

    /* O clique na navegação precisa seguir rolando até a seção, e deixar a
       URL limpa depois. */
    await pagina.click('.hero a.botao[href="#agendar"]');
    await espera(2200);
    const apos = await pagina.evaluate(() => ({
      hash: location.hash,
      topoDaSecao: Math.round(document.querySelector('#agendar').getBoundingClientRect().top),
      rolou: window.scrollY > 100,
    }));
    if (!apos.rolou) problemas.push(`chegada [${largura}px]: o clique não rolou até a seção`);
    if (apos.hash) problemas.push(`chegada [${largura}px]: a âncora não foi limpa depois do clique`);
    if (Math.abs(apos.topoDaSecao - 112) > 48) {
      problemas.push(`chegada [${largura}px]: o clique parou a ${apos.topoDaSecao}px, fora da seção`);
    }

    await contexto.close();
  }

  /* --------------------------------------------- alvos de toque e fonte */
  {
    const contexto = await navegador.newContext({ viewport: { width: 390, height: 844 } });
    const pagina = await contexto.newPage();
    await pagina.goto(endereco('/'), { waitUntil: 'networkidle', timeout: 60_000 });

    const pequenos = await pagina.evaluate(() =>
      [...document.querySelectorAll('a, button, summary')]
        .filter((el) => el.getClientRects().length > 0)
        .map((el) => {
          const r = el.getBoundingClientRect();
          return { texto: (el.textContent ?? '').trim().slice(0, 40), altura: Math.round(r.height), largura: Math.round(r.width) };
        })
        .filter((item) => item.altura < 48),
    );
    for (const item of pequenos) {
      problemas.push(`alvo de toque menor que 48px: "${item.texto}" (${item.largura}×${item.altura})`);
    }

    const miudos = await pagina.evaluate(() => {
      const vistos = new Set();
      for (const el of document.querySelectorAll('body *')) {
        if (!el.textContent?.trim() || el.children.length > 0) continue;
        if (el.closest('.so-leitor, .pular')) continue;
        const tamanho = parseFloat(getComputedStyle(el).fontSize);
        if (tamanho < 15) vistos.add(`${el.tagName.toLowerCase()}.${el.className} → ${tamanho}px`);
      }
      return [...vistos];
    });
    for (const item of miudos) problemas.push(`texto abaixo de 15px: ${item}`);

    /* ------------------------------------------- menu do celular no teclado */
    await pagina.keyboard.press('Tab');
    await pagina.click('[data-menu-abrir]');
    const abriu = await pagina.isVisible('#menu-mobile');
    const focoNoPainel = await pagina.evaluate(
      () => document.getElementById('menu-mobile')?.contains(document.activeElement) ?? false,
    );
    if (!abriu) problemas.push('menu do celular não abriu');
    if (!focoNoPainel) problemas.push('o foco não entrou no painel do menu');

    await pagina.keyboard.press('Escape');
    if (await pagina.isVisible('#menu-mobile')) problemas.push('Esc não fecha o menu do celular');
    const voltouFoco = await pagina.evaluate(
      () => document.activeElement?.hasAttribute('data-menu-abrir') ?? false,
    );
    if (!voltouFoco) problemas.push('o foco não voltou para o botão do menu depois de fechar');

    await contexto.close();
  }
} finally {
  await navegador.close();
  astro('preview', 'stop');
}

if (problemas.length === 0) {
  console.log('\n✓ axe sem violações; nenhuma rolagem horizontal; alvos e tamanhos de texto ok.\n');
} else {
  console.log(`\n${problemas.length} ponto(s) a corrigir:\n`);
  for (const problema of problemas) console.log(`  • ${problema}`);
  console.log('');
  process.exitCode = 1;
}

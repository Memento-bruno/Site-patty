/* Conferência automática de acessibilidade e de layout.

   Uso: npm run build && npm run a11y

   Roda o axe-core nas três páginas, procura rolagem horizontal em várias
   larguras (inclusive com zoom de 200%), confere o tamanho dos alvos de
   toque e testa o menu do celular pelo teclado. */
import { chromium } from 'playwright';
import { spawnSync } from 'node:child_process';
import { existsSync, readFileSync } from 'node:fs';
import { setTimeout as espera } from 'node:timers/promises';

const PORTA = Number(process.env.PORTA_PREVIEW ?? 4399);
const BASE = (process.env.BASE_PATH ?? '/site-patricia-domingues').replace(/\/$/, '');
const PAGINAS = ['/', '/politica-de-privacidade', '/pagina-que-nao-existe'];

const executavel =
  process.env.CHROMIUM_PATH || '/opt/pw-browsers/chromium-1194/chrome-linux/chrome';
const opcoesNavegador = existsSync(executavel) ? { executablePath: executavel } : {};
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

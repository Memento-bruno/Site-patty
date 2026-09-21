/* Capturas de tela da página construída, para revisão visual.

   Uso:  npm run build && npm run capturas
   Ou:   npm run capturas -- / /politica-de-privacidade

   Sobe o preview do Astro, fotografa nas larguras de desktop e de celular e
   salva em capturas/ (pasta ignorada pelo git). */
import { chromium } from 'playwright';
import { spawnSync } from 'node:child_process';
import { mkdir } from 'node:fs/promises';
import { existsSync } from 'node:fs';
import { setTimeout as espera } from 'node:timers/promises';

const PORTA = Number(process.env.PORTA_PREVIEW ?? 4399);
const BASE = (process.env.BASE_PATH ?? '/Site-patty').replace(/\/$/, '');
const CAMINHOS = process.argv.slice(2).length ? process.argv.slice(2) : ['/'];

const TELAS = [
  { nome: 'desktop', largura: 1440, altura: 900 },
  { nome: 'celular', largura: 390, altura: 844 },
];

/* O container já traz o Chromium em /opt/pw-browsers. Se a versão do
   Playwright instalada não bater com ele, apontamos o executável na mão em
   vez de baixar um navegador novo. */
const executavel =
  process.env.CHROMIUM_PATH || '/opt/pw-browsers/chromium-1194/chrome-linux/chrome';
const opcoesNavegador = existsSync(executavel) ? { executablePath: executavel } : {};

const astro = (...argumentos) =>
  spawnSync('npx', ['astro', ...argumentos], { stdio: 'ignore' });

/* O preview do Astro roda como daemon: paramos o que estiver de pé antes,
   para não fotografar um build antigo. */
astro('preview', 'stop');
astro('preview', '--port', String(PORTA), '--host', '127.0.0.1');

await mkdir('capturas', { recursive: true });

const raiz = `http://127.0.0.1:${PORTA}${BASE}/`;
let respondeu = false;
for (let tentativa = 0; tentativa < 120 && !respondeu; tentativa += 1) {
  try {
    respondeu = (await fetch(raiz)).ok;
  } catch {
    /* ainda subindo */
  }
  if (!respondeu) await espera(250);
}
if (!respondeu) {
  astro('preview', 'stop');
  throw new Error(`O preview não respondeu em ${raiz}`);
}

const navegador = await chromium.launch(opcoesNavegador);
try {
  for (const tela of TELAS) {
    const contexto = await navegador.newContext({
      viewport: { width: tela.largura, height: tela.altura },
      deviceScaleFactor: 1,
      reducedMotion: 'reduce',
      locale: 'pt-BR',
    });
    const pagina = await contexto.newPage();

    for (const caminho of CAMINHOS) {
      const rotulo = caminho === '/' ? 'inicio' : caminho.replace(/\//g, '-').replace(/^-/, '');
      await pagina.goto(`http://127.0.0.1:${PORTA}${BASE}${caminho}`, {
        waitUntil: 'networkidle',
        timeout: 60_000,
      });
      await pagina.evaluate(() => document.fonts.ready);
      await espera(200);

      await pagina.screenshot({
        path: `capturas/${rotulo}-${tela.nome}-inteira.png`,
        fullPage: true,
      });
      await pagina.screenshot({ path: `capturas/${rotulo}-${tela.nome}-dobra.png` });
      console.log(`capturas/${rotulo}-${tela.nome}-*.png`);
    }

    await contexto.close();
  }
} finally {
  await navegador.close();
  astro('preview', 'stop');
}

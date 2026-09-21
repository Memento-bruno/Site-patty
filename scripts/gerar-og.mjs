/* Gera public/og.jpg (1200 × 630), a imagem que aparece quando alguém
   compartilha o link do site no WhatsApp, no Instagram ou no Facebook.

   Uso: npm run og

   O desenho fica em scripts/og-template.html. Quando houver foto real da
   Patricia, troque o bloco .retrato por um <img> com o recorte do retrato e
   rode o comando de novo. */
import { chromium } from 'playwright';
import { existsSync } from 'node:fs';
import { pathToFileURL } from 'node:url';
import { resolve } from 'node:path';

const executavel =
  process.env.CHROMIUM_PATH || '/opt/pw-browsers/chromium-1194/chrome-linux/chrome';
const opcoes = existsSync(executavel) ? { executablePath: executavel } : {};

const navegador = await chromium.launch(opcoes);
try {
  const pagina = await navegador.newPage({
    viewport: { width: 1200, height: 630 },
    deviceScaleFactor: 1,
  });

  await pagina.goto(pathToFileURL(resolve('scripts/og-template.html')).href, {
    waitUntil: 'load',
  });
  await pagina.evaluate(() => document.fonts.ready);

  await pagina.screenshot({ path: 'public/og.jpg', type: 'jpeg', quality: 88 });
  console.log('public/og.jpg gerada (1200 × 630).');
} finally {
  await navegador.close();
}

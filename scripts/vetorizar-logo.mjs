/* Converte os PNGs da logo da Patricia em SVG.

   Uso: node scripts/vetorizar-logo.mjs <png> <destino.svg>

   Por que SVG: a logo aparece no cabeçalho (pequena), no rodapé (média) e no
   favicon. Em SVG ela fica nítida em qualquer tamanho, pesa poucos KB e —
   por usar currentColor — se recolore sozinha no fundo claro e dentro da
   faixa escura, sem precisar de um segundo arquivo.

   Esta etapa é feita uma vez e o resultado fica versionado em src/icons/.
   O pacote `potrace` só é necessário para rodar este script. */
import sharp from 'sharp';
import { trace } from 'potrace';
import { writeFile } from 'node:fs/promises';
import { promisify } from 'node:util';

const tracar = promisify(trace);

const [origem, destino] = process.argv.slice(2);
if (!origem || !destino) throw new Error('uso: vetorizar-logo.mjs <png> <destino.svg>');

/* Achata sobre branco (o traço é preto com fundo transparente), recorta a
   margem vazia e reduz: 2000 px já dá precisão de sobra para o traçado. */
const bitmap = await sharp(origem)
  .flatten({ background: '#ffffff' })
  .trim({ threshold: 10 })
  .resize({ width: 2000, withoutEnlargement: true })
  .greyscale()
  .png()
  .toBuffer();

const svgBruto = await tracar(bitmap, {
  threshold: 190,
  turdSize: 2,        // preserva os detalhes finos do traço
  optCurve: true,
  optTolerance: 0.2,
  color: '#000000',
  background: 'transparent',
});

/* O potrace escreve coordenadas com 3 casas decimais. Como o SVG entra
   embutido no HTML, cada casa a mais é peso na página. Arredondar para uma
   casa é invisível: o viewBox tem ~2000 unidades de largura e a marca é
   exibida entre 48 px (cabeçalho) e 200 px (rodapé), então 0,1 unidade vale
   entre 0,002 px e 0,01 px na tela. */
const arredondar = (texto) =>
  texto.replace(/-?\d+\.\d+/g, (n) => String(Math.round(Number(n) * 10) / 10));

/* Deixa a cor a cargo do CSS e tira o que não precisa ir para o HTML. */
const svg = arredondar(svgBruto)
  .replace(/ fill="#000000"/g, ' fill="currentColor"')
  .replace(/<svg /, '<svg fill="currentColor" ')
  .replace(/ version="[\d.]+"/, '')
  /* Sem width/height fixos: quem dimensiona é o CSS, e a proporção
     continua vindo do viewBox. */
  .replace(/<svg([^>]*?) width="[^"]*" height="[^"]*"/, '<svg$1')
  .replace(/ xmlns:xlink="[^"]*"/, '')
  .replace(/<!--[\s\S]*?-->/g, '')
  .replace(/\n\s*\n/g, '\n')
  .trim();

await writeFile(destino, svg + '\n');

const tamanho = Buffer.byteLength(svg) / 1024;
const caixa = svg.match(/viewBox="([^"]+)"/)?.[1] ?? '?';
console.log(`${destino}  viewBox="${caixa}"  ${tamanho.toFixed(1)}KB`);

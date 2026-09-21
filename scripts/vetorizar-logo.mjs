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

/* Deixa a cor a cargo do CSS e tira o que não precisa ir para o HTML. */
const svg = svgBruto
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

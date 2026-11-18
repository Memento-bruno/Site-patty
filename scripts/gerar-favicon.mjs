/* Gera o ícone da aba do navegador (favicon) a partir do logo da Patricia.

   Uso: npm run favicon

   O desenho é o `simbolo` de src/icons/marca/ (o mesmo do cabeçalho), e as
   cores vêm de src/styles/tokens.css. Se a paleta mudar, é só rodar de novo.

   Arquivos gerados em public/:
   - favicon.svg           muda de cor sozinho: logo escuro quando o
                           computador está no tema claro e logo claro quando
                           está no tema escuro (@media prefers-color-scheme
                           dentro do próprio SVG).
   - favicon.ico           reserva (32 px, logo escuro) para navegador antigo
                           que não lê SVG e para quem pede /favicon.ico.
   - apple-touch-icon.png  180 px, logo claro sobre o azul-marinho, para
                           quando alguém salva o site na tela do iPhone. */
import { chromium } from 'playwright';
import { opcoesNavegador } from './navegador.mjs';
import { readFileSync, writeFileSync } from 'node:fs';

const tokens = readFileSync('src/styles/tokens.css', 'utf8');
function token(nome) {
  const achado = tokens.match(new RegExp(`--${nome}:\\s*(#[0-9A-Fa-f]{3,8})`));
  if (!achado) throw new Error(`Token não encontrado em tokens.css: --${nome}`);
  return achado[1];
}

const ESCURO = token('ameixa-900'); /* logo no tema claro */
const CLARO = token('texto-claro'); /* logo no tema escuro */

const simbolo = readFileSync('src/icons/marca/simbolo.svg', 'utf8');
const caminho = simbolo.match(/<path d="([^"]+)"/)[1];

/* O símbolo é mais alto que largo (2000 × 2460). O viewBox quadrado centra
   o desenho com uma folga pequena. O traço engrossa a linha: em 16 px o
   desenho original some de tão fino. Testado em 50, 90 e 130: 110 fica
   legível na aba sem a borboleta virar um borrão. */
const LADO = 2560;
const TRACO = 110;

function svg({ estilo, lado = LADO, fundo, traco = TRACO }) {
  const caixa = `${(2000 - lado) / 2} ${(2460 - lado) / 2} ${lado} ${lado}`;
  const [x, y] = caixa.split(' ');
  const retangulo = fundo
    ? `<rect x="${x}" y="${y}" width="${lado}" height="${lado}" fill="${fundo}"/>`
    : '';
  return `<svg xmlns="http://www.w3.org/2000/svg" viewBox="${caixa}">
<style>${estilo}</style>${retangulo}
<path fill-rule="evenodd" stroke-width="${traco}" stroke-linejoin="round" stroke-linecap="round" d="${caminho}"/>
</svg>
`;
}

const cor = (c) => `path{fill:${c};stroke:${c}}`;

writeFileSync(
  'public/favicon.svg',
  svg({
    estilo: `${cor(ESCURO)}@media (prefers-color-scheme:dark){${cor(CLARO)}}`,
  }),
);

const navegador = await chromium.launch(opcoesNavegador);
try {
  const pagina = await navegador.newPage({ deviceScaleFactor: 1 });

  async function png(conteudo, lado) {
    await pagina.setViewportSize({ width: lado, height: lado });
    await pagina.setContent(
      `<style>html,body{margin:0;background:transparent}svg{display:block;width:${lado}px;height:${lado}px}</style>${conteudo}`,
    );
    return pagina.screenshot({ omitBackground: true });
  }

  const escuro = await png(svg({ estilo: cor(ESCURO) }), 32);

  /* No iPhone o ícone vira um quadrado com cantos arredondados pelo sistema;
     o fundo precisa ser cheio. Em 180 px o traço fino já aparece bem. */
  writeFileSync(
    'public/apple-touch-icon.png',
    await png(svg({ estilo: cor(CLARO), lado: LADO * 1.35, fundo: ESCURO, traco: 45 }), 180),
  );

  /* .ico com um PNG dentro (aceito por todos os navegadores atuais). */
  const cabecalho = Buffer.alloc(22);
  cabecalho.writeUInt16LE(0, 0); /* reservado */
  cabecalho.writeUInt16LE(1, 2); /* tipo: ícone */
  cabecalho.writeUInt16LE(1, 4); /* uma imagem */
  cabecalho.writeUInt8(32, 6); /* largura */
  cabecalho.writeUInt8(32, 7); /* altura */
  cabecalho.writeUInt8(0, 8); /* paleta */
  cabecalho.writeUInt8(0, 9); /* reservado */
  cabecalho.writeUInt16LE(1, 10); /* planos */
  cabecalho.writeUInt16LE(32, 12); /* bits por pixel */
  cabecalho.writeUInt32LE(escuro.length, 14);
  cabecalho.writeUInt32LE(22, 18); /* onde a imagem começa */
  writeFileSync('public/favicon.ico', Buffer.concat([cabecalho, escuro]));

  console.log('Favicon gerado em public/ (svg, ico e apple-touch-icon).');
} finally {
  await navegador.close();
}

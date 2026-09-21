/* Roda depois do `astro build`.

   Procura marcadores [[CONFIRMAR: ...]] que chegaram ao HTML publicado e
   avisa quantos são. Não derruba o build de propósito: o site precisa poder
   ser visto antes de a Patricia confirmar tudo. Use a lista como conferência
   final antes de publicar de verdade. */
import { readdir, readFile } from 'node:fs/promises';
import { join, relative } from 'node:path';

const RAIZ = 'dist';
const PADRAO = /\[\[CONFIRMAR[^\]]*\]\]/g;

async function arquivosHtml(pasta) {
  const encontrados = [];
  for (const item of await readdir(pasta, { withFileTypes: true })) {
    const caminho = join(pasta, item.name);
    if (item.isDirectory()) encontrados.push(...(await arquivosHtml(caminho)));
    else if (item.name.endsWith('.html')) encontrados.push(caminho);
  }
  return encontrados;
}

const ocorrencias = [];

for (const arquivo of await arquivosHtml(RAIZ)) {
  const conteudo = await readFile(arquivo, 'utf8');
  for (const achado of conteudo.match(PADRAO) ?? []) {
    ocorrencias.push({ pagina: relative(RAIZ, arquivo), marcador: achado });
  }
}

if (ocorrencias.length === 0) {
  console.log('\n✓ Nenhum [[CONFIRMAR]] pendente no site.\n');
  process.exit(0);
}

const porMarcador = new Map();
for (const { pagina, marcador } of ocorrencias) {
  if (!porMarcador.has(marcador)) porMarcador.set(marcador, new Set());
  porMarcador.get(marcador).add(pagina);
}

console.log(
  `\n⚠  ${ocorrencias.length} marcador(es) [[CONFIRMAR]] ainda aparecem no site publicado:\n`,
);
for (const [marcador, paginas] of porMarcador) {
  console.log(`   ${marcador}`);
  console.log(`      em: ${[...paginas].join(', ')}`);
}
console.log(
  '\n   Preencha os dados em src/data/site.ts e rode o build de novo.' +
    '\n   A lista completa de pendências está em docs/pendencias.md.\n',
);

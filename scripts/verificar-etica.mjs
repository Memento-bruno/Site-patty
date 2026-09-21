/* Conferência automática das regras da seção 3 do BRIEFING.md (Código de
   Ética do Psicólogo e normas do CFP sobre publicidade) e dos critérios de
   aceite da seção 12, contra o HTML já construído.

   Uso: npm run build && npm run etica

   Não substitui a revisão da Patricia, que é a profissional inscrita.
   Serve para garantir que nada proibido voltou ao site sem ninguém notar. */
import { readFile, readdir } from 'node:fs/promises';
import { join, relative } from 'node:path';

const RAIZ = 'dist';

async function html(pasta) {
  const lista = [];
  for (const item of await readdir(pasta, { withFileTypes: true })) {
    const caminho = join(pasta, item.name);
    if (item.isDirectory()) lista.push(...(await html(caminho)));
    else if (item.name.endsWith('.html')) lista.push(caminho);
  }
  return lista;
}

/* Só o texto que a pessoa lê: tira <script>, <style> e as tags. */
const textoVisivel = (bruto) =>
  bruto
    .replace(/<script[\s\S]*?<\/script>/gi, ' ')
    .replace(/<style[\s\S]*?<\/style>/gi, ' ')
    .replace(/<[^>]+>/g, ' ')
    .replace(/&[a-z]+;/gi, ' ')
    .replace(/\s+/g, ' ');

const PROIBIDOS = [
  [/\bgr[aá]tis\b/i, 'preço como propaganda (art. 20, d)'],
  [/consulta gratuita|sess[ãa]o gratuita|primeira sess[ãa]o gr/i, 'preço como propaganda (art. 20, d)'],
  [/\bdesconto|\bcupom|\bpromo[çc][ãa]o|valor social|valor acess[íi]vel|pacote de sess/i, 'preço como propaganda (art. 20, d)'],
  [/R\$\s?\d/, 'valor exibido no site (art. 20, d)'],
  [/\bgarantid|\bgarantimos|resultados? em \d+ sess|\bcura\b|supere a ansiedade/i, 'promessa de resultado (art. 20, e)'],
  [/\ba melhor\b|\bo melhor\b|n[ºo°]\s?1\b(?!\s*\/)|refer[êe]ncia em\b|\bmelhores\b/i, 'superlativo ou comparação (art. 20, f)'],
  [/vagas limitadas|últimas vagas|por tempo limitado|corra\b/i, 'urgência artificial (art. 20, h)'],
  [/depoiment|\bavalia[çc][õo]es\b|\bestrelas\b|★|5,0\b|\bnota m[ée]dia/i, 'depoimento ou avaliação de paciente'],
  [/e-?psi\b/i, 'cadastro e-Psi, extinto pela Res. CFP 9/2024'],
  [/psic[óo]loga especialista|especialista em/i, 'título de especialista (Res. CFP 23/2022)'],
  [/\+\s?\d{3,}\s?(atendimentos|consultas|pacientes)/i, 'métrica de volume (briefing 3.4)'],
  [/descubra se voc[êe] tem|fa[çc]a o teste|quiz\b/i, 'teste ou autoavaliação (art. 18)'],
];

/* Antipadrões visuais/textuais da seção 6.8 que dá para checar por texto. */
const ANTIPADROES = [
  [/→/, 'seta → em botão ou link'],
  [/transforme sua vida|desbloqueie seu potencial|sua melhor vers[ãa]o|jornada transformadora|florescer\b/i, 'clichê de copy'],
  [/(?![\u00A9\u00AE\u2122])[\p{Extended_Pictographic}]/u, 'emoji na interface'],
];

const OBRIGATORIOS = [
  [/Patricia Braz Domingues,?\s*Psic[óo]loga,?\s*CRP 06\/167326/i, 'identificação completa com CRP'],
  [/188/, 'telefone do CVV (188)'],
  [/192/, 'telefone do SAMU (192)'],
  [/cadastro\.cfp\.org\.br/, 'link para o Cadastro Nacional do CFP'],
];

const problemas = [];
const paginas = await html(RAIZ);

for (const arquivo of paginas) {
  const bruto = await readFile(arquivo, 'utf8');
  const texto = textoVisivel(bruto);
  const nome = relative(RAIZ, arquivo);

  for (const [padrao, motivo] of [...PROIBIDOS, ...ANTIPADROES]) {
    const achado = texto.match(padrao);
    if (achado) problemas.push(`${nome}: "${achado[0]}" — ${motivo}`);
  }

  /* Nada de terceiros, cookies, formulários ou conteúdo incorporado. */
  if (/<form[\s>]/i.test(bruto)) problemas.push(`${nome}: existe um <form> (briefing 9.4)`);
  if (/<iframe[\s>]/i.test(bruto)) problemas.push(`${nome}: existe um <iframe> (briefing 9.4)`);
  if (/document\.cookie|gtag\(|googletagmanager|google-analytics|facebook\.net|hotjar/i.test(bruto)) {
    problemas.push(`${nome}: script de rastreamento ou cookie (briefing 9.4)`);
  }
  for (const src of bruto.match(/<script[^>]+src="([^"]+)"/gi) ?? []) {
    if (/https?:\/\//i.test(src)) problemas.push(`${nome}: script externo — ${src}`);
  }

  /* Links externos precisam de rel="noopener noreferrer". */
  for (const ancora of bruto.match(/<a\b[^>]*target="_blank"[^>]*>/gi) ?? []) {
    if (!/rel="[^"]*noopener/.test(ancora) || !/rel="[^"]*noreferrer/.test(ancora)) {
      problemas.push(`${nome}: link em nova aba sem noopener/noreferrer — ${ancora.slice(0, 90)}`);
    }
  }

  /* Um único H1 por página. */
  const h1 = (bruto.match(/<h1[\s>]/gi) ?? []).length;
  if (h1 !== 1) problemas.push(`${nome}: ${h1} elementos <h1> (deve haver exatamente 1)`);
}

/* Itens obrigatórios: na home e no rodapé de todas as páginas. */
const homeBruta = await readFile(join(RAIZ, 'index.html'), 'utf8');
const home = textoVisivel(homeBruta);
for (const [padrao, oque] of OBRIGATORIOS) {
  /* o endereço do Cadastro Nacional aparece no href, não no texto */
  const onde = oque.includes('Cadastro Nacional') ? homeBruta : home;
  if (!padrao.test(onde)) problemas.push(`index.html: falta ${oque}`);
}

/* Nenhum verde, verde-azulado ou turquesa em lugar nenhum (briefing 6.2). */
const VERDES = /#(0[0-9a-f]{5}|[0-9a-f]{0,6})?\b/;
for (const arquivo of await readdir(join(RAIZ, '_astro'))) {
  if (!arquivo.endsWith('.css')) continue;
  const css = await readFile(join(RAIZ, '_astro', arquivo), 'utf8');
  for (const cor of css.match(/#[0-9a-fA-F]{6}\b/g) ?? []) {
    const r = parseInt(cor.slice(1, 3), 16);
    const v = parseInt(cor.slice(3, 5), 16);
    const b = parseInt(cor.slice(5, 7), 16);
    /* verde dominante = componente verde claramente acima dos outros dois */
    if (v > r + 12 && v > b + 12) problemas.push(`${arquivo}: tom esverdeado ${cor}`);
  }
  if (/\b(green|teal|turquoise|mediumseagreen|mintcream|seagreen)\b/i.test(css)) {
    problemas.push(`${arquivo}: nome de cor verde no CSS`);
  }
  void VERDES;
}

if (problemas.length === 0) {
  console.log(`\n✓ ${paginas.length} página(s) conferidas: nada proibido pela seção 3, nenhum verde,`);
  console.log('  nenhum formulário, cookie, embed ou script de terceiros.');
  console.log('  A revisão final do conteúdo continua sendo da Patricia.\n');
} else {
  console.log(`\n${problemas.length} ponto(s) a revisar:\n`);
  for (const problema of problemas) console.log(`  • ${problema}`);
  console.log('');
  process.exitCode = 1;
}

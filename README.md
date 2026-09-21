# Site da Patricia Braz Domingues

Site profissional da psicóloga **Patricia Braz Domingues, CRP 06/167326**,
que atende online. Uma página só, mais a política de privacidade e a página
de endereço não encontrado.

Feito em [Astro](https://astro.build) com CSS puro. Sem cookies, sem
formulários, sem rastreamento e sem scripts de terceiros.

- O que o site precisa ser e por quê: [`BRIEFING.md`](BRIEFING.md)
- Decisões de design: [`docs/plano-design.md`](docs/plano-design.md)
- **O que ainda falta confirmar: [`docs/pendencias.md`](docs/pendencias.md)**

---

## Mudar um texto do site

Todo texto e todo link moram num arquivo só: **`src/data/site.ts`**.

Você não precisa mexer em mais nada. Abra o arquivo, procure o trecho que quer
mudar (os comentários dizem a que seção cada bloco pertence), troque o texto
entre aspas e salve.

Alguns exemplos:

| Para mudar | Procure por |
|---|---|
| O título grande da primeira tela | `hero.titulo` |
| O parágrafo abaixo do título | `hero.paragrafo` |
| Os três cartões do topo | `essencial` |
| A lista "Talvez você esteja aqui porque…" | `reconhecer.itens` |
| Os quatro passos de "Como funciona" | `comoFunciona.passos` |
| O texto da seção "Quem vai te acompanhar" | `sobre.paragrafos` |
| A formação | `sobre.formacao.itens` |
| Os temas atendidos | `sobre.temas.itens` |
| As perguntas e respostas | `duvidas.itens` |
| O número do WhatsApp e a mensagem pronta | `links.whatsappNumero`, `links.whatsappMensagem` |
| O endereço da Vittude | `links.vittude` |

Duas regras ao editar:

1. **Curso em andamento** é marcado com `andamento: true`. O site escreve
   "Em andamento" sozinho. Nunca apague isso de um curso que ainda não
   terminou.
2. Um dado que ainda não foi confirmado fica escrito assim:
   `[[CONFIRMAR: o que falta]]`. Ele aparece destacado em amarelo enquanto
   você edita e o comando de publicar avisa quantos ainda restam. Troque pelo
   dado real quando souber.

### Ligar um link que está desligado

Alguns links estão desligados porque o endereço não foi confirmado (o
Instagram, por exemplo). Eles têm o valor `null`:

```ts
instagram: null as string | null,
```

Para ligar, basta trocar `null` pelo endereço completo entre aspas:

```ts
instagram: 'https://instagram.com/psipatriciadomingues' as string | null,
```

O link volta a aparecer no rodapé e na seção "Como agendar" sozinho.

---

## Trocar as fotos

Hoje o site mostra um espaço liso em lilás com a legenda "Foto da Patricia".
Para colocar as fotos de verdade:

1. Salve os arquivos em **`src/assets/fotos/`** com estes nomes exatos:
   - `hero.jpg` — retrato vertical na proporção 4:5, no mínimo 1200 × 1500 px,
     fundo calmo e com espaço acima da cabeça (a foto é recortada em arco).
   - `sobre.jpg` — um segundo retrato, diferente do primeiro.
2. Rode `npm run build`. O site passa a usar as fotos sozinho, já convertidas
   para os formatos modernos e em vários tamanhos.
3. Gere de novo a imagem de compartilhamento com `npm run og` (ver abaixo).

Também dá para usar `.png` ou `.webp`. Só precisa manter o nome (`hero` e
`sobre`).

Precisa mudar a descrição da foto para quem usa leitor de tela? Está em
`hero.foto.alt` e `sobre.foto.alt`, no `src/data/site.ts`.

**Nunca** use foto de banco de imagens nem imagem gerada por inteligência
artificial: além de parecer artificial, pode sugerir que são pacientes reais.

---

## Publicar

O site publica sozinho. Toda vez que uma alteração chega na branch `main`
deste repositório, o GitHub constrói e publica a versão nova em poucos
minutos.

Dá para acompanhar na aba **Actions** do repositório. Se algo der errado, a
publicação para e o site que já estava no ar continua como estava.

A publicação só acontece se o site passar na conferência das regras do
Conselho Federal de Psicologia (ver abaixo).

### Ligar o GitHub Pages pela primeira vez

Uma vez só, nas configurações do repositório: **Settings → Pages → Source →
GitHub Actions**.

### Quando houver domínio próprio

1. Crie o arquivo `public/CNAME` com o domínio dentro, sem `https://`:
   ```
   patriciadomingues.com.br
   ```
2. Em `astro.config.mjs`, troque as duas linhas do topo:
   ```js
   const site = process.env.SITE_URL || 'https://patriciadomingues.com.br';
   const base = process.env.BASE_PATH || '/';
   ```
3. Atualize o endereço do sitemap em `public/robots.txt`.
4. Aponte o domínio para o GitHub Pages no painel do registro.br.

---

## Rodar na sua máquina

Precisa do [Node.js](https://nodejs.org) 20 ou mais novo.

```bash
npm install      # uma vez só
npm run dev      # abre em http://localhost:4321
```

Enquanto `npm run dev` está rodando, toda alteração salva aparece na hora no
navegador.

### Todos os comandos

| Comando | O que faz |
|---|---|
| `npm run dev` | Abre o site na sua máquina para editar |
| `npm run build` | Constrói o site e avisa quantos `[[CONFIRMAR]]` faltam |
| `npm run preview` | Mostra o site já construído, como ele vai ficar no ar |
| `npm run etica` | Confere as regras do CFP sobre publicidade |
| `npm run a11y` | Confere acessibilidade, rolagem horizontal e o menu do celular |
| `npm run og` | Gera de novo a imagem que aparece ao compartilhar o link |
| `npm run capturas` | Tira fotos do site no computador e no celular, em `capturas/` |

Para medir desempenho, com o site construído e `npm run preview` rodando:

```bash
npx lighthouse http://localhost:4321/site-patricia-domingues/ --view
```

---

## As regras que o site precisa seguir

O conteúdo segue o Código de Ética Profissional do Psicólogo e as normas do
CFP sobre publicidade. Na prática, isso quer dizer que o site **não** pode ter:

- preço, desconto, pacote ou "primeira sessão grátis";
- promessa de resultado ("supere a ansiedade", "resultados em X sessões");
- superlativo ou comparação ("a melhor", "referência em");
- depoimento, avaliação, estrela ou print das notas da Vittude;
- caso clínico, mesmo sem nome;
- a palavra "especialista" como título profissional;
- teste ou quiz de autoavaliação;
- menção ao cadastro e-Psi, que foi extinto em 2024.

E **precisa** ter, sempre: o nome completo, a palavra "Psicóloga" e o CRP; o
aviso de que não é serviço de emergência, com 188 (CVV) e 192 (SAMU); e o link
para o Cadastro Nacional do CFP.

O comando `npm run etica` confere tudo isso automaticamente e trava a
publicação se algo proibido voltar ao site. Ele não substitui a leitura da
Patricia, que é a profissional inscrita e responde pelo conteúdo.

A seção 3 do [`BRIEFING.md`](BRIEFING.md) traz as normas de referência.

---

## Como o projeto está organizado

```
src/
├── data/site.ts        ← TODOS os textos e links (é aqui que você edita)
├── styles/tokens.css   ← cores, fontes, espaçamentos
├── styles/global.css
├── assets/fotos/       ← as fotos da Patricia
├── icons/              ← ícones (Phosphor, MIT)
├── layouts/Base.astro  ← <head>, SEO, dados estruturados
├── components/         ← as peças de cada seção
└── pages/              ← as três páginas do site
```

As cores vivem todas em `src/styles/tokens.css`. Trocar a paleta inteira do
site é editar esse arquivo e mais nenhum — a paleta alternativa do briefing
está comentada no fim dele.

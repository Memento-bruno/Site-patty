# Site da Patricia Braz Domingues — contexto do projeto

Documento para anexar a um Projeto no claude.ai. Reúne, num arquivo só, o que
uma conversa nova precisa saber para mexer no site sem quebrar nada.

O repositório tem documentos mais longos sobre cada assunto —
[`BRIEFING.md`](../BRIEFING.md) (a especificação original do cliente),
[`docs/plano-design.md`](plano-design.md) e [`docs/pendencias.md`](pendencias.md).
Este aqui é o resumo operacional.

---

## 1. O que é, e onde está

Site profissional da psicóloga **Patricia Braz Domingues, CRP 06/167326**, que
atende online. Uma página única, mais política de privacidade e página 404.

| | |
|---|---|
| No ar em | https://memento-bruno.github.io/Site-patty/ |
| Repositório | https://github.com/Memento-bruno/Site-patty |
| Stack | Astro 7, CSS puro, TypeScript. Sem Tailwind, sem biblioteca de componentes |
| Publicação | Automática: todo push na `main` reconstrói e publica |
| JavaScript | Só dois comportamentos: menu do celular e revelação ao rolar |
| Terceiros | Nenhum. Sem cookies, formulários, embeds ou scripts externos |

**Todo texto e todo link vivem em `src/data/site.ts`.** Nenhum componente tem
texto fixo no código. Para mudar qualquer palavra do site, é lá.

---

## 2. A regra que não se negocia

O conteúdo segue o **Código de Ética Profissional do Psicólogo** e as
resoluções do CFP sobre publicidade. Isso não é preferência de estilo: a
Patricia responde pelo conteúdo perante o Conselho.

O site **não pode** conter:

- preço, desconto, pacote, "primeira sessão grátis";
- promessa de resultado ("supere a ansiedade", "resultados em X sessões");
- superlativo ou comparação ("a melhor", "referência em");
- depoimento, avaliação, estrela, print de nota da Vittude;
- caso clínico, mesmo anonimizado;
- a palavra "especialista" como título profissional (ela não tem registro de
  especialista no CRP — Res. CFP 23/2022);
- teste ou quiz de autoavaliação;
- menção ao cadastro e-Psi, extinto pela Res. CFP 9/2024.

E **precisa** sempre conter: nome completo + "Psicóloga" + CRP; o aviso de que
não é serviço de emergência, com 188 (CVV) e 192 (SAMU); e o link para o
Cadastro Nacional do CFP.

`npm run etica` confere tudo isso contra o HTML construído e **trava a
publicação** se algo proibido voltar. Não é substituto da leitura da Patricia.

---

## 3. Comandos

| Comando | O que faz |
|---|---|
| `npm run dev` | Abre o site localmente para editar |
| `npm run build` | Constrói e avisa quantos `[[CONFIRMAR]]` restam (hoje: zero) |
| `npm run etica` | Confere as regras do CFP. Roda também na publicação |
| `npm run a11y` | axe-core, rolagem horizontal, alvos de toque, revelação ao rolar, chegada por URL |
| `npm run og` | Gera de novo a imagem de compartilhamento |
| `npm run capturas` | Fotografa o site no computador e no celular |

**Rode `npm run etica` e `npm run a11y` antes de qualquer push.** Os dois já
pegaram defeitos reais.

---

## 4. Decisões de design que parecem arbitrárias e não são

Cada uma destas foi tomada por um motivo. Desfazê-las sem saber o motivo
estraga alguma coisa.

**O arco aparece em exatamente dois lugares** — o recorte da foto do hero e os
marcadores em ocre da lista "Talvez você esteja aqui". Era três, até a logo
real da Patricia chegar e substituir o monograma provisório. A escassez é o
que faz a forma ser lida como assinatura e não como papel de parede.

**A logo tem três recortes, um por tamanho.** Só o símbolo no cabeçalho (48 px);
só a borboleta no centro do selo e no favicon; a assinatura inteira no rodapé.
Abaixo de ~110 px o nome manuscrito fica ilegível — por isso o cabeçalho leva o
nome tipografado ao lado do símbolo. Os SVGs usam `currentColor`, então a marca
acompanha a faixa escura e qualquer troca de paleta sem arquivo novo.

**Cartão só em dois lugares** (os três do hero e os dois de "Como agendar"). O
resto se separa por faixa de fundo, régua vertical e linha fina. Grade de
cartões idênticos em toda seção é a marca registrada de site gerado em série.

**O hero não anima, e títulos de seção também não.** O hero é a primeira tela e
o elemento do LCP. Nos demais, o H2 ancora e o conteúdo chega sob ele.

**A paleta não tem verde.** Pedido explícito do cliente. `npm run etica`
confere isso procurando tons esverdeados no CSS construído.

**Nada de números** — sem "+4.000 atendimentos", sem nota média. Confiança vem
de formação e método, não de métrica.

---

## 5. Dois defeitos que o cliente encontrou, e o padrão entre eles

Vale ler antes de escrever testes novos.

**O site pulava para uma seção ao abrir.** Clicar na navegação deixava a âncora
na URL (`/#agendar`); essa URL entrava no favorito e no autocompletar. Pior: o
`scroll-behavior: smooth` estava no `html` desde o início e capturava o salto
inicial, fazendo a página descer animada por 2 segundos — e no celular parar
712 px antes da seção, porque o documento cresce durante a descida. Hoje a
âncora é removida no `<head>`, antes de o corpo ser lido, e a rolagem suave só
liga depois do load.

**No celular a revelação acontecia fora da tela.** O observador olhava o
contêiner e revelava todos os filhos de uma vez. No desktop funciona (itens
lado a lado); no celular eles empilham e os últimos eram revelados centenas de
pixels abaixo da dobra. 6 dos 20 blocos animavam onde ninguém via, e 3 nunca
eram revelados. Hoje cada item é observado por si, e a cascata vale só para os
que entram na mesma chamada.

**O padrão:** nos dois casos o teste existia, mas media a coisa certa na
*condição* errada — um só olhava a página já carregada, o outro só rodava a
1440 px. E os dois perguntavam "o mecanismo funcionou?" em vez de "a pessoa
viu?". O `npm run a11y` agora cobre chegada por URL e roda a revelação em
390 px além de 1440 px, exigindo que o bloco apareça **enquanto está na tela**.

---

## 6. Como mexer nas coisas mais comuns

**Trocar um texto** → `src/data/site.ts`. Os comentários dizem a que seção cada
bloco pertence.

**Trocar uma foto** → salve em `src/assets/fotos/` como `hero.jpg` ou
`sobre.jpg` (4:5, retrato vertical). O site passa a usá-la sozinho. Depois rode
`npm run og`.

**Ligar um link desligado** → troque `null` pela URL completa em `links`.

**Trocar a paleta inteira** → `src/styles/tokens.css`, e só ele. A Paleta B
alternativa está comentada no fim do arquivo.

**Curso em andamento** → marcado com `andamento: true`, e o site escreve "Em
andamento" sozinho. Nunca apresentar como concluído um curso que não terminou.

---

## 7. O que ainda está em aberto

**Esperando resposta do Bruno:**

1. O **transtorno bipolar** saiu da lista de temas de propósito? Ele pediu para
   incluí-lo numa rodada, e a lista revista da rodada seguinte não o trazia.
2. Quer um **verificador de maiúsculas** permanente junto dos outros comandos?
   Existe como script descartável; pegaria uma minúscula no início de parágrafo
   antes de ir ao ar.

**Decisões adiadas:**

- **Métricas de visita.** Ele quer medir, mas adiou a ferramenta. Plausible ou
  Umami — as duas sem cookie, então o site segue sem banner de consentimento.
- **Domínio próprio.** Hoje em `memento-bruno.github.io/Site-patty`. Trocar não
  exige refazer nada: o endereço e o caminho base vêm da configuração do GitHub
  Pages na hora da publicação.

**Falta o dado:** instituição e ano da Formação em Perdas e Luto — a linha
aparece hoje só com o nome do curso.

**Antes de divulgar o link:** a Patricia precisa ler e aprovar todo o texto. É
ela quem responde pelo conteúdo. Três pontos vieram de perfis públicos e não de
confirmação direta: se atende brasileiros no exterior em português, se o perfil
do Google expõe endereço (o site não publica nenhum), e o título do hero.

---

## 8. Armadilhas do ambiente

- **O container é reciclado.** Entre sessões o projeto some de `/home/user`.
  Basta clonar de novo: `git clone --depth 1 https://github.com/Memento-bruno/site-patty`
  e `npm ci`.
- **O caminho do Chromium muda** quando a imagem é atualizada (já foi de
  `chromium-1194/chrome-linux` para `chromium-1243/chrome-linux64`).
  `scripts/navegador.mjs` procura em vez de fixar — use ele.
- **O Astro embute os scripts no HTML** em vez de servir arquivos `.js`
  separados. Testes que tentam bloquear `_astro/*.js` não bloqueiam nada.

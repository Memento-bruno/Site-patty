# Plano de design

Documento da fase 1 do `BRIEFING.md`. Registra os tokens finais, a estrutura
confirmada de cada tela e as decisões que separam este site do padrão que
sairia "de fábrica" para qualquer site de psicologia.

## 1. Três princípios

### 1.1 O arco é a única assinatura, e ela é escassa

O arco (◠) aparece em exatamente três lugares: a foto do hero, o monograma do
logotipo e os marcadores da lista "Talvez você esteja aqui". Em nenhum outro.

O reflexo automático seria repetir a forma em todo canto — topo de seção,
divisória, moldura de ícone — até virar papel de parede. A escassez é o que faz
o arco ser lido como assinatura e não como enfeite. Se a forma aparecer num
quarto lugar, ela deixa de significar alguma coisa.

### 1.2 O CRP ocupa o lugar do ornamento

A referência visual traz um selo circular com "Best Working Since 2024" — um
adorno que existe só para preencher espaço, e que aqui seria proibido pelo
art. 20 do Código de Ética.

Em vez de tirar o selo, trocamos o que ele carrega: o único ornamento circular
da página gira o texto "Psicóloga ✦ CRP 06/167326 ✦ Atendimento online". O
elemento mais decorativo da tela é justamente o que cumpre a exigência legal de
identificação. Confiança por transparência, não por adjetivo — e sem um "selo de
verificado" genérico, que não significaria nada.

### 1.3 Hierarquia por faixa, não por cartão

Cartões aparecem em dois lugares: os três do hero e os dois de "Como agendar".
Todas as outras seções se separam por faixa de fundo (`--lilas-100`), régua
vertical entre colunas, ou pela linha fina que liga os quatro passos numerados.

Grade de cartões idênticos em todas as seções é a marca registrada de site
gerado em série: cada bloco de conteúdo recebe a mesma caixa branca, o mesmo
raio e a mesma sombra cinza, e o olho perde a noção do que é mais importante.
Restringir o cartão a dois momentos devolve peso a eles e obriga cada seção a
encontrar a própria forma.

### 1.4 Nota de legibilidade (restrição que atravessa tudo)

Parte do público tem mais de 60 anos. Isso não é um detalhe de acessibilidade
no fim da lista: é a restrição que define corpo de texto em 19 px, piso
absoluto de 15 px, alvos de toque de 48 px, botão primário sólido (e não só com
contorno) e alinhamento à esquerda em tudo, inclusive nos títulos de seção.

## 2. Tokens finais

Paleta A do briefing, "ameixa e ocre". Vive inteira em `src/styles/tokens.css`.

| Token | Hex | Uso | Contraste sobre `--papel` |
|---|---|---|---|
| `--ameixa-900` | `#3B2745` | Títulos, botão primário, faixa "Como agendar" | 16,0:1 |
| `--ameixa-600` | `#6E4F7E` | Links, ícones, contornos, anel de foco | 6,6:1 |
| `--lilas-200` | `#DCD0E4` | Bloco atrás da foto, placeholder de foto | — (só gráfico) |
| `--lilas-100` | `#EFE9F3` | Degradê do hero, faixas de seção | — (só fundo) |
| `--papel` | `#FBFAFC` | Fundo geral | — |
| `--tinta` | `#231B28` | Texto corrido | 16,0:1 |
| `--ocre-500` | `#B8862F` | Anel do selo e marcadores em arco. **Nunca em texto** | 3,1:1 |
| `--linha` | `#E3DAE8` | Bordas e divisórias | — |
| `--texto-suave` | `#4B3D55` | Linhas de apoio e legendas | 9,6:1 |

Token derivado `--texto-claro` (`#F4EFF7`) para texto sobre `--ameixa-900`
(13,0:1). A classe `.em-ameixa` redefine `--texto`, `--linha` e `--foco` dentro
da faixa escura, para que nenhum componente precise conhecer o contexto em que
foi colocado.

Nenhum verde, verde-azulado, turquesa ou menta em lugar nenhum. A Paleta B
("azul-tinta e areia") está comentada no fim do `tokens.css`: trocar de paleta é
editar um arquivo.

**Tipografia.** Schibsted Grotesk (títulos, navegação, botões, rótulos) e
Literata (texto corrido, 1.7 de entrelinha, medida de 66 caracteres). Ambas
auto-hospedadas via `@fontsource-variable`, com `font-display: swap`. Nenhuma
requisição ao Google Fonts.

**Formas.** Hero 32 px · cartões 20 px · foto do "Sobre" 20 px · botões em
pílula · foto do hero `999px 999px 0 0` em 4:5. Uma única sombra no site, nos
três cartões do hero. Um único degradê, o do hero.

**Movimento.** Uma única animação automática: o texto do selo, uma volta a cada
40 s. Parada com `prefers-reduced-motion: reduce`. Nada de animação de entrada
ao rolar, parallax ou efeito em cartão.

## 3. Estrutura confirmada

```
DESKTOP (>= 1024 px)
+-----------------------------------------------------------------------+
| ◠ Patricia Domingues  ( Terapia  Como funciona  Sobre  Dúvidas )  (Agendar sessão) |
|                                                                       |
|  Psicóloga Patricia Braz Domingues, CRP 06/167326     .-""""-.        |
|                                                      /        \       |
|  Um espaço seguro                             (SELO)|   FOTO   |#     |
|  para entender o                                    |  arco    |#     |
|  que você sente.                                    |   4:5    |#     |
|                                                     |          |#     |
|  Atendo adultos e idosos por videochamada...        +----------+#     |
|                                                       ###########     |
|  (Agendar sessão)    (o) Chamar no WhatsApp                           |
|                                                                       |
|   +---------------+  +---------------+  +---------------+             |
+---| Online, de    |--| Adultos e     |--| Sessões de    |-------------+
    | onde estiver  |  | idosos        |  | 50 minutos    |
    +---------------+  +---------------+  +---------------+
```

Seções abaixo do hero:

| Seção | Forma |
|---|---|
| Talvez você esteja aqui | H2 nas colunas 1–5, lista em duas colunas nas 6–12, marcador em arco ocre |
| O que acontece na terapia | Faixa `--lilas-100`, parágrafo, colunas "É" / "Não é" separadas por régua vertical, bloco "Como eu trabalho" |
| Como funciona | 4 passos em linha, numerados, ligados por uma linha fina; caixa "Para a sessão"; botão |
| Quem vai te acompanhar | Foto nas colunas 1–5, texto nas 6–12, formação em lista cronológica, temas em lista corrida |
| Como agendar | Faixa `--ameixa-900`, 2 blocos com borda clara, sem sombra |
| Dúvidas | Coluna única de 760 px com `<details>` nativos |
| Rodapé | Fundo `--papel`, linha superior, 3 colunas |

No mobile (< 768 px): hero empilhado com o texto antes da foto, "Como funciona"
vira lista vertical com a linha ligando os números à esquerda, rodapé empilhado,
e uma barra fixa embaixo com Vittude e WhatsApp.

## 4. Revisão contra a seção 6.8 (o que foi trocado)

Cada linha abaixo é uma decisão que teria saído no automático e foi substituída.

| Reflexo automático | O que ficou | Motivo |
|---|---|---|
| Rótulo em CAIXA ALTA espaçada acima de cada H2 | Nada acima do H2; a linha de apoio, quando existe, é sentence case em Schibsted Grotesk 15 px | 6.8: rótulo em versalete é a assinatura de template |
| "Saiba mais" / "Agende agora →" | "Ver horários na Vittude", "Chamar no WhatsApp", "Agendar sessão" — nomes que dizem o que acontece, iguais em todo o site | 6.8 e 7 |
| Ícone de cérebro, cabeça com engrenagem ou flor de lótus | Câmera de vídeo, duas pessoas, relógio (Phosphor regular, SVG inline) | 6.7: clichê de categoria |
| Grade de 6 cartões para "Talvez você esteja aqui" | Lista em duas colunas com marcador em arco ocre | 1.3 |
| Numeração 01 / 02 / 03 como enfeite em várias seções | Números só em "Como funciona", onde existe sequência real | 6.8 |
| Contador de pacientes, nota média, estrelas | Nada. Formação e abordagem no lugar | 3.3 e 3.4 |
| Seção de depoimentos | Não existe | 3.3: vedado pelos CRPs |
| Degradês de apoio em várias seções | Um degradê só, no hero | 6.4 |
| Banco de imagens com "paciente sorrindo" | Placeholder liso em `--lilas-200` até a foto real chegar | 6.7 |
| Acordeão de FAQ em JavaScript | `<details>`/`<summary>` nativos | 8: JavaScript só no menu mobile |
| Ano do rodapé escrito à mão | `new Date().getFullYear()` | 5.10 |

Duas decisões merecem registro explícito porque contrariam a referência visual:

1. **Botão primário sólido.** Na referência o botão principal é só contorno. Para
   um público que inclui pessoas idosas, o contorno enfraquece o alvo mais
   importante da página. O contorno ficou para o botão secundário.
2. **Tudo alinhado à esquerda, inclusive os H2.** Títulos de seção centralizados
   são o padrão da categoria; alinhar à esquerda mantém uma única margem óptica
   descendo a página e dá a ela uma leitura de documento, não de folheto.

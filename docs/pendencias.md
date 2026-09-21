# Pendências

Estado depois da rodada de confirmações com o Bruno.

**O site não tem mais nenhum marcador `[[CONFIRMAR]]`.** Rodar `npm run build`
confirma isso no fim da saída.

## 1. Confirmado e já aplicado

| Item | Resposta | Onde entrou |
|---|---|---|
| Instagram | `@psipatriciadomingues` | rodapé e "Como agendar" |
| Idade mínima | a partir de 18 anos | FAQ |
| Recibo para reembolso | emite | FAQ |
| Pagamento no particular | Pix, transferência ou cartão | bloco "Direto comigo" |
| Temas | entram TOC e transtorno bipolar | "Temas com que trabalho" |
| Graduação | UNIP — Universidade Paulista, São Paulo, conclusão 2020 | Formação |
| Curso do InPBE | concluído (saiu a marcação "Em andamento") | Formação |
| Pós em Neuropsicologia | segue em andamento | Formação |
| E-mail profissional | `psi.patriciabraz@gmail.com`, como link | Política de privacidade |
| Horário de resposta | não divulgar | frase removida de "Como agendar" |
| Grafia do nome no CRP | "Patricia Braz Domingues", sem acento | identificação em todo o site |
| Título do hero | mantido o primeiro | Hero |
| Logotipo | o dela, vetorizado | cabeçalho, selo, rodapé, favicon |
| Fotos | as duas enviadas | Hero e "Sobre" |
| Zenklub | não atende mais | não aparece |
| Atendimento presencial | não faz, só online | site segue dizendo online |
| Título de especialista | não tem registro no CRP | site nunca usa "especialista" como título |
| Tempo de atuação | não exibir | não aparece |
| Repositório | público | GitHub Pages |

## 2. Em aberto

### 2.1 Falta o dado

**Formação em Cuidado com Perdas e Luto: instituição e ano.** A linha aparece
hoje só com o nome do curso, que é correto mas menos transparente que as
outras da lista. Para completar, preencha `instituicao` e `periodo` desse item
em `sobre.formacao.itens`, em `src/data/site.ts`.

### 2.2 Decisão adiada

1. **Métricas de visita.** O Bruno quer medir, mas a ferramenta fica para
   depois. Plausible (~US$9/mês) ou Umami (plano gratuito com limite) — as duas
   funcionam sem cookie e sem dado pessoal, então o site continua sem banner de
   consentimento. É um script de uma linha no `Base.astro` quando decidir.
2. **Domínio próprio.** Por ora fica em `memento-bruno.github.io/Site-patty`.
   Trocar depois não exige refazer nada: o endereço e o caminho base vêm da
   configuração do GitHub Pages na hora da publicação. O README explica o passo
   a passo.
3. **Cores próprias.** Se ela usa uma paleta no Instagram, dá para adaptar os
   tokens. Hoje o site usa a Paleta A do briefing ("ameixa e ocre"), e a
   Paleta B está comentada no fim de `src/styles/tokens.css`. O logotipo é
   vetorial e herda a cor do texto, então acompanha qualquer troca sem precisar
   de arquivo novo.

### 2.3 Conferir com a Patricia antes de divulgar

1. **Leitura de todo o texto.** Os textos foram escritos a partir do briefing e
   dos perfis públicos dela. Ela é a profissional inscrita e responde pelo
   conteúdo perante o CRP — nenhuma conferência automática substitui essa
   leitura.
2. **Atendimento fora do Brasil.** O site afirma que ela acompanha brasileiros
   em outros países, por videochamada e em português. Isso veio do perfil da
   Vittude, não de confirmação direta.
3. **Endereço físico.** O perfil do Google pode exibir endereço. O site não
   publica endereço nenhum, que é o mais seguro para quem atende só online. Se
   o perfil do Google mostrar um endereço residencial, vale revisar lá.
4. **Foto do hero.** A foto do parque tem bastante verde ao fundo e uma
   camiseta vermelha forte, enquanto a paleta do site é ameixa e ocre. Publicada
   assim por decisão do Bruno; dá para inverter com a foto da caverna (tons
   quentes, que conversam melhor com a paleta) em poucos minutos.

## 3. Do briefing

A imagem `docs/referencia-visual.jpg`, citada na seção 6.1, não foi enviada.
O layout seguiu a descrição escrita: hero em container claro, navegação em
pílula, foto em arco com bloco deslocado, selo circular e três cartões sobre a
base do hero.

## 4. Antes de divulgar

- [ ] A Patricia leu e aprovou todos os textos.
- [x] `npm run build` não lista nenhum `[[CONFIRMAR]]`.
- [x] `npm run etica` passa.
- [x] `npm run a11y` passa.
- [x] Fotos reais no lugar dos placeholders.
- [x] `public/og.jpg` gerada com a foto real.
- [x] Logotipo dela no cabeçalho, no selo, no rodapé e no favicon.

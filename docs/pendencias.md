# Pendências para confirmar com a Patricia

Lista gerada no fim da construção do site. Nada aqui foi inventado: onde o
dado não estava confirmado, o site **omite** a informação ou mostra um
marcador `[[CONFIRMAR]]`.

Rodar `npm run build` sempre lista, no fim, quantos marcadores ainda aparecem
no site publicado.

## 1. Marcadores visíveis no site (6)

Estes aparecem no site como está hoje. Cada um sai assim que o valor
correspondente for preenchido em `src/data/site.ts`.

| Onde | Marcador | O que precisa |
|---|---|---|
| Sobre → Formação | `[[CONFIRMAR: instituição e período]]` | Instituição e ano da **Formação em Cuidado com Perdas e Luto** |
| Sobre → Formação | `[[CONFIRMAR: instituição e período]]` | Instituição e ano da **Graduação em Psicologia** |
| Dúvidas | `[[CONFIRMAR: idade mínima atendida]]` | A partir de que idade ela atende (um perfil antigo cita 16 anos) |
| Dúvidas | `[[CONFIRMAR: emite recibo para reembolso?]]` | Se emite recibo para reembolso de plano de saúde |
| Como agendar | `[[CONFIRMAR: dias e horários]]` | Dias e horários em que responde mensagens |
| Política de privacidade | `[[CONFIRMAR: e-mail profissional]]` | E-mail para questões de privacidade |

## 2. Informações que ficaram de fora do site

Sem confirmação, preferi não publicar. O site funciona sem elas; cada uma
volta editando `src/data/site.ts`.

| Assunto | Situação | Onde editar |
|---|---|---|
| **Instagram** | Duas grafias aparecem nas fontes: `@psipatriciadomingues` (link enviado) e `@psi.patriciadomingues` (Keepo e EuTerapeuta). O link está desligado — link errado é pior que link nenhum. | `links.instagram` (URL completa) |
| **Zenklub** | Não se sabe se ela ainda atende por lá. O bloco C de "Como agendar" não existe. | `links.zenklub` |
| **TOC e transtorno bipolar** | Aparecem no perfil da Vittude, mas não foram confirmados. Fora da lista de temas. | `sobre.temas.itens` |
| **Formas de pagamento no particular** | Não informadas. O texto diz apenas que valor e forma de pagamento são combinados pelo WhatsApp. | `agendar.blocos` |
| **Atendimento presencial** | Citado no perfil do EuTerapeuta. O site afirma atendimento exclusivamente online. | `hero.paragrafo`, `essencial` |
| **Tempo de atuação** | Poderia aparecer de forma factual. Não aparece. | `sobre.paragrafos` |
| **Endereço físico** | O perfil no Google pode exibir endereço. O site não publica endereço nenhum. | — |
| **Título de especialista no CRP** | O site descreve a PUCPR como *especialização* (formação), nunca como título de "especialista", que exigiria registro no CRP (Res. CFP 23/2022). Se ela tiver o título registrado, dá para informar a área. | `sobre.formacao` |

## 3. Decisões que dependem dela

1. **Grafia exata do nome** como consta no registro do CRP — hoje o site usa
   "Patricia Braz Domingues" em toda identificação.
2. **Título do hero.** Está "Um espaço seguro para entender o que você sente."
   As outras duas opções do briefing estão comentadas logo acima do campo
   `hero.titulo`, em `src/data/site.ts`.
3. **Fotos.** O site mostra um espaço liso em lilás com a legenda "Foto da
   Patricia" até as fotos reais entrarem. Ver o README.
4. **Cores próprias.** O logotipo dela já está no site (resolvido). Falta saber
   se ela usa uma paleta própria no Instagram — hoje o site usa a Paleta A do
   briefing ("ameixa e ocre"), e a Paleta B está comentada no fim de
   `src/styles/tokens.css`. O logotipo é vetorial e herda a cor do texto, então
   acompanha qualquer paleta sem precisar de novo arquivo.
5. **Domínio e e-mail profissional.** Sugestões do briefing para verificar no
   registro.br: `patriciadomingues.com.br`, `patriciabrazdomingues.com.br`,
   `psicologapatriciadomingues.com.br`.
6. **Métricas de visita sem cookies** (Plausible ou Umami). Hoje o site não
   tem nenhuma medição, e por isso também não precisa de aviso de cookies.
7. **Atendimento fora do Brasil.** O site afirma que ela acompanha brasileiros
   em outros países, em português — extraído do perfil da Vittude. Vale
   confirmar se está correto e se atende em outro idioma.

## 4. Do próprio briefing

- A imagem `docs/referencia-visual.jpg`, citada na seção 6.1, não foi enviada
  junto com o briefing. O layout seguiu a descrição escrita da seção 6.1
  (hero em container claro, header com navegação em pílula, foto em arco com
  bloco deslocado, selo circular, três cartões sobre a base do hero).

## 5. Antes de publicar de verdade

- [ ] A Patricia leu e aprovou todos os textos do site.
- [ ] `npm run build` não lista nenhum `[[CONFIRMAR]]` restante.
- [ ] `npm run etica` passa.
- [ ] As fotos reais estão em `src/assets/fotos/`.
- [ ] `public/og.jpg` foi gerada de novo com a foto real (`npm run og`).

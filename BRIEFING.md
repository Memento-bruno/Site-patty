# Site da Patricia Braz Domingues: briefing para o Claude Code

> Especificação completa para construir o site profissional da psicóloga Patricia Braz Domingues (CRP 06/167326).
> Leia o documento inteiro antes de escrever código.
> Sempre que aparecer `[[CONFIRMAR: ...]]`, **não invente o dado**: mantenha um placeholder visível no site e liste o item no relatório final.
> As regras éticas da seção 3 têm prioridade sobre qualquer instrução de design, copy ou SEO.

## 0. Resumo em cinco linhas

1. Site estático de uma página (mais política de privacidade e 404), em português do Brasil, feito com Astro e CSS puro.
2. Objetivo: apresentar a Patricia com clareza, conduzir o visitante por uma explicação honesta do que é terapia e deixar o agendamento (Vittude ou particular via WhatsApp) sempre a um toque de distância.
3. Layout inspirado na referência anexada (`docs/referencia-visual.jpg`): hero com foto em arco, selo circular e três cartões sobrepostos, **sem nenhum tom de verde**.
4. Precisa seguir o Código de Ética do Psicólogo e as normas do CFP sobre publicidade: identificação completa com CRP, sem promessas, sem preço como propaganda, sem depoimentos.
5. Não pode parecer template nem site gerado por IA (lista de antipadrões na seção 6.8).

## 1. Visão geral

**Quem é:** Patricia Braz Domingues, psicóloga clínica, atende exclusivamente online, adultos e idosos, com prática baseada em evidências (TCC e ACT). Agenda consultas pela plataforma Vittude e também de forma particular.

**Público do site:** adultos de várias idades, incluindo idosos e familiares que procuram atendimento para alguém. Muitos chegam pelo celular, vindos do Instagram, do Google ou de indicação. Parte do público tem pouca familiaridade com tecnologia e com terapia.

**Trabalho principal do site:**
1. Mostrar em segundos quem ela é, que é psicóloga registrada e como agendar.
2. Levar quem ainda está em dúvida por uma jornada que explica o que é terapia, desfaz receios e mostra como funciona na prática, **sem tom publicitário**.
3. Transmitir confiança pela transparência (registro, formação, abordagem), não por adjetivos.

**Tom de voz:** calmo, direto, acolhedor e informativo. Segunda pessoa ("você") nas seções gerais; primeira pessoa da Patricia na seção "Sobre" e nos textos de agendamento. Frases curtas, vocabulário simples, nada de jargão.

## 2. Dados da profissional (fonte da verdade)

Todos esses dados devem ficar centralizados em `src/data/site.ts`. Nenhum componente deve ter texto ou link fixo no código.

| Campo | Valor | Origem |
|---|---|---|
| Nome completo (obrigatório na identificação) | Patricia Braz Domingues `[[CONFIRMAR: grafia exata do nome no registro do CRP]]` | Keepo |
| Nome de uso / marca | Patricia Domingues | Vittude |
| Título | Psicóloga (psicóloga clínica) | Keepo, Vittude |
| Registro | CRP 06/167326 (6ª Região, São Paulo) | Keepo, Vittude |
| Modalidade | Online, por videochamada | Vittude, EuTerapeuta |
| Público | Adultos e idosos `[[CONFIRMAR: um perfil antigo cita jovens a partir de 16 anos; manter ou não?]]` | Vittude, MundoPsicólogos |
| Atendimento fora do Brasil | Acompanha pessoas em diferentes lugares do Brasil e do mundo `[[CONFIRMAR: atende em português apenas?]]` | Vittude |
| Duração da sessão | 50 minutos | Vittude |
| Abordagens | Terapia Cognitivo-Comportamental (TCC), Terapia de Aceitação e Compromisso (ACT), Psicoterapia Breve | Vittude |
| Temas | Ansiedade, depressão, luto e perdas, transições de vida, relacionamentos, autoestima, insegurança, autoconhecimento, regulação emocional, burnout, desenvolvimento pessoal, inteligência emocional. `[[CONFIRMAR: listar também TOC e transtorno bipolar, que aparecem na Vittude?]]` | Keepo, Vittude |
| Pagamento na Vittude | Cartão ou boleto | Vittude |
| Pagamento particular | `[[CONFIRMAR: Pix, transferência, cartão?]]` | — |
| WhatsApp | +55 11 96862-4262 (número para link: `5511968624262`) | Keepo, EuTerapeuta |
| Vittude | https://vittude.com/psicologo/patricia-domingues | Usuário |
| Instagram | `[[CONFIRMAR: @psipatriciadomingues (link enviado pelo usuário) ou @psi.patriciadomingues (Keepo e EuTerapeuta)]]` | Usuário, Keepo |
| Perfil no Google | https://maps.app.goo.gl/uVwuSHFzQA7XVy5s9 `[[CONFIRMAR: o perfil exibe endereço físico? Se for só área de atendimento, não publicar endereço]]` | Usuário |
| Keepo (link na bio atual) | https://patriciabrazdomingues.keepo.bio | Usuário |
| Zenklub | `[[CONFIRMAR: ainda atende pela Zenklub? Se sim, qual o link do perfil]]` | Keepo (avaliações) |
| E-mail profissional | `[[CONFIRMAR]]` (usado só na política de privacidade) | — |
| Horário de resposta de mensagens | `[[CONFIRMAR]]` | — |
| Recibo para reembolso de plano de saúde | `[[CONFIRMAR]]` | — |

### Formação (exibir do mais recente para o mais antigo)

| Formação | Instituição | Período | Observação |
|---|---|---|---|
| Curso de Psicoterapia Baseada em Evidências | InPBE (Instituto de Psicologia Baseada em Evidências) | 2025–2026 | Em andamento `[[CONFIRMAR]]` |
| Pós-graduação em Neuropsicologia | Universidade Anhembi Morumbi | 2025–2026 | Em andamento. Aparece numa versão anterior do perfil da Vittude `[[CONFIRMAR se segue cursando]]` |
| Especialização (pós-graduação lato sensu) em Saúde Mental e Desenvolvimento Humano | PUCPR | 2023–2024 | Trabalho final: "A saúde mental do idoso diante de suas perdas e luto: contribuições da psicoterapia de Aceitação e Compromisso" |
| Formação em Cuidado com Perdas e Luto | `[[CONFIRMAR instituição]]` | `[[CONFIRMAR]]` | |
| Graduação em Psicologia | `[[CONFIRMAR instituição]]` | `[[CONFIRMAR]]` | Participou de projetos sociais e grupos de estudo durante a formação |

Cursos em andamento aparecem sempre com a indicação "em andamento". Nunca apresentá-los como concluídos.

### Texto de apresentação escrito pela própria Patricia (Keepo)

Use como base para a seção "Sobre" (a versão adaptada está na seção 5). Resumo fiel do original: nasceu e cresceu em São Paulo, e a complexidade das experiências humanas na cidade a levou à Psicologia; durante a formação participou de projetos sociais e grupos de estudo; hoje foca em autoconhecimento, autoestima, luto, transições de vida, relacionamentos, ansiedade, depressão e regulação emocional; acredita numa abordagem científica e sensível, que considera o contexto de cada pessoa; quer que quem ela acompanha se sinta ouvido, compreendido e confiante para dar os próximos passos.

## 3. Regras éticas (CFP e CRP): obrigatórias

### 3.1 Normas de referência

- Código de Ética Profissional do Psicólogo (Resolução CFP nº 10/2005), em especial art. 9º (sigilo), art. 18 (instrumentos e técnicas a leigos), art. 19 (comunicação com a sociedade) e art. 20 (publicidade).
- Resolução CFP nº 3/2007, arts. 53 a 58 (publicidade).
- Resolução CFP nº 11/2000 (oferta de serviços; veda publicidade enganosa ou abusiva) e Código de Defesa do Consumidor.
- Nota Técnica CFP nº 1/2022 (publicidade e uso profissional de redes sociais e internet).
- Resolução CFP nº 9/2024 (atendimento mediado por tecnologias digitais). Ela revogou a Resolução 11/2018 e extinguiu o cadastro e-Psi em agosto de 2024. **Não exibir selo ou menção a "cadastro e-Psi".**
- Resolução CFP nº 13/2022 (psicoterapia; atendimento de menores exige consentimento de responsável).
- Resolução CFP nº 23/2022 (títulos de especialista).
- LGPD (Lei 13.709/2018), dados de saúde são dados sensíveis.

Este checklist foi montado a partir das normas públicas. A revisão final do conteúdo é responsabilidade da Patricia, como profissional inscrita.

### 3.2 Obrigatório no site

1. **Identificação completa** sempre que o serviço for apresentado: nome completo sem abreviação, a palavra "Psicóloga" e o registro com a região: `Patricia Braz Domingues, Psicóloga, CRP 06/167326`. Deve aparecer na primeira tela (hero), na seção "Sobre", no rodapé, no `<title>` e nos metadados.
2. Divulgar só títulos e qualificações que ela realmente possui, com cursos em andamento identificados como tal.
3. Divulgar só técnicas e abordagens reconhecidas pela profissão (TCC, ACT e Psicoterapia Breve estão ok).
4. Conteúdo com finalidade educativa e base científica, em linguagem acessível (art. 19).
5. Aviso de que o site e as sessões agendadas não são serviço de emergência, com CVV 188 e SAMU 192.
6. Link para o Cadastro Nacional de Profissionais de Psicologia (https://cadastro.cfp.org.br), para quem quiser verificar o registro.

### 3.3 Proibido

| Não fazer | Motivo | O que usar no lugar |
|---|---|---|
| "Primeira sessão grátis", "consulta gratuita" (o botão "Free Consultation" da referência), "desconto", "valor social", "valor acessível", pacotes, cupons, sorteios | Art. 20, d: preço não pode ser usado como propaganda | "Agendar sessão". Valores são informados no contato ou na Vittude |
| Exibir preço como chamariz | Idem | Não mostrar valores no site |
| Promessas de resultado: "supere a ansiedade", "resultados em X sessões", "cura", "garantido", "vai melhorar" | Art. 20, e: vedada previsão taxativa de resultados | Descrever o processo, não o resultado |
| Superlativos e comparação: "a melhor", "nº 1", "referência em" (o selo "Best Working Since" da referência) | Art. 20, f: autopromoção em detrimento de outros | Selo com a identificação profissional (seção 6.1) |
| Sensacionalismo e urgência artificial: "vagas limitadas", contadores, imagens de sofrimento dramático | Art. 20, h | Tom sóbrio |
| Depoimentos ou avaliações de pacientes, estrelas, prints ou widgets das avaliações da Vittude/Zenklub (inclusive as que hoje aparecem no Keepo) | Orientação dos CRPs com base no art. 20 e no sigilo: pedir depoimentos pode interferir no vínculo terapêutico | Transparência sobre formação e método |
| Casos clínicos, mesmo anonimizados, histórias de "antes e depois" | Sigilo (art. 9º) | Situações genéricas na seção "Talvez você esteja aqui" |
| Usar "especialista" como título profissional ("Psicóloga especialista em...") | Res. CFP 23/2022: o título exige registro de especialista no CRP, em áreas definidas | "Especialização em Saúde Mental e Desenvolvimento Humano (PUCPR)" como formação `[[CONFIRMAR: se ela tiver o título de especialista registrado no CRP, informar a área para ajustar]]` |
| Testes, quizzes ou autoavaliações ("descubra se você tem ansiedade") | Art. 18 e regras de avaliação psicológica | Nenhum |
| Fotos de banco de imagens encenando sessão ou "pacientes"; imagens geradas por IA de pessoas | Podem sugerir casos reais e soam artificiais | Fotos reais da Patricia |
| Selo ou menção ao "cadastro e-Psi" | Extinto pela Res. CFP 9/2024 | "Atendimento online conforme a regulamentação do CFP" (só no FAQ) |

### 3.4 Recomendado (sobriedade)

- Não usar métricas de volume como argumento ("+4.000 consultas", "387 avaliações", "5,0 estrelas"). O tempo de atuação pode aparecer de forma factual se a Patricia quiser `[[CONFIRMAR]]`.
- Nas mensagens de agendamento, orientar a pessoa a não mandar detalhes de saúde por escrito.

## 4. Arquitetura da informação: a jornada

O site é uma página única que segue a ordem em que uma pessoa costuma decidir começar terapia. Cada etapa responde à dúvida que a anterior deixou.

| # | Seção (âncora) | Pergunta que responde | Etapa da jornada |
|---|---|---|---|
| — | Header fixo | "Onde agendo?" (sempre visível) | — |
| 1 | Hero (`#inicio`) | "Quem é e o que faz?" | Chegar |
| 2 | O essencial (3 cartões) | "Como é o atendimento, em resumo?" | Chegar |
| 3 | Talvez você esteja aqui (`#terapia`) | "Isso é para mim?" | Reconhecer |
| 4 | O que acontece na terapia | "O que é, de verdade?" | Entender |
| 5 | Como funciona (`#como-funciona`) | "Como começo, na prática?" | Planejar |
| 6 | Quem vai te acompanhar (`#sobre`) | "Posso confiar nela?" | Confiar |
| 7 | Como agendar (`#agendar`) | "Qual caminho escolho?" | Agir |
| 8 | Dúvidas (`#duvidas`) | "E se...?" | Resolver o que sobrou |
| 9 | Rodapé | Identificação, links, emergência | — |
| — | Barra fixa de agendamento (só mobile) | "Onde agendo?" | — |

Páginas extras: `/politica-de-privacidade` e `/404`.

**Navegação principal:** Terapia (`#terapia`), Como funciona (`#como-funciona`), Sobre (`#sobre`), Dúvidas (`#duvidas`) e o botão "Agendar sessão" (`#agendar`).

**Pontos de agendamento:** header, hero, fim de "Como funciona", seção "Como agendar", fim do FAQ, rodapé e barra mobile. As chamadas são sempre sóbrias e com o mesmo texto; ninguém deve se sentir pressionado.

## 5. Conteúdo, seção por seção

Os textos abaixo são rascunhos prontos para uso, escritos para passar pelas regras da seção 3. Use-os como estão. Se precisar ajustar para caber no layout, encurte; não acrescente adjetivos, estatísticas ou promessas. Tudo deve passar pela aprovação da Patricia antes da publicação.

### 5.1 Header

- Logo: monograma em arco (◠, ver 6.4) + "Patricia Domingues" em Schibsted Grotesk 700. Abaixo, em tamanho pequeno: "Psicóloga, CRP 06/167326". `[[CONFIRMAR: ela já tem logotipo? Se sim, usar o dela]]`
- Navegação: Terapia, Como funciona, Sobre, Dúvidas.
- Botão: **Agendar sessão** (leva para `#agendar`).

### 5.2 Hero

- Linha de identificação (pequena, acima do título, em caixa normal): **Psicóloga Patricia Braz Domingues, CRP 06/167326**
- Título (H1): **Um espaço seguro para entender o que você sente.**
  - Alternativas para a Patricia escolher: "Às vezes, o primeiro passo é conversar com alguém preparado para ouvir." / "Para cuidar do que pesa, com calma e com método."
- Parágrafo: "Atendo adultos e idosos por videochamada, de onde você estiver, com uma prática baseada em evidências e atenta ao contexto de cada pessoa."
- Botão primário: **Agendar sessão** (`#agendar`)
- Link secundário com ícone circular (ocupa o lugar do "Watch More" da referência): **Chamar no WhatsApp** (abre o WhatsApp direto)
- Selo circular (texto em volta): "Psicóloga ✦ CRP 06/167326 ✦ Atendimento online ✦". No centro, o monograma em arco. `aria-label` do selo: "Psicóloga, CRP 06/167326, atendimento online".

### 5.3 O essencial (três cartões sobre a base do hero)

| Ícone | Título | Texto |
|---|---|---|
| Câmera de vídeo | Online, de onde você estiver | Sessões por videochamada. Você só precisa de internet, um fone de ouvido e um lugar reservado. |
| Duas pessoas | Adultos e idosos | Acompanho pessoas em diferentes fases da vida, no Brasil e brasileiros que moram em outros países. `[[CONFIRMAR faixa etária]]` |
| Relógio | Sessões de 50 minutos | A frequência é combinada entre nós, de acordo com o seu momento. |

### 5.4 Talvez você esteja aqui porque… (`#terapia`)

- H2: **Talvez você esteja aqui porque…**
- Lista (seis itens, marcador = pequeno arco ocre):
  - A preocupação não desliga, mesmo quando está tudo aparentemente bem.
  - Você perdeu alguém ou algo importante, e o luto tem sido difícil de carregar.
  - Está no meio de uma mudança de trabalho, de cidade ou de fase da vida, e se sente sem chão.
  - Os relacionamentos têm trazido mais desgaste do que conforto.
  - Sente um cansaço que não passa com descanso, ou tem vivido no automático.
  - Quer se conhecer melhor, mesmo sem nenhuma crise acontecendo.
- Fechamento: "Nada disso precisa virar um rótulo. Na terapia, a gente começa entendendo o que está acontecendo com você, no seu contexto, sem pressa e sem julgamento."

### 5.5 O que acontece na terapia

- H2: **O que acontece na terapia**
- Parágrafo: "Psicoterapia é um trabalho feito a dois, com uma profissional habilitada, sigilo e método. É um espaço para olhar com cuidado para o que você pensa, sente e faz, e para o que é importante na sua vida."
- Duas colunas:
  - **É:** um lugar de escuta atenta e sem julgamento; um processo com objetivos combinados entre nós; um trabalho apoiado em conhecimento científico; confidencial.
  - **Não é:** alguém dizendo o que você deve fazer; só para quem está em crise; um processo com prazo ou resultado definido de antemão.
- Bloco "Como eu trabalho" (H3): "Meu trabalho é orientado pela Terapia Cognitivo-Comportamental (TCC) e pela Terapia de Aceitação e Compromisso (ACT). Na prática, olhamos juntos para a relação entre pensamentos, emoções e comportamentos, buscamos formas mais flexíveis de lidar com o que é difícil e aproximamos suas escolhas daquilo que tem valor para você."
- Frase final, discreta: "Procurar terapia não é sinal de fraqueza e não precisa esperar o limite. É uma forma de cuidar da saúde, como qualquer outra."

### 5.6 Como funciona (`#como-funciona`)

É a única seção com numeração, porque é uma sequência real.

- H2: **Como funciona**
- Passos:
  1. **Você escolhe como agendar.** Pela Vittude, com agenda e pagamento na própria plataforma, ou diretamente comigo pelo WhatsApp.
  2. **Primeira sessão.** Nos conhecemos, você conta o que te trouxe e avaliamos juntos se o atendimento online é adequado para o seu momento.
  3. **Combinamos o caminho.** Definimos objetivos, frequência e por onde começar.
  4. **Acompanhamento.** Sessões de 50 minutos por videochamada. Ao longo do processo, revisamos juntos como você está.
- Caixa "Para a sessão": "Internet estável, celular ou computador com câmera, fone de ouvido e um lugar onde você possa falar à vontade."
- Botão: **Agendar sessão**

### 5.7 Quem vai te acompanhar (`#sobre`)

- H2: **Quem vai te acompanhar**
- Foto 2 da Patricia (retrato diferente do hero).
- Texto:

  > Olá, eu sou a Patricia. Muito prazer!
  >
  > Sou psicóloga clínica e atendo online adultos e idosos, acompanhando pessoas em diferentes lugares do Brasil e do mundo.
  >
  > Minha história começou em São Paulo. Crescer nessa cidade despertou meu interesse pela complexidade das experiências humanas e me levou à Psicologia. Durante a formação, participei de projetos sociais e grupos de estudo, que ampliaram meu entendimento da psicologia como espaço de cuidado e de aprendizado contínuo.
  >
  > Acredito numa abordagem científica e sensível, que considera o contexto de cada pessoa. Meu objetivo é oferecer um espaço de escuta e compreensão, para que você possa dar os próximos passos com mais confiança.

- Linha de identificação: **Patricia Braz Domingues, Psicóloga, CRP 06/167326**, com link discreto "Verificar registro no Cadastro Nacional do CFP".
- H3 **Formação**: lista cronológica (tabela da seção 2), com período à esquerda e curso/instituição à direita.
- H3 **Temas com que trabalho**: lista simples em linha, separada por vírgulas ou em duas colunas (sem "chips" coloridos): ansiedade, depressão, luto e perdas, transições de vida, relacionamentos, autoestima e insegurança, autoconhecimento, regulação emocional, burnout e estresse. `[[CONFIRMAR TOC e transtorno bipolar]]`
- `[[CONFIRMAR: ela ainda faz acompanhamento terapêutico presencial, citado no perfil do EuTerapeuta? Se sim, incluir uma linha]]`

### 5.8 Como agendar (`#agendar`)

- H2: **Como agendar**
- Parágrafo: "Escolha o caminho mais prático para você. Se tiver dúvidas antes de marcar, pode me chamar no WhatsApp."
- Bloco A, **Pela Vittude**: "Veja os horários disponíveis e agende direto na plataforma. Pagamento por cartão ou boleto." Botão: **Ver horários na Vittude**
- Bloco B, **Direto comigo**: "Me envie uma mensagem pelo WhatsApp para combinarmos horário, valor e forma de pagamento." Botão: **Chamar no WhatsApp**
- Bloco C (só se confirmado), **Pela Zenklub**: `[[CONFIRMAR]]`
- Nota pequena abaixo: "Por mensagem, não é preciso contar detalhes sobre a sua saúde. Conversamos sobre isso na sessão." Se houver: "Respondo mensagens `[[CONFIRMAR dias e horários]]`."
- Links secundários, menores: Instagram e perfil no Google.
- Nenhum preço nesta seção.

### 5.9 Dúvidas (`#duvidas`)

Usar `<details>`/`<summary>` nativos.

1. **A terapia online funciona?** O atendimento psicológico online é reconhecido e regulamentado pelo Conselho Federal de Psicologia. Para muitas pessoas, ele funciona bem e facilita manter a regularidade. Na primeira sessão, avaliamos juntos se esse formato é adequado para o seu momento.
2. **Preciso estar em crise para começar?** Não. Muita gente começa a terapia para se conhecer melhor, atravessar uma mudança ou cuidar de algo antes que fique mais pesado.
3. **O que eu falar fica em sigilo?** Sim. O sigilo profissional é um dever previsto no Código de Ética do Psicólogo. Se quiser, conversamos sobre isso logo na primeira sessão.
4. **Quanto tempo dura a terapia?** Depende do que você busca e de como o processo caminha. Não existe um número fixo de sessões; revisamos isso juntos ao longo do caminho.
5. **Você atende quem mora fora do Brasil?** `[[CONFIRMAR]]` Sugestão: "Sim. Atendo brasileiros que vivem em outros países, em português."
6. **Qual a idade mínima?** `[[CONFIRMAR]]` Se atender menores de 18: mencionar que é necessário o consentimento de um responsável.
7. **Você emite recibo para reembolso do plano de saúde?** `[[CONFIRMAR]]`
8. **E se eu precisar de ajuda agora?** Este site e as sessões agendadas não são um serviço de emergência. Se você está em risco ou pensando em se machucar, ligue 188 (CVV, gratuito, 24 horas) ou 192 (SAMU), ou procure o pronto-socorro mais próximo.

Depois da lista: "Ficou alguma dúvida?" + link **Chamar no WhatsApp**.

### 5.10 Rodapé

- Três colunas no desktop, empilhadas no mobile:
  1. Logo + **Patricia Braz Domingues, Psicóloga, CRP 06/167326** + "Atendimento psicológico online para adultos e idosos." + link "Verificar registro no Cadastro Nacional do CFP".
  2. Links: Vittude, WhatsApp, Instagram, Perfil no Google, Política de privacidade.
  3. Bloco de emergência: "Em caso de emergência, ligue 188 (CVV) ou 192 (SAMU)."
- Linha final: "© 2026 Patricia Braz Domingues" (ano gerado automaticamente).

### 5.11 Política de privacidade (`/politica-de-privacidade`)

Texto curto e claro: o site não usa cookies nem ferramentas de rastreamento `[[ajustar se for adotada uma ferramenta de métricas sem cookies]]`; não há formulários; os links levam para serviços externos (Vittude, WhatsApp, Instagram, Google) com políticas próprias; contato para questões de privacidade: `[[CONFIRMAR e-mail]]`; data da última atualização.

### 5.12 Página 404

"Esta página não existe ou mudou de endereço." + botão **Voltar para o início** + link **Chamar no WhatsApp**.

## 6. Direção visual

### 6.1 A referência (`docs/referencia-visual.jpg`)

A imagem é um modelo de site de saúde mental em tons de verde. O degradê verde-azulado **em volta** do site é só a moldura de apresentação da imagem; ignore. Não copie nenhum asset, logo, nome ou texto do modelo.

**Manter da referência:**
- Hero dentro de um container claro com cantos bem arredondados e um degradê muito suave.
- Header com logo à esquerda, navegação agrupada dentro de uma "pílula" de fundo levemente mais escuro no centro, e botão em formato de pílula à direita.
- Hero em duas colunas: texto à esquerda (linha pequena, título grande quebrando em três linhas, parágrafo curto, dois chamados lado a lado) e foto à direita.
- Foto em formato de **arco** (topo semicircular, base reta) com um bloco sólido deslocado atrás, para baixo e para a direita.
- **Selo circular** com texto girando em volta, sobreposto à borda esquerda da foto.
- Três cartões brancos que "sobem" sobre a base do hero, com ícone de linha, título e texto curto.
- Muito respiro e hierarquia clara.

**Mudar em relação à referência:**

| Na referência | No site da Patricia | Motivo |
|---|---|---|
| Verde e verde-azulado | Paleta ameixa e ocre (6.2) | Pedido do cliente: nenhum verde |
| Botão "Free Consultation" | "Agendar sessão" | Ética (3.3) |
| Selo "Best Working Since 2024" | Selo "Psicóloga ✦ CRP 06/167326 ✦ Atendimento online ✦" | Ética, e o CRP vira elemento de confiança |
| "Watch More" com botão de play | "Chamar no WhatsApp" com ícone circular | Não há vídeo. Se um dia houver vídeo de apresentação, pode voltar |
| Tipografia genérica (tipo Roboto) | Schibsted Grotesk + Literata (6.3) | Personalidade e leitura confortável |
| Ícones de cérebro, cabeça e máscaras | Ícones neutros de linha (vídeo, pessoas, relógio) | Evitar clichês |
| Botão primário só com contorno | Botão primário sólido; contorno fica para o secundário | Clareza para o público idoso |
| Cartões em todas as seções (padrão de template) | Cartões só no hero e em "Como agendar"; o resto usa faixas de fundo, listas e colunas | Evitar cara de template |

### 6.2 Paleta

**Paleta A, "ameixa e ocre" (usar esta):**

| Token | Hex | Uso |
|---|---|---|
| `--ameixa-900` | `#3B2745` | Títulos, botão primário, fundo da seção "Como agendar" |
| `--ameixa-600` | `#6E4F7E` | Links, ícones, contornos, anel de foco (contraste ≈ 6,5:1 sobre `--papel`) |
| `--lilas-200` | `#DCD0E4` | Bloco deslocado atrás da foto, faixas de destaque |
| `--lilas-100` | `#EFE9F3` | Início do degradê do hero, fundo de seções alternadas |
| `--papel` | `#FBFAFC` | Fundo geral |
| `--tinta` | `#231B28` | Texto corrido |
| `--ocre-500` | `#B8862F` | Acento decorativo: anel do selo, marcadores em arco da lista. **Nunca em texto** (contraste ≈ 3:1, serve só para elementos gráficos) |
| `--linha` | `#E3DAE8` | Bordas e divisórias |

Degradê do hero: de `--lilas-100` (canto superior esquerdo) para `--papel`, bem sutil. É o **único** degradê do site.

**Paleta B, "azul-tinta e areia" (alternativa, caso a Patricia prefira):** `#1E3350`, `#4A6485`, `#D9E1EA`, `#EEF2F6`, `#F9F9F7`, `#1A1F26`, `#C4A277`, `#E1E6EC`, na mesma ordem de papéis. Toda a cor deve viver em `src/styles/tokens.css`, de modo que trocar de paleta seja editar um único arquivo.

`[[CONFIRMAR: se a Patricia já usa cores próprias no Instagram, avaliar adaptar os tokens a elas]]`

**Proibido:** qualquer verde, verde-azulado, turquesa ou menta; fundo creme com acento terracota; fundo escuro com acento neon.

Tema claro apenas. Não é preciso modo escuro.

### 6.3 Tipografia

- **Títulos, navegação, botões e rótulos:** Schibsted Grotesk (variável), pesos 500 a 800. H1 em 700, `letter-spacing: -0.02em`, `line-height: 1.05`.
- **Texto corrido:** Literata (variável), 400 e 500, `line-height: 1.7`, largura máxima de 66 caracteres.
- **Escala:**
  - H1: `clamp(2.6rem, 1.6rem + 4.5vw, 4.75rem)`
  - H2: `clamp(1.9rem, 1.3rem + 2.4vw, 2.9rem)`
  - H3: `1.375rem`
  - Corpo: `1.125rem` (18 px) no mobile, `1.1875rem` (19 px) no desktop
  - Pequeno: `0.9375rem` (15 px). **Nada menor que 15 px** em lugar nenhum (público idoso)
- Fontes auto-hospedadas via `@fontsource-variable/schibsted-grotesk` e `@fontsource-variable/literata`. Não carregar do Google Fonts (privacidade e desempenho). `font-display: swap`.
- Fallbacks: `"Schibsted Grotesk Variable", "Helvetica Neue", Arial, sans-serif` e `"Literata Variable", Georgia, "Times New Roman", serif`.
- Tudo em caixa normal (sentence case). Nenhum rótulo em caixa alta. Nenhuma palavra do título destacada com outra cor, itálico ou marca-texto.

### 6.4 Formas, espaço e profundidade

- **Assinatura visual: o arco.** Aparece forte na foto do hero e, discretamente, em dois lugares: o monograma do logo (um arco simples ◠) e os marcadores da lista "Talvez você esteja aqui" (pequenos arcos em `--ocre-500`). Em nenhum outro lugar.
- **Raios, com hierarquia:** container do hero 32 px; cartões 20 px; foto do "Sobre" 20 px; botões em pílula (999 px); foto do hero `border-radius: 999px 999px 0 0` com proporção 4:5.
- **Sombra:** só nos três cartões sobre o hero: `0 18px 40px -24px rgb(59 39 69 / 0.35)`. Nenhum outro elemento tem sombra.
- **Bloco atrás da foto:** mesmo formato de arco, cor `--lilas-200`, deslocado 16 px para a direita e 16 px para baixo.
- **Espaçamento:** escala 4, 8, 12, 16, 24, 32, 48, 64, 96, 128 px. Seções com 96 a 128 px de respiro vertical no desktop e 64 a 80 px no mobile.
- **Grade:** container com largura máxima de 1200 px e margens laterais de 24 px no mobile. Hero em 12 colunas: texto nas colunas 1 a 7, foto nas colunas 8 a 12.
- **Alinhamento:** tudo alinhado à esquerda, inclusive os títulos de seção. Nada centralizado, exceto o conteúdo do selo.

### 6.5 Wireframes

Legenda: `#` = bloco lilás deslocado atrás da foto; `(SELO)` = selo circular girando, sobreposto à borda esquerda da foto; `( )` = botão em pílula.

```
DESKTOP (>= 1024 px)
+-----------------------------------------------------------------------+
| ◠ Patricia Domingues   ( Terapia  Como funciona  Sobre  Dúvidas )  (Agendar sessão) |
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

```
MOBILE (< 768 px)
+-----------------------------+
| ◠ Patricia Domingues     ☰  |
|                             |
| Psicóloga Patricia Braz     |
| Domingues, CRP 06/167326    |
| Um espaço seguro            |
| para entender o que         |
| você sente.                 |
| Atendo adultos e idosos...  |
| (   Agendar sessão    )     |  <- largura total
| (o) Chamar no WhatsApp      |
|        .-"""-.              |
|  (SELO) FOTO  |#   máx 60vh |
|        +------+#            |
| [ Online, de onde estiver ] |
| [ Adultos e idosos        ] |
| [ Sessões de 50 minutos   ] |
|             ...             |
|=============================|
| ( Vittude )  ( WhatsApp )   |  <- barra fixa
+-----------------------------+
```

Seções abaixo do hero (desktop):

```
Talvez você esteja aqui   H2 nas colunas 1-5 | lista em duas colunas nas colunas 6-12, marcador em arco ocre
O que acontece na terapia faixa de fundo --lilas-100 | parágrafo | colunas "É" e "Não é" separadas por uma linha vertical | bloco "Como eu trabalho"
Como funciona             4 passos em linha, numerados 1 a 4, ligados por uma linha fina | caixa "Para a sessão" | botão
Quem vai te acompanhar    foto nas colunas 1-5 | texto nas colunas 6-12 | formação como lista cronológica | temas em lista simples
Como agendar              faixa --ameixa-900 com texto claro | 2 (ou 3) blocos lado a lado com borda clara, sem sombra
Dúvidas                   coluna única de até 760 px com <details>
Rodapé                    fundo --papel, linha superior, 3 colunas
```

No mobile, "Como funciona" vira lista vertical com a linha conectando os números à esquerda.

### 6.6 Movimento

- **Uma única animação automática:** o texto do selo gira devagar (uma volta a cada 40 s, `linear`, infinito). Com `prefers-reduced-motion: reduce`, fica parado.
- FAQ abre e fecha com o comportamento nativo de `<details>`.
- Hover de botões e links: só mudança de cor ou sublinhado, transição de até 150 ms.
- Rolagem suave para as âncoras só quando `prefers-reduced-motion` não estiver ativo.
- Sem animação de entrada ao rolar, sem parallax, sem efeitos em cartões.

### 6.7 Imagens e ícones

- **Fotos reais da Patricia, obrigatório.** `[[CONFIRMAR: enviar as fotos para src/assets/fotos/]]`
  - Hero: retrato vertical 4:5, mínimo 1200 × 1500 px, fundo calmo, com espaço acima da cabeça para o arco.
  - Sobre: segundo retrato (vertical ou 3:2), diferente do hero.
  - Enquanto não houver fotos, usar um placeholder neutro em `--lilas-200` com o texto "Foto da Patricia". **Nunca** usar banco de imagens nem imagem gerada por IA.
- Texto alternativo descritivo, por exemplo: "Patricia Braz Domingues sorrindo, sentada em frente a uma estante clara".
- **Ícones:** um único conjunto de ícones de linha (Phosphor, estilo "light" ou "regular"), SVG inline, cor `--ameixa-600`, traço uniforme. Proibidos: cérebro, cabeça com engrenagem, quebra-cabeça, flor de lótus, folhas, coração com as mãos.
- **Imagem de compartilhamento (Open Graph):** 1200 × 630, foto do hero recortada + nome completo + "Psicóloga, CRP 06/167326" sobre `--papel`.
- **Favicon:** o monograma em arco em `--ameixa-900`, em SVG.

### 6.8 Antipadrões: o site não pode parecer gerado por IA

**Visual, não fazer:**
- Fundo creme (perto de `#F4F1EA`) com serifa de alto contraste e acento terracota.
- Fundo quase preto com um único acento vibrante.
- Degradês decorativos espalhados pela página (só o do hero é permitido).
- Grades de cartões idênticos em todas as seções, todos com o mesmo raio e a mesma sombra cinza.
- Rótulo pequeno em CAIXA ALTA espaçada acima de cada título.
- Uma palavra do título em outra cor, itálico ou com marca-texto.
- Numeração 01 / 02 / 03 fora da seção "Como funciona".
- Seta "→" em botões e links.
- Textos de metadados unidos por pontos médios ("A · B · C") ou rótulos no formato "PALAVRA — fragmento".
- Emojis na interface.
- Glassmorphism, blobs orgânicos flutuando, cursores personalizados, parallax, animação de entrada em cada seção.
- Números de destaque ("+4.000 atendimentos", "5,0 ★").
- Fonte monoespaçada para rótulos.

**Texto, não fazer:**
- Clichês: "transforme sua vida", "desbloqueie seu potencial", "florescer", "sua melhor versão", "jornada transformadora", "não é apenas X, é Y".
- Sequências de três adjetivos ("acolhedor, humano e transformador").
- Travessões em excesso e perguntas retóricas em série.
- Urgência artificial.

**Fazer:** frases curtas, verbos simples, nomes de botões que dizem exatamente o que acontece ("Ver horários na Vittude", não "Saiba mais"). Um botão tem o mesmo nome em todo o site.

## 7. Agendamento sempre acessível

- **Desktop:** header fixo (`position: sticky`) com o botão "Agendar sessão" sempre visível. Ao rolar, o header pode ficar mais compacto, sem animação chamativa.
- **Mobile:** barra fixa na parte de baixo com dois botões lado a lado, com ícone e texto: **Vittude** e **WhatsApp**. Altura mínima de 56 px, mais `env(safe-area-inset-bottom)`. Adicionar `padding-bottom` no `body` para a barra não cobrir o rodapé.
- **Menu mobile:** botão ☰ abre um painel de tela cheia com a navegação e os dois links de agendamento. Fecha com Esc, com o botão fechar e ao tocar num link. Foco preso dentro do painel enquanto aberto.
- **Links:**
  - Vittude: `https://vittude.com/psicologo/patricia-domingues`
  - WhatsApp: `https://wa.me/5511968624262?text=` + `encodeURIComponent("Olá, Patricia! Vim pelo seu site e gostaria de saber sobre horários para sessão.")`
  - Todos os links externos: `target="_blank" rel="noopener noreferrer"` e um texto visualmente oculto "(abre em nova aba)".
- **Nomes dos botões (iguais em todo o site):** "Agendar sessão" (vai para `#agendar`), "Ver horários na Vittude", "Chamar no WhatsApp".
- Alvos de toque com pelo menos 48 × 48 px.

## 8. Stack técnica e estrutura

- **Astro** (última versão estável), saída estática, TypeScript.
- **CSS puro** com custom properties (`tokens.css` + `global.css`) e CSS escopado nos componentes `.astro`. Sem Tailwind e sem biblioteca de componentes.
- **JavaScript mínimo:** só o menu mobile. O selo é um SVG com `<textPath>` animado por CSS.
- **Imagens:** `astro:assets` (`<Picture>` com AVIF e WebP). Foto do hero com `loading="eager"` e `fetchpriority="high"`.
- **Pacotes:** `@fontsource-variable/schibsted-grotesk`, `@fontsource-variable/literata`, `@astrojs/sitemap`. Ícones Phosphor copiados como SVG para `src/icons/` (sem dependência em tempo de execução).
- **Deploy:** GitHub Actions com a action oficial do Astro para GitHub Pages. Observação: GitHub Pages em repositório **privado** exige plano pago do GitHub; se o repositório for privado, usar Cloudflare Pages, Netlify ou Vercel (gratuitos). `[[CONFIRMAR: repositório público ou privado]]`
- **Domínio:** `[[CONFIRMAR]]`. Sugestões para verificar no registro.br: `patriciadomingues.com.br`, `patriciabrazdomingues.com.br`, `psicologapatriciadomingues.com.br`. Arquivo `public/CNAME` quando houver domínio.

```
site-patricia-domingues/
├── BRIEFING.md                  # este arquivo
├── CLAUDE.md                    # uma linha: "Leia BRIEFING.md antes de qualquer tarefa."
├── README.md                    # como editar textos e publicar (gerado na fase 6)
├── docs/
│   ├── referencia-visual.jpg    # imagem de referência enviada pelo cliente
│   └── plano-design.md          # gerado na fase 1
├── public/
│   ├── favicon.svg
│   ├── og.jpg
│   └── robots.txt
├── src/
│   ├── data/site.ts             # TODOS os textos, links e dados da profissional
│   ├── styles/tokens.css        # cores, fontes, espaçamentos, raios
│   ├── styles/global.css
│   ├── icons/                   # SVGs Phosphor
│   ├── assets/fotos/            # fotos da Patricia
│   ├── layouts/Base.astro       # <head>, SEO, JSON-LD, skip link
│   ├── components/
│   │   ├── Header.astro
│   │   ├── MenuMobile.astro
│   │   ├── Hero.astro
│   │   ├── SeloCRP.astro
│   │   ├── Essencial.astro
│   │   ├── Reconhecer.astro
│   │   ├── Terapia.astro
│   │   ├── ComoFunciona.astro
│   │   ├── Sobre.astro
│   │   ├── Agendar.astro
│   │   ├── Duvidas.astro
│   │   ├── Rodape.astro
│   │   └── BarraAgendamento.astro
│   └── pages/
│       ├── index.astro
│       ├── politica-de-privacidade.astro
│       └── 404.astro
├── astro.config.mjs
└── .github/workflows/deploy.yml
```

Formato sugerido para `src/data/site.ts`:

```ts
export const profissional = {
  nomeCompleto: 'Patricia Braz Domingues',
  nomeUso: 'Patricia Domingues',
  titulo: 'Psicóloga',
  crp: 'CRP 06/167326',
  identificacao: 'Patricia Braz Domingues, Psicóloga, CRP 06/167326',
  modalidade: 'Atendimento online',
  duracaoSessao: '50 minutos',
};

export const links = {
  vittude: 'https://vittude.com/psicologo/patricia-domingues',
  whatsappNumero: '5511968624262',
  whatsappMensagem: 'Olá, Patricia! Vim pelo seu site e gostaria de saber sobre horários para sessão.',
  instagram: '[[CONFIRMAR]]',
  google: 'https://maps.app.goo.gl/uVwuSHFzQA7XVy5s9',
  zenklub: null, // [[CONFIRMAR]]
  cadastroCfp: 'https://cadastro.cfp.org.br',
};

export const whatsappUrl = `https://wa.me/${links.whatsappNumero}?text=${encodeURIComponent(links.whatsappMensagem)}`;

// + objetos com os textos de cada seção (seção 5 deste briefing)
```

Placeholders `[[CONFIRMAR]]` que chegarem ao HTML devem aparecer com estilo visível (fundo amarelo-claro tracejado) apenas em ambiente de desenvolvimento, e o build de produção deve emitir um aviso listando quantos restam.

## 9. SEO, acessibilidade, desempenho e privacidade

### 9.1 SEO
- `<html lang="pt-BR">`.
- `<title>`: "Patricia Braz Domingues | Psicóloga online | CRP 06/167326".
- Meta description (até 160 caracteres): "Psicoterapia online para adultos e idosos com a psicóloga Patricia Braz Domingues, CRP 06/167326. Agende pela Vittude ou pelo WhatsApp."
- Canonical, Open Graph e Twitter Card com `og.jpg`.
- Um único H1; H2 por seção; H3 dentro das seções.
- JSON-LD `Person` com `name`, `jobTitle: "Psicóloga"`, `identifier` (CRP), `knowsLanguage: "pt-BR"`, `sameAs` (Vittude, Instagram, Google). Sem `aggregateRating` nem `review`.
- `sitemap.xml` e `robots.txt`.

### 9.2 Acessibilidade (WCAG 2.2 AA, pensando no público idoso)
- Contraste AA em todo texto; `--ocre-500` nunca em texto.
- Foco visível em tudo: `outline: 3px solid var(--ameixa-600); outline-offset: 3px`.
- Link "Pular para o conteúdo" como primeiro elemento focável.
- Landmarks (`header`, `nav`, `main`, `footer`) e ordem de leitura lógica.
- Selo: texto rotativo com `aria-hidden="true"` e `role="img"` + `aria-label` com o texto completo.
- Zoom de 200% sem quebrar o layout nem gerar rolagem horizontal.
- Nenhum texto dentro de imagem.
- Testar com teclado e com leitor de tela (VoiceOver ou NVDA).

### 9.3 Desempenho
- Lighthouse mobile ≥ 95 em Desempenho, Acessibilidade, Boas práticas e SEO.
- LCP abaixo de 2,5 s; CLS abaixo de 0,1.
- Pré-carregar só o arquivo de fonte usado no H1.
- Nenhum script de terceiros.

### 9.4 Privacidade (LGPD)
- **Sem formulários.** Todo contato é por link externo (Vittude e WhatsApp), para não coletar dados de saúde no site.
- **Sem cookies, pixels ou Google Analytics.** Se a Patricia quiser métricas de visitas, usar uma ferramenta sem cookies (Plausible ou Umami) `[[CONFIRMAR]]`. Sem cookies, não é preciso banner de consentimento.
- **Sem embeds** do Instagram, Google Maps ou YouTube (carregam rastreadores). Usar links simples.
- Página `/politica-de-privacidade` (5.11).

## 10. Pendências para confirmar com a Patricia

1. Grafia exata do nome completo no registro do CRP.
2. Perfil correto do Instagram: `@psipatriciadomingues` ou `@psi.patriciadomingues`.
3. O perfil do Google mostra endereço físico? Deve aparecer no site?
4. Ainda atende pela Zenklub? Link do perfil.
5. Faixa etária atendida (a partir de 16 ou 18 anos?).
6. Atende brasileiros no exterior? Só em português?
7. Emite recibo para reembolso de plano de saúde?
8. Formas de pagamento no particular.
9. Dias e horários em que responde mensagens.
10. Instituição e ano da graduação e da formação em Perdas e Luto; situação atual da pós em Neuropsicologia e do curso do InPBE.
11. Possui título de especialista registrado no CRP? Em qual área?
12. Quer listar TOC e transtorno bipolar entre os temas?
13. Ainda faz acompanhamento terapêutico presencial?
14. Já tem logotipo ou cores próprias?
15. Fotos profissionais (hero e sobre).
16. Escolha do título do hero entre as três opções.
17. Domínio e e-mail profissional.
18. Repositório público ou privado (define onde publicar).
19. Quer métricas de visitas sem cookies?

## 11. Fases de execução (Claude Code)

Faça um commit ao fim de cada fase, com mensagem descritiva em português.

**Fase 0, preparação.** Criar o projeto Astro, a estrutura de pastas da seção 8, instalar dependências, criar `tokens.css` com a paleta A e a tipografia, e preencher `src/data/site.ts` com todos os dados e textos deste briefing. Criar `CLAUDE.md` com uma linha apontando para este arquivo.

**Fase 1, plano de design.** Antes de escrever componentes, gerar `docs/plano-design.md` com: tokens finais, wireframes confirmados e três princípios do que torna este site único. Revisar o plano contra a seção 6.8; se alguma decisão parecer o padrão que você usaria para qualquer site de psicologia, trocar e registrar o motivo.

**Fase 2, header e hero.** Header, menu mobile, hero, selo e os três cartões. Tirar capturas de tela (Playwright) em 1440 px e 390 px de largura, comparar com a referência e com a seção 6, e corrigir o que destoar.

**Fase 3, jornada.** Seções 5.4 a 5.10 e a barra de agendamento mobile. Novas capturas de tela da página inteira nas duas larguras e autocrítica: se algo pode ser removido sem perda, remover.

**Fase 4, acabamento.** SEO, JSON-LD, OG image, favicon, política de privacidade, 404, `sitemap.xml`, `robots.txt`. Rodar Lighthouse e axe e corrigir o que for apontado.

**Fase 5, revisão ética.** Percorrer a seção 3 item por item contra o texto renderizado. Rodar a busca abaixo e revisar cada ocorrência manualmente (algumas palavras têm usos legítimos, como "se conhecer melhor"):

```bash
grep -rniE "gr[aá]tis|gratuit|desconto|promo|pacote|melhor|garant|cura|especialista|depoiment|avalia[cç][õo]es|estrelas|★|→|e-psi|vagas" src/
```

**Fase 6, publicação.** Workflow de deploy, `README.md` explicando em linguagem simples como editar textos (em `src/data/site.ts`), como trocar fotos e como publicar. Relatório final listando todos os `[[CONFIRMAR]]` que ainda restam.

## 12. Critérios de aceite

- [ ] Identificação "Patricia Braz Domingues, Psicóloga, CRP 06/167326" visível na primeira tela, no "Sobre", no rodapé e no `<title>`.
- [ ] Nenhum tom de verde, verde-azulado ou turquesa em lugar nenhum.
- [ ] Botão de agendamento visível em qualquer ponto da rolagem, no desktop e no mobile.
- [ ] Links da Vittude e do WhatsApp funcionando, com mensagem pré-preenchida no WhatsApp.
- [ ] Nenhuma ocorrência de preço como chamariz, promessa de resultado, superlativo, depoimento, avaliação, estrela ou menção ao e-Psi.
- [ ] Aviso de emergência (188 e 192) no FAQ e no rodapé.
- [ ] Link para o Cadastro Nacional do CFP.
- [ ] Layout fiel à estrutura da referência (seção 6.1) e livre dos antipadrões da seção 6.8.
- [ ] Única animação automática: o selo, parado com `prefers-reduced-motion`.
- [ ] Corpo de texto com 18 px ou mais; nada abaixo de 15 px.
- [ ] Lighthouse mobile ≥ 95 nas quatro categorias; axe sem erros.
- [ ] Nenhum cookie, formulário, embed ou script de terceiros.
- [ ] Todos os textos e links editáveis em `src/data/site.ts`.
- [ ] Relatório final com as pendências `[[CONFIRMAR]]` restantes.

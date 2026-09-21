/* ---------------------------------------------------------------------------
   FONTE DA VERDADE DO SITE
   Todo texto, link e dado da profissional mora aqui. Nenhum componente deve
   ter texto ou endereço fixo no código.

   Para editar o site, mude os valores deste arquivo e publique. O README
   explica o passo a passo.

   `[[CONFIRMAR: ...]]` marca um dado que ainda não foi confirmado pela
   Patricia. Em desenvolvimento ele aparece destacado em amarelo; o comando
   de build avisa quantos ainda restam. Substitua o texto pelo dado real
   assim que ele for confirmado.
   --------------------------------------------------------------------------- */

export const profissional = {
  nomeCompleto: 'Patricia Braz Domingues',
  nomeUso: 'Patricia Domingues',
  titulo: 'Psicóloga',
  crp: 'CRP 06/167326',
  /* Identificação exigida pelo Código de Ética. Aparece no hero, no "Sobre",
     no rodapé, no <title> e nos metadados. */
  identificacao: 'Patricia Braz Domingues, Psicóloga, CRP 06/167326',
  modalidade: 'Atendimento online',
  duracaoSessao: '50 minutos',
} as const;

export const links = {
  vittude: 'https://vittude.com/psicologo/patricia-domingues',
  whatsappNumero: '5511968624262',
  whatsappMensagem:
    'Olá, Patricia! Vim pelo seu site e gostaria de saber sobre horários para sessão.',
  /* Dois perfis diferentes aparecem nas fontes. Enquanto não houver
     confirmação, o link fica fora do site (melhor nenhum link do que um
     link errado). Preencha com a URL completa para o link voltar. */
  instagram: null as string | null,
  google: 'https://maps.app.goo.gl/uVwuSHFzQA7XVy5s9',
  zenklub: null as string | null,
  cadastroCfp: 'https://cadastro.cfp.org.br',
} as const;

export const whatsappUrl =
  `https://wa.me/${links.whatsappNumero}?text=${encodeURIComponent(links.whatsappMensagem)}`;

/* Um botão tem o mesmo nome em todo o site (briefing 6.8). */
export const botoes = {
  agendar: 'Agendar sessão',
  vittude: 'Ver horários na Vittude',
  whatsapp: 'Chamar no WhatsApp',
  verificarCrp: 'Verificar registro no Cadastro Nacional do CFP',
} as const;

export const navegacao = [
  { rotulo: 'Terapia', href: '#terapia' },
  { rotulo: 'Como funciona', href: '#como-funciona' },
  { rotulo: 'Sobre', href: '#sobre' },
  { rotulo: 'Dúvidas', href: '#duvidas' },
] as const;

/* --------------------------------------------------------------------------- SEO */

export const seo = {
  titulo: 'Patricia Braz Domingues | Psicóloga online | CRP 06/167326',
  descricao:
    'Psicoterapia online para adultos e idosos com a psicóloga Patricia Braz Domingues, CRP 06/167326. Agende pela Vittude ou pelo WhatsApp.',
  idioma: 'pt-BR',
  og: '/og.jpg',
} as const;

/* --------------------------------------------------------------------------- Hero */

export const hero = {
  identificacao: 'Psicóloga Patricia Braz Domingues, CRP 06/167326',
  /* Alternativas para a Patricia escolher (trocar o valor de `titulo`):
     - 'Às vezes, o primeiro passo é conversar com alguém preparado para ouvir.'
     - 'Para cuidar do que pesa, com calma e com método.' */
  titulo: 'Um espaço seguro para entender o que você sente.',
  paragrafo:
    'Atendo adultos e idosos por videochamada, de onde você estiver, com uma prática baseada em evidências e atenta ao contexto de cada pessoa.',
  selo: {
    /* O ✦ separa as partes no texto que gira em volta do selo. */
    texto: 'Psicóloga ✦ CRP 06/167326 ✦ Atendimento online ✦',
    descricao: 'Psicóloga, CRP 06/167326, atendimento online',
  },
  foto: {
    alt: 'Retrato de Patricia Braz Domingues',
    /* Sem foto, o site mostra um espaço neutro em lilás com esta legenda.
       Nunca usar banco de imagens nem imagem gerada por IA. */
    legendaPlaceholder: 'Foto da Patricia',
  },
} as const;

/* --------------------------------------------------------------------------- O essencial */

export const essencial = [
  {
    icone: 'video-camera',
    titulo: 'Online, de onde você estiver',
    texto:
      'Sessões por videochamada. Você só precisa de internet, um fone de ouvido e um lugar reservado.',
  },
  {
    icone: 'users',
    titulo: 'Adultos e idosos',
    texto:
      'Acompanho pessoas em diferentes fases da vida, no Brasil e brasileiros que moram em outros países.',
  },
  {
    icone: 'clock',
    titulo: 'Sessões de 50 minutos',
    texto: 'A frequência é combinada entre nós, de acordo com o seu momento.',
  },
] as const;

/* --------------------------------------------------------------------------- Talvez você esteja aqui */

export const reconhecer = {
  titulo: 'Talvez você esteja aqui porque…',
  itens: [
    'A preocupação não desliga, mesmo quando está tudo aparentemente bem.',
    'Você perdeu alguém ou algo importante, e o luto tem sido difícil de carregar.',
    'Está no meio de uma mudança de trabalho, de cidade ou de fase da vida, e se sente sem chão.',
    'Os relacionamentos têm trazido mais desgaste do que conforto.',
    'Sente um cansaço que não passa com descanso, ou tem vivido no automático.',
    'Quer se conhecer melhor, mesmo sem nenhuma crise acontecendo.',
  ],
  fechamento:
    'Nada disso precisa virar um rótulo. Na terapia, a gente começa entendendo o que está acontecendo com você, no seu contexto, sem pressa e sem julgamento.',
} as const;

/* --------------------------------------------------------------------------- O que acontece na terapia */

export const terapia = {
  titulo: 'O que acontece na terapia',
  paragrafo:
    'Psicoterapia é um trabalho feito a dois, com uma profissional habilitada, sigilo e método. É um espaço para olhar com cuidado para o que você pensa, sente e faz, e para o que é importante na sua vida.',
  e: {
    titulo: 'É',
    itens: [
      'Um lugar de escuta atenta e sem julgamento.',
      'Um processo com objetivos combinados entre nós.',
      'Um trabalho apoiado em conhecimento científico.',
      'Confidencial.',
    ],
  },
  naoE: {
    titulo: 'Não é',
    itens: [
      'Alguém dizendo o que você deve fazer.',
      'Só para quem está em crise.',
      'Um processo com prazo ou resultado definido de antemão.',
    ],
  },
  comoTrabalho: {
    titulo: 'Como eu trabalho',
    texto:
      'Meu trabalho é orientado pela Terapia Cognitivo-Comportamental (TCC) e pela Terapia de Aceitação e Compromisso (ACT). Na prática, olhamos juntos para a relação entre pensamentos, emoções e comportamentos, buscamos formas mais flexíveis de lidar com o que é difícil e aproximamos suas escolhas daquilo que tem valor para você.',
  },
  fraseFinal:
    'Procurar terapia não é sinal de fraqueza e não precisa esperar o limite. É uma forma de cuidar da saúde, como qualquer outra.',
} as const;

/* --------------------------------------------------------------------------- Como funciona */

export const comoFunciona = {
  titulo: 'Como funciona',
  passos: [
    {
      titulo: 'Você escolhe como agendar.',
      texto:
        'Pela Vittude, com agenda e pagamento na própria plataforma, ou diretamente comigo pelo WhatsApp.',
    },
    {
      titulo: 'Primeira sessão.',
      texto:
        'Nos conhecemos, você conta o que te trouxe e avaliamos juntos se o atendimento online é adequado para o seu momento.',
    },
    {
      titulo: 'Combinamos o caminho.',
      texto: 'Definimos objetivos, frequência e por onde começar.',
    },
    {
      titulo: 'Acompanhamento.',
      texto:
        'Sessões de 50 minutos por videochamada. Ao longo do processo, revisamos juntos como você está.',
    },
  ],
  caixa: {
    titulo: 'Para a sessão',
    texto:
      'Internet estável, celular ou computador com câmera, fone de ouvido e um lugar onde você possa falar à vontade.',
  },
} as const;

/* --------------------------------------------------------------------------- Quem vai te acompanhar */

export const sobre = {
  titulo: 'Quem vai te acompanhar',
  paragrafos: [
    'Olá, eu sou a Patricia. Muito prazer!',
    'Sou psicóloga clínica e atendo online adultos e idosos, acompanhando pessoas em diferentes lugares do Brasil e do mundo.',
    'Minha história começou em São Paulo. Crescer nessa cidade despertou meu interesse pela complexidade das experiências humanas e me levou à Psicologia. Durante a formação, participei de projetos sociais e grupos de estudo, que ampliaram meu entendimento da psicologia como espaço de cuidado e de aprendizado contínuo.',
    'Acredito numa abordagem científica e sensível, que considera o contexto de cada pessoa. Meu objetivo é oferecer um espaço de escuta e compreensão, para que você possa dar os próximos passos com mais confiança.',
  ],
  foto: {
    alt: 'Segundo retrato de Patricia Braz Domingues',
    legendaPlaceholder: 'Foto da Patricia',
  },
  formacao: {
    titulo: 'Formação',
    /* Do mais recente para o mais antigo. `andamento: true` faz o site
       escrever "em andamento" — nunca apresentar como concluído. */
    itens: [
      {
        periodo: '2025–2026',
        curso: 'Curso de Psicoterapia Baseada em Evidências',
        instituicao: 'InPBE — Instituto de Psicologia Baseada em Evidências',
        andamento: true,
        nota: null as string | null,
      },
      {
        periodo: '2025–2026',
        curso: 'Pós-graduação em Neuropsicologia',
        instituicao: 'Universidade Anhembi Morumbi',
        andamento: true,
        nota: null as string | null,
      },
      {
        periodo: '2023–2024',
        curso:
          'Especialização (pós-graduação lato sensu) em Saúde Mental e Desenvolvimento Humano',
        instituicao: 'PUCPR',
        andamento: false,
        nota: 'Trabalho final: “A saúde mental do idoso diante de suas perdas e luto: contribuições da psicoterapia de Aceitação e Compromisso”.',
      },
      {
        periodo: null as string | null,
        curso: 'Formação em Cuidado com Perdas e Luto',
        instituicao: '[[CONFIRMAR: instituição e período]]',
        andamento: false,
        nota: null as string | null,
      },
      {
        periodo: null as string | null,
        curso: 'Graduação em Psicologia',
        instituicao: '[[CONFIRMAR: instituição e período]]',
        andamento: false,
        nota: null as string | null,
      },
    ],
  },
  temas: {
    titulo: 'Temas com que trabalho',
    /* Não incluídos por falta de confirmação: TOC e transtorno bipolar. */
    itens: [
      'ansiedade',
      'depressão',
      'luto e perdas',
      'transições de vida',
      'relacionamentos',
      'autoestima e insegurança',
      'autoconhecimento',
      'regulação emocional',
      'burnout e estresse',
    ],
  },
} as const;

/* --------------------------------------------------------------------------- Como agendar */

export const agendar = {
  titulo: 'Como agendar',
  paragrafo:
    'Escolha o caminho mais prático para você. Se tiver dúvidas antes de marcar, pode me chamar no WhatsApp.',
  blocos: [
    {
      id: 'vittude',
      titulo: 'Pela Vittude',
      texto:
        'Veja os horários disponíveis e agende direto na plataforma. Pagamento por cartão ou boleto.',
      acao: 'vittude',
    },
    {
      id: 'whatsapp',
      titulo: 'Direto comigo',
      texto:
        'Me envie uma mensagem pelo WhatsApp para combinarmos horário, valor e forma de pagamento.',
      acao: 'whatsapp',
    },
  ],
  /* Orientação de privacidade recomendada pelo briefing (3.4). */
  nota: 'Por mensagem, não é preciso contar detalhes sobre a sua saúde. Conversamos sobre isso na sessão.',
  horarioResposta: 'Respondo mensagens [[CONFIRMAR: dias e horários]].',
} as const;

/* --------------------------------------------------------------------------- Dúvidas */

export const duvidas = {
  titulo: 'Dúvidas',
  itens: [
    {
      pergunta: 'A terapia online funciona?',
      resposta:
        'O atendimento psicológico online é reconhecido e regulamentado pelo Conselho Federal de Psicologia. Para muitas pessoas, ele funciona bem e facilita manter a regularidade. Na primeira sessão, avaliamos juntos se esse formato é adequado para o seu momento.',
    },
    {
      pergunta: 'Preciso estar em crise para começar?',
      resposta:
        'Não. Muita gente começa a terapia para se conhecer melhor, atravessar uma mudança ou cuidar de algo antes que fique mais pesado.',
    },
    {
      pergunta: 'O que eu falar fica em sigilo?',
      resposta:
        'Sim. O sigilo profissional é um dever previsto no Código de Ética do Psicólogo. Se quiser, conversamos sobre isso logo na primeira sessão.',
    },
    {
      pergunta: 'Quanto tempo dura a terapia?',
      resposta:
        'Depende do que você busca e de como o processo caminha. Não existe um número fixo de sessões; revisamos isso juntos ao longo do caminho.',
    },
    {
      pergunta: 'Você atende quem mora fora do Brasil?',
      resposta:
        'Sim. Acompanho brasileiros que vivem em outros países, por videochamada e em português.',
    },
    {
      pergunta: 'Qual a idade mínima?',
      resposta: 'Atendo adultos e idosos. [[CONFIRMAR: idade mínima atendida]]',
    },
    {
      pergunta: 'Você emite recibo para reembolso do plano de saúde?',
      resposta: '[[CONFIRMAR: emite recibo para reembolso?]]',
    },
    {
      pergunta: 'E se eu precisar de ajuda agora?',
      resposta:
        'Este site e as sessões agendadas não são um serviço de emergência. Se você está em risco ou pensando em se machucar, ligue 188 (CVV, gratuito, 24 horas) ou 192 (SAMU), ou procure o pronto-socorro mais próximo.',
    },
  ],
  fechamento: 'Ficou alguma dúvida?',
} as const;

/* --------------------------------------------------------------------------- Rodapé */

export const rodape = {
  resumo: 'Atendimento psicológico online para adultos e idosos.',
  tituloLinks: 'Links',
  emergencia: {
    titulo: 'Emergência',
    texto:
      'Este site não é um serviço de emergência. Em caso de emergência, ligue 188 (CVV) ou 192 (SAMU), ou procure o pronto-socorro mais próximo.',
  },
} as const;

/* --------------------------------------------------------------------------- Páginas extras */

export const privacidade = {
  titulo: 'Política de privacidade',
  atualizacao: '2026-09-21',
  blocos: [
    {
      titulo: 'Resumo',
      paragrafos: [
        'Este site é uma página informativa. Ele não coleta, não armazena e não compartilha dados pessoais de quem o visita.',
      ],
    },
    {
      titulo: 'Cookies e medição de audiência',
      paragrafos: [
        'O site não usa cookies, pixels de rastreamento nem ferramentas de análise de audiência. Por isso não existe banner de consentimento.',
      ],
    },
    {
      titulo: 'Formulários',
      paragrafos: [
        'Não há formulários no site. Nada do que você digitar em outro lugar chega até aqui.',
        'O contato acontece por links para serviços externos. Ao clicar, você sai deste site e passa a ser regido pela política de privacidade do serviço escolhido.',
      ],
    },
    {
      titulo: 'Links para serviços externos',
      paragrafos: [
        'Os links do site levam para a Vittude (agendamento e pagamento), o WhatsApp (mensagens), o Instagram, o perfil no Google e o Cadastro Nacional de Profissionais de Psicologia do CFP. Cada um desses serviços tem política de privacidade própria, que você pode consultar nos respectivos sites.',
        'Não há conteúdo incorporado de terceiros nesta página — nenhum mapa, vídeo ou publicação embutida —, justamente para que nenhum rastreador seja carregado sem que você escolha.',
      ],
    },
    {
      titulo: 'Dados de saúde',
      paragrafos: [
        'Dados sobre saúde são dados pessoais sensíveis segundo a Lei Geral de Proteção de Dados (Lei 13.709/2018). Por isso este site não pede nenhuma informação sobre a sua saúde.',
        'Ao enviar uma mensagem para agendar, não é preciso descrever sintomas, diagnósticos ou histórico. Essa conversa acontece na sessão, protegida pelo sigilo profissional previsto no Código de Ética do Psicólogo.',
      ],
    },
    {
      titulo: 'Informações tratadas no atendimento',
      paragrafos: [
        'As informações compartilhadas durante a psicoterapia são protegidas pelo sigilo profissional e tratadas conforme o Código de Ética Profissional do Psicólogo e a Resolução CFP nº 1/2009, sobre registro documental. Essa política se refere apenas ao site; as condições do atendimento são combinadas diretamente com a Patricia no início do processo.',
      ],
    },
    {
      titulo: 'Contato para questões de privacidade',
      paragrafos: [
        'Para falar sobre privacidade, escreva para [[CONFIRMAR: e-mail profissional]].',
      ],
    },
  ],
} as const;

export const naoEncontrada = {
  titulo: 'Esta página não existe ou mudou de endereço.',
  paragrafo:
    'Talvez o endereço tenha sido digitado com uma letra a mais, ou o link que te trouxe até aqui esteja desatualizado.',
  voltar: 'Voltar para o início',
} as const;

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
  instagram: 'https://www.instagram.com/psipatriciadomingues/' as string | null,
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
    'Psicóloga Patricia Braz Domingues, CRP 06/167326. Psicoterapia por videochamada para adultos e idosos, com TCC e ACT. Agende pela Vittude ou pelo WhatsApp.',
  idioma: 'pt-BR',
  og: '/og.jpg',
} as const;

/* --------------------------------------------------------------------------- Hero */

export const hero = {
  identificacao: 'Psicóloga Patricia Braz Domingues, CRP 06/167326',
  /* Alternativas para a Patricia escolher (trocar o valor de `titulo`):
     - 'Terapia por videochamada, com uma psicóloga que explica cada passo.'
     - 'Um horário só seu para cuidar do que não cabe no resto da semana.' */
  titulo: 'Cinquenta minutos para falar do que você vem adiando.',
  paragrafo:
    'Sou psicóloga e atendo adultos e idosos por videochamada. Trabalho com TCC e ACT, duas abordagens com pesquisa por trás. Você não precisa saber o que isso significa para começar: eu explico no caminho.',
  selo: {
    /* O ✦ separa as partes no texto que gira em volta do selo. */
    texto: 'Psicóloga ✦ CRP 06/167326 ✦ Atendimento online ✦',
    descricao: 'Psicóloga, CRP 06/167326, atendimento online',
  },
  foto: {
    alt: 'Patricia Braz Domingues sorrindo, sentada à mesa de trabalho, com uma planta ao fundo',
    /* Sem foto, o site mostra um espaço neutro em lilás com esta legenda.
       Nunca usar banco de imagens nem imagem gerada por IA. */
    legendaPlaceholder: 'Foto da Patricia',
  },
} as const;

/* --------------------------------------------------------------------------- O essencial */

export const essencial = [
  {
    icone: 'video-camera',
    titulo: 'Por videochamada',
    texto:
      'Você entra pelo celular ou pelo computador, de casa, do trabalho ou de outro país. Precisa de internet, fone de ouvido e uma porta fechada.',
  },
  {
    icone: 'users',
    titulo: 'Adultos e idosos',
    texto:
      'A partir dos 18 anos, sem idade máxima. Boa parte do que estudei foi sobre envelhecimento, luto e quem cuida de alguém da família.',
  },
  {
    icone: 'clock',
    titulo: 'Sessões de 50 minutos',
    texto:
      'A primeira serve para a gente se conhecer. Depois, combinamos a frequência: semanal é o mais comum, mas não é regra.',
  },
] as const;

/* --------------------------------------------------------------------------- Talvez você esteja aqui */

export const reconhecer = {
  titulo: 'O que costuma trazer alguém até aqui',
  itens: [
    'Você acorda cansado, e o dia nem começou.',
    'Perdeu alguém, e as pessoas em volta já acham que era hora de você estar bem.',
    'Aposentou, mudou de cidade, os filhos saíram de casa. A rotina mudou e você ainda não.',
    'Discute com quem ama por coisas pequenas e depois não sabe explicar por quê.',
    'Adia tudo, inclusive o que gosta de fazer.',
    'Nada está exatamente errado. Mesmo assim, você gostaria de se entender melhor.',
  ],
  fechamento:
    'Você não precisa se encaixar em nenhuma dessas frases para começar. Na primeira conversa eu pergunto o que te trouxe, e partimos daí.',
} as const;

/* --------------------------------------------------------------------------- O que acontece na terapia */

export const terapia = {
  titulo: 'O que acontece numa sessão',
  paragrafo:
    'Uma sessão é uma conversa com direção. Você fala, eu pergunto, e aos poucos montamos um mapa do que está acontecendo: em que situações o problema aparece, o que passa pela sua cabeça nessas horas, o que você faz em seguida. Às vezes você sai com uma tarefa para a semana, um exercício ou algo para observar. Na sessão seguinte, começamos por ela.',
  /* O que fica combinado desde o início. Quatro itens, lidos como pares
     "palavra: explicação". */
  combinados: {
    titulo: 'O que fica combinado desde o início',
    itens: [
      {
        termo: 'Sigilo',
        texto:
          'O que você diz na sessão não sai dela. Isso é um dever do meu código de ética, não um favor.',
      },
      {
        termo: 'Objetivos definidos a dois',
        texto:
          'Combinamos o que você quer que mude e, de tempos em tempos, revisamos se estamos no caminho.',
      },
      {
        termo: 'Sem prazo fechado',
        texto:
          'Nem eu nem você sabemos hoje quantas sessões vão ser. Isso se descobre no caminho, e você pode parar quando quiser.',
      },
      {
        termo: 'Nenhum conselho pronto',
        texto:
          'Eu não vou dizer o que você deve fazer da sua vida. O trabalho é você enxergar as opções com mais clareza, não eu escolher por você.',
      },
    ],
  },
  comoTrabalho: {
    titulo: 'Como eu trabalho',
    texto:
      'Trabalho com Terapia Cognitivo-Comportamental (TCC) e Terapia de Aceitação e Compromisso (ACT). Os nomes são compridos; a ideia não é. A TCC olha para a ligação entre o que você pensa, o que sente e o que faz, e testa se o jeito de pensar que virou hábito ainda serve. A ACT parte do princípio de que parte do desconforto não vai sumir, e pergunta o que você quer fazer da sua vida mesmo assim. Uso uma ou outra conforme o que aparece, e explico por quê.',
  },
  fraseFinal:
    'Não precisa estar no fundo do poço. Muita gente marca a primeira sessão só porque cansou de ter a mesma conversa com a própria cabeça.',
} as const;

/* --------------------------------------------------------------------------- Como funciona */

export const comoFunciona = {
  titulo: 'Como funciona',
  passos: [
    {
      titulo: 'Você escolhe por onde marcar.',
      texto:
        'Pela Vittude, com os horários abertos e o pagamento feito lá mesmo, ou por mensagem no WhatsApp, direto comigo.',
    },
    {
      titulo: 'Primeira sessão.',
      texto:
        'Cinquenta minutos para você contar o que te trouxe e eu entender se o meu trabalho serve para isso. Se eu achar que outro tipo de atendimento serve melhor, digo.',
    },
    {
      titulo: 'Combinamos o rumo.',
      texto:
        'A partir da segunda sessão definimos o que você quer que mude, com que frequência nos vemos e por onde começar.',
    },
    {
      titulo: 'Acompanhamento.',
      texto:
        'Sessões por videochamada, de preferência sempre no mesmo horário. De tempos em tempos paramos para revisar: o que mudou, o que falta, se faz sentido continuar.',
    },
  ],
  caixa: {
    titulo: 'Para a sessão',
    texto:
      'Internet que não caia, fone de ouvido e um lugar onde ninguém vai entrar nos próximos 50 minutos. Pode ser o carro estacionado, se for o único canto tranquilo da casa.',
  },
} as const;

/* --------------------------------------------------------------------------- Quem vai te acompanhar */

export const sobre = {
  titulo: 'Quem vai te acompanhar',
  paragrafos: [
    'Eu sou a Patricia. Nasci e cresci em São Paulo, e foi aqui que decidi estudar Psicologia: numa cidade desse tamanho, você cruza com milhares de pessoas por dia e não sabe nada sobre nenhuma delas. Eu queria saber.',
    'Na graduação passei por projetos sociais e grupos de estudo, e meu trabalho de conclusão foi sobre quem cuida de um familiar com Alzheimer. Esse assunto, o de quem cuida e o de quem perde, ficou comigo: a especialização que fiz depois tratou da saúde mental do idoso diante das perdas, e em seguida veio uma formação específica em luto.',
    'Hoje atendo só online, adultos e idosos, no Brasil e fora dele. Trabalho com TCC e ACT porque são abordagens com pesquisa por trás e porque dá para explicar o que estamos fazendo. E eu gosto de explicar.',
    'O que eu quero de uma sessão é simples de dizer: que você saia dela entendendo um pouco melhor o que estava embolado quando entrou.',
  ],
  foto: {
    alt: 'Patricia Braz Domingues sorrindo, sentada diante do notebook onde faz os atendimentos',
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
        andamento: false,
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
        periodo: '2022–2023' as string | null,
        curso: 'Formação em Perdas e Luto',
        instituicao: 'AC Bastos' as string | null,
        andamento: false,
        nota: 'Acolhimento, avaliação e intervenção.' as string | null,
      },
      {
        periodo: '2016–2020' as string | null,
        curso: 'Graduação em Psicologia',
        instituicao: 'Universidade Paulista (UNIP)' as string | null,
        nota: 'Trabalho final: “Breve revisão bibliográfica: as implicações psicológicas na vida dos cuidadores familiares de pacientes com a doença de Alzheimer”.' as string | null,
        andamento: false,
      },
    ],
  },
  temas: {
    titulo: 'Temas com que trabalho',
    /* Em minúsculas, menos as siglas. A lista é lida como uma frase corrida
       e quem capitaliza a primeira é o componente Sobre.astro, na hora de
       montar a frase — assim a ordem pode mudar sem deixar um "Ansiedade"
       perdido no meio dela. */
    itens: [
      'ansiedade',
      'TDAH',
      'autoestima e insegurança',
      'relacionamentos',
      'regulação emocional',
      'estresse e burnout',
      'questões profissionais',
      'luto e perdas',
      'transições de vida',
      'procrastinação e organização',
      'depressão',
      'TOC',
      'autoconhecimento',
    ],
  },
} as const;

/* --------------------------------------------------------------------------- Como agendar */

export const agendar = {
  titulo: 'Como agendar',
  paragrafo:
    'Dois caminhos, o mesmo atendimento. Pela Vittude você vê a agenda e paga na hora; pelo WhatsApp a gente combina direto. Se quiser tirar uma dúvida antes de marcar, pode chamar.',
  blocos: [
    {
      id: 'vittude',
      titulo: 'Pela Vittude',
      texto:
        'Os horários livres aparecem na plataforma, e o pagamento é feito lá mesmo, por cartão ou boleto.',
      acao: 'vittude',
    },
    {
      id: 'whatsapp',
      titulo: 'Direto comigo',
      texto:
        'Me mande uma mensagem dizendo que veio pelo site. Eu respondo com os horários que tenho, e combinamos valor e forma de pagamento: Pix, transferência ou cartão.',
      acao: 'whatsapp',
    },
  ],
  /* Orientação de privacidade recomendada pelo briefing (3.4). */
  nota: 'Não precisa explicar o motivo na mensagem. Isso fica para a sessão, onde há sigilo.',
  /* Sem horário de resposta divulgado, por escolha dela. Para informar,
     preencha a frase aqui e ela volta a aparecer abaixo dos dois blocos. */
  horarioResposta: null as string | null,
} as const;

/* --------------------------------------------------------------------------- Dúvidas */

export const duvidas = {
  titulo: 'Dúvidas',
  itens: [
    {
      pergunta: 'A terapia online funciona?',
      resposta:
        'O Conselho Federal de Psicologia regulamenta o atendimento online desde 2018, e hoje ele faz parte do dia a dia da profissão. O que muda em relação ao presencial é prático: você precisa de um lugar reservado e de uma conexão estável. O resto não muda: a conversa, o sigilo, o método. Se na primeira sessão eu perceber que o formato não está funcionando para você, eu falo.',
    },
    {
      pergunta: 'Preciso estar em crise para começar?',
      resposta:
        'Não. Crise é um dos motivos, não o único. Tem gente que começa porque uma decisão não sai do lugar, porque a rotina mudou, ou só porque quer se conhecer com mais honestidade.',
    },
    {
      pergunta: 'O que eu falar fica em sigilo?',
      resposta:
        'Sim. O que você diz na sessão fica entre nós, por dever ético e legal. As exceções são raras e previstas no Código de Ética, como um risco grave à sua vida ou à de outra pessoa. Se quiser, explico todas elas na primeira conversa.',
    },
    {
      pergunta: 'Quanto tempo dura a terapia?',
      resposta:
        'Não dá para saber antes de começar. Alguns processos são curtos e focados em um problema; outros duram mais. De tempos em tempos revisamos juntos se faz sentido continuar, e a decisão de parar é sempre sua.',
    },
    {
      pergunta: 'Você atende quem mora fora do Brasil?',
      resposta:
        'Sim. Atendo brasileiros que vivem em outros países, em português. Combinamos o horário já convertido para o seu fuso.',
    },
    {
      pergunta: 'Qual a idade mínima?',
      resposta: '18 anos. Não atendo crianças nem adolescentes.',
    },
    {
      pergunta: 'Você emite recibo para reembolso do plano de saúde?',
      resposta:
        'Sim. Emito recibo com o meu CRP para você pedir o reembolso. Quanto o plano devolve depende das regras do seu contrato, e vale conferir com ele antes.',
    },
    {
      pergunta: 'E se eu precisar de ajuda agora?',
      resposta:
        'Este site e as sessões marcadas não são um serviço de emergência. Se você está em risco agora ou pensando em se machucar, ligue 188 (CVV, gratuito, 24 horas) ou 192 (SAMU), ou vá ao pronto-socorro mais próximo.',
    },
  ],
  fechamento: 'Sobrou alguma pergunta?',
} as const;

/* --------------------------------------------------------------------------- Rodapé */

export const rodape = {
  resumo: 'Psicoterapia por videochamada para adultos e idosos.',
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
        'Para falar sobre privacidade, escreva para psi.patriciabraz@gmail.com.',
      ],
    },
  ],
} as const;

export const naoEncontrada = {
  titulo: 'Essa página não existe, ou mudou de lugar.',
  paragrafo:
    'Pode ser uma letra a mais no endereço, ou um link antigo. O site inteiro está na página inicial.',
  voltar: 'Voltar para o início',
} as const;

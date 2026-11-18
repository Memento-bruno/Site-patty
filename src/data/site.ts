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

/* --------------------------------------------------------------------------- Por onde podemos começar (temas) */

/* Cada tema vira um cartão; "Entenda melhor" abre uma janela com o texto
   completo. Para tirar um tema, apague o bloco dele; para mudar a ordem,
   mude a ordem dos blocos. O número do cartão é contado sozinho. */
export const temas = {
  rotulo: 'Por onde podemos começar',
  titulo: 'O que pode trazer você',
  tituloDestaque: 'à psicoterapia?',
  paragrafo:
    'Nem sempre sabemos dar nome ao que estamos sentindo. Algumas pessoas chegam com uma dificuldade específica; outras querem compreender melhor suas relações, escolhas e formas de viver. Conheça alguns dos temas que podemos trabalhar.',
  nota:
    'Os temas não substituem uma avaliação clínica individual. Cada acompanhamento considera suas necessidades, objetivos e condições de vida.',
  botaoMais: 'Entenda melhor',
  janela: {
    acontecendo: 'O que pode estar acontecendo',
    trabalho: 'O que podemos investigar e trabalhar',
    aviso: 'Sobre a avaliação:',
    rodape: 'Informação geral. O cuidado é planejado individualmente.',
    voltar: 'Voltar aos temas',
    fechar: 'Fechar explicação',
  },
  itens: [
    {
      chave: 'ansiedade',
      categoria: 'Emoções e bem-estar',
      titulo: 'Ansiedade e preocupação',
      resumo: 'Medos, preocupações constantes e tentativas de evitar o que provoca desconforto.',
      introducao: 'A ansiedade é uma experiência humana importante, mas pode se tornar desgastante quando a preocupação ocupa grande parte do dia ou o medo passa a organizar suas decisões.',
      acontecendo: [
        'Talvez você se perceba antecipando situações negativas, buscando garantias de que tudo vai ficar bem ou evitando conversas, lugares e decisões. Em algumas pessoas, a ansiedade aparece principalmente no corpo; em outras, nas preocupações repetitivas ou no medo de julgamento.',
        'Essas experiências têm formas e funções diferentes. Por isso, compreender quando a ansiedade surge e o que a mantém costuma ser mais útil do que tratá-la como uma coisa só.'
      ],
      trabalho: [
        'Podemos investigar a relação entre interpretações, atenção, sensações físicas e comportamentos. Conforme cada caso, o trabalho pode envolver reavaliação de pensamentos, experimentos comportamentais, exposição planejada a situações temidas e redução de estratégias de evitação.',
        'Também podemos desenvolver maneiras de responder às emoções difíceis sem que elas precisem determinar todas as suas escolhas.'
      ],
      frase: 'O objetivo não é nunca mais sentir ansiedade, mas diminuir o espaço que ela ocupa nas suas decisões.'
    },
    {
      chave: 'depressao',
      categoria: 'Emoções e bem-estar',
      titulo: 'Depressão e desânimo',
      resumo: 'Tristeza persistente, perda de interesse e dificuldade para retomar atividades e vínculos.',
      introducao: 'Quando atividades antes significativas perdem o sentido, a energia diminui ou até tarefas simples parecem exigir demais, é importante compreender o que está acontecendo.',
      acontecendo: [
        'Desânimo, alterações do sono, isolamento, autocrítica, dificuldade de concentração e redução do interesse podem ocorrer de maneiras diferentes. Nem toda tristeza é depressão; e uma avaliação cuidadosa considera duração, intensidade, contexto e impacto na vida.',
        'Às vezes, quanto menos a pessoa consegue se envolver em atividades e relações importantes, menos oportunidades encontra para experimentar interesse, prazer ou realização, mantendo um ciclo difícil de interromper.'
      ],
      trabalho: [
        'Na psicoterapia, podemos identificar padrões de pensamento e comportamento que contribuem para o sofrimento, explorar circunstâncias que o agravam e planejar passos graduais de retomada. A ativação comportamental é uma possibilidade: não significa apenas ocupar a agenda, mas reconstruir contato com atividades, pessoas e experiências relevantes para você.',
        'Quando necessário, o cuidado pode envolver articulação com avaliação ou acompanhamento médico.'
      ],
      frase: 'Às vezes, a motivação não vem antes do primeiro passo; ela pode ser reconstruída ao longo do caminho.'
    },
    {
      chave: 'estresse',
      categoria: 'Emoções e bem-estar',
      titulo: 'Estresse e esgotamento',
      resumo: 'Sobrecarga, exigências prolongadas e dificuldade de encontrar tempo para descansar.',
      introducao: 'Nem todo estresse indica um problema psicológico. Mas, quando as exigências superam por muito tempo os recursos disponíveis, a saúde e a qualidade de vida podem ser afetadas.',
      acontecendo: [
        'Cansaço persistente, irritabilidade, dificuldade para se desligar do trabalho, sensação de urgência constante e alterações no sono podem indicar que algo precisa de atenção. Em alguns casos, a sobrecarga está relacionada ao trabalho; em outros, à combinação entre responsabilidades profissionais, familiares e pessoais.',
        'O contexto importa: nem toda dificuldade se resolve apenas mudando pensamentos ou aprendendo a relaxar.'
      ],
      trabalho: [
        'Podemos mapear fontes de estresse, identificar padrões de autocobrança, analisar limites e possibilidades reais de mudança e desenvolver habilidades de resolução de problemas, comunicação e organização. Técnicas de atenção ao momento presente e estratégias de recuperação podem ser úteis, quando adequadas.',
        'O trabalho também considera condições externas sobre as quais você talvez tenha controle limitado e como lidar com elas sem transformar toda a responsabilidade em algo individual.'
      ],
      frase: 'Cuidar de si nem sempre significa suportar melhor tudo o que exige demais.'
    },
    {
      chave: 'regulacao',
      categoria: 'Emoções e bem-estar',
      titulo: 'Regulação emocional',
      resumo: 'Emoções intensas, reações impulsivas e dificuldade para atravessar momentos difíceis.',
      introducao: 'Sentir intensamente não é, por si só, um problema. A dificuldade pode surgir quando as emoções parecem tomar conta das decisões, das relações ou da maneira como você trata a si mesmo.',
      acontecendo: [
        'Algumas pessoas se arrependem de reações impulsivas. Outras tentam evitar qualquer desconforto ou sentem que uma emoção demora muito para diminuir. As respostas emocionais são influenciadas pela história pessoal, pelos vínculos, pelo corpo e pelo contexto.'
      ],
      trabalho: [
        'A psicoterapia pode ajudar a identificar sinais emocionais, reconhecer situações de vulnerabilidade e compreender o que acontece antes e depois de determinadas reações. Podemos utilizar habilidades de atenção plena, regulação emocional, tolerância ao mal-estar e comunicação interpessoal, conforme a necessidade.',
        'Isso inclui recursos presentes na TCC e nas terapias contextuais, inclusive habilidades derivadas da DBT, sem pressupor que todo acompanhamento corresponda ao programa completo de DBT.'
      ],
      frase: 'Aprender a lidar com uma emoção não exige deixar de senti-la.'
    },
    {
      chave: 'relacionamentos',
      categoria: 'Relações e desenvolvimento',
      titulo: 'Relacionamentos e autoestima',
      resumo: 'Conflitos, dificuldade para estabelecer limites, insegurança e autocrítica.',
      introducao: 'Os relacionamentos podem oferecer conexão e apoio, mas também despertar insegurança, conflitos e padrões difíceis de compreender.',
      acontecendo: [
        'Medo de desagradar, necessidade constante de aprovação, dificuldade para expressar o que sente, conflitos repetidos e autocrítica intensa são algumas questões possíveis. Experiências anteriores influenciam o que esperamos dos outros, mas não determinam sozinhas todas as relações que construímos.'
      ],
      trabalho: [
        'Podemos investigar interpretações sobre si e sobre as pessoas, padrões de comunicação, expectativas e maneiras de responder ao medo de rejeição. O trabalho pode incluir habilidades de assertividade, definição de limites, manejo de conflitos e uma relação mais flexível com a autocrítica.',
        'Na psicoterapia individual, olhamos para como você participa e é afetado pelas relações — sem atribuir a você toda a responsabilidade pelo que acontece entre duas pessoas.'
      ],
      frase: 'Relações não dependem apenas de encontrar alguém compatível, mas também do que acontece entre as pessoas.'
    },
    {
      chave: 'sono',
      categoria: 'Emoções e bem-estar',
      titulo: 'Sono e insônia',
      resumo: 'Dificuldade para adormecer, despertares noturnos e preocupação com o próprio sono.',
      introducao: 'Dormir mal não afeta apenas a noite. Pode influenciar o humor, a atenção, a disposição e a forma como atravessamos o dia.',
      acontecendo: [
        'Algumas pessoas demoram a pegar no sono; outras acordam muitas vezes, despertam cedo demais ou desenvolvem receio de não conseguir dormir. Hábitos, condições de saúde, horários de trabalho, ansiedade e a própria tentativa de controlar o sono podem estar envolvidos.',
        'É importante distinguir uma dificuldade pontual de um quadro persistente e considerar a necessidade de avaliação médica para outras causas.'
      ],
      trabalho: [
        'Podemos compreender a relação entre rotina, comportamentos, pensamentos e sono, e trabalhar mudanças compatíveis com sua realidade. Estratégias comportamentais, organização de horários e manejo da preocupação com o dormir podem fazer parte do processo, conforme a avaliação.',
        'Em casos de insônia crônica, a TCC específica para insônia (TCC-I) é uma referência de tratamento, e o encaminhamento para avaliação especializada pode ser indicado quando necessário.'
      ],
      frase: 'Dormir melhor não é simplesmente se esforçar mais para dormir.'
    },
    {
      chave: 'autoconhecimento',
      categoria: 'Relações e desenvolvimento',
      titulo: 'Autoconhecimento e desenvolvimento pessoal',
      resumo: 'Compreender padrões, escolhas, valores e novas possibilidades de ação.',
      introducao: 'Você não precisa estar em crise ou ter um diagnóstico para procurar psicoterapia. O desejo de compreender melhor a própria experiência também pode ser um ponto de partida.',
      acontecendo: [
        'Talvez você queira entender por que determinadas situações provocam reações tão intensas, por que certos padrões se repetem ou o que realmente importa nas decisões que precisa tomar. Conhecer a si mesmo não significa encontrar uma identidade fixa e definitiva.'
      ],
      trabalho: [
        'Podemos explorar experiências do cotidiano, identificar interpretações e comportamentos recorrentes, reconhecer valores pessoais e experimentar formas diferentes de agir. O processo combina reflexão com observação e prática, conforme seus objetivos.',
        'A ideia não é dizer quem você deveria ser, mas ampliar a compreensão que você tem de si e as possibilidades de escolha na sua vida.'
      ],
      frase: 'Autoconhecimento também é descobrir que alguns caminhos podem ser construídos de outra forma.'
    },
    {
      chave: 'autocobranca',
      categoria: 'Relações e desenvolvimento',
      titulo: 'Autocobrança e procrastinação',
      resumo: 'Perfeccionismo, medo de errar, adiamentos e dificuldade de começar ou concluir tarefas.',
      introducao: 'Às vezes, quanto mais importante uma tarefa parece, mais difícil é começar. E quanto maior a cobrança, menos espaço sobra para experimentar e aprender.',
      acontecendo: [
        'Adiar decisões, revisar algo inúmeras vezes, sentir culpa ao descansar ou acreditar que só vale tentar quando há garantia de acerto podem fazer parte de ciclos de autocobrança e evitação. Em outros casos, dificuldades de planejamento e organização também precisam ser consideradas.'
      ],
      trabalho: [
        'Podemos compreender a função dos adiamentos, flexibilizar padrões rígidos de exigência, desenvolver estratégias de organização e testar passos menores e realistas. A atenção não fica apenas na força de vontade, mas também nas condições que tornam uma ação mais ou menos possível.',
        'Nem toda procrastinação é preguiça ou TDAH: investigar o contexto e o funcionamento individual é parte do trabalho.'
      ],
      frase: 'Mudanças sustentáveis costumam depender menos de cobrança e mais de condições possíveis para agir.'
    },
    {
      chave: 'tdah',
      categoria: 'Neurodesenvolvimento',
      titulo: 'TDAH em adultos',
      resumo: 'Atenção, planejamento, organização, impulsividade e regulação emocional no cotidiano.',
      introducao: 'Para algumas pessoas, manter a atenção, organizar tarefas, administrar o tempo e concluir projetos exige esforço constante — inclusive quando existe interesse e vontade de fazer.',
      acontecendo: [
        'O TDAH pode envolver dificuldades de atenção, controle de impulsos e funções executivas, mas experiências semelhantes também podem surgir por outros motivos. Por isso, uma compreensão clínica cuidadosa é importante para não transformar qualquer distração em diagnóstico.'
      ],
      trabalho: [
        'O acompanhamento psicoterapêutico pode envolver estratégias de organização, planejamento, uso de lembretes externos, divisão de tarefas em etapas, autorregulação emocional e manejo de padrões que dificultam o cotidiano. Essas intervenções são adaptadas às necessidades individuais.',
        'Se houver suspeita de TDAH, uma triagem clínica pode ajudar a identificar a necessidade de encaminhamento para avaliação especializada.'
      ],
      frase: 'Organização não precisa depender apenas de lembrar, resistir ou se cobrar mais.',
      aviso: 'Não realizo avaliação neuropsicológica. Ofereço triagem clínica para encaminhamento, quando indicado, e acompanhamento psicoterapêutico.'
    },
    {
      chave: 'autismo',
      categoria: 'Neurodesenvolvimento',
      titulo: 'Autismo em adultos',
      resumo: 'Compreender o próprio jeito de perceber, sentir e se relacionar, com ou sem diagnóstico.',
      introducao: 'Muitas pessoas só começam a se perguntar sobre autismo na vida adulta, às vezes depois de anos sentindo que precisam se esforçar mais do que os outros para dar conta de situações sociais, mudanças ou ambientes cheios de estímulos.',
      acontecendo: [
        'O autismo é uma condição do neurodesenvolvimento que se manifesta de formas muito diferentes de pessoa para pessoa. Em adultos, pode aparecer em diferenças na comunicação e na interação social, na sensibilidade a sons, luzes ou texturas, na preferência por rotinas previsíveis e em interesses intensos.',
        'Algumas pessoas passaram a vida tentando disfarçar essas características para se adaptar, o que pode trazer cansaço, ansiedade e a sensação de não pertencer. Nem toda dificuldade social é autismo; por isso, uma compreensão clínica cuidadosa é importante.'
      ],
      trabalho: [
        'A psicoterapia não tem como objetivo mudar quem você é. Podemos compreender o que torna certas situações mais difíceis, identificar sinais de sobrecarga, organizar a rotina e o ambiente de forma mais compatível com suas necessidades e desenvolver maneiras de comunicar limites e preferências.',
        'Também podemos olhar para a ansiedade, a autocrítica ou o esgotamento que às vezes acompanham anos de adaptação, respeitando seu ritmo e sua forma de funcionar.'
      ],
      frase: 'Entender o próprio funcionamento pode abrir espaço para viver de um jeito menos custoso.',
      aviso: 'Não realizo avaliação neuropsicológica. Quando há dúvida sobre o diagnóstico, posso orientar o encaminhamento para avaliação com profissionais habilitados.'
    },
    {
      chave: 'mudancas',
      categoria: 'Transições e experiências',
      titulo: 'Mudanças de vida, luto e crises',
      resumo: 'Perdas, separações, transições de carreira e períodos em que a vida precisa se reorganizar.',
      introducao: 'Algumas mudanças são escolhidas; outras chegam sem aviso. Mesmo transições desejadas podem trazer perdas, insegurança e sentimentos difíceis de organizar.',
      acontecendo: [
        'O luto não acontece apenas diante de uma morte. Pode estar relacionado à perda de vínculos, de planos ou de uma forma de viver. Separações, adoecimento, mudanças de cidade, transições profissionais e outras crises também podem exigir adaptações importantes.',
        'Não existe uma única maneira ou um prazo universal para viver essas experiências.'
      ],
      trabalho: [
        'Na psicoterapia, podemos acolher o sofrimento, compreender o que mudou e explorar recursos para lidar com as exigências do presente. Isso pode envolver trabalhar emoções, pensamentos, vínculos, decisões e possibilidades de reconstrução do cotidiano, sem apressar a elaboração de perdas.'
      ],
      frase: 'A terapia não precisa apagar o que aconteceu para ajudar a construir uma relação diferente com essa história.'
    },
    {
      chave: 'intercultural',
      categoria: 'Transições e experiências',
      titulo: 'Psicoterapia intercultural',
      resumo: 'Imigração, expatriação, pertencimento e vida entre diferentes referências culturais.',
      introducao: 'Viver em outro país pode ampliar possibilidades, mas também transformar vínculos, referências, rotina e o modo como você se percebe.',
      acontecendo: [
        'Distância de pessoas importantes, diferenças culturais, barreiras linguísticas, solidão, pertencimento, experiências de discriminação e mudanças na identidade profissional podem atravessar a imigração e a expatriação. Essas experiências não são, por si mesmas, transtornos psicológicos.',
        'A transição de carreira pode ganhar contornos específicos quando ocorre junto à adaptação a outra cultura, mas também pode ser trabalhada independentemente de uma mudança de país.'
      ],
      trabalho: [
        'Podemos explorar como a experiência intercultural se relaciona com suas emoções, vínculos e escolhas, e desenvolver recursos para lidar com mudanças e conflitos entre expectativas pessoais, familiares e culturais. O trabalho considera sua história, sua realidade e o que tem valor para você.'
      ],
      frase: 'Mudar de país não significa deixar de ser quem você é — mas pode abrir perguntas novas sobre pertencimento e identidade.'
    }
  ],
} as const;

/* --------------------------------------------------------------------------- O que acontece na terapia */

export const terapia = {
  titulo: 'O que acontece na terapia',
  rotulo: 'Um passo de cada vez',
  /* As cinco paradas do percurso, na ordem. O ícone de cada uma é um
     arquivo de src/icons/ (sem o .svg). */
  paradas: {
    oQueE: { icone: 'chats-circle', titulo: 'Um trabalho feito a dois' },
    contexto: { icone: 'user-focus', titulo: 'Você, por inteiro' },
    eNaoE: { icone: 'signpost', titulo: 'O que a terapia é, e o que não é' },
    metodo: { icone: 'arrows-clockwise', titulo: 'Como eu trabalho' },
    final: { icone: 'plant', titulo: 'Quando procurar' },
  },
  paragrafo:
    'Psicoterapia é um trabalho feito a dois, com uma profissional habilitada, sigilo e método. É um espaço para olhar com cuidado para o que você pensa, sente e faz, e para o que é importante na sua vida.',
  contexto: {
    paragrafos: [
      'O sofrimento psicológico não acontece isoladamente. É atravessado por experiências, vínculos, saúde, trabalho, recursos disponíveis e pelo momento que cada pessoa está vivendo.',
      'Na terapia, buscamos compreender o que está acontecendo sem reduzir você a um diagnóstico, a uma lembrança ou a um único comportamento.',
    ],
    centro: 'você',
    instrucao: 'Toque em cada parte para ver como ela entra na conversa.',
    fatores: [
      { icone: 'book-open', nome: 'Experiências', texto: 'O que você já viveu e aprendeu ajuda a entender como você reage hoje.' },
      { icone: 'users-three', nome: 'Vínculos', texto: 'As pessoas com quem você convive influenciam o que você sente, e são influenciadas também.' },
      { icone: 'first-aid', nome: 'Saúde', texto: 'Sono, corpo, doenças e tratamentos em curso também entram na conversa.' },
      { icone: 'briefcase', nome: 'Trabalho', texto: 'A rotina, as exigências e a falta ou o excesso de trabalho pesam no dia a dia.' },
      { icone: 'toolbox', nome: 'Recursos', texto: 'Tempo, dinheiro, apoio e acesso a cuidados mudam o que é possível em cada momento.' },
      { icone: 'hourglass-medium', nome: 'Momento de vida', texto: 'Cada fase traz perguntas próprias, e o que faz sentido agora pode mudar depois.' },
    ],
  },
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
    texto:
      'Meu trabalho é orientado pela Terapia Cognitivo-Comportamental (TCC) e pela Terapia de Aceitação e Compromisso (ACT). Na prática, olhamos juntos para a relação entre pensamentos, emoções e comportamentos, buscamos formas mais flexíveis de lidar com o que é difícil e aproximamos suas escolhas daquilo que tem valor para você.',
    ciclo: [
      { icone: 'brain', nome: 'Pensamentos' },
      { icone: 'heart', nome: 'Emoções' },
      { icone: 'footprints', nome: 'Comportamentos' },
    ],
  },
  fraseFinal:
    'Procurar terapia não é sinal de fraqueza e não precisa esperar o limite. É uma forma de cuidar da saúde, como qualquer outra.',
} as const;

/* --------------------------------------------------------------------------- Como funciona */

export const comoFunciona = {
  titulo: 'Como funciona',
  passos: [
    {
      icone: 'calendar-blank',
      titulo: 'Você escolhe como agendar.',
      texto:
        'Pela Vittude, com agenda e pagamento na própria plataforma, ou diretamente comigo pelo WhatsApp.',
    },
    {
      icone: 'hand-waving',
      titulo: 'Primeira sessão.',
      texto:
        'Nos conhecemos, você conta o que te trouxe e avaliamos juntos se o atendimento online é adequado para o seu momento.',
    },
    {
      icone: 'path',
      titulo: 'Combinamos o caminho.',
      texto: 'Definimos objetivos, frequência e por onde começar.',
    },
    {
      icone: 'video-camera',
      titulo: 'Acompanhamento.',
      texto:
        'Sessões de 50 minutos por videochamada. Ao longo do processo, revisamos juntos como você está.',
    },
  ],
  caixa: {
    titulo: 'Para a sessão',
    instrucao: 'Antes do horário, confira o que vai precisar. Toque em cada item.',
    itens: [
      { icone: 'wifi-high', texto: 'Internet estável' },
      { icone: 'laptop', texto: 'Celular ou computador com câmera' },
      { icone: 'headphones', texto: 'Fone de ouvido' },
      { icone: 'armchair', texto: 'Um lugar onde você possa falar à vontade' },
    ],
    pronto: 'Tudo certo para a sessão.',
  },
} as const;

/* --------------------------------------------------------------------------- Quem vai te acompanhar */

export const sobre = {
  titulo: 'Quem vai te acompanhar',
  paragrafos: [
    'Sou Patricia Braz Domingues, psicóloga clínica (CRP 06/167326). Atendo adultos e idosos de forma online, incluindo brasileiros que vivem em outros países.',
    'Minha prática busca unir o conhecimento científico à compreensão da experiência singular de cada pessoa. Isso significa olhar para o sofrimento com atenção à história, às relações e às condições concretas de vida, sem perder de vista o que pode ser construído daqui para frente.',
    'Entendo a psicoterapia como um trabalho colaborativo, em que acolhimento, reflexão e intervenções práticas se complementam.',
  ],
  foto: {
    alt: 'Patricia Braz Domingues sorrindo, sentada diante do notebook onde faz os atendimentos',
    legendaPlaceholder: 'Foto da Patricia',
  },
  formacao: {
    titulo: 'Formação',
    rotuloRolagem: 'Deslize para o lado para ver todas.',
    /* Do mais recente para o mais antigo. `andamento: true` faz o site
       escrever "em andamento" — nunca apresentar como concluído. */
    itens: [
      {
        periodo: '2025–2026',
        curso: 'Curso de Psicoterapia Baseada em Evidências',
        icone: 'book-open',
        instituicao: 'InPBE — Instituto de Psicologia Baseada em Evidências',
        andamento: false,
        nota: null as string | null,
      },
      {
        periodo: '2025–2026',
        curso: 'Pós-graduação em Neuropsicologia',
        icone: 'brain',
        instituicao: 'Universidade Anhembi Morumbi',
        andamento: true,
        nota: null as string | null,
      },
      {
        periodo: '2023–2024',
        curso:
          'Especialização (pós-graduação lato sensu) em Saúde Mental e Desenvolvimento Humano',
        icone: 'certificate',
        instituicao: 'PUCPR',
        andamento: false,
        nota: 'Trabalho final: “A saúde mental do idoso diante de suas perdas e luto: contribuições da psicoterapia de Aceitação e Compromisso”.',
      },
      {
        periodo: '2022–2023' as string | null,
        curso: 'Formação em Perdas e Luto',
        icone: 'hand-heart',
        instituicao: 'AC Bastos' as string | null,
        andamento: false,
        nota: 'Acolhimento, avaliação e intervenção.' as string | null,
      },
      {
        periodo: '2016–2020' as string | null,
        curso: 'Graduação em Psicologia',
        icone: 'graduation-cap',
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
        'Me envie uma mensagem pelo WhatsApp para combinarmos horário e valor. O pagamento pode ser por Pix, transferência ou cartão.',
      acao: 'whatsapp',
    },
  ],
  /* Orientação de privacidade recomendada pelo briefing (3.4). */
  nota: 'Por mensagem, não é preciso contar detalhes sobre a sua saúde. Conversamos sobre isso na sessão.',
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
      resposta:
        'Atendo adultos e idosos, a partir de 18 anos.',
    },
    {
      pergunta: 'Você emite recibo para reembolso do plano de saúde?',
      resposta:
        'Sim. Emito recibo das sessões para você solicitar reembolso ao seu plano de saúde. Quanto o plano devolve depende das regras de cada contrato.',
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
        'Para falar sobre privacidade, escreva para psi.patriciabraz@gmail.com.',
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

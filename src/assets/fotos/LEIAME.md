# Fotos da Patricia

Salve aqui as fotos, com estes nomes exatos:

- **`hero.jpg`** — retrato vertical na proporção 4:5, no mínimo 1200 × 1500 px.
  Fundo calmo e espaço acima da cabeça: a foto é recortada em arco.
- **`sobre.jpg`** — um segundo retrato, diferente do primeiro.

Também funciona com `.png`, `.webp` ou `.avif`. O que importa é o nome do
arquivo (`hero` e `sobre`).

Assim que o arquivo estiver aqui, rode `npm run build`: o site troca o espaço
liso em lilás pela foto sozinho, já convertida para AVIF e WebP e gerada em
vários tamanhos. Depois rode `npm run og` para atualizar a imagem que aparece
quando alguém compartilha o link.

A descrição da foto para quem usa leitor de tela fica em `src/data/site.ts`,
nos campos `hero.foto.alt` e `sobre.foto.alt`.

**Não use** foto de banco de imagens nem imagem gerada por inteligência
artificial. Além de soar artificial, pode dar a entender que são pacientes
reais — o que o Código de Ética não permite.

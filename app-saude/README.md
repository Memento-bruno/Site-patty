# Minha Saúde — app Android do Seu Isildo

Aplicativo simples, de letras grandes, para o Seu Isildo acompanhar remédios, alimentação,
exames e consultas. Todo o conteúdo vem de dois documentos:

- Histórico de Saúde — Isildo Braz Domingues (atualizado)
- Plano Alimentar Individualizado (versão 22/09/2026)

Onde os documentos dizem "a confirmar", o app também diz. Nada foi inventado.

## Seções

| Seção | O que tem |
|---|---|
| Início | Próximo remédio com botão "Já tomei", contador de água, atalhos grandes |
| Remédios de hoje | Linha do tempo do dia (manhã/tarde/noite), remédios e refeições, marcar o que tomou |
| Meus remédios | Para que serve, como tomar, quem receitou; remédios que **não** está tomando |
| Alimentação | Cardápio da semana, método do prato, pode/moderar/evitar, ideias, trocas, doces, mercado e rótulo, metas, cuidados |
| Exames | O que falta fazer, resultados de 29/08/2026 por grupo, exames anteriores |
| Médicos e consultas | Equipe atual e histórico de consultas (2022 a 2026) |
| Minha saúde | Cada condição explicada de forma simples e o que ajuda |
| O que falta fazer | Lista de próximos passos para marcar |
| Urgência e ficha médica | Botão SAMU 192, sinais de alerta e ficha para mostrar ao médico |
| Ajustes | Tamanho da letra (3 níveis) e lembretes de remédio |

Os lembretes avisam no horário de cada remédio, com botão "JÁ TOMEI" na notificação.
A vitamina B12 sai da lista sozinha depois dos 90 dias (30/11/2026).
As marcações ficam só no celular.

## Como gerar o APK

Precisa do Android SDK (ou abra a pasta no Android Studio).

```sh
./gradlew assembleRelease     # app/build/outputs/apk/release/app-release.apk
./gradlew testDebugUnitTest   # testes
./gradlew testDebugUnitTest -Proborazzi.test.record=true   # capturas em capturas/
```

O APK de release é assinado com a chave de depuração, para instalar direto no celular
(ative "instalar apps de fontes desconhecidas"). Para publicar na Play Store, use uma chave própria.

## Para atualizar o conteúdo

Tudo fica em `app/src/main/java/br/com/isildo/saude/data/`:
`Dados.kt` (remédios, horários, condições, exames, consultas, próximos passos) e
`Alimentacao.kt` (plano alimentar).

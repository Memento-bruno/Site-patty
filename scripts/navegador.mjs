/* Onde está o Chromium desta máquina.

   O container traz o navegador pré-instalado em /opt/pw-browsers, mas a
   versão (e até o nome da pasta: chrome-linux, chrome-linux64) muda quando a
   imagem é atualizada. Fixar o caminho quebra sem aviso, então aqui ele é
   procurado. Se nada for encontrado, devolve {} e o Playwright usa o
   navegador que ele mesmo tiver baixado. */
import { existsSync, readdirSync } from 'node:fs';
import { join } from 'node:path';

const RAIZ = process.env.PLAYWRIGHT_BROWSERS_PATH || '/opt/pw-browsers';

function procurar() {
  if (process.env.CHROMIUM_PATH) return process.env.CHROMIUM_PATH;
  if (!existsSync(RAIZ)) return null;

  const pastas = readdirSync(RAIZ)
    .filter((nome) => nome.startsWith('chromium-'))
    /* mais recente primeiro, comparando o número da build */
    .sort((a, b) => Number(b.split('-')[1]) - Number(a.split('-')[1]));

  for (const pasta of pastas) {
    for (const sub of ['chrome-linux64', 'chrome-linux']) {
      const caminho = join(RAIZ, pasta, sub, 'chrome');
      if (existsSync(caminho)) return caminho;
    }
  }
  return null;
}

const executavel = procurar();

export const opcoesNavegador = executavel ? { executablePath: executavel } : {};
export const caminhoChromium = executavel;

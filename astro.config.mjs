import { defineConfig } from 'astro/config';
import sitemap from '@astrojs/sitemap';

/* ---------------------------------------------------------------------------
   Onde o site fica publicado.

   Na publicação pelo GitHub Actions estes dois valores vêm prontos da própria
   configuração do GitHub Pages (ver .github/workflows/deploy.yml), então
   continuam certos mesmo se o repositório for renomeado ou passar a usar
   domínio próprio.

   Os valores abaixo são só o padrão de quem roda na própria máquina.
   --------------------------------------------------------------------------- */
const site = process.env.SITE_URL || 'https://memento-bruno.github.io';
const base = process.env.BASE_PATH || '/Site-patty';

export default defineConfig({
  site,
  base,
  output: 'static',
  trailingSlash: 'ignore',
  integrations: [sitemap()],
  build: { inlineStylesheets: 'auto' },
  image: { responsiveStyles: true },
  devToolbar: { enabled: false },
});

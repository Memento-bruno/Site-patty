import { defineConfig } from 'astro/config';
import sitemap from '@astrojs/sitemap';

/* ---------------------------------------------------------------------------
   Onde o site fica publicado.

   Hoje (GitHub Pages, repositório site-patricia-domingues):
     site: 'https://memento-bruno.github.io'   base: '/site-patricia-domingues'

   Quando houver domínio próprio (ex.: patriciadomingues.com.br), troque para:
     site: 'https://patriciadomingues.com.br'  base: '/'
   e crie o arquivo public/CNAME com o domínio dentro. O README explica.
   --------------------------------------------------------------------------- */
const site = process.env.SITE_URL || 'https://memento-bruno.github.io';
const base = process.env.BASE_PATH || '/site-patricia-domingues';

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

import { defineConfig } from 'astro/config';
import sitemap from '@astrojs/sitemap';

/* ---------------------------------------------------------------------------
   Onde o site fica publicado.

   Na publicação pelo GitHub Actions estes dois valores vêm prontos da própria
   configuração do GitHub Pages (ver .github/workflows/deploy.yml), então
   continuam certos mesmo se o repositório for renomeado ou passar a usar
   domínio próprio.

   Os valores abaixo são só o padrão de quem roda na própria máquina.

   O GitHub entrega o endereço com "http://" enquanto o certificado do domínio
   próprio não fica pronto; o site sempre se anuncia com "https://" para o
   link canônico e a imagem de compartilhamento não saírem errados.
   --------------------------------------------------------------------------- */
const site = (process.env.SITE_URL || 'https://patriciadomingues.com.br').replace(
  /^http:\/\//,
  'https://',
);
const base = process.env.BASE_PATH || '/';

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

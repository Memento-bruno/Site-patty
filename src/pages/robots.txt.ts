import type { APIRoute } from 'astro';
import { url } from '../lib/url';

/* Gerado no build para o endereço do sitemap seguir o domínio configurado
   em astro.config.mjs — inclusive quando houver domínio próprio. */
export const GET: APIRoute = ({ site }) => {
  const sitemap = new URL(url('/sitemap-index.xml'), site).href;

  return new Response(
    ['User-agent: *', 'Allow: /', '', `Sitemap: ${sitemap}`, ''].join('\n'),
    { headers: { 'Content-Type': 'text/plain; charset=utf-8' } },
  );
};

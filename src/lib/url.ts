/* Monta um endereço interno respeitando o `base` do astro.config.mjs.
   Use sempre que precisar apontar para outra página do site. */
export function url(caminho = '/'): string {
  const raiz = import.meta.env.BASE_URL.replace(/\/+$/, '');
  const resto = caminho.replace(/^\/+/, '');
  return resto ? `${raiz}/${resto}` : `${raiz}/`;
}

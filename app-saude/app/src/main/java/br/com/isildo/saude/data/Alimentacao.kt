package br.com.isildo.saude.data

import java.time.DayOfWeek

/* Conteúdo do "Plano Alimentar Individualizado", versão 22/09/2026. */

data class Meta(val nome: String, val valor: String, val comoFazer: String)

data class Porcao(val alimento: String, val quanto: String)

data class Troca(val seTem: String, val trocaPor: String, val dica: String)

data class Doce(val tipo: String, val porcao: String, val frequencia: String, val oQueE: String)

data class DiaCardapio(
    val dia: DayOfWeek,
    val nome: String,
    val cafe: String,
    val almoco: String,
    val lanche: String,
    val jantar: String,
)

data class ItemCompra(val categoria: String, val procure: String, val evite: String)

data class Aviso(val titulo: String, val texto: String)

object Alimentacao {

    val metas = listOf(
        Meta("Água", "1,8 a 2 litros por dia", "Cerca de 8 copos de 250 ml. Água é a bebida principal."),
        Meta("Proteína", "70 a 85 g por dia", "Uma fonte de proteína no café, no almoço e no jantar."),
        Meta("Fibras", "25 a 30 g por dia ou mais", "Verduras, feijão, lentilha, frutas inteiras, aveia, integrais, chia e linhaça."),
        Meta("Sal (sódio)", "Menos de 2.300 mg por dia", "Comida fresca. Menos embutidos, caldo em cubo, miojo, shoyu e temperos prontos."),
        Meta("Carboidratos", "Em todas as refeições, sem exagero", "Não precisa cortar arroz, feijão, pão, batata ou fruta. Porções moderadas."),
        Meta("Gorduras", "Preferir as boas", "Azeite, peixes, sementes, castanhas e abacate. Menos manteiga, banha e carne gorda."),
        Meta("Energia", "Cerca de 1.800 a 2.100 kcal por dia", "Faixa inicial, não é uma conta rígida. A nutricionista vai definir."),
        Meta("Peso", "Sem meta rígida agora", "Se perder peso, que seja devagar, sem perder força e músculo."),
    )

    /** Método do prato para almoço e jantar. */
    const val PRATO_VERDURAS = "Metade do prato: verduras e legumes"
    const val PRATO_PROTEINA = "Um quarto: proteína (frango, peixe, ovo, carne magra)"
    const val PRATO_CARBO = "Um quarto: carboidrato (arroz, batata, mandioca, milho…)"
    const val PRATO_FEIJAO = "Mais: feijão, lentilha ou grão-de-bico"

    const val PRATO_OBS = "Batata, mandioca, inhame, milho, cuscuz e polenta contam como carboidrato. " +
        "Feijão, lentilha e grão-de-bico são leguminosas: têm carboidrato, proteína e fibra."

    val porcoes = listOf(
        Porcao("Proteína principal", "100 a 120 g de frango, peixe ou carne magra. Ou 2 ovos."),
        Porcao("Arroz", "2 a 4 colheres de sopa de arroz cozido (ou ½ xícara de quinoa/cevada)."),
        Porcao("Feijão ou lentilha", "½ a 1 concha pequena."),
        Porcao("Batata, mandioca, inhame", "1 unidade pequena ou ½ a 1 xícara. Usar no lugar do arroz, não junto."),
        Porcao("Pão integral", "1 fatia grande ou 1 a 2 pequenas."),
        Porcao("Fruta", "1 unidade pequena ou média, ou 1 xícara picada. De preferência inteira."),
        Porcao("Castanhas", "1 porção pequena, sem sal."),
        Porcao("Azeite", "1 colher de chá a 1 colher de sopa na refeição."),
    )

    val priorize = listOf(
        "Verduras e legumes variados",
        "Feijão, lentilha, grão-de-bico e ervilha",
        "Frutas inteiras",
        "Aveia e grãos integrais",
        "Peixe, frango sem pele, ovos e carnes magras",
        "Iogurte natural sem açúcar",
        "Ricota, cottage e queijo minas frescal",
        "Azeite, castanhas sem sal e sementes",
        "Água",
    )

    val comPorcao = listOf(
        "Arroz branco, pão francês, macarrão",
        "Tapioca, cuscuz, polenta",
        "Batata, mandioca, inhame, milho",
        "Banana, manga, uva e frutas mais doces",
        "Carne vermelha magra",
        "Queijos amarelos",
        "Granola, frutas secas, pasta de amendoim",
        "Sobremesa e chocolate planejados",
    )

    val evite = listOf(
        "Refrigerante, néctar e bebidas adoçadas",
        "Suco como bebida de todo dia",
        "Bolacha recheada, doces e bolos frequentes",
        "Salsicha, linguiça, salame, mortadela, bacon e presunto",
        "Miojo, caldo em cubo, temperos e molhos muito salgados",
        "Frituras e fast-food",
        "Bebida alcoólica",
    )

    val cafeDaManha = listOf(
        "2 ovos mexidos + 1 fatia de pão 100% integral + tomate + 1 maçã pequena + café sem açúcar.",
        "Iogurte natural sem açúcar + 3 colheres de aveia + 1 colher de chia + morangos.",
        "Mingau de aveia com leite semidesnatado + canela + ½ banana + castanhas picadas.",
        "Omelete de 2 ovos com espinafre e tomate + 1 fatia de pão integral + 1 fruta pequena.",
        "Pão integral + ricota ou cottage + 1 ovo cozido + pepino ou tomate + 1 fruta.",
        "Iogurte natural + mamão + aveia + linhaça.",
        "Cuscuz em porção pequena + 2 ovos + tomate e cebola + fruta inteira.",
        "Tapioca pequena com ovo e queijo branco + tomate (de vez em quando, não todo dia).",
    )

    val lanches = listOf(
        "Iogurte natural + chia",
        "Maçã ou pera + castanhas sem sal",
        "Mamão + iogurte",
        "Ricota ou queijo minas + tomate + fruta pequena",
        "Homus + cenoura ou pepino",
        "Ovo cozido + fruta pequena",
        "Kefir ou iogurte + um pouco de aveia",
    )

    val almocoJantar = listOf(
        "½ prato de brócolis, couve-flor e tomate + 3 colheres de arroz integral + ½ a 1 concha de feijão + 120 g de frango grelhado + azeite.",
        "Salada grande + 1 batata-doce pequena + 120 a 150 g de sardinha ou peixe + legumes assados.",
        "½ prato de couve, abobrinha e berinjela + arroz em porção pequena + feijão + 100 a 120 g de patinho.",
        "Salada + ½ xícara de quinoa + 120 g de salmão + brócolis.",
        "Lentilha com legumes + porção pequena de arroz + frango assado sem pele + folhas.",
        "Omelete de 2 ovos com legumes + salada grande + batata pequena assada.",
        "Salada de grão-de-bico com tomate, pepino e cebola + frango ou peixe + azeite e limão.",
        "Arroz + feijão em porções ajustadas + carne magra + couve + tomate + abobrinha.",
        "Cuscuz pequeno + peixe grelhado + muito legume e salada (sem arroz junto).",
        "Sopa caseira de legumes com frango, feijão ou lentilha e um pouco de batata ou cevada. Nada de sopa instantânea.",
    )

    val cardapio = listOf(
        DiaCardapio(
            DayOfWeek.MONDAY, "Segunda-feira",
            cafe = "Ovos + pão integral + maçã",
            almoco = "Arroz integral + feijão + frango + brócolis ou salada",
            lanche = "Iogurte + chia",
            jantar = "Peixe assado + legumes + batata-doce pequena",
        ),
        DiaCardapio(
            DayOfWeek.TUESDAY, "Terça-feira",
            cafe = "Iogurte natural + aveia + morangos",
            almoco = "Patinho + lentilha + arroz em porção pequena + salada",
            lanche = "Pera + castanhas",
            jantar = "Omelete com legumes + salada + pão integral",
        ),
        DiaCardapio(
            DayOfWeek.WEDNESDAY, "Quarta-feira",
            cafe = "Mingau de aveia + ½ banana + sementes",
            almoco = "Peixe + feijão + arroz + couve",
            lanche = "Iogurte natural",
            jantar = "Sopa caseira de frango + legumes + um pouco de cevada ou batata",
        ),
        DiaCardapio(
            DayOfWeek.THURSDAY, "Quinta-feira",
            cafe = "Ovos + pão integral + pedacinho de abacate + laranja inteira",
            almoco = "Frango sem pele + arroz + feijão + legumes",
            lanche = "Maçã + castanhas",
            jantar = "Lentilha com legumes + salada + ovo ou frango",
        ),
        DiaCardapio(
            DayOfWeek.FRIDAY, "Sexta-feira",
            cafe = "Iogurte natural + aveia + kiwi",
            almoco = "Carne magra + feijão + legumes + um pouco de arroz ou quinoa",
            lanche = "Fruta + queijo branco",
            jantar = "Sardinha ou peixe + legumes assados + batata pequena",
        ),
        DiaCardapio(
            DayOfWeek.SATURDAY, "Sábado",
            cafe = "Omelete + pão integral + fruta",
            almoco = "Frango + arroz e feijão + salada grande",
            lanche = "Iogurte + castanhas",
            jantar = "Grão-de-bico com legumes + salada + proteína",
        ),
        DiaCardapio(
            DayOfWeek.SUNDAY, "Domingo",
            cafe = "Aveia + iogurte + fruta + sementes",
            almoco = "Almoço de família: peixe ou frango, arroz, feijão, salada e legumes",
            lanche = "Fruta + castanhas",
            jantar = "Omelete, sopa caseira ou peixe/frango com legumes",
        ),
    )

    fun cardapioDe(dia: DayOfWeek) = cardapio.first { it.dia == dia }

    const val SEM_PERFECCIONISMO = "O cardápio é um roteiro, não uma regra. Se faltar algum alimento, use as trocas. " +
        "O importante é manter o jeito de montar a refeição."

    const val REFEICAO_FAMILIA = "Numa refeição de família: uma proteína principal, legumes e uma porção moderada de carboidrato. " +
        "Evite juntar muito arroz + macarrão + batata + farofa + pão no mesmo prato. " +
        "Se tiver sobremesa, porção pequena e sem refrigerante junto."

    val trocas = listOf(
        Troca("3 colheres de arroz", "1 batata pequena, ou ½ a 1 xícara de mandioca/inhame, ou um pouco de cuscuz", "Escolha só uma base de carboidrato."),
        Troca("Pão integral no café", "Aveia, cuscuz pequeno ou tapioca pequena", "Sempre com uma proteína junto."),
        Troca("Feijão", "Lentilha, grão-de-bico ou ervilha seca", "Varie durante a semana."),
        Troca("Peixe", "Frango sem pele, ovos, carne magra, tofu", "Prefira peixe, aves, ovos e leguminosas."),
        Troca("Queijo amarelo", "Ricota, cottage, minas frescal ou iogurte natural", "Menos gordura ruim e, às vezes, menos sal."),
        Troca("Refrigerante ou suco", "Água, água com gás, café ou chá sem açúcar", "A refeição não precisa de bebida doce."),
        Troca("Embutido (salsicha, presunto…)", "Ovo, frango, peixe, carne fresca ou feijão", "Embutido não deve ser a proteína de todo dia."),
        Troca("Doce ou chocolate", "Porção planejada, de preferência depois de uma refeição", "Não junte doce + bebida doce + outra sobremesa."),
    )

    val doces = listOf(
        Doce("Chocolate 70% cacau ou mais", "10 a 20 g", "Até 2 a 3 vezes por semana", "Mais cacau e, em geral, menos açúcar."),
        Doce("Chocolate ao leite / Suflair", "20 a 25 g (meio Suflair de 50 g)", "1 a 2 vezes por semana", "Mais açúcar e menos cacau."),
        Doce("Chocolate light", "20 a 25 g", "1 a 2 vezes por semana", "Light não quer dizer sem açúcar."),
        Doce("Chocolate diet", "20 a 25 g", "1 a 2 vezes por semana", "Pode ter menos açúcar, mas não necessariamente menos calorias."),
        Doce("Chocolate zero açúcar", "20 a 25 g", "1 a 2 vezes por semana", "Continua tendo gordura, calorias e outros carboidratos."),
        Doce("Chocolate branco", "10 a 20 g", "Cerca de 1 vez por semana", "Feito de manteiga de cacau, leite e açúcar."),
        Doce("Bombom, trufa ou recheado", "1 unidade pequena (15 a 25 g)", "Cerca de 1 vez por semana", "O recheio aumenta açúcar e gordura."),
        Doce("Barra inteira de 50 g", "50 g", "Só de vez em quando (1 a 2 vezes por mês)", "Não é proibida, mas não é a porção de sempre."),
    )

    const val DOCE_MELHOR_MOMENTO = "Melhor como sobremesa, depois do almoço ou do jantar, e não beliscando durante o dia. " +
        "Se for um Suflair de 50 g, separe metade e guarde o resto para outro dia."

    val compras = listOf(
        ItemCompra("Iogurte", "Natural, sem açúcar, de preferência desnatado. Com boa proteína e poucos ingredientes.", "Saborizado, com mel, calda ou preparado de frutas."),
        ItemCompra("Pão", "Primeiro ingrediente integral. Bastante fibra. Comparar o sódio.", "\"Integral\" com farinha branca como primeiro ingrediente."),
        ItemCompra("Aveia", "Em flocos, flocos finos ou farelo, sem açúcar.", "Mingau instantâneo adoçado e cereal açucarado."),
        ItemCompra("Queijos", "Ricota, cottage, minas frescal.", "Prato, provolone, cheddar e parmesão em grande quantidade."),
        ItemCompra("Molho de tomate", "Tomate ou passata, menos sódio, sem açúcar.", "Molhos muito temperados e salgados."),
        ItemCompra("Atum e sardinha", "Versões simples. Comparar o sódio.", "Patês e versões com molhos salgados."),
        ItemCompra("Castanhas", "Sem sal e sem açúcar.", "Salgadas ou caramelizadas."),
        ItemCompra("Pasta de amendoim", "Só amendoim nos ingredientes.", "Com açúcar, mel, chocolate ou gordura vegetal."),
        ItemCompra("Temperos", "Ervas, alho, cebola, páprica, cúrcuma, limão, vinagre.", "Caldo em cubo, tempero pronto, shoyu comum."),
    )

    const val REGRA_COMPRAS = "Não precisa decorar marcas. Se o produto é natural, sem açúcar adicionado, com boa proteína " +
        "e menos gordura saturada e sódio que os parecidos, tende a ser uma boa escolha."

    val rotulo = listOf(
        "Olhe a lupa \"ALTO EM\" na frente da embalagem.",
        "Leia os ingredientes: os primeiros da lista são os que têm em maior quantidade.",
        "Compare produtos parecidos pelo valor em 100 g ou 100 ml.",
        "Preste atenção em açúcar adicionado, gordura saturada, sódio, fibras e proteína.",
        "\"Fit\", \"integral\", \"light\", \"diet\" e \"zero\" são pistas, não garantias.",
    )

    val iogurte = listOf(
        "Primeiro: confira se é natural e sem açúcar adicionado.",
        "Depois: compare a proteína. Mais proteína ajuda a matar a fome.",
        "Para o colesterol: entre parecidos, prefira o com menos gordura saturada.",
        "Para a pressão: compare o sódio.",
        "Achou sem graça? Coloque fruta, canela, aveia ou chia em casa.",
    )

    val avisos = listOf(
        Aviso(
            "Não use sal light por conta própria",
            "Sal light e substitutos têm potássio. Como toma losartana e o potássio já estava em 4,9, só use se a médica liberar. " +
                "Isso não quer dizer tirar frutas, verduras ou feijão.",
        ),
        Aviso(
            "Jejum não faz parte do plano",
            "Nada de jejum intermitente, pular refeições ou dietas muito restritas. " +
                "Jejum só quando pedirem para um exame ou procedimento, seguindo as orientações da equipe, inclusive sobre os remédios do dia.",
        ),
        Aviso(
            "Frutas não são proibidas",
            "Banana, manga, uva, melancia, mamão, laranja e outras podem entrar. Prefira a fruta inteira, uma porção por vez. " +
                "Suco, mesmo natural, concentra o açúcar e mata menos a fome.",
        ),
        Aviso(
            "Açúcar \"natural\" continua sendo açúcar",
            "Mel, açúcar mascavo, demerara, açúcar de coco, melado e xaropes não são liberados. Só em pouca quantidade.",
        ),
        Aviso(
            "Enlatados podem entrar",
            "Atum, sardinha, feijão e milho em lata não são proibidos. Prefira os com menos sódio e escorra e enxágue antes de usar.",
        ),
        Aviso(
            "Óleo de coco não é a gordura principal",
            "Tem muita gordura saturada. Para cozinhar e temperar, prefira azeite; se precisar, um pouco de óleo de canola ou outro óleo vegetal.",
        ),
        Aviso(
            "\"Diet\", \"light\", \"zero\" e \"fit\"",
            "Não quer dizer que pode à vontade. Confira no rótulo o açúcar, a gordura saturada, o sódio e o tamanho da porção.",
        ),
    )

    /** O que a nutricionista ainda vai definir (página 2). */
    val nutricionistaVaiDefinir = listOf(
        "A meta de calorias e de peso, se houver.",
        "As porções exatas ao longo do dia, conforme fome e rotina.",
        "Avaliação de massa muscular e força.",
        "Ajustes depois dos exames de novembro.",
    )
}

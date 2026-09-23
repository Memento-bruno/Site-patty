package br.com.isildo.saude.data

import java.time.LocalDate
import java.time.LocalTime
import java.time.Period

/*
 * Todo o conteúdo do aplicativo vem de dois documentos:
 *  - "Histórico de Saúde - Isildo Braz Domingues" (atualizado);
 *  - "Plano Alimentar Individualizado" (versão 22/09/2026).
 * Onde os documentos dizem "a confirmar", o app também diz. Nada foi inventado.
 */

object Fontes {
    const val HISTORICO = "Histórico de Saúde (atualizado)"
    const val PLANO = "Plano Alimentar Individualizado, versão 22/09/2026"
}

// ---------------------------------------------------------------------------
// Dados pessoais
// ---------------------------------------------------------------------------

object Pessoa {
    const val NOME = "Isildo Braz Domingues"
    const val APELIDO = "Seu Isildo"
    val NASCIMENTO: LocalDate = LocalDate.of(1954, 12, 27)
    const val ALTURA = "1,60 m"
    const val PESO = "70 kg"
    const val IMC = "27,3 kg/m² (aproximado)"
    const val ALERGIAS = "Nenhuma alergia a remédio conhecida"
    const val HISTORIA_FAMILIAR = "Sem história de melanoma na família"
    const val ESTADO_FUNCIONAL = "Completamente ativo (performance status 0, registro de 13/07/2026)"

    val antecedentes = listOf(
        "Pneumonia com internação em 1973, no Hospital Santa Catarina.",
        "Cirurgia para retirada das amígdalas.",
    )

    fun idade(hoje: LocalDate = LocalDate.now()): Int = Period.between(NASCIMENTO, hoje).years
}

// ---------------------------------------------------------------------------
// Remédios
// ---------------------------------------------------------------------------

enum class Periodo(val nome: String) { MANHA("Manhã"), TARDE("Tarde"), NOITE("Noite") }

data class Remedio(
    val id: String,
    val nome: String,
    val nomeComercial: String,
    val dose: String,
    val comoTomar: String,
    val paraQueServe: String,
    val quemReceitou: String,
    val inicio: String,
    val duracao: String,
    val observacao: String? = null,
    val doseAConfirmar: Boolean = false,
)

/** Uma tomada do dia: um remédio num horário. */
data class Tomada(
    val id: String,
    val remedioId: String,
    val horario: LocalTime,
    val instrucao: String,
    val fimDoTratamento: LocalDate? = null,
) {
    val periodo: Periodo
        get() = when {
            horario.hour < 12 -> Periodo.MANHA
            horario.hour < 18 -> Periodo.TARDE
            else -> Periodo.NOITE
        }

    fun valeEm(dia: LocalDate) = fimDoTratamento == null || !dia.isAfter(fimDoTratamento)
}

object Remedios {

    /** Vitamina B12: 90 dias a partir de 02/09/2026 (o 90º dia é 30/11/2026). */
    val FIM_B12: LocalDate = LocalDate.of(2026, 11, 30)

    val atuais = listOf(
        Remedio(
            id = "gliclazida",
            nome = "Gliclazida MR 60 mg",
            nomeComercial = "Diamicron MR",
            dose = "1 comprimido",
            comoTomar = "1 comprimido pela manhã, junto do café da manhã.",
            paraQueServe = "Diabetes. Estimula a liberação de insulina e ajuda a baixar o açúcar do sangue.",
            quemReceitou = "Dra. Karina Aragon (endocrinologista), 01/09/2026",
            inicio = "02/09/2026, às 8h",
            duracao = "Uso contínuo",
        ),
        Remedio(
            id = "metformina",
            nome = "Metformina XR 500 mg",
            nomeComercial = "Glifage XR",
            dose = "1 comprimido, 2 vezes ao dia",
            comoTomar = "1 comprimido depois do almoço e 1 comprimido depois do jantar.",
            paraQueServe = "Diabetes. Diminui a produção de açúcar pelo fígado e melhora a ação da insulina.",
            quemReceitou = "Dra. Karina Aragon (endocrinologista), 01/09/2026",
            inicio = "04/09/2026, no jantar",
            duracao = "Uso contínuo",
        ),
        Remedio(
            id = "losartana",
            nome = "Losartana",
            nomeComercial = "Losartana",
            dose = "Dose ainda a confirmar",
            comoTomar = "Tomar às 10h, conforme a receita.",
            paraQueServe = "Pressão alta. Ajuda a controlar a pressão e protege o coração.",
            quemReceitou = "Em uso atual (anotado na consulta de 01/09/2026)",
            inicio = "Já em uso",
            duracao = "Uso contínuo",
            observacao = "A dose (miligramas) ainda precisa ser confirmada. Confira na caixa ou na receita.",
            doseAConfirmar = true,
        ),
        Remedio(
            id = "vitaminaD",
            nome = "Vitamina D3 2.000 UI",
            nomeComercial = "Doss (colecalciferol)",
            dose = "1 cápsula por dia",
            comoTomar = "1 cápsula por dia, de preferência depois de uma refeição (13h, depois do almoço).",
            paraQueServe = "A vitamina D estava baixa. Ajuda os ossos, o cálcio e os músculos.",
            quemReceitou = "Dra. Karina Aragon (endocrinologista), 01/09/2026",
            inicio = "02/09/2026, às 13h",
            duracao = "Uso contínuo",
        ),
        Remedio(
            id = "rosuvastatina",
            nome = "Rosuvastatina 20 mg",
            nomeComercial = "Rosuvastatina cálcica",
            dose = "1 comprimido por dia",
            comoTomar = "1 comprimido pela boca, 1 vez ao dia, às 18h.",
            paraQueServe = "Colesterol. Baixa o colesterol ruim (LDL) e ajuda a prevenir infarto e AVC.",
            quemReceitou = "Dra. Karina Aragon (endocrinologista), 01/09/2026",
            inicio = "01/09/2026, às 18h",
            duracao = "Uso contínuo",
        ),
        Remedio(
            id = "b12",
            nome = "Vitamina B12 1.000 mcg",
            nomeComercial = "Dozemast (mecobalamina)",
            dose = "1 comprimido por dia",
            comoTomar = "Colocar 1 comprimido embaixo da língua e deixar derreter por completo. Não engolir inteiro.",
            paraQueServe = "Ajuda os nervos e a formação do sangue.",
            quemReceitou = "Dra. Karina Aragon (endocrinologista), 01/09/2026",
            inicio = "02/09/2026",
            duracao = "Por 90 dias (até cerca de 30/11/2026)",
            observacao = "Perto do fim dos 90 dias, pergunte à médica se é para continuar.",
        ),
    )

    fun porId(id: String) = atuais.first { it.id == id }

    /** Horários do dia, conforme a página 4 do plano alimentar. */
    val tomadas = listOf(
        Tomada("b12", "b12", LocalTime.of(8, 0), "1 comprimido embaixo da língua, deixar derreter", FIM_B12),
        Tomada("gliclazida", "gliclazida", LocalTime.of(8, 20), "1 comprimido com o café da manhã"),
        Tomada("losartana", "losartana", LocalTime.of(10, 0), "Dose a confirmar, conforme a receita"),
        Tomada("metformina_almoco", "metformina", LocalTime.of(12, 45), "1 comprimido depois do almoço"),
        Tomada("vitaminaD", "vitaminaD", LocalTime.of(13, 0), "1 cápsula depois do almoço"),
        Tomada("rosuvastatina", "rosuvastatina", LocalTime.of(18, 0), "1 comprimido"),
        Tomada("metformina_jantar", "metformina", LocalTime.of(20, 0), "1 comprimido depois do jantar"),
    )

    fun tomadasDe(dia: LocalDate) = tomadas.filter { it.valeEm(dia) }

    data class RemedioAnterior(val nome: String, val detalhe: String)

    val naoToma = listOf(
        RemedioAnterior(
            "AAS (aspirina)",
            "Não faz parte do tratamento atual, segundo o plano de 22/09/2026. Foi citado em 18/08/2026; se tiver dúvida, confirme com a médica.",
        ),
        RemedioAnterior(
            "Pregabalina (Lyrica)",
            "Discutida pelo ortopedista em 08/01/2024, para a dor da coluna. Uso e duração a confirmar.",
        ),
        RemedioAnterior(
            "Captopril 25 mg",
            "Dose única de 2 comprimidos em 12/03/2025, quando a pressão estava 17 por 9,4 (172 × 94).",
        ),
        RemedioAnterior(
            "Icaden creme",
            "Usado em 2022 por 15 a 20 dias para micose na virilha e na coxa.",
        ),
    )
}

// ---------------------------------------------------------------------------
// Rotina do dia (refeições + remédios)
// ---------------------------------------------------------------------------

data class ItemRotina(
    val horario: String,
    val titulo: String,
    val detalhe: String,
    val tomadaIds: List<String> = emptyList(),
    val refeicao: Boolean = false,
)

object Rotina {
    val itens = listOf(
        ItemRotina("08h", "Vitamina B12", "Embaixo da língua, deixar derreter por completo.", listOf("b12")),
        ItemRotina(
            "08h20", "Café da manhã + Gliclazida",
            "Proteína + carboidrato de boa qualidade + 1 fruta inteira. Tomar a gliclazida.",
            listOf("gliclazida"), refeicao = true,
        ),
        ItemRotina("10h", "Losartana", "Tomar conforme a receita.", listOf("losartana")),
        ItemRotina(
            "12h às 13h", "Almoço + Metformina",
            "½ prato de verduras e legumes, ¼ proteína, ¼ carboidrato e feijão. Metformina depois do almoço.",
            listOf("metformina_almoco"), refeicao = true,
        ),
        ItemRotina("13h", "Vitamina D3", "Depois do almoço.", listOf("vitaminaD")),
        ItemRotina(
            "15h30 às 17h30", "Lanche (se tiver fome)",
            "Fruta inteira com iogurte, castanhas, sementes, ovo ou queijo branco.",
            refeicao = true,
        ),
        ItemRotina("18h", "Rosuvastatina", "1 comprimido.", listOf("rosuvastatina")),
        ItemRotina(
            "19h às 20h30", "Jantar + Metformina",
            "Mesmo jeito do almoço. Metformina depois do jantar.",
            listOf("metformina_jantar"), refeicao = true,
        ),
        ItemRotina("Antes de dormir", "Ceia (só se tiver fome)", "Não é obrigatória.", refeicao = true),
    )
}

// ---------------------------------------------------------------------------
// Condições de saúde
// ---------------------------------------------------------------------------

data class Condicao(
    val nome: String,
    val emPalavrasSimples: String,
    val detalhes: List<String>,
    val oQueAjuda: List<String>,
    val emInvestigacao: Boolean = false,
)

object Condicoes {
    val lista = listOf(
        Condicao(
            nome = "Pressão alta (hipertensão)",
            emPalavrasSimples = "A pressão do sangue fica mais alta do que deveria.",
            detalhes = listOf(
                "Confirmada em 12/03/2025, com pressão de 172 × 94 mmHg (17 por 9,4).",
                "Em 14/04/2024, medida em casa: cerca de 14 por 9.",
                "Tratamento: losartana às 10h.",
            ),
            oQueAjuda = listOf(
                "Menos sal: abaixo de 2.300 mg de sódio por dia.",
                "Cuidado com industrializados salgados e com o sal no preparo.",
                "NÃO usar sal light ou substitutos com potássio por conta própria (por causa da losartana).",
            ),
        ),
        Condicao(
            nome = "Açúcar alto no sangue (diabetes)",
            emPalavrasSimples = "O açúcar do sangue está bem acima do normal, na faixa de diabetes.",
            detalhes = listOf(
                "Exame de 29/08/2026: glicose em jejum 327 mg/dL.",
                "Hemoglobina glicada (HbA1c) 11,4%. Glicose média estimada 280 mg/dL.",
                "Tratamento com a endocrinologista: metformina e gliclazida.",
            ),
            oQueAjuda = listOf(
                "Refeições em horários regulares.",
                "Usar o método do prato.",
                "Evitar refrigerante, suco e doces frequentes.",
                "A comida ajuda, mas não substitui os remédios.",
            ),
            emInvestigacao = true,
        ),
        Condicao(
            nome = "Colesterol alterado",
            emPalavrasSimples = "O colesterol ruim (LDL) está alto e o bom (HDL) está baixo.",
            detalhes = listOf(
                "Exame de 29/08/2026: colesterol total 270, LDL 182, HDL 37, VLDL 51 (mg/dL).",
                "Tratamento: rosuvastatina 20 mg às 18h.",
            ),
            oQueAjuda = listOf(
                "Menos gordura saturada: manteiga, banha, carnes gordas, queijos amarelos.",
                "Mais fibras: aveia, feijão, verduras e frutas inteiras.",
                "Preferir azeite, peixes, castanhas e sementes.",
            ),
            emInvestigacao = true,
        ),
        Condicao(
            nome = "Melanoma in situ (pinta das costas)",
            emPalavrasSimples = "Uma pinta antiga nas costas, de cerca de 3 × 3 cm, mostrou células de melanoma na camada superficial da pele.",
            detalhes = listOf(
                "A biópsia de junho/2026 analisou só um pedaço pequeno da pinta.",
                "Por isso o ICESP indicou retirar a pinta inteira, com anestesia local.",
                "Sem dor e sem sangramento. Sem quimioterapia, radioterapia ou cirurgia até agora.",
                "O próximo passo depende do resultado da pinta inteira.",
            ),
            oQueAjuda = listOf(
                "Nenhum alimento, chá, suco ou suplemento trata melanoma.",
                "A comida ajuda a manter força e a recuperação.",
                "Se houver cirurgia, falta de apetite ou perda de peso sem querer: priorizar proteína e energia.",
            ),
        ),
        Condicao(
            nome = "Hérnia de disco na coluna (L4-L5)",
            emPalavrasSimples = "Um disco da coluna lombar aperta o nervo que vai para a perna direita.",
            detalhes = listOf(
                "Causa dor que desce pela perna direita e formigamento no pé direito.",
                "Diminuiu a força para levantar o dedão do pé direito.",
                "O ortopedista (08/01/2024) falou em fisioterapia, fortalecimento e possível cirurgia.",
            ),
            oQueAjuda = listOf(
                "Natação, Pilates ou academia com carga leve a moderada.",
                "Evitar carregar muito peso; pedir ajuda para caixas pesadas.",
                "Usar cinta de apoio lombar nos esforços, quando precisar.",
            ),
        ),
        Condicao(
            nome = "Eosinófilos altos (eosinofilia)",
            emPalavrasSimples = "Um tipo de célula de defesa do sangue está acima do normal.",
            detalhes = listOf(
                "Exame de 29/08/2026: eosinófilos 14% (1.596/mm³).",
                "A causa ainda não foi descoberta.",
            ),
            oQueAjuda = listOf("Conversar sobre isso com a endocrinologista."),
            emInvestigacao = true,
        ),
        Condicao(
            nome = "Diástase abdominal",
            emPalavrasSimples = "Uma separação dos músculos da barriga.",
            detalhes = listOf(
                "Vista no ultrassom da parede da barriga. Não havia nódulos nem caroços.",
            ),
            oQueAjuda = listOf("Acompanhamento com os médicos."),
            emInvestigacao = true,
        ),
    )
}

// ---------------------------------------------------------------------------
// Exames
// ---------------------------------------------------------------------------

enum class Situacao { ALTO, BAIXO, ATENCAO, NORMAL, SEM_AVALIACAO }

data class ResultadoExame(
    val nome: String,
    val valor: String,
    val situacao: Situacao,
    val explicacao: String,
)

data class GrupoExames(val titulo: String, val resultados: List<ResultadoExame>)

data class ExamePendente(
    val nome: String,
    val pedidoPor: String,
    val detalhe: String,
)

data class ExameAnterior(val data: String, val nome: String, val resultado: String)

object Exames {
    const val DATA_ULTIMOS = "29/08/2026"
    const val LOCAL_ULTIMOS = "Prevent Senior Diagnósticos (NMAD Istambul)"

    val pendentes = listOf(
        ExamePendente(
            "Retirada completa da pinta das costas",
            "ICESP, Dra. Maria Lucia Socci (18/08/2026)",
            "Com anestesia local. Não precisa de jejum. Manter a comida e os remédios de sempre. Os pontos saem cerca de 10 dias depois. Voltar ao ICESP quando sair o resultado.",
        ),
        ExamePendente(
            "Tomografias de tórax, abdome superior e pelve",
            "ICESP, Dra. Maria Lucia Socci (18/08/2026)",
            "Ainda não foram feitas.",
        ),
        ExamePendente(
            "Avaliação com geriatra",
            "ICESP, Dra. Maria Lucia Socci (18/08/2026)",
            "Consulta com médico especialista em saúde do idoso.",
        ),
        ExamePendente(
            "Exames de sangue de novembro",
            "Dra. Karina Aragon, endocrinologista",
            "Fazer perto da consulta de retorno e levar os resultados para ela ver a evolução.",
        ),
        ExamePendente(
            "Resultado do ultrassom de abdome (19/03/2026)",
            "UBS Ponte Grande",
            "O exame foi feito, mas o resultado não está anotado.",
        ),
    )

    val ultimos = listOf(
        GrupoExames(
            "Açúcar no sangue",
            listOf(
                ResultadoExame("Glicose em jejum", "327 mg/dL", Situacao.ALTO, "Na faixa de diabetes."),
                ResultadoExame("Hemoglobina glicada (HbA1c)", "11,4%", Situacao.ALTO, "Mostra a média do açúcar dos últimos meses. Na faixa de diabetes."),
                ResultadoExame("Glicose média estimada", "280 mg/dL", Situacao.ALTO, "Calculada a partir da HbA1c."),
            ),
        ),
        GrupoExames(
            "Colesterol e gorduras",
            listOf(
                ResultadoExame("Colesterol total", "270 mg/dL", Situacao.ALTO, "Alterado."),
                ResultadoExame("LDL (colesterol ruim)", "182 mg/dL", Situacao.ALTO, "Alto. É o principal alvo da rosuvastatina."),
                ResultadoExame("HDL (colesterol bom)", "37 mg/dL", Situacao.BAIXO, "Baixo."),
                ResultadoExame("VLDL", "51 mg/dL", Situacao.SEM_AVALIACAO, ""),
            ),
        ),
        GrupoExames(
            "Vitaminas",
            listOf(
                ResultadoExame("Vitamina D", "18,0 ng/mL", Situacao.BAIXO, "Baixa. Por isso começou a vitamina D3."),
                ResultadoExame("Vitamina B12", "287 pg/mL", Situacao.SEM_AVALIACAO, "A médica receitou B12 por 90 dias."),
            ),
        ),
        GrupoExames(
            "Sangue (hemograma)",
            listOf(
                ResultadoExame("Eosinófilos", "14% (1.596/mm³)", Situacao.ALTO, "Acima do normal. Causa ainda não descoberta: falar com a endocrinologista."),
                ResultadoExame("Leucócitos", "11.400/mm³", Situacao.SEM_AVALIACAO, ""),
                ResultadoExame("Hemoglobina, hematócrito e plaquetas", "Normais", Situacao.NORMAL, "Dentro da referência."),
            ),
        ),
        GrupoExames(
            "Rins e sais minerais",
            listOf(
                ResultadoExame("Creatinina", "0,95 mg/dL", Situacao.SEM_AVALIACAO, ""),
                ResultadoExame("Ureia", "27 mg/dL", Situacao.SEM_AVALIACAO, ""),
                ResultadoExame("Potássio", "4,9 mEq/L", Situacao.ATENCAO, "Por isso não usar sal light por conta própria."),
                ResultadoExame("Sódio", "138 mEq/L", Situacao.SEM_AVALIACAO, ""),
                ResultadoExame("Magnésio", "1,9 mg/dL", Situacao.SEM_AVALIACAO, ""),
                ResultadoExame("Cálcio", "9,9 mg/dL", Situacao.SEM_AVALIACAO, ""),
            ),
        ),
        GrupoExames(
            "Fígado",
            listOf(
                ResultadoExame("TGO", "14 U/L", Situacao.SEM_AVALIACAO, ""),
                ResultadoExame("TGP", "18 U/L", Situacao.SEM_AVALIACAO, ""),
            ),
        ),
        GrupoExames(
            "Urina",
            listOf(
                ResultadoExame("Glicose na urina", "+++", Situacao.ALTO, "Açúcar passando para a urina."),
                ResultadoExame("Cetonas, proteína e hemoglobina", "Traços", Situacao.SEM_AVALIACAO, ""),
                ResultadoExame("Densidade", "1,030", Situacao.SEM_AVALIACAO, ""),
                ResultadoExame("Nitrito", "Negativo", Situacao.NORMAL, ""),
                ResultadoExame("Leucócitos e hemácias", "Normais", Situacao.NORMAL, "Dentro da referência."),
                ResultadoExame("Cultura de urina", "Sem bactérias", Situacao.NORMAL, "Sem infecção."),
            ),
        ),
        GrupoExames(
            "Próstata",
            listOf(ResultadoExame("PSA total", "1,58 ng/mL", Situacao.SEM_AVALIACAO, "")),
        ),
    )

    val anteriores = listOf(
        ExameAnterior(
            "18/06/2026", "Biópsia da pinta das costas (anatomopatológico)",
            "Melanoma in situ. Só um pedaço pequeno foi analisado; não dá para saber se a pinta inteira é só superficial.",
        ),
        ExameAnterior(
            "Até 18/08/2026", "Ultrassom da parede da barriga",
            "Sem nódulos ou caroços embaixo da pele. Diástase abdominal (separação dos músculos da barriga).",
        ),
        ExameAnterior(
            "19/03/2026", "Ultrassom de abdome (UBS Ponte Grande)",
            "Exame feito. Resultado não anotado.",
        ),
        ExameAnterior(
            "12/03/2025", "Pressão arterial (UBS Ponte Grande)",
            "172 × 94 mmHg. Pressão alta confirmada.",
        ),
        ExameAnterior(
            "14/04/2024", "Pressão arterial (em casa)",
            "Cerca de 140 × 90 mmHg (14 por 9), depois de um sangramento no olho.",
        ),
        ExameAnterior(
            "Antes de 08/01/2024", "Ressonância da coluna",
            "Hérnia de disco L4-L5 do lado direito, apertando o nervo L5. Desgaste de vários discos. Outra alteração em T12-L1, que não causava os sintomas.",
        ),
    )
}

// ---------------------------------------------------------------------------
// Médicos e consultas
// ---------------------------------------------------------------------------

data class Medico(val nome: String, val especialidade: String, val local: String, val papel: String)

data class Consulta(
    val data: String,
    val local: String,
    val profissional: String,
    val especialidade: String,
    val motivo: List<String>,
    val decidido: List<String>,
    val resultados: List<String>,
)

object Consultas {

    val equipeAtual = listOf(
        Medico(
            "Dra. Karina Aragon", "Endocrinologista", "Consultório na Mooca, São Paulo",
            "Cuida do diabetes, colesterol e vitaminas. Receitou os remédios em 01/09/2026.",
        ),
        Medico(
            "Dra. Maria Lucia Fernandes Socci", "Dermatologista", "ICESP",
            "Cuida do melanoma. Vai retirar a pinta inteira e pediu tomografias e geriatra.",
        ),
        Medico(
            "Dra. Dalila Filomena Mohallem", "Dermatologista", "CEMEG Centro / Rede Hebe Camargo",
            "Pediu a biópsia da pinta e encaminhou para o ICESP.",
        ),
        Medico(
            "Dr. Edelvan Gabana", "Ortopedista", "Ortocity",
            "Avaliou a hérnia de disco da coluna em 08/01/2024.",
        ),
        Medico(
            "UBS Ponte Grande", "Clínica geral", "Unidade Básica de Saúde",
            "Dra. Ana Kecia Lima Rodrigues (2025) e Dra. Leticia Maria Caldeira Raful (2026).",
        ),
    )

    /** Da mais recente para a mais antiga. */
    val historico = listOf(
        Consulta(
            "01/09/2026", "Consultório, Mooca, São Paulo", "Dra. Karina Aragon (CRM-SP 103996)",
            "Endocrinologia",
            motivo = listOf("Avaliar os exames de 29/08/2026: açúcar alto, colesterol alterado e vitamina D baixa."),
            decidido = listOf(
                "Metformina XR 500 mg: 1 depois do almoço e 1 depois do jantar.",
                "Gliclazida MR 60 mg: 1 pela manhã.",
                "Rosuvastatina 20 mg: 1 por dia.",
                "Vitamina D3 2.000 UI: 1 cápsula por dia, depois de refeição.",
                "Vitamina B12 1.000 mcg: 1 embaixo da língua por dia, por 90 dias.",
                "Losartana anotada às 10h.",
            ),
            resultados = listOf("Novos exames serão feitos perto da próxima consulta."),
        ),
        Consulta(
            "29/08/2026", "Prevent Senior Diagnósticos (NMAD Istambul)", "Dr. Daniel Orselli Comparato e Dr. Rafael Tsutomu Martins Yadoya (pedido)",
            "Exames de laboratório",
            motivo = listOf("Exames de sangue e urina. Não houve consulta nesse dia."),
            decidido = emptyList(),
            resultados = listOf(
                "Glicose 327, HbA1c 11,4%.",
                "Colesterol total 270, LDL 182, HDL 37.",
                "Vitamina D 18 (baixa). B12 287.",
                "Eosinófilos 14% (acima do normal).",
                "Açúcar na urina (+++). Cultura de urina sem bactérias.",
                "Rins e fígado: creatinina 0,95, TGO 14, TGP 18. PSA 1,58.",
            ),
        ),
        Consulta(
            "18/08/2026", "ICESP", "Dra. Maria Lucia Fernandes Socci (CRM-SP 106758)",
            "Dermatologia",
            motivo = listOf(
                "Avaliar o melanoma in situ da biópsia parcial.",
                "Pinta nas costas há mais de 30-40 anos, que mudou e cresceu nos últimos 4 anos. Sem dor e sem sangramento.",
            ),
            decidido = listOf(
                "Retirar a pinta inteira, com anestesia local. Sem jejum; manter comida e remédios.",
                "Pontos saem cerca de 10 dias depois. Voltar quando sair o resultado.",
                "Se for só superficial: tirar mais uma margem de cerca de 0,5 cm.",
                "Se for mais profundo: margens maiores, avaliar gânglios e talvez outra cirurgia.",
                "Pedidas tomografias de tórax, abdome superior e pelve.",
                "Pedida avaliação com geriatra.",
                "PET-CT não indicado agora.",
                "Acompanhar pressão, colesterol e açúcar.",
            ),
            resultados = listOf(
                "Tomografias: ainda não feitas.",
                "Retirada da pinta: ainda não feita.",
                "Ultrassom da parede da barriga: sem nódulos; diástase abdominal.",
            ),
        ),
        Consulta(
            "13/07/2026", "CEMEG Centro / Rede Hebe Camargo", "Dra. Dalila Filomena Mohallem (CRM-SP 63218)",
            "Dermatologia",
            motivo = listOf("Retorno com o resultado da biópsia: melanoma in situ."),
            decidido = listOf("Encaminhado para tratamento especializado (oncologia/cirurgia)."),
            resultados = listOf(
                "Metástase: desconhecida.",
                "Sem quimioterapia, radioterapia ou cirurgia anterior.",
                "Completamente ativo.",
            ),
        ),
        Consulta(
            "18/06/2026", "Laboratório não identificado", "Não identificado",
            "Biópsia (anatomopatológico)",
            motivo = listOf("Analisar o pedaço retirado da pinta das costas."),
            decidido = emptyList(),
            resultados = listOf(
                "Melanoma in situ.",
                "Só um pedaço pequeno foi analisado, sem dar para avaliar a pinta inteira.",
            ),
        ),
        Consulta(
            "12/06/2026", "CEMEG Centro", "Dra. Dalila Filomena Mohallem (CRM-SP 63218)",
            "Dermatologia",
            motivo = listOf("Pinta escura antiga nas costas, que mudou e cresceu nos últimos 4 anos (cerca de 3 × 3 cm)."),
            decidido = listOf("Pedida biópsia e exame anatomopatológico."),
            resultados = listOf("Biópsia feita depois; laudo de 18/06/2026."),
        ),
        Consulta(
            "19/03/2026", "UBS Ponte Grande", "Dra. Leticia Maria Caldeira Raful",
            "Clínica geral",
            motivo = listOf("Acompanhamento geral e continuação da investigação da pinta."),
            decidido = listOf(
                "Encaminhado para a dermatologista Dra. Dalila Mohallem.",
                "Pedidos exames de sangue completos e ultrassom de abdome.",
            ),
            resultados = listOf("Exames de sangue: não feitos nessa época.", "Ultrassom de abdome: feito, resultado não anotado."),
        ),
        Consulta(
            "12/03/2025", "UBS Ponte Grande", "Dra. Ana Kecia Lima Rodrigues (CRM-SP 233346)",
            "Clínica médica",
            motivo = listOf(
                "Tontura e desconforto na barriga durante a fisioterapia (a fisioterapia foi interrompida).",
                "Avaliar a pinta das costas.",
                "Check-up: não fazia exame de sangue havia décadas.",
            ),
            decidido = listOf(
                "Captopril 25 mg: 2 comprimidos de uma vez.",
                "Esperar 50 minutos, medir a pressão de novo e voltar.",
            ),
            resultados = listOf("Pressão 172 × 94 mmHg. Pressão alta confirmada."),
        ),
        Consulta(
            "14/04/2024", "Em casa", "Medida da pressão",
            "Pressão arterial",
            motivo = listOf("Sangramento aparente no olho."),
            decidido = emptyList(),
            resultados = listOf("Pressão cerca de 140 × 90 mmHg (14 por 9)."),
        ),
        Consulta(
            "08/01/2024", "Ortocity", "Dr. Edelvan Gabana (CRM-SP 168348)",
            "Ortopedia",
            motivo = listOf(
                "Dor que desce para a perna direita, formigamento no pé direito.",
                "Pouca força para levantar o dedão direito. Incômodo ao agachar e levantar.",
                "Já tinha feito cerca de 10 sessões de fisioterapia.",
            ),
            decidido = listOf(
                "Continuar fisioterapia e fortalecimento.",
                "Natação, Pilates ou academia com carga leve a moderada. Evitar peso em excesso.",
                "Usar cinta lombar nos esforços, quando precisar.",
                "Discutida pregabalina (Lyrica) por cerca de 6 meses.",
                "Discutida cirurgia da hérnia, por causa da perda de força.",
                "Fraqueza súbita na perna: ir ao pronto-socorro levando a ressonância.",
            ),
            resultados = listOf(
                "Hérnia de disco L4-L5 à direita, apertando o nervo L5.",
                "Desgaste de vários discos. Alteração em T12-L1 sem relação com os sintomas.",
            ),
        ),
        Consulta(
            "18/02/2022", "Dr.Consulta, Guarulhos", "Dra. Vanessa de Figueiredo Ferreira (CRM-SP 147401)",
            "Clínica geral",
            motivo = listOf("Pinta grande nas costas, sem sintomas.", "Micose na virilha e na coxa."),
            decidido = listOf(
                "Pedida biópsia da pinta (não foi feita na época).",
                "Icaden creme à noite, depois do banho, por 15 a 20 dias.",
                "Lavar roupas, meias, toalhas e cuecas com Lysoform.",
            ),
            resultados = listOf("Sem exames."),
        ),
    )
}

// ---------------------------------------------------------------------------
// Próximos passos (o que falta fazer)
// ---------------------------------------------------------------------------

data class Passo(val id: String, val titulo: String, val detalhe: String)

object ProximosPassos {
    val lista = listOf(
        Passo("retirada", "Retirar a pinta inteira no ICESP", "Anestesia local, sem jejum, manter remédios. Pontos saem em cerca de 10 dias."),
        Passo("tomografias", "Fazer as tomografias", "Tórax, abdome superior e pelve, pedidas pelo ICESP."),
        Passo("geriatra", "Passar no geriatra", "Pedido pelo ICESP em 18/08/2026."),
        Passo("exames_nov", "Fazer os exames de novembro", "E levar os resultados para a Dra. Karina."),
        Passo("nutricionista", "Marcar consulta com nutricionista", "O plano alimentar ajuda até essa consulta acontecer."),
        Passo("eosinofilia", "Perguntar sobre os eosinófilos altos", "Conversar com a Dra. Karina sobre a causa."),
        Passo("losartana", "Confirmar a dose da losartana", "Ver na caixa ou na receita e anotar."),
        Passo("b12", "Perguntar se continua a vitamina B12", "Os 90 dias acabam por volta de 30/11/2026."),
        Passo("ultrassom", "Achar o resultado do ultrassom de abdome", "Feito em 19/03/2026 pela UBS Ponte Grande."),
    )

    /** "O que fazer agora", página 2 do plano alimentar. */
    val focoDoPeriodo = listOf(
        "Tomar os remédios como a médica mandou.",
        "Comer em horários regulares.",
        "Montar o prato do jeito certo (método do prato).",
        "Diminuir sal, bebidas doces, embutidos e ultraprocessados.",
        "Fazer os exames de novembro e levar para a endocrinologista.",
    )
}

package br.com.isildo.saude

import br.com.isildo.saude.data.Alimentacao
import br.com.isildo.saude.data.Pessoa
import br.com.isildo.saude.data.Remedios
import br.com.isildo.saude.data.Rotina
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import java.time.DayOfWeek
import java.time.LocalDate

class DadosTest {

    @Test
    fun horariosEstaoEmOrdem() {
        val horarios = Remedios.tomadas.map { it.horario }
        assertEquals(horarios.sorted(), horarios)
    }

    @Test
    fun todoRemedioTemHorarioEARotinaCobreTodasAsTomadas() {
        Remedios.atuais.forEach { r -> assertTrue(r.id, Remedios.tomadas.any { it.remedioId == r.id }) }
        val ids = Remedios.tomadas.map { it.id }.toSet()
        Rotina.itens.flatMap { it.tomadaIds }.forEach { assertTrue(it, it in ids) }
        assertEquals(ids, Rotina.itens.flatMap { it.tomadaIds }.toSet())
    }

    @Test
    fun metforminaDuasVezesAoDia() {
        assertEquals(2, Remedios.tomadas.count { it.remedioId == "metformina" })
    }

    @Test
    fun vitaminaB12SaiDaListaDepoisDos90Dias() {
        // Começou em 02/09/2026: o 90º dia é 30/11/2026.
        assertEquals(LocalDate.of(2026, 9, 2).plusDays(89), Remedios.FIM_B12)
        assertTrue(Remedios.tomadasDe(LocalDate.of(2026, 11, 30)).any { it.remedioId == "b12" })
        assertFalse(Remedios.tomadasDe(LocalDate.of(2026, 12, 1)).any { it.remedioId == "b12" })
        assertEquals(6, Remedios.tomadasDe(LocalDate.of(2026, 12, 1)).size)
    }

    @Test
    fun idade() {
        assertEquals(71, Pessoa.idade(LocalDate.of(2026, 9, 23)))
        assertEquals(71, Pessoa.idade(LocalDate.of(2026, 12, 26)))
        assertEquals(72, Pessoa.idade(LocalDate.of(2026, 12, 27)))
    }

    @Test
    fun cardapioTemOsSeteDias() {
        DayOfWeek.entries.forEach { Alimentacao.cardapioDe(it) }
        assertEquals(7, Alimentacao.cardapio.size)
    }
}

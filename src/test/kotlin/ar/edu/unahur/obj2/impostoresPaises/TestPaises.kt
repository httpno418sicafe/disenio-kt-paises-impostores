package ar.edu.unahur.obj2.impostoresPaises

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.shouldBe

class TestPaises: DescribeSpec({
    describe("Etapa 1") {
        val argentina = Pais(
            "Argentina", "ARG", 1234, 1234.2,
            "América", "ARS", 338.82, listOf(), listOf("UNASUR"), listOf("Español")
        )
        val bolivia = Pais(
            "Bolivia", "BOL", 10985059, 1098581.0,
            "América", "BOB", 6.89, listOf(argentina),
            listOf("UNASUR"), listOf("Español", "Quechua", "Aymara")
        )

        val brasil = Pais(
            "Brasil", "BRA", 1098505, 109858.0,
            "América", "BRS", 19.89, listOf(argentina, bolivia),
            listOf("UNASUR"), listOf("Portuges")
        )


        it("Argentina ahora es isla") {
            argentina.esIsla().shouldBeTrue()
        }
        it("Bolivia es plurinacional y argentina no") {
            bolivia.esPlurinacional().shouldBeTrue()
            argentina.esPlurinacional().shouldBeFalse()
        }
        it("Densidad poblacional") {
            bolivia.densidadPoblacional().shouldBe(10)
        }
        it("bolivia es el mas poblado") {
            bolivia.vecinoMasPoblado().shouldBe(bolivia)
        }

        it("Bolivia es limitrofe de Argentina"){
            bolivia.esLimitrofe(argentina).shouldBeTrue()
        }
        it("Necesitan Traduccion"){
            bolivia.necesitaTraduccion(argentina).shouldBeFalse()
            brasil.necesitaTraduccion(argentina).shouldBeTrue()
        }
        it("Potenciales Aliados"){
            bolivia.potencialAliado(argentina).shouldBeTrue()
            brasil.potencialAliado(bolivia).shouldBeFalse()
        }
        it("Conviene ir de compras"){
            bolivia.convieneIrAComprar(argentina).shouldBeTrue()
            argentina.convieneIrAComprar(bolivia).shouldBeFalse()
        }
        it("Valor equivalente del dinero"){
            bolivia.convertirAMoneda(200.0, argentina).shouldBe(466893.96)
        }
    }
})
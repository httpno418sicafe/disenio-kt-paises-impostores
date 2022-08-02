package ar.edu.unahur.obj2.impostoresPaises

import io.kotest.assertions.show.show
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.shouldBe

class TestObservatorio: DescribeSpec({
    describe("Etapa 2") {
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

        val observatorio: Observatorio = Observatorio.getInstance(listOf(argentina, bolivia, brasil))
        val observatorio2: Observatorio = Observatorio.getInstance(listOf(bolivia, argentina, brasil))

        it("Singleton Funciona") {
            observatorio2.paises.shouldBe(listOf(argentina,bolivia, brasil))
        }

        it("Decorador funciona"){
            val observatorioDecorado = MedidaBilletesDecorador(observatorio)
            observatorio.leerDolares("Argentina","Bolivia").show()
            observatorioDecorado.meAlcanzaCon100para1dolar("Argentina", "Bolivia").show()
        }

        it("Son limitrofes") {
            observatorio.sonLimitrofes("Argentina", "Bolivia").shouldBeFalse()
            observatorio.sonLimitrofes("Brasil", "Bolivia").shouldBeTrue()
        }
        it("Necesitan Traduccion") {
            observatorio.necesitanTraduccion("Argentina", "Brasil").shouldBeTrue()
            observatorio.necesitanTraduccion("Bolivia", "Argentina").shouldBeFalse()
        }
        it("potenciales aliados") {
            observatorio.potencialesAliados("Argentina", "Bolivia").shouldBeTrue()
            observatorio.potencialesAliados("Bolivia", "Brasil").shouldBeFalse()
        }
        it("conviene ir de compras") {
            observatorio.convieneIrDeCompras("Argentina", "Brasil").shouldBeFalse()
            observatorio.convieneIrDeCompras("Brasil", "Argentina").shouldBeTrue()
        }

        it("Convertir moneda"){
            observatorio.convertirMoneda(200.0,"Brasil", "Argentina").shouldBe(1347825.96)
        }
        it("Codigos con mayor Densidad"){
            observatorio.codigosISO5conMayorDensidad().shouldBe(listOf("ARG", "BOL", "BRA"))
        }
        it("Continente con mas plurinacionales") {
            observatorio.continenteConMasPlurinacionales().shouldBe("América")
        }
        it("Promedio Poblaciones Insulares"){
            observatorio.promedioDensidadPoblacionalInsulares().shouldBe(1.0)
        }
    }
})
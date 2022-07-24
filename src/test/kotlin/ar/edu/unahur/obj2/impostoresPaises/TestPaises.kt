package ar.edu.unahur.obj2.impostoresPaises

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.shouldBe

class TestPaises: DescribeSpec({
    describe("Etapa 1") {
        val argentina = Pais("Argentina", "ARG", 1234, 1234.2,
            "América", "ARS", 338.82, listOf(), listOf("UNASUR"), listOf("Español")
        )
        val bolivia = Pais("Bolivia", "BOL", 10985059, 1098581.0,
            "América","BOB",6.89, listOf(argentina),
            listOf("UNASUR"), listOf("Español", "Quechua", "Aymara"))

        it("Argentina ahora es isla"){
            argentina.esIsla().shouldBeTrue()
        }
        it("Bolivia es plurinacional y argentina no"){
            bolivia.esPlurinacional().shouldBeTrue()
            argentina.esPlurinacional().shouldBeFalse()
        }
        it("Densidad poblacional"){
            bolivia.densidadPoblacional().shouldBe(10)
        }
        it("bolivia es el mas poblado"){
            bolivia.vecinoMasPoblado().shouldBe(bolivia)
        }
    }
})
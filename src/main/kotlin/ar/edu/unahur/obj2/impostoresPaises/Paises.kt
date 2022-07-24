package ar.edu.unahur.obj2.impostoresPaises

import kotlin.math.roundToInt

class Pais(
    val nombre: String, val codigoIso3: String, val poblacion: Int, val superficie: Double, val continente: String,
    val codigoMoneda: String, val cotizacionDolar: Double, val paisesLimitrofes: List<Pais>, val bloquesRegionales: List<String>,
    val idiomasOficiales: List<String>) {

    fun esPlurinacional(): Boolean {
        return this.idiomasOficiales.size >= 2
    }

    fun esIsla(): Boolean {
        return this.paisesLimitrofes.isEmpty()
    }

    fun densidadPoblacional(): Int {
        return (this.poblacion/this.superficie).roundToInt()
    }

    fun vecinoMasPoblado(): Pais? {
        var listaTodos = paisesLimitrofes.toMutableList()
        listaTodos.add(this)
        return listaTodos.maxByOrNull { it.poblacion }
    }

    fun esLimitrofe(pais: Pais): Boolean {
        return this.paisesLimitrofes.contains(pais)
    }

    fun necesitaTraduccion(pais: Pais): Boolean {
        return this.idiomasOficiales.intersect(pais.idiomasOficiales.toSet()).isEmpty()
    }

    fun potencialAliado(pais: Pais): Boolean {
        return !this.necesitaTraduccion(pais) and !this.bloquesRegionales.intersect(pais.bloquesRegionales.toSet()).isEmpty()
    }

    fun convieneIrAComprar(pais: Pais): Boolean {
        return this.cotizacionDolar < pais.cotizacionDolar
    }

    fun convertirAMoneda(dinero: Double, pais: Pais): Double {
        val valorDolar = dinero * this.cotizacionDolar
        return (((valorDolar * pais.cotizacionDolar) * 100).roundToInt().toDouble() / 100)
    }
}
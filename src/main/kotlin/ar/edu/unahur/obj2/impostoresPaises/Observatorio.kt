package ar.edu.unahur.obj2.impostoresPaises

class Observatorio (val paises: List<Pais>) {

    fun sonLimitrofes(primerPais: String, segundoPais: String): Boolean {
        val paisUno = this.paises.find { it.nombre == primerPais }
        val paisDos = this.paises.find { it.nombre == segundoPais }
        return paisUno!!.esLimitrofe(paisDos!!)
    }

    fun necesitanTraduccion(primerPais: String, segundoPais: String): Boolean {
        val paisUno = this.paises.find { it.nombre == primerPais }
        val paisDos = this.paises.find { it.nombre == segundoPais }
        return paisUno!!.necesitaTraduccion(paisDos!!)
    }

    fun potencialesAliados(primerPais: String, segundoPais: String): Boolean {
        val paisUno = this.paises.find { it.nombre == primerPais }
        val paisDos = this.paises.find { it.nombre == segundoPais }
        return paisUno!!.potencialAliado(paisDos!!)
    }
    fun convieneIrDeCompras(primerPais: String, segundoPais: String): Boolean {
        val paisUno = this.paises.find { it.nombre == primerPais }
        val paisDos = this.paises.find { it.nombre == segundoPais }
        return paisUno!!.convieneIrAComprar(paisDos!!)
    }
    fun convertirMoneda(dinero: Double, primerPais: String, segundoPais: String): Double {
        val paisUno = this.paises.find { it.nombre == primerPais }
        val paisDos = this.paises.find { it.nombre == segundoPais }
        return paisUno!!.convertirAMoneda(dinero, paisDos!!)
    }
    fun codigosISO5conMayorDensidad(): List<String>{
        val paisesOrdenadosPorDensidad = this.paises.sortedBy {it.densidadPoblacional()}
        return paisesOrdenadosPorDensidad.take(5).map{it.codigoIso3}
    }

    fun continenteConMasPlurinacionales(): String {

    }
}
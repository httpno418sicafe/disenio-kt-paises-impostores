package ar.edu.unahur.obj2.impostoresPaises


interface Medidas {
    fun leerDolares(primerPais: String, segundoPais: String)
}

open class MedidaDecorador(val envuelto: Observatorio): Medidas {
    override fun leerDolares(primerPais: String, segundoPais: String) {
        envuelto.leerDolares(primerPais, segundoPais)
    }
}

class MedidaBilletesDecorador: MedidaDecorador{
    constructor(envuelto: Observatorio): super(envuelto)

    override fun leerDolares(primerPais: String, segundoPais: String) {
        super.leerDolares(primerPais, segundoPais)
    }

    fun meAlcanzaCon100para1dolar(primerPais: String, segundoPais: String) {
        val paisUno = envuelto.paises.find { it.nombre == primerPais }
        val paisDos = envuelto.paises.find { it.nombre == segundoPais }
        println("En el pais " + paisUno!!.nombre + ", me alcanza con 100 para 1 dolar? : " + (paisUno!!.cotizacionDolar <= 100).toString()+".")
        println("En el pais " + paisDos!!.nombre + ", me alcanza con 100 para 1 dolar? : " + (paisDos!!.cotizacionDolar <= 100).toString()+".")
    }
}

/*Utilizo el patron singleton para evitar que no haya mas de una instancia del observatorio.
 */
class Observatorio: Medidas{
    var paises: List<Pais>

    private constructor(paises: List<Pais>) {
        this.paises = paises
    }
    companion object {
        private var instance: Observatorio? = null

        fun getInstance(paises: List<Pais>): Observatorio {
            if (this.instance == null) {
                this.instance = Observatorio(paises)
            }
            return this.instance!!
        }
    }

    override fun leerDolares(primerPais: String, segundoPais: String) {
        val paisUno = this.paises.find { it.nombre == primerPais }
        val paisDos = this.paises.find { it.nombre == segundoPais }
        println("El valor del dolar en el pais " + paisUno!!.nombre + " es " + paisUno!!.cotizacionDolar.toString() +".")
        println("El valor del dolar en el pais " + paisDos!!.nombre + " es " + paisDos!!.cotizacionDolar.toString() +".")
    }

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

    fun codigosISO5conMayorDensidad(): List<String> {
        val paisesOrdenadosPorDensidad = this.paises.sortedBy { it.densidadPoblacional() }
        return paisesOrdenadosPorDensidad.take(5).map { it.codigoIso3 }
    }

    fun continenteConMasPlurinacionales(): String {
        val paisesPorContinentes = this.paises.groupBy { it.continente }
        var continenteConMayorDensidad: String = ""
        for (key in paisesPorContinentes.keys) {
            if (continenteConMayorDensidad == "") {
                continenteConMayorDensidad = key
            } else {
                if (paisesPorContinentes[continenteConMayorDensidad]!!.sumBy { it.densidadPoblacional() } < paisesPorContinentes[key]!!.sumBy { it.densidadPoblacional() }) {
                    continenteConMayorDensidad = key
                }
            }
        }
        return continenteConMayorDensidad
    }

    fun promedioDensidadPoblacionalInsulares(): Double {
        val paisesInsulares = this.paises.filter { it.esIsla() }
        return ((paisesInsulares.sumBy { it.densidadPoblacional() } / paisesInsulares.size) * 100).toDouble() / 100
    }
}
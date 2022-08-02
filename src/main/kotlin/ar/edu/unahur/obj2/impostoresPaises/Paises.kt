package ar.edu.unahur.obj2.impostoresPaises

import kotlin.math.roundToInt

interface Builder {
    fun setNombreCodigoPais(nombre: String, codigoIso3: String)
    fun setPoblacionBuilder(poblacion: Int)
    fun setSuperficieBuilder(superficie: Double)
    fun setContinenteBuilder(continente: String)
    fun setCodigoMonedaBuilder(codigoMoneda: String)
    fun setCotizaiconDolarBuilder(cotizacionDolar: Double)
    fun setPaisesLimitrofesBuilder(paisesLimitrofes: List<Pais>)
    fun setBloquesRegionalesBuilder(bloquesRegionales: List<String>)
    fun setIdiomasOficialesBuilder(idiomasOficiales: List<String>)
}

class LandBuilder: Builder {
    lateinit var idiomasOficiales: List<String>
    lateinit var bloquesRegionales: List<String>
    lateinit var paisesLimitrofes: List<Pais>
    var cotizacionDolar: Double = 0.0
    lateinit var codigoMoneda: String
    lateinit var nombre: String
    lateinit var codigoIso3: String
    var poblacion: Int = 0
    var superficie: Double = 0.0
    lateinit var continente: String

    override fun setNombreCodigoPais(nombre: String, codigoIso3: String) {
        this.nombre = nombre
        this.codigoIso3 = codigoIso3
    }

    override fun setPoblacionBuilder(poblacion: Int) {
        this.poblacion = poblacion
    }

    override fun setSuperficieBuilder(superficie: Double) {
        this.superficie = superficie
    }

    override fun setContinenteBuilder(continente: String) {
        this.continente = continente
    }

    override fun setCodigoMonedaBuilder(codigoMoneda: String) {
        this.codigoMoneda = codigoMoneda
    }

    override fun setCotizaiconDolarBuilder(cotizacionDolar: Double) {
        this.cotizacionDolar = cotizacionDolar
    }

    override fun setPaisesLimitrofesBuilder(paisesLimitrofes: List<Pais>) {
        this.paisesLimitrofes = paisesLimitrofes
    }

    override fun setBloquesRegionalesBuilder(bloquesRegionales: List<String>) {
        this.bloquesRegionales = bloquesRegionales
    }

    override fun setIdiomasOficialesBuilder(idiomasOficiales: List<String>) {
        this.idiomasOficiales = idiomasOficiales
    }

    fun resetBuilder(){
        this.nombre = ""
        this.codigoMoneda = ""
        this.codigoIso3=""
        this.superficie = 0.0
        this.poblacion = 0
        this.continente = ""
        this.cotizacionDolar = 0.0
        this.paisesLimitrofes = listOf()
        this.bloquesRegionales = listOf()
        this.idiomasOficiales = listOf()
    }

    fun getInstance(): Pais {
        val pais = Pais(this.nombre, this.codigoIso3, this.poblacion, this.superficie, this.continente, this.codigoMoneda,
            this.cotizacionDolar, this.paisesLimitrofes, this.bloquesRegionales, this.idiomasOficiales)
        this.resetBuilder()
        return pais
    }
}

class Director {
    fun construirPais(builder: Builder, nombre: String, codigoIso3: String, poblacion: Int, superficie: Double, continente: String,
                       codigoMoneda: String, cotizacionDolar: Double, paisesLimitrofes: List<Pais>, bloquesRegionales: List<String>,
                      idiomasOficiales: List<String>){
        builder.setNombreCodigoPais(nombre, codigoIso3)
        builder.setPoblacionBuilder(poblacion)
        builder.setSuperficieBuilder(superficie)
        builder.setContinenteBuilder(continente)
        builder.setCodigoMonedaBuilder(codigoMoneda)
        builder.setCotizaiconDolarBuilder(cotizacionDolar)
        builder.setBloquesRegionalesBuilder(bloquesRegionales)
        builder.setPaisesLimitrofesBuilder(paisesLimitrofes)
        builder.setIdiomasOficialesBuilder(idiomasOficiales)
    }

    fun construirIsla(builder: Builder, nombre: String, codigoIso3: String, poblacion: Int, superficie: Double, continente: String,
                      codigoMoneda: String, cotizacionDolar: Double, idiomasOficiales: List<String>){
        builder.setNombreCodigoPais(nombre, codigoIso3)
        builder.setPoblacionBuilder(poblacion)
        builder.setSuperficieBuilder(superficie)
        builder.setContinenteBuilder(continente)
        builder.setCodigoMonedaBuilder(codigoMoneda)
        builder.setCotizaiconDolarBuilder(cotizacionDolar)
        builder.setIdiomasOficialesBuilder(idiomasOficiales)
    }

    fun construirNacion(builder: Builder, nombre: String, codigoIso3: String, poblacion: Int, idiomasOficiales: List<String>){
        builder.setNombreCodigoPais(nombre, codigoIso3)
        builder.setPoblacionBuilder(poblacion)
        builder.setIdiomasOficialesBuilder(idiomasOficiales)
    }
}

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
        return ((valorDolar * pais.cotizacionDolar) * 100).roundToInt().toDouble() / 100
    }
}
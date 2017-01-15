package pe.com.pacasmayo.sgcp.persistencia.dataObjects;

/**
 * Cubicacion entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Cubicacion implements java.io.Serializable {

	// Fields

	private Long pkCodigoCubicacion;
	private Cubicacionproducto cubicacionproducto;
	private Medioalmacenamiento medioalmacenamiento;
	private Double volumenM3Cubicacion;
	private Double areaCrestaM2Cubicacion;
	private Double areaPieM2Cubicacion;
	private Double diferenciaNivelMtsCubicacion;
	private String observacionCubicacion;
	private Double areaOcupadaM2Cubicacion;
	private Double relacionCubicacion;
	private Double alturaLadoCarbonMtsCubicaci;
	private Double alturaCentralMtsCubicacion;
	private Double alturaLadoClinkerMtsCubicac;
	private Double alturaLibrePromedioMtsCubic;
	private Double unidadKgCubicacion;
	private Double densidadMedioalmacenamiento;

	// Constructors

	/** default constructor */
	public Cubicacion() {
	}

	/** minimal constructor */
	public Cubicacion(Cubicacionproducto cubicacionproducto, Medioalmacenamiento medioalmacenamiento, Double volumenM3Cubicacion,
			Double areaCrestaM2Cubicacion, Double areaPieM2Cubicacion, Double diferenciaNivelMtsCubicacion,
			Double areaOcupadaM2Cubicacion) {
		this.cubicacionproducto = cubicacionproducto;
		this.medioalmacenamiento = medioalmacenamiento;
		this.volumenM3Cubicacion = volumenM3Cubicacion;
		this.areaCrestaM2Cubicacion = areaCrestaM2Cubicacion;
		this.areaPieM2Cubicacion = areaPieM2Cubicacion;
		this.diferenciaNivelMtsCubicacion = diferenciaNivelMtsCubicacion;
		this.areaOcupadaM2Cubicacion = areaOcupadaM2Cubicacion;
	}

	/** full constructor */
	public Cubicacion(Cubicacionproducto cubicacionproducto, Medioalmacenamiento medioalmacenamiento, Double volumenM3Cubicacion,
			Double areaCrestaM2Cubicacion, Double areaPieM2Cubicacion, Double diferenciaNivelMtsCubicacion,
			String observacionCubicacion, Double areaOcupadaM2Cubicacion, Double relacionCubicacion,
			Double alturaLadoCarbonMtsCubicaci, Double alturaCentralMtsCubicacion, Double alturaLadoClinkerMtsCubicac,
			Double alturaLibrePromedioMtsCubic, Double unidadKgCubicacion, Double densidadMedioalmacenamiento) {
		this.cubicacionproducto = cubicacionproducto;
		this.medioalmacenamiento = medioalmacenamiento;
		this.volumenM3Cubicacion = volumenM3Cubicacion;
		this.areaCrestaM2Cubicacion = areaCrestaM2Cubicacion;
		this.areaPieM2Cubicacion = areaPieM2Cubicacion;
		this.diferenciaNivelMtsCubicacion = diferenciaNivelMtsCubicacion;
		this.observacionCubicacion = observacionCubicacion;
		this.areaOcupadaM2Cubicacion = areaOcupadaM2Cubicacion;
		this.relacionCubicacion = relacionCubicacion;
		this.alturaLadoCarbonMtsCubicaci = alturaLadoCarbonMtsCubicaci;
		this.alturaCentralMtsCubicacion = alturaCentralMtsCubicacion;
		this.alturaLadoClinkerMtsCubicac = alturaLadoClinkerMtsCubicac;
		this.alturaLibrePromedioMtsCubic = alturaLibrePromedioMtsCubic;
		this.unidadKgCubicacion = unidadKgCubicacion;
		this.densidadMedioalmacenamiento = densidadMedioalmacenamiento;
	}

	// Property accessors

	public Long getPkCodigoCubicacion() {
		return this.pkCodigoCubicacion;
	}

	public void setPkCodigoCubicacion(Long pkCodigoCubicacion) {
		this.pkCodigoCubicacion = pkCodigoCubicacion;
	}

	public Cubicacionproducto getCubicacionproducto() {
		return this.cubicacionproducto;
	}

	public void setCubicacionproducto(Cubicacionproducto cubicacionproducto) {
		this.cubicacionproducto = cubicacionproducto;
	}

	public Medioalmacenamiento getMedioalmacenamiento() {
		return this.medioalmacenamiento;
	}

	public void setMedioalmacenamiento(Medioalmacenamiento medioalmacenamiento) {
		this.medioalmacenamiento = medioalmacenamiento;
	}

	public Double getVolumenM3Cubicacion() {
		return this.volumenM3Cubicacion;
	}

	public void setVolumenM3Cubicacion(Double volumenM3Cubicacion) {
		this.volumenM3Cubicacion = volumenM3Cubicacion;
	}

	public Double getAreaCrestaM2Cubicacion() {
		return this.areaCrestaM2Cubicacion;
	}

	public void setAreaCrestaM2Cubicacion(Double areaCrestaM2Cubicacion) {
		this.areaCrestaM2Cubicacion = areaCrestaM2Cubicacion;
	}

	public Double getAreaPieM2Cubicacion() {
		return this.areaPieM2Cubicacion;
	}

	public void setAreaPieM2Cubicacion(Double areaPieM2Cubicacion) {
		this.areaPieM2Cubicacion = areaPieM2Cubicacion;
	}

	public Double getDiferenciaNivelMtsCubicacion() {
		return this.diferenciaNivelMtsCubicacion;
	}

	public void setDiferenciaNivelMtsCubicacion(Double diferenciaNivelMtsCubicacion) {
		this.diferenciaNivelMtsCubicacion = diferenciaNivelMtsCubicacion;
	}

	public String getObservacionCubicacion() {
		return this.observacionCubicacion;
	}

	public void setObservacionCubicacion(String observacionCubicacion) {
		this.observacionCubicacion = observacionCubicacion;
	}

	public Double getAreaOcupadaM2Cubicacion() {
		return this.areaOcupadaM2Cubicacion;
	}

	public void setAreaOcupadaM2Cubicacion(Double areaOcupadaM2Cubicacion) {
		this.areaOcupadaM2Cubicacion = areaOcupadaM2Cubicacion;
	}

	public Double getRelacionCubicacion() {
		return this.relacionCubicacion;
	}

	public void setRelacionCubicacion(Double relacionCubicacion) {
		this.relacionCubicacion = relacionCubicacion;
	}

	public Double getAlturaLadoCarbonMtsCubicaci() {
		return this.alturaLadoCarbonMtsCubicaci;
	}

	public void setAlturaLadoCarbonMtsCubicaci(Double alturaLadoCarbonMtsCubicaci) {
		this.alturaLadoCarbonMtsCubicaci = alturaLadoCarbonMtsCubicaci;
	}

	public Double getAlturaCentralMtsCubicacion() {
		return this.alturaCentralMtsCubicacion;
	}

	public void setAlturaCentralMtsCubicacion(Double alturaCentralMtsCubicacion) {
		this.alturaCentralMtsCubicacion = alturaCentralMtsCubicacion;
	}

	public Double getAlturaLadoClinkerMtsCubicac() {
		return this.alturaLadoClinkerMtsCubicac;
	}

	public void setAlturaLadoClinkerMtsCubicac(Double alturaLadoClinkerMtsCubicac) {
		this.alturaLadoClinkerMtsCubicac = alturaLadoClinkerMtsCubicac;
	}

	public Double getAlturaLibrePromedioMtsCubic() {
		return this.alturaLibrePromedioMtsCubic;
	}

	public void setAlturaLibrePromedioMtsCubic(Double alturaLibrePromedioMtsCubic) {
		this.alturaLibrePromedioMtsCubic = alturaLibrePromedioMtsCubic;
	}

	public Double getUnidadKgCubicacion() {
		return this.unidadKgCubicacion;
	}

	public void setUnidadKgCubicacion(Double unidadKgCubicacion) {
		this.unidadKgCubicacion = unidadKgCubicacion;
	}

	/**
	 * @return the densidadMedioalmacenamiento
	 */
	public Double getDensidadMedioalmacenamiento() {
		return densidadMedioalmacenamiento;
	}

	/**
	 * @param densidadMedioalmacenamiento the densidadMedioalmacenamiento to set
	 */
	public void setDensidadMedioalmacenamiento(Double densidadMedioalmacenamiento) {
		this.densidadMedioalmacenamiento = densidadMedioalmacenamiento;
	}

}
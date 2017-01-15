package pe.com.pacasmayo.sgcp.persistencia.dataObjects;

import java.util.HashSet;
import java.util.Set;

/**
 * Dosificacionregistromensual entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Dosificacionregistromensual implements java.io.Serializable {

	// Fields

	private Long pkCodigoDosificacionregistrom;
	private Plananual plananual;
	private Dosificacion dosificacion;
	private Short mesDosificacionregistromensual;
	private Integer annoDosificacionregistromensua;
	private Double cantidadDosificacionregistrome;
	private Double factorDosificacionDosificacio;
	private Set consumocomponenteplans = new HashSet(0);

	// Constructors

	/** default constructor */
	public Dosificacionregistromensual() {
	}

	/** minimal constructor */
	public Dosificacionregistromensual(Plananual plananual, Dosificacion dosificacion, Short mesDosificacionregistromensual,
			Integer annoDosificacionregistromensua, Double cantidadDosificacionregistrome, Double factorDosificacionDosificacio) {
		this.plananual = plananual;
		this.dosificacion = dosificacion;
		this.mesDosificacionregistromensual = mesDosificacionregistromensual;
		this.annoDosificacionregistromensua = annoDosificacionregistromensua;
		this.cantidadDosificacionregistrome = cantidadDosificacionregistrome;
		this.factorDosificacionDosificacio = factorDosificacionDosificacio;
	}

	/** full constructor */
	public Dosificacionregistromensual(Plananual plananual, Dosificacion dosificacion, Short mesDosificacionregistromensual,
			Integer annoDosificacionregistromensua, Double cantidadDosificacionregistrome, Double factorDosificacionDosificacio,
			Set consumocomponenteplans) {
		this.plananual = plananual;
		this.dosificacion = dosificacion;
		this.mesDosificacionregistromensual = mesDosificacionregistromensual;
		this.annoDosificacionregistromensua = annoDosificacionregistromensua;
		this.cantidadDosificacionregistrome = cantidadDosificacionregistrome;
		this.factorDosificacionDosificacio = factorDosificacionDosificacio;
		this.consumocomponenteplans = consumocomponenteplans;
	}

	// Property accessors

	public Long getPkCodigoDosificacionregistrom() {
		return this.pkCodigoDosificacionregistrom;
	}

	public void setPkCodigoDosificacionregistrom(Long pkCodigoDosificacionregistrom) {
		this.pkCodigoDosificacionregistrom = pkCodigoDosificacionregistrom;
	}

	public Plananual getPlananual() {
		return this.plananual;
	}

	public void setPlananual(Plananual plananual) {
		this.plananual = plananual;
	}

	public Dosificacion getDosificacion() {
		return this.dosificacion;
	}

	public void setDosificacion(Dosificacion dosificacion) {
		this.dosificacion = dosificacion;
	}

	public Short getMesDosificacionregistromensual() {
		return this.mesDosificacionregistromensual;
	}

	public void setMesDosificacionregistromensual(Short mesDosificacionregistromensual) {
		this.mesDosificacionregistromensual = mesDosificacionregistromensual;
	}

	public Integer getAnnoDosificacionregistromensua() {
		return this.annoDosificacionregistromensua;
	}

	public void setAnnoDosificacionregistromensua(Integer annoDosificacionregistromensua) {
		this.annoDosificacionregistromensua = annoDosificacionregistromensua;
	}

	public Double getCantidadDosificacionregistrome() {
		return this.cantidadDosificacionregistrome;
	}

	public void setCantidadDosificacionregistrome(Double cantidadDosificacionregistrome) {
		this.cantidadDosificacionregistrome = cantidadDosificacionregistrome;
	}

	public Double getFactorDosificacionDosificacio() {
		return this.factorDosificacionDosificacio;
	}

	public void setFactorDosificacionDosificacio(Double factorDosificacionDosificacio) {
		this.factorDosificacionDosificacio = factorDosificacionDosificacio;
	}

	public Set getConsumocomponenteplans() {
		return this.consumocomponenteplans;
	}

	public void setConsumocomponenteplans(Set consumocomponenteplans) {
		this.consumocomponenteplans = consumocomponenteplans;
	}

}
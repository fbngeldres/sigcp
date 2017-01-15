package pe.com.pacasmayo.sgcp.persistencia.dataObjects;

import java.util.HashSet;
import java.util.Set;

/**
 * Medicion entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Medicion implements java.io.Serializable {

	// Fields

	private Long pkCodigoMedicion;
	private Registromedicion registromedicion;
	private Produccion produccion;
	private Medioalmacenamiento medioalmacenamiento;
	private Double cantidadAlmacenadaMedicion;
	private Double porcentajeUsoMedicion;
	private Short numeroMedidasMedicion;
	private Set alturasilos = new HashSet(0);

	// Constructors

	/** default constructor */
	public Medicion() {
	}

	/** minimal constructor */
	public Medicion(Registromedicion registromedicion, Produccion produccion, Medioalmacenamiento medioalmacenamiento,
			Double cantidadAlmacenadaMedicion, Double porcentajeUsoMedicion) {
		this.registromedicion = registromedicion;
		this.produccion = produccion;
		this.medioalmacenamiento = medioalmacenamiento;
		this.cantidadAlmacenadaMedicion = cantidadAlmacenadaMedicion;
		this.porcentajeUsoMedicion = porcentajeUsoMedicion;
	}

	/** full constructor */
	public Medicion(Registromedicion registromedicion, Produccion produccion, Medioalmacenamiento medioalmacenamiento,
			Double cantidadAlmacenadaMedicion, Double porcentajeUsoMedicion, Short numeroMedidasMedicion, Set alturasilos) {
		this.registromedicion = registromedicion;
		this.produccion = produccion;
		this.medioalmacenamiento = medioalmacenamiento;
		this.cantidadAlmacenadaMedicion = cantidadAlmacenadaMedicion;
		this.porcentajeUsoMedicion = porcentajeUsoMedicion;
		this.numeroMedidasMedicion = numeroMedidasMedicion;
		this.alturasilos = alturasilos;
	}

	// Property accessors

	public Long getPkCodigoMedicion() {
		return this.pkCodigoMedicion;
	}

	public void setPkCodigoMedicion(Long pkCodigoMedicion) {
		this.pkCodigoMedicion = pkCodigoMedicion;
	}

	public Registromedicion getRegistromedicion() {
		return this.registromedicion;
	}

	public void setRegistromedicion(Registromedicion registromedicion) {
		this.registromedicion = registromedicion;
	}

	public Produccion getProduccion() {
		return this.produccion;
	}

	public void setProduccion(Produccion produccion) {
		this.produccion = produccion;
	}

	public Medioalmacenamiento getMedioalmacenamiento() {
		return this.medioalmacenamiento;
	}

	public void setMedioalmacenamiento(Medioalmacenamiento medioalmacenamiento) {
		this.medioalmacenamiento = medioalmacenamiento;
	}

	public Double getCantidadAlmacenadaMedicion() {
		return this.cantidadAlmacenadaMedicion;
	}

	public void setCantidadAlmacenadaMedicion(Double cantidadAlmacenadaMedicion) {
		this.cantidadAlmacenadaMedicion = cantidadAlmacenadaMedicion;
	}

	public Double getPorcentajeUsoMedicion() {
		return this.porcentajeUsoMedicion;
	}

	public void setPorcentajeUsoMedicion(Double porcentajeUsoMedicion) {
		this.porcentajeUsoMedicion = porcentajeUsoMedicion;
	}

	public Short getNumeroMedidasMedicion() {
		return this.numeroMedidasMedicion;
	}

	public void setNumeroMedidasMedicion(Short numeroMedidasMedicion) {
		this.numeroMedidasMedicion = numeroMedidasMedicion;
	}

	public Set getAlturasilos() {
		return this.alturasilos;
	}

	public void setAlturasilos(Set alturasilos) {
		this.alturasilos = alturasilos;
	}

}
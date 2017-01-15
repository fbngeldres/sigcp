package pe.com.pacasmayo.sgcp.persistencia.dataObjects;

import java.util.HashSet;
import java.util.Set;

/**
 * Recursoregistromensual entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Recursoregistromensual implements java.io.Serializable {

	// Fields

	private Long pkCodigoRecursoregistromensua;
	private Plananual plananual;
	private Recurso recurso;
	private Operacion operacion;
	private Short mesRecursoregistromensual;
	private Integer annoRecursoregistromensual;
	private Double cantidadRecursoregistromensual;
	private Set consumorecursoplans = new HashSet(0);

	// Constructors

	/** default constructor */
	public Recursoregistromensual() {
	}

	/** minimal constructor */
	public Recursoregistromensual(Short mesRecursoregistromensual, Integer annoRecursoregistromensual,
			Double cantidadRecursoregistromensual) {
		this.mesRecursoregistromensual = mesRecursoregistromensual;
		this.annoRecursoregistromensual = annoRecursoregistromensual;
		this.cantidadRecursoregistromensual = cantidadRecursoregistromensual;
	}

	/** full constructor */
	public Recursoregistromensual(Plananual plananual, Recurso recurso, Operacion operacion, Short mesRecursoregistromensual,
			Integer annoRecursoregistromensual, Double cantidadRecursoregistromensual, Set consumorecursoplans) {
		this.plananual = plananual;
		this.recurso = recurso;
		this.operacion = operacion;
		this.mesRecursoregistromensual = mesRecursoregistromensual;
		this.annoRecursoregistromensual = annoRecursoregistromensual;
		this.cantidadRecursoregistromensual = cantidadRecursoregistromensual;
		this.consumorecursoplans = consumorecursoplans;
	}

	// Property accessors

	public Long getPkCodigoRecursoregistromensua() {
		return this.pkCodigoRecursoregistromensua;
	}

	public void setPkCodigoRecursoregistromensua(Long pkCodigoRecursoregistromensua) {
		this.pkCodigoRecursoregistromensua = pkCodigoRecursoregistromensua;
	}

	public Plananual getPlananual() {
		return this.plananual;
	}

	public void setPlananual(Plananual plananual) {
		this.plananual = plananual;
	}

	public Recurso getRecurso() {
		return this.recurso;
	}

	public void setRecurso(Recurso recurso) {
		this.recurso = recurso;
	}

	public Operacion getOperacion() {
		return this.operacion;
	}

	public void setOperacion(Operacion operacion) {
		this.operacion = operacion;
	}

	public Short getMesRecursoregistromensual() {
		return this.mesRecursoregistromensual;
	}

	public void setMesRecursoregistromensual(Short mesRecursoregistromensual) {
		this.mesRecursoregistromensual = mesRecursoregistromensual;
	}

	public Integer getAnnoRecursoregistromensual() {
		return this.annoRecursoregistromensual;
	}

	public void setAnnoRecursoregistromensual(Integer annoRecursoregistromensual) {
		this.annoRecursoregistromensual = annoRecursoregistromensual;
	}

	public Double getCantidadRecursoregistromensual() {
		return this.cantidadRecursoregistromensual;
	}

	public void setCantidadRecursoregistromensual(Double cantidadRecursoregistromensual) {
		this.cantidadRecursoregistromensual = cantidadRecursoregistromensual;
	}

	public Set getConsumorecursoplans() {
		return this.consumorecursoplans;
	}

	public void setConsumorecursoplans(Set consumorecursoplans) {
		this.consumorecursoplans = consumorecursoplans;
	}

}
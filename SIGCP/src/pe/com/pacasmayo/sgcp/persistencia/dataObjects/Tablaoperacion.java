package pe.com.pacasmayo.sgcp.persistencia.dataObjects;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Tablaoperacion entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Tablaoperacion implements java.io.Serializable {

	// Fields

	private Long pkCodigoTablaoperacion;
	private Produccionpuestotrabajo produccionpuestotrabajo;
	private Date fechaTablaoperacion;
	private Double totalTmTablaoperacion;
	private Double totalHoraTablaoperacion;
	private Set productogenerados = new HashSet(0);

	// Constructors

	/** default constructor */
	public Tablaoperacion() {
	}

	/** minimal constructor */
	public Tablaoperacion(Produccionpuestotrabajo produccionpuestotrabajo, Date fechaTablaoperacion,
			Double totalTmTablaoperacion, Double totalHoraTablaoperacion) {
		this.produccionpuestotrabajo = produccionpuestotrabajo;
		this.fechaTablaoperacion = fechaTablaoperacion;
		this.totalTmTablaoperacion = totalTmTablaoperacion;
		this.totalHoraTablaoperacion = totalHoraTablaoperacion;
	}

	/** full constructor */
	public Tablaoperacion(Produccionpuestotrabajo produccionpuestotrabajo, Date fechaTablaoperacion,
			Double totalTmTablaoperacion, Double totalHoraTablaoperacion, Set productogenerados) {
		this.produccionpuestotrabajo = produccionpuestotrabajo;
		this.fechaTablaoperacion = fechaTablaoperacion;
		this.totalTmTablaoperacion = totalTmTablaoperacion;
		this.totalHoraTablaoperacion = totalHoraTablaoperacion;
		this.productogenerados = productogenerados;
	}

	// Property accessors

	public Long getPkCodigoTablaoperacion() {
		return this.pkCodigoTablaoperacion;
	}

	public void setPkCodigoTablaoperacion(Long pkCodigoTablaoperacion) {
		this.pkCodigoTablaoperacion = pkCodigoTablaoperacion;
	}

	public Produccionpuestotrabajo getProduccionpuestotrabajo() {
		return this.produccionpuestotrabajo;
	}

	public void setProduccionpuestotrabajo(Produccionpuestotrabajo produccionpuestotrabajo) {
		this.produccionpuestotrabajo = produccionpuestotrabajo;
	}

	public Date getFechaTablaoperacion() {
		return this.fechaTablaoperacion;
	}

	public void setFechaTablaoperacion(Date fechaTablaoperacion) {
		this.fechaTablaoperacion = fechaTablaoperacion;
	}

	public Double getTotalTmTablaoperacion() {
		return this.totalTmTablaoperacion;
	}

	public void setTotalTmTablaoperacion(Double totalTmTablaoperacion) {
		this.totalTmTablaoperacion = totalTmTablaoperacion;
	}

	public Double getTotalHoraTablaoperacion() {
		return this.totalHoraTablaoperacion;
	}

	public void setTotalHoraTablaoperacion(Double totalHoraTablaoperacion) {
		this.totalHoraTablaoperacion = totalHoraTablaoperacion;
	}

	public Set getProductogenerados() {
		return this.productogenerados;
	}

	public void setProductogenerados(Set productogenerados) {
		this.productogenerados = productogenerados;
	}

}
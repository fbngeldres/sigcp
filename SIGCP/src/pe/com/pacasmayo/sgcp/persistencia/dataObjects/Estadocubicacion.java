package pe.com.pacasmayo.sgcp.persistencia.dataObjects;

import java.util.HashSet;
import java.util.Set;

/**
 * Estadocubicacion entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Estadocubicacion implements java.io.Serializable {

	// Fields

	private Long pkCodigoEstadocubicacion;
	private String nombreEstadocubicacion;
	private Set cubicacionproductos = new HashSet(0);

	// Constructors

	/** default constructor */
	public Estadocubicacion() {
	}

	/** minimal constructor */
	public Estadocubicacion(String nombreEstadocubicacion) {
		this.nombreEstadocubicacion = nombreEstadocubicacion;
	}

	/** full constructor */
	public Estadocubicacion(String nombreEstadocubicacion, Set cubicacionproductos) {
		this.nombreEstadocubicacion = nombreEstadocubicacion;
		this.cubicacionproductos = cubicacionproductos;
	}

	// Property accessors

	public Long getPkCodigoEstadocubicacion() {
		return this.pkCodigoEstadocubicacion;
	}

	public void setPkCodigoEstadocubicacion(Long pkCodigoEstadocubicacion) {
		this.pkCodigoEstadocubicacion = pkCodigoEstadocubicacion;
	}

	public String getNombreEstadocubicacion() {
		return this.nombreEstadocubicacion;
	}

	public void setNombreEstadocubicacion(String nombreEstadocubicacion) {
		this.nombreEstadocubicacion = nombreEstadocubicacion;
	}

	public Set getCubicacionproductos() {
		return this.cubicacionproductos;
	}

	public void setCubicacionproductos(Set cubicacionproductos) {
		this.cubicacionproductos = cubicacionproductos;
	}

}
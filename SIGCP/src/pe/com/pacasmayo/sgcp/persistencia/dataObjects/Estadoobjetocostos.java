package pe.com.pacasmayo.sgcp.persistencia.dataObjects;

import java.util.HashSet;
import java.util.Set;

/**
 * Estadoobjetocostos entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Estadoobjetocostos implements java.io.Serializable {

	// Fields

	private Long pkCodigoEstadoobjetocostos;
	private String nombreEstadoobjetocostos;
	private Set objetocostoses = new HashSet(0);

	// Constructors

	/** default constructor */
	public Estadoobjetocostos() {
	}

	/** minimal constructor */
	public Estadoobjetocostos(String nombreEstadoobjetocostos) {
		this.nombreEstadoobjetocostos = nombreEstadoobjetocostos;
	}

	/** full constructor */
	public Estadoobjetocostos(String nombreEstadoobjetocostos, Set objetocostoses) {
		this.nombreEstadoobjetocostos = nombreEstadoobjetocostos;
		this.objetocostoses = objetocostoses;
	}

	// Property accessors

	public Long getPkCodigoEstadoobjetocostos() {
		return this.pkCodigoEstadoobjetocostos;
	}

	public void setPkCodigoEstadoobjetocostos(Long pkCodigoEstadoobjetocostos) {
		this.pkCodigoEstadoobjetocostos = pkCodigoEstadoobjetocostos;
	}

	public String getNombreEstadoobjetocostos() {
		return this.nombreEstadoobjetocostos;
	}

	public void setNombreEstadoobjetocostos(String nombreEstadoobjetocostos) {
		this.nombreEstadoobjetocostos = nombreEstadoobjetocostos;
	}

	public Set getObjetocostoses() {
		return this.objetocostoses;
	}

	public void setObjetocostoses(Set objetocostoses) {
		this.objetocostoses = objetocostoses;
	}

}
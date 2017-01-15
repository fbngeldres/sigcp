package pe.com.pacasmayo.sgcp.persistencia.dataObjects;

import java.util.HashSet;
import java.util.Set;

/**
 * Tipoobjetocostos entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Tipoobjetocostos implements java.io.Serializable {

	// Fields

	private Long pkCodigoTipoobjetocostos;
	private String nombreTipoobjetocostos;
	private Set objetocostoses = new HashSet(0);

	// Constructors

	/** default constructor */
	public Tipoobjetocostos() {
	}

	/** minimal constructor */
	public Tipoobjetocostos(String nombreTipoobjetocostos) {
		this.nombreTipoobjetocostos = nombreTipoobjetocostos;
	}

	/** full constructor */
	public Tipoobjetocostos(String nombreTipoobjetocostos, Set objetocostoses) {
		this.nombreTipoobjetocostos = nombreTipoobjetocostos;
		this.objetocostoses = objetocostoses;
	}

	// Property accessors

	public Long getPkCodigoTipoobjetocostos() {
		return this.pkCodigoTipoobjetocostos;
	}

	public void setPkCodigoTipoobjetocostos(Long pkCodigoTipoobjetocostos) {
		this.pkCodigoTipoobjetocostos = pkCodigoTipoobjetocostos;
	}

	public String getNombreTipoobjetocostos() {
		return this.nombreTipoobjetocostos;
	}

	public void setNombreTipoobjetocostos(String nombreTipoobjetocostos) {
		this.nombreTipoobjetocostos = nombreTipoobjetocostos;
	}

	public Set getObjetocostoses() {
		return this.objetocostoses;
	}

	public void setObjetocostoses(Set objetocostoses) {
		this.objetocostoses = objetocostoses;
	}

}
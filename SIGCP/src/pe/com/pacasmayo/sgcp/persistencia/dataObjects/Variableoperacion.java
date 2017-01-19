package pe.com.pacasmayo.sgcp.persistencia.dataObjects;

import java.util.HashSet;
import java.util.Set;

/**
 * Variableoperacion entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Variableoperacion implements java.io.Serializable {

	// Fields

	private Long pkCodigoVariableoperacion;
	private Unidadmedida unidadmedida;
	private Tipovariableoperacion tipovariableoperacion;
	private String nombreVariableoperacion;
	private Set columnaplantillaproductos = new HashSet(0);

	// Constructors

	/** default constructor */
	public Variableoperacion() {
	}

	/** minimal constructor */
	public Variableoperacion(Unidadmedida unidadmedida, Tipovariableoperacion tipovariableoperacion,
			String nombreVariableoperacion) {
		this.unidadmedida = unidadmedida;
		this.tipovariableoperacion = tipovariableoperacion;
		this.nombreVariableoperacion = nombreVariableoperacion;
	}

	/** full constructor */
	public Variableoperacion(Unidadmedida unidadmedida, Tipovariableoperacion tipovariableoperacion,
			String nombreVariableoperacion, Set columnaplantillaproductos
		) {
		this.unidadmedida = unidadmedida;
		this.tipovariableoperacion = tipovariableoperacion;
		this.nombreVariableoperacion = nombreVariableoperacion;
		this.columnaplantillaproductos = columnaplantillaproductos;
		
	}

	// Property accessors

	public Long getPkCodigoVariableoperacion() {
		return this.pkCodigoVariableoperacion;
	}

	public void setPkCodigoVariableoperacion(Long pkCodigoVariableoperacion) {
		this.pkCodigoVariableoperacion = pkCodigoVariableoperacion;
	}

	public Unidadmedida getUnidadmedida() {
		return this.unidadmedida;
	}

	public void setUnidadmedida(Unidadmedida unidadmedida) {
		this.unidadmedida = unidadmedida;
	}

	public Tipovariableoperacion getTipovariableoperacion() {
		return this.tipovariableoperacion;
	}

	public void setTipovariableoperacion(Tipovariableoperacion tipovariableoperacion) {
		this.tipovariableoperacion = tipovariableoperacion;
	}

	public String getNombreVariableoperacion() {
		return this.nombreVariableoperacion;
	}

	public void setNombreVariableoperacion(String nombreVariableoperacion) {
		this.nombreVariableoperacion = nombreVariableoperacion;
	}

	public Set getColumnaplantillaproductos() {
		return this.columnaplantillaproductos;
	}

	public void setColumnaplantillaproductos(Set columnaplantillaproductos) {
		this.columnaplantillaproductos = columnaplantillaproductos;
	}

	

}
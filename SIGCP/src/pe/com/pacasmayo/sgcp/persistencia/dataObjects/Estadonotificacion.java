package pe.com.pacasmayo.sgcp.persistencia.dataObjects;

import java.util.HashSet;
import java.util.Set;

/**
 * Estadonotificacion entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Estadonotificacion implements java.io.Serializable {

	// Fields

	private Long pkCodigoEstadonotificacion;
	private String nombreEstadonotificacion;

	// Constructors

	/** default constructor */
	public Estadonotificacion() {
	}

	/** minimal constructor */
	public Estadonotificacion(String nombreEstadonotificacion) {
		this.nombreEstadonotificacion = nombreEstadonotificacion;
	}

	/** full constructor */
	public Estadonotificacion(String nombreEstadonotificacion,
			Set notificaciondiarias) {
		this.nombreEstadonotificacion = nombreEstadonotificacion;

	}

	// Property accessors

	public Long getPkCodigoEstadonotificacion() {
		return this.pkCodigoEstadonotificacion;
	}

	public void setPkCodigoEstadonotificacion(Long pkCodigoEstadonotificacion) {
		this.pkCodigoEstadonotificacion = pkCodigoEstadonotificacion;
	}

	public String getNombreEstadonotificacion() {
		return this.nombreEstadonotificacion;
	}

	public void setNombreEstadonotificacion(String nombreEstadonotificacion) {
		this.nombreEstadonotificacion = nombreEstadonotificacion;
	}

}
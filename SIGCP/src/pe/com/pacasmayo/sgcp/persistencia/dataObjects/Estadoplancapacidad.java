package pe.com.pacasmayo.sgcp.persistencia.dataObjects;

import java.util.HashSet;
import java.util.Set;

/**
 * Estadoplananual entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Estadoplancapacidad implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long pkCodigoEstadoplancapacidad;
	private String nombreEstadoplancapacidad;
	private String descripcionEstadoplancapacidad;
	private Set plancapacidads = new HashSet(0);

	public Estadoplancapacidad() {
		// TODO Auto-generated constructor stub
	}

	public Estadoplancapacidad(Long pkCodigoEstadoplancapacidad, String nombreEstadoplancapacidad,
			String descripcionEstadoplancapacidad, Set plancapacidads) {
		super();
		this.pkCodigoEstadoplancapacidad = pkCodigoEstadoplancapacidad;
		this.nombreEstadoplancapacidad = nombreEstadoplancapacidad;
		this.descripcionEstadoplancapacidad = descripcionEstadoplancapacidad;
		this.plancapacidads = plancapacidads;
	}

	/**
	 * @return the pkCodigoEstadoplancapacidad
	 */
	public Long getPkCodigoEstadoplancapacidad() {
		return pkCodigoEstadoplancapacidad;
	}

	/**
	 * @param pkCodigoEstadoplancapacidad the pkCodigoEstadoplancapacidad to set
	 */
	public void setPkCodigoEstadoplancapacidad(Long pkCodigoEstadoplancapacidad) {
		this.pkCodigoEstadoplancapacidad = pkCodigoEstadoplancapacidad;
	}

	/**
	 * @return the nombreEstadoplancapacidad
	 */
	public String getNombreEstadoplancapacidad() {
		return nombreEstadoplancapacidad;
	}

	/**
	 * @param nombreEstadoplancapacidad the nombreEstadoplancapacidad to set
	 */
	public void setNombreEstadoplancapacidad(String nombreEstadoplancapacidad) {
		this.nombreEstadoplancapacidad = nombreEstadoplancapacidad;
	}

	/**
	 * @return the descripcionEstadoplancapacidad
	 */
	public String getDescripcionEstadoplancapacidad() {
		return descripcionEstadoplancapacidad;
	}

	/**
	 * @param descripcionEstadoplancapacidad the descripcionEstadoplancapacidad
	 *            to set
	 */
	public void setDescripcionEstadoplancapacidad(String descripcionEstadoplancapacidad) {
		this.descripcionEstadoplancapacidad = descripcionEstadoplancapacidad;
	}

	/**
	 * @return the plancapacidads
	 */
	public Set getPlancapacidads() {
		return plancapacidads;
	}

	/**
	 * @param plancapacidads the plancapacidads to set
	 */
	public void setPlancapacidads(Set plancapacidads) {
		this.plancapacidads = plancapacidads;
	}

	/**
	 * @return the serialVersionUID
	 */
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

}
package pe.com.pacasmayo.sgcp.persistencia.dataObjects;

import java.util.HashSet;
import java.util.Set;

/**
 * Plancapacidad entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Plancapacidad implements java.io.Serializable {

	// Fields

	private Long pkCodigoPlancapacidad;
	private Unidadmedida unidadmedida;
	private Puestotrabajo puestotrabajo;
	private Set plancapacidadregistromensuals = new HashSet(0);
	private Integer annoPlancapacidad;
	private String versionPlancapacidad;
	private Estadoplancapacidad estadoplancapacidad;
	private Lineanegocio lineanegocio;

	// Constructors

	/** default constructor */
	public Plancapacidad() {
	}

	/** full constructor */
	public Plancapacidad(Unidadmedida unidadmedida, Puestotrabajo puestotrabajo, Set plancapacidadregistromensuals,
			Integer annoPlancapacidad, String versionPlancapacidad, Estadoplancapacidad estadoplancapacidad,
			Lineanegocio lineanegocio) {
		this.unidadmedida = unidadmedida;
		this.puestotrabajo = puestotrabajo;
		this.plancapacidadregistromensuals = plancapacidadregistromensuals;
		this.annoPlancapacidad = annoPlancapacidad;
		this.versionPlancapacidad = versionPlancapacidad;
		this.estadoplancapacidad = estadoplancapacidad;
		this.lineanegocio = lineanegocio;
	}

	// Property accessors

	public Long getPkCodigoPlancapacidad() {
		return this.pkCodigoPlancapacidad;
	}

	public void setPkCodigoPlancapacidad(Long pkCodigoPlancapacidad) {
		this.pkCodigoPlancapacidad = pkCodigoPlancapacidad;
	}

	public Unidadmedida getUnidadmedida() {
		return this.unidadmedida;
	}

	public void setUnidadmedida(Unidadmedida unidadmedida) {
		this.unidadmedida = unidadmedida;
	}

	public Puestotrabajo getPuestotrabajo() {
		return this.puestotrabajo;
	}

	public void setPuestotrabajo(Puestotrabajo puestotrabajo) {
		this.puestotrabajo = puestotrabajo;
	}

	public Set getPlancapacidadregistromensuals() {
		return this.plancapacidadregistromensuals;
	}

	public void setPlancapacidadregistromensuals(Set plancapacidadregistromensuals) {
		this.plancapacidadregistromensuals = plancapacidadregistromensuals;
	}

	/**
	 * @return the annoPlancapacidad
	 */
	public Integer getAnnoPlancapacidad() {
		return annoPlancapacidad;
	}

	/**
	 * @param annoPlancapacidad the annoPlancapacidad to set
	 */
	public void setAnnoPlancapacidad(Integer annoPlancapacidad) {
		this.annoPlancapacidad = annoPlancapacidad;
	}

	/**
	 * @return the versionPlancapacidad
	 */
	public String getVersionPlancapacidad() {
		return versionPlancapacidad;
	}

	/**
	 * @param versionPlancapacidad the versionPlancapacidad to set
	 */
	public void setVersionPlancapacidad(String versionPlancapacidad) {
		this.versionPlancapacidad = versionPlancapacidad;
	}

	/**
	 * @return the estadoplancapacidad
	 */
	public Estadoplancapacidad getEstadoplancapacidad() {
		return estadoplancapacidad;
	}

	/**
	 * @param estadoplancapacidad the estadoplancapacidad to set
	 */
	public void setEstadoplancapacidad(Estadoplancapacidad estadoplancapacidad) {
		this.estadoplancapacidad = estadoplancapacidad;
	}

	/**
	 * @return the lineanegocio
	 */
	public Lineanegocio getLineanegocio() {
		return lineanegocio;
	}

	/**
	 * @param lineanegocio the lineanegocio to set
	 */
	public void setLineanegocio(Lineanegocio lineanegocio) {
		this.lineanegocio = lineanegocio;
	}

}
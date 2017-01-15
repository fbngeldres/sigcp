package pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente;

import java.util.Date;

public class RegistroreporteecsDTO implements java.io.Serializable {

	// Fields

	private Long pkCodigoRegistroreporteecs;
	private Date fechaRegistroreporteecs;
	private String nombreRegistroreporteecs;

	public RegistroreporteecsDTO() {

	}

	/**
	 * @return the pkCodigoRegistroreporteecs
	 */
	public Long getPkCodigoRegistroreporteecs() {
		return pkCodigoRegistroreporteecs;
	}

	/**
	 * @param pkCodigoRegistroreporteecs the pkCodigoRegistroreporteecs to set
	 */
	public void setPkCodigoRegistroreporteecs(Long pkCodigoRegistroreporteecs) {
		this.pkCodigoRegistroreporteecs = pkCodigoRegistroreporteecs;
	}

	/**
	 * @return the fechaRegistroreporteecs
	 */
	public Date getFechaRegistroreporteecs() {
		return fechaRegistroreporteecs;
	}

	/**
	 * @param fechaRegistroreporteecs the fechaRegistroreporteecs to set
	 */
	public void setFechaRegistroreporteecs(Date fechaRegistroreporteecs) {
		this.fechaRegistroreporteecs = fechaRegistroreporteecs;
	}

	/**
	 * @return the nombreRegistroreporteecs
	 */
	public String getNombreRegistroreporteecs() {
		return nombreRegistroreporteecs;
	}

	/**
	 * @param nombreRegistroreporteecs the nombreRegistroreporteecs to set
	 */
	public void setNombreRegistroreporteecs(String nombreRegistroreporteecs) {
		this.nombreRegistroreporteecs = nombreRegistroreporteecs;
	}
}
package pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente;

public class HoraUbicacionDTO implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long codigoHoraUbicacion;
	private Long pkCodigoHora;
	private Short horaHora;

	public Long getPkCodigoHora() {
		return pkCodigoHora;
	}

	public void setPkCodigoHora(Long pkCodigoHora) {
		this.pkCodigoHora = pkCodigoHora;
	}

	public Short getHoraHora() {
		return horaHora;
	}

	public void setHoraHora(Short horaHora) {
		this.horaHora = horaHora;
	}

	/**
	 * @return the codigoHoraUbicacion
	 */
	public Long getCodigoHoraUbicacion() {
		return codigoHoraUbicacion;
	}

	/**
	 * @param codigoHoraUbicacion the codigoHoraUbicacion to set
	 */
	public void setCodigoHoraUbicacion(Long codigoHoraUbicacion) {
		this.codigoHoraUbicacion = codigoHoraUbicacion;
	}
}
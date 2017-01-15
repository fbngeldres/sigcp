package pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente;

public class HoraDTO implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long pkCodigoHora;
	private Short horaHora;
	private Long codigoTurno;

	public Long getCodigoTurno() {
		return codigoTurno;
	}

	public void setCodigoTurno(Long codigoTurno) {
		this.codigoTurno = codigoTurno;
	}

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
}
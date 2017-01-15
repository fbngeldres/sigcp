package pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente;

public class HoraPuntaDTO implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Long pkCodigoHorapunta;
	private Long hora;
	private Long tipoHoraPunta;
	private Long codigoPuestoTrabajo;

	public Long getCodigoPuestoTrabajo() {
		return codigoPuestoTrabajo;
	}

	public void setCodigoPuestoTrabajo(Long codigoPuestoTrabajo) {
		this.codigoPuestoTrabajo = codigoPuestoTrabajo;
	}

	public Long getPkCodigoHorapunta() {
		return pkCodigoHorapunta;
	}

	public void setPkCodigoHorapunta(Long pkCodigoHorapunta) {
		this.pkCodigoHorapunta = pkCodigoHorapunta;
	}

	public Long getHora() {
		return hora;
	}

	public void setHora(Long hora) {
		this.hora = hora;
	}

	public Long getTipoHoraPunta() {
		return tipoHoraPunta;
	}

	public void setTipoHoraPunta(Long tipoHoraPunta) {
		this.tipoHoraPunta = tipoHoraPunta;
	}

}
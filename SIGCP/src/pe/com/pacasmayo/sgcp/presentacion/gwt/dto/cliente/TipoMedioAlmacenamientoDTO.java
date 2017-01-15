package pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente;

public class TipoMedioAlmacenamientoDTO implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private Long pkCodigoTipomedioalmacenamien;

	private String nombreTipomedioalmacenamiento;

	public Long getPkCodigoTipomedioalmacenamien() {
		return pkCodigoTipomedioalmacenamien;
	}

	public void setPkCodigoTipomedioalmacenamien(Long pkCodigoTipomedioalmacenamien) {
		this.pkCodigoTipomedioalmacenamien = pkCodigoTipomedioalmacenamien;
	}

	public String getNombreTipomedioalmacenamiento() {
		return nombreTipomedioalmacenamiento;
	}

	public void setNombreTipomedioalmacenamiento(String nombreTipomedioalmacenamiento) {
		this.nombreTipomedioalmacenamiento = nombreTipomedioalmacenamiento;
	}
}

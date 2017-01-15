package pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente;

public class MedioAlmacenamientoDTO implements java.io.Serializable {

	private static final long serialVersionUID = 3134922718755215347L;

	private Long pkCodigoMedioalmacenamiento;
	private String nombreMedioalmacenamiento;
	private Short numeroMedioalmacenamiento;

	public Long getPkCodigoMedioalmacenamiento() {
		return pkCodigoMedioalmacenamiento;
	}

	public void setPkCodigoMedioalmacenamiento(Long pkCodigoMedioalmacenamiento) {
		this.pkCodigoMedioalmacenamiento = pkCodigoMedioalmacenamiento;
	}

	public String getNombreMedioalmacenamiento() {
		return nombreMedioalmacenamiento;
	}

	public void setNombreMedioalmacenamiento(String nombreMedioalmacenamiento) {
		this.nombreMedioalmacenamiento = nombreMedioalmacenamiento;
	}

	public Short getNumeroMedioalmacenamiento() {
		return numeroMedioalmacenamiento;
	}

	public void setNumeroMedioalmacenamiento(Short numeroMedioalmacenamiento) {
		this.numeroMedioalmacenamiento = numeroMedioalmacenamiento;
	}

}

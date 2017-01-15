package pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente;

public class TableroControlDTO implements java.io.Serializable {

	private static final long serialVersionUID = 713634128947709436L;
	private Long pkCodigoTableroControl;
	private String nombreTableroControl;
	private String descripcionTableroControl;

	public Long getPkCodigoTableroControl() {
		return pkCodigoTableroControl;
	}

	public void setPkCodigoTableroControl(Long pkCodigoTableroControl) {
		this.pkCodigoTableroControl = pkCodigoTableroControl;
	}

	public String getNombreTableroControl() {
		return nombreTableroControl;
	}

	public void setNombreTableroControl(String nombreTableroControl) {
		this.nombreTableroControl = nombreTableroControl;
	}

	public String getDescripcionTableroControl() {
		return descripcionTableroControl;
	}

	public void setDescripcionTableroControl(String descripcionTableroControl) {
		this.descripcionTableroControl = descripcionTableroControl;
	}
}

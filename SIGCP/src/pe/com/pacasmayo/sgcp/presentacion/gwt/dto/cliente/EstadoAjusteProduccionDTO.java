package pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente;

public class EstadoAjusteProduccionDTO implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4542250381554983070L;
	private Long pkCodigoEstadoajusteproduccio;
	private String nombreEstadoajusteproduccion;

	public Long getPkCodigoEstadoajusteproduccio() {
		return pkCodigoEstadoajusteproduccio;
	}

	public void setPkCodigoEstadoajusteproduccio(Long pkCodigoEstadoajusteproduccio) {
		this.pkCodigoEstadoajusteproduccio = pkCodigoEstadoajusteproduccio;
	}

	public String getNombreEstadoajusteproduccion() {
		return nombreEstadoajusteproduccion;
	}

	public void setNombreEstadoajusteproduccion(String nombreEstadoajusteproduccion) {
		this.nombreEstadoajusteproduccion = nombreEstadoajusteproduccion;
	}
}

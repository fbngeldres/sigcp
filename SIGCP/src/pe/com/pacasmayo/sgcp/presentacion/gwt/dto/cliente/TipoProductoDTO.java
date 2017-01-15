package pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente;

/**
 * Division entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class TipoProductoDTO implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private Long pkCodigoTipoProducto;
	private String nombreTipoProducto;
	private String siglasTipoProducto;

	// Constructors

	/** default constructor */
	public TipoProductoDTO() {
	}

	public Long getPkCodigoTipoProducto() {
		return pkCodigoTipoProducto;
	}

	public void setPkCodigoTipoProducto(Long pkCodigoTipoProducto) {
		this.pkCodigoTipoProducto = pkCodigoTipoProducto;
	}

	public String getNombreTipoProducto() {
		return nombreTipoProducto;
	}

	public void setNombreTipoProducto(String nombreTipoProducto) {
		this.nombreTipoProducto = nombreTipoProducto;
	}

	public String getSiglasTipoProducto() {
		return siglasTipoProducto;
	}

	public void setSiglasTipoProducto(String siglasTipoProducto) {
		this.siglasTipoProducto = siglasTipoProducto;
	}
}
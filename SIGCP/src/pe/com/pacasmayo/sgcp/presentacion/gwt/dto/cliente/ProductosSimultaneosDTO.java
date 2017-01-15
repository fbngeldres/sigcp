package pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente;

public class ProductosSimultaneosDTO implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private Integer codigoProducto1;
	private Integer codigoProducto2;

	public ProductosSimultaneosDTO() {

	}

	public Integer getCodigoProducto1() {
		return codigoProducto1;
	}

	public void setCodigoProducto1(Integer codigoProducto1) {
		this.codigoProducto1 = codigoProducto1;
	}

	public Integer getCodigoProducto2() {
		return codigoProducto2;
	}

	public void setCodigoProducto2(Integer codigoProducto2) {
		this.codigoProducto2 = codigoProducto2;
	}

}

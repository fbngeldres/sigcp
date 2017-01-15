package pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente;

/**
 * Esta clase representa una columna en la tabla de stock.
 */
public class StockDTO implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long codigoRegistroMedicion;
	private String fecha;
	private Long codigoProducto;
	private String nombreProducto;
	private Long codigoEstado;
	private String nombreEstado;
	private Double stock;

	public Long getCodigoProducto() {
		return codigoProducto;
	}

	public void setCodigoProducto(Long codigoProducto) {
		this.codigoProducto = codigoProducto;
	}

	public String getNombreProducto() {
		return nombreProducto;
	}

	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}

	public Double getStock() {
		return stock;
	}

	public void setStock(Double stock) {
		this.stock = stock;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public Long getCodigoEstado() {
		return codigoEstado;
	}

	public void setCodigoEstado(Long codigoEstado) {
		this.codigoEstado = codigoEstado;
	}

	public String getNombreEstado() {
		return nombreEstado;
	}

	public void setNombreEstado(String nombreEstado) {
		this.nombreEstado = nombreEstado;
	}

	public Long getCodigoRegistroMedicion() {
		return codigoRegistroMedicion;
	}

	public void setCodigoRegistroMedicion(Long codigoRegistroMedicion) {
		this.codigoRegistroMedicion = codigoRegistroMedicion;
	}
}
package pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente;

import java.util.Date;
import java.util.Map;

public class RegistroPuestoTrabajoDTO implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private Date fecha;
	Map<String, Map<String, Double>> productos;

	/**
	 * @return the fecha
	 */
	public Date getFecha() {
		return fecha;
	}

	/**
	 * @param fecha the fecha to set
	 */
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	/**
	 * @return the productos
	 */
	public Map<String, Map<String, Double>> getProductos() {
		return productos;
	}

	/**
	 * @param productos the productos to set
	 */
	public void setProductos(Map<String, Map<String, Double>> productos) {
		this.productos = productos;
	}

}

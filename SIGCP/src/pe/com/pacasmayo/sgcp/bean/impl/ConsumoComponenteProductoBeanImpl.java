package pe.com.pacasmayo.sgcp.bean.impl;

import pe.com.pacasmayo.sgcp.bean.ConsumoComponenteProductoBean;
import pe.com.pacasmayo.sgcp.bean.ProductoBean;

public class ConsumoComponenteProductoBeanImpl implements ConsumoComponenteProductoBean {

	private Long codigoProductoConsumidor;
	private String nombreProductoConsumidor;
	private Double consumo;
	private ProductoBean producto;
	private ProductoBean productoConsumido;

	private String unidadMedida;

	public ConsumoComponenteProductoBeanImpl() {
		consumo = 0.00;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.ConsumoCommponenteProductoBean#
	 * getCodigoProductoConsumidor()
	 */
	public Long getCodigoProductoConsumidor() {
		return codigoProductoConsumidor;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.ConsumoCommponenteProductoBean#
	 * setCodigoProductoConsumidor(java.lang.Long)
	 */
	public void setCodigoProductoConsumidor(Long codigoProductoConsumidor) {
		this.codigoProductoConsumidor = codigoProductoConsumidor;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.ConsumoCommponenteProductoBean#
	 * getNombreProductoConsumidor()
	 */
	public String getNombreProductoConsumidor() {
		return nombreProductoConsumidor;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.ConsumoCommponenteProductoBean#
	 * setNombreProductoConsumidor(java.lang.String)
	 */
	public void setNombreProductoConsumidor(String nombreProductoConsumidor) {
		this.nombreProductoConsumidor = nombreProductoConsumidor;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.ConsumoCommponenteProductoBean#getConsumo
	 * ()
	 */
	public Double getConsumo() {
		return consumo;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.ConsumoCommponenteProductoBean#setConsumo
	 * (java.lang.Double)
	 */
	public void setConsumo(Double consumo) {
		this.consumo = consumo;
	}

	public ProductoBean getProducto() {
		return producto;
	}

	public void setProducto(ProductoBean producto) {
		this.producto = producto;
	}

	public ProductoBean getProductoConsumido() {
		return productoConsumido;
	}

	public void setProductoConsumido(ProductoBean productoConsumido) {
		this.productoConsumido = productoConsumido;
	}

	public String getUnidadMedida() {
		return unidadMedida;
	}

	public void setUnidadMedida(String unidadMedida) {
		this.unidadMedida = unidadMedida;
	}

}

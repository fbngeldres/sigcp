package pe.com.pacasmayo.sgcp.persistencia.dataObjects;

import java.io.Serializable;

public class Movimientoajusteproducto implements Serializable {

	// Fields

	private Long pkCodigomvimientoajusteproducto;
	private Puestotrabajoproduccion puestotrabajoproduccion;
	private Producto producto;
	private Double cantidadMovimientoajusteproducto;

	// Constructors

	/** default constructor */
	public Movimientoajusteproducto() {
	}

	// Property accessors

	/**
	 * @return the pkCodigomvimientoajusteproducto
	 */
	public Long getPkCodigomvimientoajusteproducto() {
		return pkCodigomvimientoajusteproducto;
	}

	/**
	 * @param pkCodigomvimientoajusteproducto the
	 *            pkCodigomvimientoajusteproducto to set
	 */
	public void setPkCodigomvimientoajusteproducto(Long pkCodigomvimientoajusteproducto) {
		this.pkCodigomvimientoajusteproducto = pkCodigomvimientoajusteproducto;
	}

	/**
	 * @return the puestotrabajoproduccion
	 */
	public Puestotrabajoproduccion getPuestotrabajoproduccion() {
		return puestotrabajoproduccion;
	}

	/**
	 * @param puestotrabajoproduccion the puestotrabajoproduccion to set
	 */
	public void setPuestotrabajoproduccion(Puestotrabajoproduccion puestotrabajoproduccion) {
		this.puestotrabajoproduccion = puestotrabajoproduccion;
	}

	/**
	 * @return the producto
	 */
	public Producto getProducto() {
		return producto;
	}

	/**
	 * @param producto the producto to set
	 */
	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	/**
	 * @return the cantidadMovimientoajusteproducto
	 */
	public Double getCantidadMovimientoajusteproducto() {
		return cantidadMovimientoajusteproducto;
	}

	/**
	 * @param cantidadMovimientoajusteproducto the
	 *            cantidadMovimientoajusteproducto to set
	 */
	public void setCantidadMovimientoajusteproducto(Double cantidadMovimientoajusteproducto) {
		this.cantidadMovimientoajusteproducto = cantidadMovimientoajusteproducto;
	}

}

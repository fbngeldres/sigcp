package pe.com.pacasmayo.sgcp.persistencia.dataObjects;

/**
 * Movimientoajuste entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Movimientoajuste implements java.io.Serializable {

	// Fields

	private Long pkCodigoMovimientoajuste;
	private Producto producto;
	private Estadomovimiento estadomovimiento;
	private Consumocomponenteajuste consumocomponenteajuste;
	private Tipomovimiento tipomovimiento;
	private Double cantidadMovimientoajuste;
	private String descripcionMovimientoajuste;
	private Boolean movManualMovimientoajuste;
	private Tipocomponenteajuste tipocomponenteajuste;

	// Constructors

	/** default constructor */
	public Movimientoajuste() {
	}

	/** full constructor */
	public Movimientoajuste(Producto producto, Estadomovimiento estadomovimiento,
			Consumocomponenteajuste consumocomponenteajuste, Tipomovimiento tipomovimiento, Double cantidadMovimientoajuste,
			String descripcionMovimientoajuste) {
		this.producto = producto;
		this.estadomovimiento = estadomovimiento;
		this.consumocomponenteajuste = consumocomponenteajuste;
		this.tipomovimiento = tipomovimiento;
		this.cantidadMovimientoajuste = cantidadMovimientoajuste;
		this.descripcionMovimientoajuste = descripcionMovimientoajuste;
	}

	// Property accessors

	public Long getPkCodigoMovimientoajuste() {
		return this.pkCodigoMovimientoajuste;
	}

	public void setPkCodigoMovimientoajuste(Long pkCodigoMovimientoajuste) {
		this.pkCodigoMovimientoajuste = pkCodigoMovimientoajuste;
	}

	public Producto getProducto() {
		return this.producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public Estadomovimiento getEstadomovimiento() {
		return this.estadomovimiento;
	}

	public void setEstadomovimiento(Estadomovimiento estadomovimiento) {
		this.estadomovimiento = estadomovimiento;
	}

	public Consumocomponenteajuste getConsumocomponenteajuste() {
		return this.consumocomponenteajuste;
	}

	public void setConsumocomponenteajuste(Consumocomponenteajuste consumocomponenteajuste) {
		this.consumocomponenteajuste = consumocomponenteajuste;
	}

	public Tipomovimiento getTipomovimiento() {
		return this.tipomovimiento;
	}

	public void setTipomovimiento(Tipomovimiento tipomovimiento) {
		this.tipomovimiento = tipomovimiento;
	}

	public Double getCantidadMovimientoajuste() {
		return this.cantidadMovimientoajuste;
	}

	public void setCantidadMovimientoajuste(Double cantidadMovimientoajuste) {
		this.cantidadMovimientoajuste = cantidadMovimientoajuste;
	}

	public String getDescripcionMovimientoajuste() {
		return this.descripcionMovimientoajuste;
	}

	public void setDescripcionMovimientoajuste(String descripcionMovimientoajuste) {
		this.descripcionMovimientoajuste = descripcionMovimientoajuste;
	}

	public Boolean getMovManualMovimientoajuste() {
		return movManualMovimientoajuste;
	}

	public void setMovManualMovimientoajuste(Boolean movManualMovimientoajuste) {
		this.movManualMovimientoajuste = movManualMovimientoajuste;
	}

	/**
	 * @return the tipocomponenteajuste
	 */
	public Tipocomponenteajuste getTipocomponenteajuste() {
		return tipocomponenteajuste;
	}

	/**
	 * @param tipocomponenteajuste the tipocomponenteajuste to set
	 */
	public void setTipocomponenteajuste(Tipocomponenteajuste tipocomponenteajuste) {
		this.tipocomponenteajuste = tipocomponenteajuste;
	}

}
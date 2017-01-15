package pe.com.pacasmayo.sgcp.persistencia.dataObjects;

import java.util.Date;

import pe.com.pacasmayo.sgcp.bean.MovimientoBean;

/**
 * Movimiento entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Movimiento implements java.io.Serializable {

	// Fields

	private Long pkCodigoMovimiento;
	private Unidadmedida unidadmedida;
	private Producto producto;
	private Estadomovimiento estadomovimiento;
	private Ubicacion ubicacionByFkCodigoUbicacionDestino;
	private Lineanegocio lineanegocio;
	private Ubicacion ubicacionByFkCodigoUbicacionOrigen;
	private Tipomovimiento tipomovimiento;
	private Medioalmacenamiento medioalmacenamiento;
	private Documentomaterial documentomaterial;
	private Double cantidadMovimiento;
	private Double cantidadMovimientoHumedad;
	private Double factorHumedad;
	private Date fechaMovimiento;
	private Double factorVolqueteMovimiento;
	private Long numeroViajesMovimiento;
	private String origenMovimiento;
	private String codigoSapproductoMovimiento;

	// Constructors

	/** default constructor */
	public Movimiento() {
	}

	/** minimal constructor */
	public Movimiento(Unidadmedida unidadmedida, Producto producto, Estadomovimiento estadomovimiento, Lineanegocio lineanegocio,
			Tipomovimiento tipomovimiento, Documentomaterial documentomaterial, Double cantidadMovimiento, Date fechaMovimiento,
			String origenMovimiento) {
		this.unidadmedida = unidadmedida;
		this.producto = producto;
		this.estadomovimiento = estadomovimiento;
		this.lineanegocio = lineanegocio;
		this.tipomovimiento = tipomovimiento;
		this.documentomaterial = documentomaterial;
		this.cantidadMovimiento = cantidadMovimiento;
		this.fechaMovimiento = fechaMovimiento;
		this.origenMovimiento = origenMovimiento;
	}

	/** full constructor */
	public Movimiento(Unidadmedida unidadmedida, Producto producto, Estadomovimiento estadomovimiento,
			Ubicacion ubicacionByFkCodigoUbicacionDestino, Lineanegocio lineanegocio,
			Ubicacion ubicacionByFkCodigoUbicacionOrigen, Tipomovimiento tipomovimiento, Medioalmacenamiento medioalmacenamiento,
			Documentomaterial documentomaterial, Double cantidadMovimiento, Date fechaMovimiento,
			Double factorVolqueteMovimiento, Long numeroViajesMovimiento, String origenMovimiento) {
		this.unidadmedida = unidadmedida;
		this.producto = producto;
		this.estadomovimiento = estadomovimiento;
		this.ubicacionByFkCodigoUbicacionDestino = ubicacionByFkCodigoUbicacionDestino;
		this.lineanegocio = lineanegocio;
		this.ubicacionByFkCodigoUbicacionOrigen = ubicacionByFkCodigoUbicacionOrigen;
		this.tipomovimiento = tipomovimiento;
		this.medioalmacenamiento = medioalmacenamiento;
		this.documentomaterial = documentomaterial;
		this.cantidadMovimiento = cantidadMovimiento;
		this.fechaMovimiento = fechaMovimiento;
		this.factorVolqueteMovimiento = factorVolqueteMovimiento;
		this.numeroViajesMovimiento = numeroViajesMovimiento;
		this.origenMovimiento = origenMovimiento;
	}

	/**
	 * Constructor de la Clase dado el MovimientoBean
	 * 
	 * @param movimientoBean
	 */
	public Movimiento(MovimientoBean movimientoBean) {

		// Cantidad de Movimiento
		this.cantidadMovimiento = movimientoBean.getCantidadMovimiento();
		// Estado Movimiento
		Estadomovimiento estadomovimiento = new Estadomovimiento();
		estadomovimiento.setPkCodigoEstadomovimiento(movimientoBean.getEstadomovimiento().getCodigo());
		this.estadomovimiento = estadomovimiento;
		// Factor Volquete Movimiento
		this.factorVolqueteMovimiento = movimientoBean.getFactorVolqueteMovimiento();
		// Fecha Movimiento
		this.fechaMovimiento = movimientoBean.getFechaMovimiento();
		// Linea Negocio
		Lineanegocio lineanegocio = new Lineanegocio();
		lineanegocio.setPkCodigoLineanegocio(movimientoBean.getLineanegocio().getCodigo());
		this.lineanegocio = lineanegocio;
		// Medio Almacenamiento
		if (movimientoBean.getMedioalmacenamiento() != null && movimientoBean.getMedioalmacenamiento().getCodigo() != null) {
			Medioalmacenamiento medioalmacenamiento = new Medioalmacenamiento();
			medioalmacenamiento.setPkCodigoMedioalmacenamiento(movimientoBean.getMedioalmacenamiento().getCodigo());
			this.medioalmacenamiento = medioalmacenamiento;
		}
		// Numero Viajes Movimiento
		this.numeroViajesMovimiento = movimientoBean.getNumeroViajesMovimiento();
		// Producto
		Producto producto = new Producto();
		producto.setPkCodigoProducto(movimientoBean.getProducto().getCodigo());
		this.producto = producto;
		// Tipo Movimiento
		Tipomovimiento tipomovimiento = new Tipomovimiento();
		tipomovimiento.setPkCodigoTipomovimiento(movimientoBean.getTipomovimiento().getCodigo());
		this.tipomovimiento = tipomovimiento;
		// Ubicacion Origen
		if (movimientoBean.getUbicacionByFkCodigoUbicacionOrigen() != null
				&& movimientoBean.getUbicacionByFkCodigoUbicacionOrigen().getCodigo() != null) {
			Ubicacion ubicacionOrigen = new Ubicacion();
			ubicacionOrigen.setPkCodigoUbicacion(movimientoBean.getUbicacionByFkCodigoUbicacionOrigen().getCodigo());
			this.ubicacionByFkCodigoUbicacionOrigen = ubicacionOrigen;
		}
		// Ubicacion Destino
		if (movimientoBean.getUbicacionByFkCodigoUbicacionDestino() != null
				&& movimientoBean.getUbicacionByFkCodigoUbicacionDestino().getCodigo() != null) {
			Ubicacion ubicacionDestino = new Ubicacion();
			ubicacionDestino.setPkCodigoUbicacion(movimientoBean.getUbicacionByFkCodigoUbicacionDestino().getCodigo());
			this.ubicacionByFkCodigoUbicacionDestino = ubicacionDestino;
		}
		// Unidad Medida
		Unidadmedida unidadmedida = new Unidadmedida();
		unidadmedida.setPkCodigoUnidadMedida(movimientoBean.getUnidadmedida().getCodigo());
		this.unidadmedida = unidadmedida;
		this.origenMovimiento = "";
		this.factorHumedad = 0d;
		this.cantidadMovimientoHumedad = movimientoBean.getCantidadMovimiento();
		this.codigoSapproductoMovimiento = movimientoBean.getCodigoSapproducto();

	}

	// Property accessors

	public Long getPkCodigoMovimiento() {
		return this.pkCodigoMovimiento;
	}

	public void setPkCodigoMovimiento(Long pkCodigoMovimiento) {
		this.pkCodigoMovimiento = pkCodigoMovimiento;
	}

	public Unidadmedida getUnidadmedida() {
		return this.unidadmedida;
	}

	public void setUnidadmedida(Unidadmedida unidadmedida) {
		this.unidadmedida = unidadmedida;
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

	public Ubicacion getUbicacionByFkCodigoUbicacionDestino() {
		return this.ubicacionByFkCodigoUbicacionDestino;
	}

	public void setUbicacionByFkCodigoUbicacionDestino(Ubicacion ubicacionByFkCodigoUbicacionDestino) {
		this.ubicacionByFkCodigoUbicacionDestino = ubicacionByFkCodigoUbicacionDestino;
	}

	public Lineanegocio getLineanegocio() {
		return this.lineanegocio;
	}

	public void setLineanegocio(Lineanegocio lineanegocio) {
		this.lineanegocio = lineanegocio;
	}

	public Ubicacion getUbicacionByFkCodigoUbicacionOrigen() {
		return this.ubicacionByFkCodigoUbicacionOrigen;
	}

	public void setUbicacionByFkCodigoUbicacionOrigen(Ubicacion ubicacionByFkCodigoUbicacionOrigen) {
		this.ubicacionByFkCodigoUbicacionOrigen = ubicacionByFkCodigoUbicacionOrigen;
	}

	public Tipomovimiento getTipomovimiento() {
		return this.tipomovimiento;
	}

	public void setTipomovimiento(Tipomovimiento tipomovimiento) {
		this.tipomovimiento = tipomovimiento;
	}

	public Medioalmacenamiento getMedioalmacenamiento() {
		return this.medioalmacenamiento;
	}

	public void setMedioalmacenamiento(Medioalmacenamiento medioalmacenamiento) {
		this.medioalmacenamiento = medioalmacenamiento;
	}

	public Documentomaterial getDocumentomaterial() {
		return this.documentomaterial;
	}

	public void setDocumentomaterial(Documentomaterial documentomaterial) {
		this.documentomaterial = documentomaterial;
	}

	public Double getCantidadMovimiento() {
		return this.cantidadMovimiento;
	}

	public void setCantidadMovimiento(Double cantidadMovimiento) {
		this.cantidadMovimiento = cantidadMovimiento;
	}

	public Date getFechaMovimiento() {
		return this.fechaMovimiento;
	}

	public void setFechaMovimiento(Date fechaMovimiento) {
		this.fechaMovimiento = fechaMovimiento;
	}

	public Double getFactorVolqueteMovimiento() {
		return this.factorVolqueteMovimiento;
	}

	public void setFactorVolqueteMovimiento(Double factorVolqueteMovimiento) {
		this.factorVolqueteMovimiento = factorVolqueteMovimiento;
	}

	public Long getNumeroViajesMovimiento() {
		return this.numeroViajesMovimiento;
	}

	public void setNumeroViajesMovimiento(Long numeroViajesMovimiento) {
		this.numeroViajesMovimiento = numeroViajesMovimiento;
	}

	public String getOrigenMovimiento() {
		return this.origenMovimiento;
	}

	public void setOrigenMovimiento(String origenMovimiento) {
		this.origenMovimiento = origenMovimiento;
	}

	public boolean esTranslado(Movimiento mov) {
		boolean ret = true;
		ret &= this.getFechaMovimiento().equals(mov.getFechaMovimiento());
		ret &= this.getLineanegocio().getPkCodigoLineanegocio().longValue() == mov.getLineanegocio().getPkCodigoLineanegocio()
				.longValue();
		ret &= Math.abs(this.getCantidadMovimiento().doubleValue()) == Math.abs(mov.getCantidadMovimiento().doubleValue());

		try {
			ret &= this.getUbicacionByFkCodigoUbicacionOrigen().getAlmacen().getPkCodigoAlmacen().longValue() != mov
					.getUbicacionByFkCodigoUbicacionOrigen().getAlmacen().getPkCodigoAlmacen().longValue();

		} catch (NullPointerException e) {
			return false;
		}

		return ret;
	}

	/**
	 * @return the cantidadMovimientoHumedad
	 */
	public Double getCantidadMovimientoHumedad() {
		return cantidadMovimientoHumedad;
	}

	/**
	 * @param cantidadMovimientoHumedad the cantidadMovimientoHumedad to set
	 */
	public void setCantidadMovimientoHumedad(Double cantidadMovimientoHumedad) {
		this.cantidadMovimientoHumedad = cantidadMovimientoHumedad;
	}

	/**
	 * @return the factorHumedad
	 */
	public Double getFactorHumedad() {
		return factorHumedad;
	}

	/**
	 * @param factorHumedad the factorHumedad to set
	 */
	public void setFactorHumedad(Double factorHumedad) {
		this.factorHumedad = factorHumedad;
	}

	public String getCodigoSapproductoMovimiento() {
		return codigoSapproductoMovimiento;
	}

	public void setCodigoSapproductoMovimiento(String codigoSapproductoMovimiento) {
		this.codigoSapproductoMovimiento = codigoSapproductoMovimiento;
	}
}
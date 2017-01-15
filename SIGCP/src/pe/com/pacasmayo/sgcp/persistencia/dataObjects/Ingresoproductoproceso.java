package pe.com.pacasmayo.sgcp.persistencia.dataObjects;

import java.util.Date;

/**
 * Ingresoproductoproceso entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Ingresoproductoproceso implements java.io.Serializable {

	// Fields

	private Long pkCodigoIngresoproductoproces;
	private Ubicacion ubicacion;
	private Producto producto;
	private Turno turno;
	private Ingresoproducto ingresoproducto;
	private Date fechaIngresoproductoproceso;
	private String horaIngresoproductoproceso;
	private String placaVehiculoIngresoproductop;
	private Double factorPesoTnIngresoproductop;
	private Short numeroViajesIngresoproductopr;
	private Double toneladasIngresadasIngresopro;
	// SE AGREGO PARA LA MODIFICACION 1333
	private String ticketIngresoproductoproceso;
	private String notaEntregaIngresoproductoproceso;
	private String observacionesIngresoproductoproceso;

	// Constructors

	/** default constructor */
	public Ingresoproductoproceso() {
	}

	/** minimal constructor */
	public Ingresoproductoproceso(Producto producto, Turno turno, Ingresoproducto ingresoproducto,
			Date fechaIngresoproductoproceso, String horaIngresoproductoproceso, String placaVehiculoIngresoproductop,
			Double factorPesoTnIngresoproductop, Short numeroViajesIngresoproductopr, Double toneladasIngresadasIngresopro,
			String ticketIngresoproductoproceso, String notaEntregaIngresoproductoproceso,
			String observacionesIngresoproductoproceso) {
		this.producto = producto;
		this.turno = turno;
		this.ingresoproducto = ingresoproducto;
		this.fechaIngresoproductoproceso = fechaIngresoproductoproceso;
		this.horaIngresoproductoproceso = horaIngresoproductoproceso;
		this.placaVehiculoIngresoproductop = placaVehiculoIngresoproductop;
		this.factorPesoTnIngresoproductop = factorPesoTnIngresoproductop;
		this.numeroViajesIngresoproductopr = numeroViajesIngresoproductopr;
		this.toneladasIngresadasIngresopro = toneladasIngresadasIngresopro;
		this.ticketIngresoproductoproceso = ticketIngresoproductoproceso;
		this.notaEntregaIngresoproductoproceso = notaEntregaIngresoproductoproceso;
		this.observacionesIngresoproductoproceso = observacionesIngresoproductoproceso;
	}

	/** full constructor */
	public Ingresoproductoproceso(Ubicacion ubicacion, Producto producto, Turno turno, Ingresoproducto ingresoproducto,
			Date fechaIngresoproductoproceso, String horaIngresoproductoproceso, String placaVehiculoIngresoproductop,
			Double factorPesoTnIngresoproductop, Short numeroViajesIngresoproductopr, Double toneladasIngresadasIngresopro,
			String ticketIngresoproductoproceso, String notaEntregaIngresoproductoproceso,
			String observacionesIngresoproductoproceso) {
		this.ubicacion = ubicacion;
		this.producto = producto;
		this.turno = turno;
		this.ingresoproducto = ingresoproducto;
		this.fechaIngresoproductoproceso = fechaIngresoproductoproceso;
		this.horaIngresoproductoproceso = horaIngresoproductoproceso;
		this.placaVehiculoIngresoproductop = placaVehiculoIngresoproductop;
		this.factorPesoTnIngresoproductop = factorPesoTnIngresoproductop;
		this.numeroViajesIngresoproductopr = numeroViajesIngresoproductopr;
		this.toneladasIngresadasIngresopro = toneladasIngresadasIngresopro;
		this.ticketIngresoproductoproceso = ticketIngresoproductoproceso;
		this.notaEntregaIngresoproductoproceso = notaEntregaIngresoproductoproceso;
		this.observacionesIngresoproductoproceso = observacionesIngresoproductoproceso;
	}

	// Property accessors

	public Long getPkCodigoIngresoproductoproces() {
		return this.pkCodigoIngresoproductoproces;
	}

	public void setPkCodigoIngresoproductoproces(Long pkCodigoIngresoproductoproces) {
		this.pkCodigoIngresoproductoproces = pkCodigoIngresoproductoproces;
	}

	public Ubicacion getUbicacion() {
		return this.ubicacion;
	}

	public void setUbicacion(Ubicacion ubicacion) {
		this.ubicacion = ubicacion;
	}

	public Producto getProducto() {
		return this.producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public Turno getTurno() {
		return this.turno;
	}

	public void setTurno(Turno turno) {
		this.turno = turno;
	}

	public Ingresoproducto getIngresoproducto() {
		return this.ingresoproducto;
	}

	public void setIngresoproducto(Ingresoproducto ingresoproducto) {
		this.ingresoproducto = ingresoproducto;
	}

	public Date getFechaIngresoproductoproceso() {
		return this.fechaIngresoproductoproceso;
	}

	public void setFechaIngresoproductoproceso(Date fechaIngresoproductoproceso) {
		this.fechaIngresoproductoproceso = fechaIngresoproductoproceso;
	}

	public String getHoraIngresoproductoproceso() {
		return this.horaIngresoproductoproceso;
	}

	public void setHoraIngresoproductoproceso(String horaIngresoproductoproceso) {
		this.horaIngresoproductoproceso = horaIngresoproductoproceso;
	}

	public String getPlacaVehiculoIngresoproductop() {
		return this.placaVehiculoIngresoproductop;
	}

	public void setPlacaVehiculoIngresoproductop(String placaVehiculoIngresoproductop) {
		this.placaVehiculoIngresoproductop = placaVehiculoIngresoproductop;
	}

	public Double getFactorPesoTnIngresoproductop() {
		return this.factorPesoTnIngresoproductop;
	}

	public void setFactorPesoTnIngresoproductop(Double factorPesoTnIngresoproductop) {
		this.factorPesoTnIngresoproductop = factorPesoTnIngresoproductop;
	}

	public Short getNumeroViajesIngresoproductopr() {
		return this.numeroViajesIngresoproductopr;
	}

	public void setNumeroViajesIngresoproductopr(Short numeroViajesIngresoproductopr) {
		this.numeroViajesIngresoproductopr = numeroViajesIngresoproductopr;
	}

	public Double getToneladasIngresadasIngresopro() {
		return this.toneladasIngresadasIngresopro;
	}

	public void setToneladasIngresadasIngresopro(Double toneladasIngresadasIngresopro) {
		this.toneladasIngresadasIngresopro = toneladasIngresadasIngresopro;
	}

	public String getTicketIngresoproductoproceso() {
		return ticketIngresoproductoproceso;
	}

	public void setTicketIngresoproductoproceso(String ticketIngresoproductoproceso) {
		this.ticketIngresoproductoproceso = ticketIngresoproductoproceso;
	}

	public String getNotaEntregaIngresoproductoproceso() {
		return notaEntregaIngresoproductoproceso;
	}

	public void setNotaEntregaIngresoproductoproceso(String notaEntregaIngresoproductoproceso) {
		this.notaEntregaIngresoproductoproceso = notaEntregaIngresoproductoproceso;
	}

	public String getObservacionesIngresoproductoproceso() {
		return observacionesIngresoproductoproceso;
	}

	public void setObservacionesIngresoproductoproceso(String observacionesIngresoproductoproceso) {
		this.observacionesIngresoproductoproceso = observacionesIngresoproductoproceso;
	}
}
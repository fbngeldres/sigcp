package pe.com.pacasmayo.sgcp.persistencia.dataObjects;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Ordenproduccion entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Ordenproduccion implements java.io.Serializable {

	// Fields

	private Long pkCodigoOrdenproduccion;
	private Estadoordenproduccion estadoordenproduccion;
	private Hojaruta hojaruta;
	private Usuario usuarioByFkCodigoUsuarioRegistro;
	private Usuario usuarioByFkCodigoUsuarioAprueba;
	private Produccion produccion;
	private Short mesOrdenproduccion;
	private String numeroOrdenOrdenproduccion;
	private String numeroDocumentoOrdenproduccio;
	private Double produccionEstimadaOrdenproduc;
	private Double produccionEjecutadaOrdenprodu;
	private Date fechaRegistroOrdenproduccion;
	private Date fechaAprobacionOrdenproduccio;
	private Set producciondiarias = new HashSet(0);
	private Set notificacionproduccions = new HashSet(0);


	private Set ordenproduccionmanuals = new HashSet(0);

	private Set ajusteproductos = new HashSet(0);
	private Set ordenproduccionplans = new HashSet(0);
	private Set productogenerados = new HashSet(0);

	// Constructors

	/** default constructor */
	public Ordenproduccion() {
	}

	/** minimal constructor */
	public Ordenproduccion(Estadoordenproduccion estadoordenproduccion, Usuario usuarioByFkCodigoUsuarioRegistro,
			Usuario usuarioByFkCodigoUsuarioAprueba, Produccion produccion, Short mesOrdenproduccion,
			String numeroOrdenOrdenproduccion, Double produccionEstimadaOrdenproduc) {
		this.estadoordenproduccion = estadoordenproduccion;
		this.usuarioByFkCodigoUsuarioRegistro = usuarioByFkCodigoUsuarioRegistro;
		this.usuarioByFkCodigoUsuarioAprueba = usuarioByFkCodigoUsuarioAprueba;
		this.produccion = produccion;
		this.mesOrdenproduccion = mesOrdenproduccion;
		this.numeroOrdenOrdenproduccion = numeroOrdenOrdenproduccion;
		this.produccionEstimadaOrdenproduc = produccionEstimadaOrdenproduc;
	}

	/** full constructor */
	public Ordenproduccion(Estadoordenproduccion estadoordenproduccion, Hojaruta hojaruta,
			Usuario usuarioByFkCodigoUsuarioRegistro, Usuario usuarioByFkCodigoUsuarioAprueba, Produccion produccion,
			Short mesOrdenproduccion, String numeroOrdenOrdenproduccion, String numeroDocumentoOrdenproduccio,
			Double produccionEstimadaOrdenproduc, Double produccionEjecutadaOrdenprodu, Date fechaRegistroOrdenproduccion,
			Date fechaAprobacionOrdenproduccio, Set producciondiarias, Set notificacionproduccions, 
			 Set ordenproduccionmanuals,  Set ajusteproductos, Set ordenproduccionplans,
			Set productogenerados) {
		this.estadoordenproduccion = estadoordenproduccion;
		this.hojaruta = hojaruta;
		this.usuarioByFkCodigoUsuarioRegistro = usuarioByFkCodigoUsuarioRegistro;
		this.usuarioByFkCodigoUsuarioAprueba = usuarioByFkCodigoUsuarioAprueba;
		this.produccion = produccion;
		this.mesOrdenproduccion = mesOrdenproduccion;
		this.numeroOrdenOrdenproduccion = numeroOrdenOrdenproduccion;
		this.numeroDocumentoOrdenproduccio = numeroDocumentoOrdenproduccio;
		this.produccionEstimadaOrdenproduc = produccionEstimadaOrdenproduc;
		this.produccionEjecutadaOrdenprodu = produccionEjecutadaOrdenprodu;
		this.fechaRegistroOrdenproduccion = fechaRegistroOrdenproduccion;
		this.fechaAprobacionOrdenproduccio = fechaAprobacionOrdenproduccio;
		this.producciondiarias = producciondiarias;
		this.notificacionproduccions = notificacionproduccions;

		this.ordenproduccionmanuals = ordenproduccionmanuals;

		this.ajusteproductos = ajusteproductos;
		this.ordenproduccionplans = ordenproduccionplans;
		this.productogenerados = productogenerados;
	}

	// Property accessors

	public Long getPkCodigoOrdenproduccion() {
		return this.pkCodigoOrdenproduccion;
	}

	public void setPkCodigoOrdenproduccion(Long pkCodigoOrdenproduccion) {
		this.pkCodigoOrdenproduccion = pkCodigoOrdenproduccion;
	}

	public Estadoordenproduccion getEstadoordenproduccion() {
		return this.estadoordenproduccion;
	}

	public void setEstadoordenproduccion(Estadoordenproduccion estadoordenproduccion) {
		this.estadoordenproduccion = estadoordenproduccion;
	}

	public Hojaruta getHojaruta() {
		return this.hojaruta;
	}

	public void setHojaruta(Hojaruta hojaruta) {
		this.hojaruta = hojaruta;
	}

	public Usuario getUsuarioByFkCodigoUsuarioRegistro() {
		return this.usuarioByFkCodigoUsuarioRegistro;
	}

	public void setUsuarioByFkCodigoUsuarioRegistro(Usuario usuarioByFkCodigoUsuarioRegistro) {
		this.usuarioByFkCodigoUsuarioRegistro = usuarioByFkCodigoUsuarioRegistro;
	}

	public Usuario getUsuarioByFkCodigoUsuarioAprueba() {
		return this.usuarioByFkCodigoUsuarioAprueba;
	}

	public void setUsuarioByFkCodigoUsuarioAprueba(Usuario usuarioByFkCodigoUsuarioAprueba) {
		this.usuarioByFkCodigoUsuarioAprueba = usuarioByFkCodigoUsuarioAprueba;
	}

	public Produccion getProduccion() {
		return this.produccion;
	}

	public void setProduccion(Produccion produccion) {
		this.produccion = produccion;
	}

	public Short getMesOrdenproduccion() {
		return this.mesOrdenproduccion;
	}

	public void setMesOrdenproduccion(Short mesOrdenproduccion) {
		this.mesOrdenproduccion = mesOrdenproduccion;
	}

	public String getNumeroOrdenOrdenproduccion() {
		return this.numeroOrdenOrdenproduccion;
	}

	public void setNumeroOrdenOrdenproduccion(String numeroOrdenOrdenproduccion) {
		this.numeroOrdenOrdenproduccion = numeroOrdenOrdenproduccion;
	}

	public String getNumeroDocumentoOrdenproduccio() {
		return this.numeroDocumentoOrdenproduccio;
	}

	public void setNumeroDocumentoOrdenproduccio(String numeroDocumentoOrdenproduccio) {
		this.numeroDocumentoOrdenproduccio = numeroDocumentoOrdenproduccio;
	}

	public Double getProduccionEstimadaOrdenproduc() {
		return this.produccionEstimadaOrdenproduc;
	}

	public void setProduccionEstimadaOrdenproduc(Double produccionEstimadaOrdenproduc) {
		this.produccionEstimadaOrdenproduc = produccionEstimadaOrdenproduc;
	}

	public Double getProduccionEjecutadaOrdenprodu() {
		return this.produccionEjecutadaOrdenprodu;
	}

	public void setProduccionEjecutadaOrdenprodu(Double produccionEjecutadaOrdenprodu) {
		this.produccionEjecutadaOrdenprodu = produccionEjecutadaOrdenprodu;
	}

	public Date getFechaRegistroOrdenproduccion() {
		return this.fechaRegistroOrdenproduccion;
	}

	public void setFechaRegistroOrdenproduccion(Date fechaRegistroOrdenproduccion) {
		this.fechaRegistroOrdenproduccion = fechaRegistroOrdenproduccion;
	}

	public Date getFechaAprobacionOrdenproduccio() {
		return this.fechaAprobacionOrdenproduccio;
	}

	public void setFechaAprobacionOrdenproduccio(Date fechaAprobacionOrdenproduccio) {
		this.fechaAprobacionOrdenproduccio = fechaAprobacionOrdenproduccio;
	}

	public Set getProducciondiarias() {
		return this.producciondiarias;
	}

	public void setProducciondiarias(Set producciondiarias) {
		this.producciondiarias = producciondiarias;
	}

	public Set getNotificacionproduccions() {
		return this.notificacionproduccions;
	}

	public void setNotificacionproduccions(Set notificacionproduccions) {
		this.notificacionproduccions = notificacionproduccions;
	}



	public Set getOrdenproduccionmanuals() {
		return this.ordenproduccionmanuals;
	}

	public void setOrdenproduccionmanuals(Set ordenproduccionmanuals) {
		this.ordenproduccionmanuals = ordenproduccionmanuals;
	}



	public Set getAjusteproductos() {
		return this.ajusteproductos;
	}

	public void setAjusteproductos(Set ajusteproductos) {
		this.ajusteproductos = ajusteproductos;
	}

	public Set getOrdenproduccionplans() {
		return this.ordenproduccionplans;
	}

	public void setOrdenproduccionplans(Set ordenproduccionplans) {
		this.ordenproduccionplans = ordenproduccionplans;
	}

	public Set getProductogenerados() {
		return this.productogenerados;
	}

	public void setProductogenerados(Set productogenerados) {
		this.productogenerados = productogenerados;
	}

}
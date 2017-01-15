package pe.com.pacasmayo.sgcp.persistencia.dataObjects;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import pe.com.pacasmayo.sgcp.bean.DocumentoMaterialBean;

/**
 * Documentomaterial entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Documentomaterial implements java.io.Serializable {

	// Fields

	private Long pkCodigoDocumentomaterial;
	private Usuario usuario;
	private Tipodocumentomaterial tipodocumentomaterial;
	private Sociedad sociedad;
	private Periodocontable periodocontable;
	private Date fechaDocumentomaterial;
	private Boolean origenSapMovimiento = false;
	private Set movimientos = new HashSet(0);

	/**
	 * Agregado por Jordan
	 */
	private String ticket;
	private String notaEntrega;
	private String observacion;
	private String placa;

	// Constructors

	/** default constructor */
	public Documentomaterial() {
	}

	/** minimal constructor */
	public Documentomaterial(Usuario usuario, Tipodocumentomaterial tipodocumentomaterial, Sociedad sociedad,
			Periodocontable periodocontable, Date fechaDocumentomaterial) {
		this.usuario = usuario;
		this.tipodocumentomaterial = tipodocumentomaterial;
		this.sociedad = sociedad;
		this.periodocontable = periodocontable;
		this.fechaDocumentomaterial = fechaDocumentomaterial;
	}

	/** full constructor */
	public Documentomaterial(Long pkCodigoDocumentomaterial, Usuario usuario, Tipodocumentomaterial tipodocumentomaterial,
			Sociedad sociedad, Periodocontable periodocontable, Date fechaDocumentomaterial, Boolean origenSapMovimiento,
			Set movimientos, String ticket, String notaEntrega, String observacion, String placa) {
		this.pkCodigoDocumentomaterial = pkCodigoDocumentomaterial;
		this.usuario = usuario;
		this.tipodocumentomaterial = tipodocumentomaterial;
		this.sociedad = sociedad;
		this.periodocontable = periodocontable;
		this.fechaDocumentomaterial = fechaDocumentomaterial;
		this.origenSapMovimiento = origenSapMovimiento;
		this.movimientos = movimientos;
		this.ticket = ticket;
		this.notaEntrega = notaEntrega;
		this.observacion = observacion;
		this.placa = placa;
	}

	/**
	 * Constructor de la Clase dado el DocumentoMaterialBean
	 * 
	 * @param documentoMaterialBean
	 */
	public Documentomaterial(DocumentoMaterialBean documentoMaterialBean) {

		// Fecha Documento
		this.fechaDocumentomaterial = documentoMaterialBean.getFechaDocumentomaterial();
		// Tipo Documento Material
		Tipodocumentomaterial tipodocumentomaterial = new Tipodocumentomaterial();
		tipodocumentomaterial.setPkCodigoTipodocumentomaterial(documentoMaterialBean.getTipodocumentomaterial().getCodigo());
		
		this.tipodocumentomaterial = tipodocumentomaterial;
		// Origen SAP Movimiento
		this.origenSapMovimiento = documentoMaterialBean.getOrigenSapMovimiento();
		// Periodo Contable
		Periodocontable periodocontable = new Periodocontable();
		periodocontable.setPkCodigoPeridocontable(documentoMaterialBean.getPeriodocontable().getCodigo());
		this.periodocontable = periodocontable;
		// Sociedad
		Sociedad sociedad = new Sociedad();
		sociedad.setPkCodigoSociedad(documentoMaterialBean.getSociedad().getCodigo());
		this.sociedad = sociedad;
		// Usuario
		Usuario usuario = new Usuario();
		usuario.setPkCodigoUsuario(documentoMaterialBean.getUsuario().getCodigo());

		/**
		 * JORDAN
		 */
		this.setTicket(documentoMaterialBean.getTicket());
		this.setObservacion(documentoMaterialBean.getObservacion());
		this.setNotaEntrega(documentoMaterialBean.getNotaEntrega());
		this.setPlaca(documentoMaterialBean.getPlaca());

		this.usuario = usuario;

	}

	// Property accessors

	public Long getPkCodigoDocumentomaterial() {
		return this.pkCodigoDocumentomaterial;
	}

	public void setPkCodigoDocumentomaterial(Long pkCodigoDocumentomaterial) {
		this.pkCodigoDocumentomaterial = pkCodigoDocumentomaterial;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Tipodocumentomaterial getTipodocumentomaterial() {
		return this.tipodocumentomaterial;
	}

	public void setTipodocumentomaterial(Tipodocumentomaterial tipodocumentomaterial) {
		this.tipodocumentomaterial = tipodocumentomaterial;
	}

	public Sociedad getSociedad() {
		return this.sociedad;
	}

	public void setSociedad(Sociedad sociedad) {
		this.sociedad = sociedad;
	}

	public Periodocontable getPeriodocontable() {
		return this.periodocontable;
	}

	public void setPeriodocontable(Periodocontable periodocontable) {
		this.periodocontable = periodocontable;
	}

	public Date getFechaDocumentomaterial() {
		return this.fechaDocumentomaterial;
	}

	public void setFechaDocumentomaterial(Date fechaDocumentomaterial) {
		this.fechaDocumentomaterial = fechaDocumentomaterial;
	}

	public Set getMovimientos() {
		return this.movimientos;
	}

	public void setMovimientos(Set movimientos) {
		this.movimientos = movimientos;
	}

	public Boolean getOrigenSapMovimiento() {
		return origenSapMovimiento;
	}

	public void setOrigenSapMovimiento(Boolean origenSapMovimiento) {
		this.origenSapMovimiento = origenSapMovimiento;
	}

	/**
	 * @return the ticket
	 */
	public String getTicket() {
		return ticket;
	}

	/**
	 * @param ticket the ticket to set
	 */
	public void setTicket(String ticket) {
		this.ticket = ticket;
	}

	/**
	 * @return the notaEntrega
	 */
	public String getNotaEntrega() {
		return notaEntrega;
	}

	/**
	 * @param notaEntrega the notaEntrega to set
	 */
	public void setNotaEntrega(String notaEntrega) {
		this.notaEntrega = notaEntrega;
	}

	/**
	 * @return the observacion
	 */
	public String getObservacion() {
		return observacion;
	}

	/**
	 * @param observacion the observacion to set
	 */
	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	/**
	 * @return the placa
	 */
	public String getPlaca() {
		return placa;
	}

	/**
	 * @param placa the placa to set
	 */
	public void setPlaca(String placa) {
		this.placa = placa;
	}

}
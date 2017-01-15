package pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente;

import java.util.Date;

public class OrdenProduccionDTO implements java.io.Serializable {

	private static final long serialVersionUID = 9137594914227247231L;
	private Long pkCodigoOrdenproduccion;
	private Short mesOrdenproduccion;
	private String numeroOrdenOrdenproduccion;
	private String numeroDocumentoOrdenproduccio;
	private Double produccionEstimadaOrdenproduc;
	private Double produccionEjecutadaOrdenprodu;
	private Date fechaRegistroOrdenproduccion;
	private Date fechaAprobacionOrdenproduccio;
	private ProduccionDTO produccion;
	private String nombreProducto;

	public Long getPkCodigoOrdenproduccion() {
		return pkCodigoOrdenproduccion;
	}

	public void setPkCodigoOrdenproduccion(Long pkCodigoOrdenproduccion) {
		this.pkCodigoOrdenproduccion = pkCodigoOrdenproduccion;
	}

	public Short getMesOrdenproduccion() {
		return mesOrdenproduccion;
	}

	public void setMesOrdenproduccion(Short mesOrdenproduccion) {
		this.mesOrdenproduccion = mesOrdenproduccion;
	}

	public String getNumeroOrdenOrdenproduccion() {
		return numeroOrdenOrdenproduccion;
	}

	public void setNumeroOrdenOrdenproduccion(String numeroOrdenOrdenproduccion) {
		this.numeroOrdenOrdenproduccion = numeroOrdenOrdenproduccion;
	}

	public String getNumeroDocumentoOrdenproduccio() {
		return numeroDocumentoOrdenproduccio;
	}

	public void setNumeroDocumentoOrdenproduccio(String numeroDocumentoOrdenproduccio) {
		this.numeroDocumentoOrdenproduccio = numeroDocumentoOrdenproduccio;
	}

	public Double getProduccionEstimadaOrdenproduc() {
		return produccionEstimadaOrdenproduc;
	}

	public void setProduccionEstimadaOrdenproduc(Double produccionEstimadaOrdenproduc) {
		this.produccionEstimadaOrdenproduc = produccionEstimadaOrdenproduc;
	}

	public Double getProduccionEjecutadaOrdenprodu() {
		return produccionEjecutadaOrdenprodu;
	}

	public void setProduccionEjecutadaOrdenprodu(Double produccionEjecutadaOrdenprodu) {
		this.produccionEjecutadaOrdenprodu = produccionEjecutadaOrdenprodu;
	}

	public Date getFechaRegistroOrdenproduccion() {
		return fechaRegistroOrdenproduccion;
	}

	public void setFechaRegistroOrdenproduccion(Date fechaRegistroOrdenproduccion) {
		this.fechaRegistroOrdenproduccion = fechaRegistroOrdenproduccion;
	}

	public Date getFechaAprobacionOrdenproduccio() {
		return fechaAprobacionOrdenproduccio;
	}

	public void setFechaAprobacionOrdenproduccio(Date fechaAprobacionOrdenproduccio) {
		this.fechaAprobacionOrdenproduccio = fechaAprobacionOrdenproduccio;
	}

	public ProduccionDTO getProduccion() {
		return produccion;
	}

	public void setProduccion(ProduccionDTO produccion) {
		this.produccion = produccion;
	}

	public String getNombreProducto() {
		return nombreProducto;
	}

	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}
}
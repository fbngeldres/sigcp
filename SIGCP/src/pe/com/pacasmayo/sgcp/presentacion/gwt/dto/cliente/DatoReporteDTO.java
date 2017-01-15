package pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente;

import java.util.List;

public class DatoReporteDTO implements java.io.Serializable {

	private static final long serialVersionUID = -5928007435726293573L;

	// Atributos que solo aplican para el Registro de variables de Operacion
	private Long pkCodigoDatoreporte;
	private Long pkCodigoHora;
	private Long codigoLineaNegocio;
	private String nombreLineaNegocio;
	private Short valorHora = new Short((short) 0);
	private Long pkRegistroReporte;
	private Double variable1Datoreporte = new Double(0d);
	private Double variable2Datoreporte = new Double(0d);
	private Double variable3Datoreporte = new Double(0d);
	private Double variable4Datoreporte = new Double(0d);
	private Double variable5Datoreporte = new Double(0d);
	private Double variable6Datoreporte = new Double(0d);
	private Double variable7Datoreporte = new Double(0d);
	private Double variable8Datoreporte = new Double(0d);
	private Double variable9Datoreporte = new Double(0d);
	private Double variable10Datoreporte = new Double(0d);
	private Double variable11Datoreporte = new Double(0d);
	private Double variable12Datoreporte = new Double(0d);
	private Double variable13Datoreporte = new Double(0d);
	private Double variable14Datoreporte = new Double(0d);
	private Double variable15Datoreporte = new Double(0d);
	private boolean seleccion;
	private String numeroOrdenSeleccionada;
	private Integer codigoOrdenSeleccionada;
	// private String nombreProducto;
	private Integer codigoProducto;
	private String nombreSilo;
	private Integer codigoSilo;
	private String observaciones;
	private Long codigoNotificacionPlanta;
	private Boolean cambioProduccionLavado = false;
	private Boolean cambioProduccionNormal = false;
	private String cambioProduccionHora = "";

	private Long codigoSiloAntCambioProduc;
	private Long codigoTurno;

	private Boolean notificacionAprobada = false;

	private String plantilla;
	private Integer codigoPlantilla;

	// estado notifiacion
	private String estadoNotificaciondiaria;

	// Estos dos atributos: ordenes y observacion solo aplican para Registro de
	// variables de Produccion
	private List<OrdenProduccionDTO> ordenes;

	// Constructors

	/**
	 * @return the estadoNotificaciondiaria
	 */
	public String getEstadoNotificaciondiaria() {
		return estadoNotificaciondiaria;
	}

	/**
	 * @param estadoNotificaciondiaria the estadoNotificaciondiaria to set
	 */
	public void setEstadoNotificaciondiaria(String estadoNotificaciondiaria) {
		this.estadoNotificaciondiaria = estadoNotificaciondiaria;
	}

	/** default constructor */
	public DatoReporteDTO() {

	}

	/**
	 * @return the pkCodigoDatoreporte
	 */
	public Long getPkCodigoDatoreporte() {
		return pkCodigoDatoreporte;
	}

	/**
	 * @param pkCodigoDatoreporte the pkCodigoDatoreporte to set
	 */
	public void setPkCodigoDatoreporte(Long pkCodigoDatoreporte) {
		this.pkCodigoDatoreporte = pkCodigoDatoreporte;
	}

	/**
	 * @return the pkCodigoHora
	 */
	public Long getPkCodigoHora() {
		return pkCodigoHora;
	}

	/**
	 * @param pkCodigoHora the pkCodigoHora to set
	 */
	public void setPkCodigoHora(Long pkCodigoHora) {
		this.pkCodigoHora = pkCodigoHora;
	}

	/**
	 * @return the pkRegistroReporte
	 */
	public Long getPkRegistroReporte() {
		return pkRegistroReporte;
	}

	/**
	 * @param pkRegistroReporte the pkRegistroReporte to set
	 */
	public void setPkRegistroReporte(Long pkRegistroReporte) {
		this.pkRegistroReporte = pkRegistroReporte;
	}

	/**
	 * @return the variable1Datoreporte
	 */
	public Double getVariable1Datoreporte() {
		return variable1Datoreporte;
	}

	/**
	 * @param variable1Datoreporte the variable1Datoreporte to set
	 */
	public void setVariable1Datoreporte(Double variable1Datoreporte) {
		this.variable1Datoreporte = variable1Datoreporte;
	}

	/**
	 * @return the variable2Datoreporte
	 */
	public Double getVariable2Datoreporte() {
		return variable2Datoreporte;
	}

	/**
	 * @param variable2Datoreporte the variable2Datoreporte to set
	 */
	public void setVariable2Datoreporte(Double variable2Datoreporte) {
		this.variable2Datoreporte = variable2Datoreporte;
	}

	/**
	 * @return the variable3Datoreporte
	 */
	public Double getVariable3Datoreporte() {
		return variable3Datoreporte;
	}

	/**
	 * @param variable3Datoreporte the variable3Datoreporte to set
	 */
	public void setVariable3Datoreporte(Double variable3Datoreporte) {
		this.variable3Datoreporte = variable3Datoreporte;
	}

	/**
	 * @return the variable4Datoreporte
	 */
	public Double getVariable4Datoreporte() {
		return variable4Datoreporte;
	}

	/**
	 * @param variable4Datoreporte the variable4Datoreporte to set
	 */
	public void setVariable4Datoreporte(Double variable4Datoreporte) {
		this.variable4Datoreporte = variable4Datoreporte;
	}

	/**
	 * @return the variable5Datoreporte
	 */
	public Double getVariable5Datoreporte() {
		return variable5Datoreporte;
	}

	/**
	 * @param variable5Datoreporte the variable5Datoreporte to set
	 */
	public void setVariable5Datoreporte(Double variable5Datoreporte) {
		this.variable5Datoreporte = variable5Datoreporte;
	}

	/**
	 * @return the variable6Datoreporte
	 */
	public Double getVariable6Datoreporte() {
		return variable6Datoreporte;
	}

	/**
	 * @param variable6Datoreporte the variable6Datoreporte to set
	 */
	public void setVariable6Datoreporte(Double variable6Datoreporte) {
		this.variable6Datoreporte = variable6Datoreporte;
	}

	/**
	 * @return the variable7Datoreporte
	 */
	public Double getVariable7Datoreporte() {
		return variable7Datoreporte;
	}

	/**
	 * @param variable7Datoreporte the variable7Datoreporte to set
	 */
	public void setVariable7Datoreporte(Double variable7Datoreporte) {
		this.variable7Datoreporte = variable7Datoreporte;
	}

	/**
	 * @return the variable8Datoreporte
	 */
	public Double getVariable8Datoreporte() {
		return variable8Datoreporte;
	}

	/**
	 * @param variable8Datoreporte the variable8Datoreporte to set
	 */
	public void setVariable8Datoreporte(Double variable8Datoreporte) {
		this.variable8Datoreporte = variable8Datoreporte;
	}

	/**
	 * @return the variable9Datoreporte
	 */
	public Double getVariable9Datoreporte() {
		return variable9Datoreporte;
	}

	/**
	 * @param variable9Datoreporte the variable9Datoreporte to set
	 */
	public void setVariable9Datoreporte(Double variable9Datoreporte) {
		this.variable9Datoreporte = variable9Datoreporte;
	}

	/**
	 * @return the variable10Datoreporte
	 */
	public Double getVariable10Datoreporte() {
		return variable10Datoreporte;
	}

	/**
	 * @param variable10Datoreporte the variable10Datoreporte to set
	 */
	public void setVariable10Datoreporte(Double variable10Datoreporte) {
		this.variable10Datoreporte = variable10Datoreporte;
	}

	/**
	 * @return the variable11Datoreporte
	 */
	public Double getVariable11Datoreporte() {
		return variable11Datoreporte;
	}

	/**
	 * @param variable11Datoreporte the variable11Datoreporte to set
	 */
	public void setVariable11Datoreporte(Double variable11Datoreporte) {
		this.variable11Datoreporte = variable11Datoreporte;
	}

	/**
	 * @return the variable12Datoreporte
	 */
	public Double getVariable12Datoreporte() {
		return variable12Datoreporte;
	}

	/**
	 * @param variable12Datoreporte the variable12Datoreporte to set
	 */
	public void setVariable12Datoreporte(Double variable12Datoreporte) {
		this.variable12Datoreporte = variable12Datoreporte;
	}

	/**
	 * @return the variable13Datoreporte
	 */
	public Double getVariable13Datoreporte() {
		return variable13Datoreporte;
	}

	/**
	 * @param variable13Datoreporte the variable13Datoreporte to set
	 */
	public void setVariable13Datoreporte(Double variable13Datoreporte) {
		this.variable13Datoreporte = variable13Datoreporte;
	}

	/**
	 * @return the variable14Datoreporte
	 */
	public Double getVariable14Datoreporte() {
		return variable14Datoreporte;
	}

	/**
	 * @param variable14Datoreporte the variable14Datoreporte to set
	 */
	public void setVariable14Datoreporte(Double variable14Datoreporte) {
		this.variable14Datoreporte = variable14Datoreporte;
	}

	/**
	 * @return the variable15Datoreporte
	 */
	public Double getVariable15Datoreporte() {
		return variable15Datoreporte;
	}

	/**
	 * @param variable15Datoreporte the variable15Datoreporte to set
	 */
	public void setVariable15Datoreporte(Double variable15Datoreporte) {
		this.variable15Datoreporte = variable15Datoreporte;
	}

	/**
	 * @return the hora
	 */
	public Short getHora() {
		return valorHora;
	}

	/**
	 * @param hora the hora to set
	 */
	public void setHora(Short hora) {
		this.valorHora = hora;
	}

	public List<OrdenProduccionDTO> getOrdenes() {
		return ordenes;
	}

	public void setOrdenes(List<OrdenProduccionDTO> ordenes) {
		this.ordenes = ordenes;
	}

	public Short getValorHora() {
		return valorHora;
	}

	public void setValorHora(Short valorHora) {
		this.valorHora = valorHora;
	}

	public boolean isSeleccion() {
		return seleccion;
	}

	public void setSeleccion(boolean seleccion) {
		this.seleccion = seleccion;
	}

	public String getNumeroOrden() {
		return numeroOrdenSeleccionada;
	}

	public void setNumeroOrden(String numeroOrden) {
		this.numeroOrdenSeleccionada = numeroOrden;
	}

	public Integer getCodigoOrden() {
		return codigoOrdenSeleccionada;
	}

	public void setCodigoOrden(Integer codigoOrden) {
		this.codigoOrdenSeleccionada = codigoOrden;
	}

	// public String getNombreProducto() {
	// return nombreProducto;
	// }
	//
	// public void setNombreProducto(String nombreProducto) {
	// this.nombreProducto = nombreProducto;
	// }
	//
	public Integer getCodigoProducto() {
		return codigoProducto;
	}

	public void setCodigoProducto(Integer codigoProducto) {
		this.codigoProducto = codigoProducto;
	}

	public String getNombreSilo() {
		return nombreSilo;
	}

	public void setNombreSilo(String nombreSilo) {
		this.nombreSilo = nombreSilo;
	}

	public Integer getCodigoSilo() {
		return codigoSilo;
	}

	public void setCodigoSilo(Integer codigoSilo) {
		this.codigoSilo = codigoSilo;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public Long getCodigoLineaNegocio() {
		return codigoLineaNegocio;
	}

	public void setCodigoLineaNegocio(Long codigoLineaNegocio) {
		this.codigoLineaNegocio = codigoLineaNegocio;
	}

	public String getNombreLineaNegocio() {
		return nombreLineaNegocio;
	}

	public void setNombreLineaNegocio(String nombreLineaNegocio) {
		this.nombreLineaNegocio = nombreLineaNegocio;
	}

	public Long getCodigoNotificacionPlanta() {
		return codigoNotificacionPlanta;
	}

	public void setCodigoNotificacionPlanta(Long codigoNotificacionPlanta) {
		this.codigoNotificacionPlanta = codigoNotificacionPlanta;
	}

	public String getCambioProduccionHora() {
		return cambioProduccionHora;
	}

	public void setCambioProduccionHora(String cambioProduccionHora) {
		this.cambioProduccionHora = cambioProduccionHora;
	}

	public Boolean getCambioProduccionLavado() {
		return cambioProduccionLavado;
	}

	public void setCambioProduccionLavado(Boolean cambioProduccionLavado) {
		this.cambioProduccionLavado = cambioProduccionLavado;
	}

	public Boolean getCambioProduccionNormal() {
		return cambioProduccionNormal;
	}

	public void setCambioProduccionNormal(Boolean cambioProduccionNormal) {
		this.cambioProduccionNormal = cambioProduccionNormal;
	}

	public Long getCodigoSiloAntCambioProduc() {
		return codigoSiloAntCambioProduc;
	}

	public void setCodigoSiloAntCambioProduc(Long codigoSiloAntCambioProduc) {
		this.codigoSiloAntCambioProduc = codigoSiloAntCambioProduc;
	}

	public Long getCodigoTurno() {
		return codigoTurno;
	}

	public void setCodigoTurno(Long codigoTurno) {
		this.codigoTurno = codigoTurno;
	}

	public Boolean getNotificacionAprobada() {
		return notificacionAprobada;
	}

	public void setNotificacionAprobada(Boolean notificacionAprobada) {
		this.notificacionAprobada = notificacionAprobada;
	}

	public String getPlantilla() {
		return plantilla;
	}

	public void setPlantilla(String plantilla) {
		this.plantilla = plantilla;
	}

	public Integer getCodigoPlantilla() {
		return codigoPlantilla;
	}

	public void setCodigoPlantilla(Integer codigoPlantilla) {
		this.codigoPlantilla = codigoPlantilla;
	}
}
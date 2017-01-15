package pe.com.pacasmayo.sgcp.bean.reporte.parteDiario.produccion;

public class PuestoTrabajoProduccionGraficoBean {
	private Integer dia;
	private String nombrePuestoTrabajo;
	private Double toneladasproducidas;
	private String siglaProducto;
	private String nombrePuestoTrabajoporhora;
	private Double toneladasproducidasporhora;
	private Boolean reportetoneladasporhora;
	private String nombreEjeY;
	private Double horaProduccion;

	public PuestoTrabajoProduccionGraficoBean() {

	}

	public PuestoTrabajoProduccionGraficoBean(Integer dia, String nombrePuestoTrabajo, Double toneladasproducidas,
			String siglaProducto) {
		this.dia = dia;
		this.nombrePuestoTrabajo = nombrePuestoTrabajo;
		this.toneladasproducidas = toneladasproducidas;
		this.reportetoneladasporhora = Boolean.FALSE;
		this.siglaProducto = siglaProducto;

	}
	
	public PuestoTrabajoProduccionGraficoBean(Integer dia, String nombrePuestoTrabajo, Double toneladasproducidas,
			String siglaProducto, String nombreEjeY, String nombrePuestoTrabajoporhora, Double horaProduccion) {
		this.dia = dia;
		this.nombrePuestoTrabajo = nombrePuestoTrabajo;
		this.toneladasproducidas = toneladasproducidas;
		this.nombrePuestoTrabajoporhora = nombrePuestoTrabajoporhora;
		this.nombreEjeY = nombreEjeY;
		if (toneladasproducidas > 0d && horaProduccion > 0) {
			this.toneladasproducidasporhora = toneladasproducidas / horaProduccion;
		} else {
			this.toneladasproducidasporhora = 0d;
		}
		this.reportetoneladasporhora = Boolean.TRUE;
		this.siglaProducto = siglaProducto;
		this.horaProduccion = horaProduccion;

	}
	
	public PuestoTrabajoProduccionGraficoBean(Integer dia, String nombrePuestoTrabajo, Double toneladasproducidas,
			String siglaProducto, String nombreEjeY, Double horaProduccion) {
		this.dia = dia;
		this.nombrePuestoTrabajo = nombrePuestoTrabajo;
		this.nombrePuestoTrabajoporhora = "Horas";
		this.nombreEjeY = nombreEjeY;
		this.toneladasproducidas = toneladasproducidas;
		this.toneladasproducidasporhora = horaProduccion;
		
		this.reportetoneladasporhora = Boolean.TRUE;
		this.siglaProducto = siglaProducto;
		this.horaProduccion = horaProduccion;

	}

	/**
	 * @return the nombrePuestoTrabajo
	 */
	public String getNombrePuestoTrabajo() {
		return nombrePuestoTrabajo;
	}

	/**
	 * @param nombrePuestoTrabajo the nombrePuestoTrabajo to set
	 */
	public void setNombrePuestoTrabajo(String nombrePuestoTrabajo) {
		this.nombrePuestoTrabajo = nombrePuestoTrabajo;
	}

	/**
	 * @return the toneladasproducidas
	 */
	public Double getToneladasproducidas() {
		return toneladasproducidas;
	}

	/**
	 * @param toneladasproducidas the toneladasproducidas to set
	 */
	public void setToneladasproducidas(Double toneladasproducidas) {
		this.toneladasproducidas = toneladasproducidas;
	}

	/**
	 * @return the dia
	 */
	public Integer getDia() {
		return dia;
	}

	/**
	 * @param dia the dia to set
	 */
	public void setDia(Integer dia) {
		this.dia = dia;
	}

	/**
	 * @return the nombrePuestoTrabajoporhora
	 */
	public String getNombrePuestoTrabajoporhora() {
		return nombrePuestoTrabajoporhora;
	}

	/**
	 * @param nombrePuestoTrabajoporhora the nombrePuestoTrabajoporhora to set
	 */
	public void setNombrePuestoTrabajoporhora(String nombrePuestoTrabajoporhora) {
		this.nombrePuestoTrabajoporhora = nombrePuestoTrabajoporhora;
	}

	/**
	 * @return the toneladasproducidasporhora
	 */
	public Double getToneladasproducidasporhora() {
		return toneladasproducidasporhora;
	}

	/**
	 * @param toneladasproducidasporhora the toneladasproducidasporhora to set
	 */
	public void setToneladasproducidasporhora(Double toneladasproducidasporhora) {
		this.toneladasproducidasporhora = toneladasproducidasporhora;
	}

	/**
	 * @return the reportetoneladasporhora
	 */
	public Boolean getReportetoneladasporhora() {
		return reportetoneladasporhora;
	}

	/**
	 * @param reportetoneladasporhora the reportetoneladasporhora to set
	 */
	public void setReportetoneladasporhora(Boolean reportetoneladasporhora) {
		this.reportetoneladasporhora = reportetoneladasporhora;
	}

	/**
	 * @return the nombreEjeY
	 */
	public String getNombreEjeY() {
		return nombreEjeY;
	}

	/**
	 * @param nombreEjeY the nombreEjeY to set
	 */
	public void setNombreEjeY(String nombreEjeY) {
		this.nombreEjeY = nombreEjeY;
	}

	/**
	 * @return the siglaProducto
	 */
	public String getSiglaProducto() {
		return siglaProducto;
	}

	/**
	 * @param siglaProducto the siglaProducto to set
	 */
	public void setSiglaProducto(String siglaProducto) {
		this.siglaProducto = siglaProducto;
	}

	public Double getHoraProduccion() {
		return horaProduccion;
	}

	public void setHoraProduccion(Double horaProduccion) {
		this.horaProduccion = horaProduccion;
	}
	
}

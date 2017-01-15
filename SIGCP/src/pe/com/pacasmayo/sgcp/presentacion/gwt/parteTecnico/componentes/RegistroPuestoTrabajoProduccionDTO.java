package pe.com.pacasmayo.sgcp.presentacion.gwt.parteTecnico.componentes;

public class RegistroPuestoTrabajoProduccionDTO implements java.io.Serializable {

	private static final long serialVersionUID = -5041371946278104902L;
	private String nombrePuestoTrabajo;
	private Long codigoPuestoTrabajo;

	private Double mesAnteriorTM;
	private Double mesAnteriorHR;

	private Double produccionTM;
	private Double produccionHR;
	private Double produccionTMPH;
	private Double produccionKCAL;
	private Double produccionCarProd;
	private Double produccionCarCalent;

	private Double ajusteTM;
	private Double ajusteHR;

	private Double produccionRealTM;
	private Double produccionRealHR;
	private Double produccionRealTMPH;
	private Double produccionRealKCAL;
	private Double produccionRealCarProd;
	private Double produccionRealCarCalent;

	private Double minimoRendimiento;
	private Double maximoRendimiento;

	private Double factAntrac;

	private Double tasaProduccionNominal;

	private Double poderCalorificoPonderado;
	private String nombreProducto;
	private Long valorMax;
	private Double bunkerProduccion;
	private Double bunkerCalent;
	private Double bunkerProduccionReal;
	private Double bunkerCalentReal;

	public Long getValorMax() {
		return valorMax;
	}

	/**
	 * @return the bunkerProduccion
	 */
	public Double getBunkerProduccion() {
		return bunkerProduccion;
	}

	/**
	 * @param bunkerProduccion the bunkerProduccion to set
	 */
	public void setBunkerProduccion(Double bunkerProduccion) {
		this.bunkerProduccion = bunkerProduccion;
	}

	/**
	 * @return the bunkerCalent
	 */
	public Double getBunkerCalent() {
		return bunkerCalent;
	}

	/**
	 * @param bunkerCalent the bunkerCalent to set
	 */
	public void setBunkerCalent(Double bunkerCalent) {
		this.bunkerCalent = bunkerCalent;
	}

	/**
	 * @return the bunkerProduccionReal
	 */
	public Double getBunkerProduccionReal() {
		return bunkerProduccionReal;
	}

	/**
	 * @param bunkerProduccionReal the bunkerProduccionReal to set
	 */
	public void setBunkerProduccionReal(Double bunkerProduccionReal) {
		this.bunkerProduccionReal = bunkerProduccionReal;
	}

	/**
	 * @return the bunkerCalentReal
	 */
	public Double getBunkerCalentReal() {
		return bunkerCalentReal;
	}

	/**
	 * @param bunkerCalentReal the bunkerCalentReal to set
	 */
	public void setBunkerCalentReal(Double bunkerCalentReal) {
		this.bunkerCalentReal = bunkerCalentReal;
	}

	public void setValorMax(Long valorMax) {
		this.valorMax = valorMax;
	}

	public String getNombreProducto() {
		return nombreProducto;
	}

	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}

	public Double getProduccionKCAL() {
		return produccionKCAL;
	}

	public void setProduccionKCAL(Double produccionKCAL) {
		this.produccionKCAL = produccionKCAL;
	}

	public Double getProduccionRealKCAL() {
		return produccionRealKCAL;
	}

	public void setProduccionRealKCAL(Double produccionRealKCAL) {
		this.produccionRealKCAL = produccionRealKCAL;
	}

	public String getNombrePuestoTrabajo() {
		return nombrePuestoTrabajo;
	}

	public void setNombrePuestoTrabajo(String nombrePuestoTrabajo) {
		this.nombrePuestoTrabajo = nombrePuestoTrabajo;
	}

	public Long getCodigoPuestoTrabajo() {
		return codigoPuestoTrabajo;
	}

	public void setCodigoPuestoTrabajo(Long codigoPuestoTrabajo) {
		this.codigoPuestoTrabajo = codigoPuestoTrabajo;
	}

	public Double getMesAnteriorTM() {
		return mesAnteriorTM;
	}

	public void setMesAnteriorTM(Double mesAnteriorTM) {
		this.mesAnteriorTM = mesAnteriorTM;
	}

	public Double getMesAnteriorHR() {
		return mesAnteriorHR;
	}

	public void setMesAnteriorHR(Double mesAnteriorHR) {
		this.mesAnteriorHR = mesAnteriorHR;
	}

	public Double getProduccionTM() {
		return produccionTM;
	}

	public void setProduccionTM(Double produccionTM) {
		this.produccionTM = produccionTM;
	}

	public Double getProduccionHR() {
		return produccionHR;
	}

	public void setProduccionHR(Double produccionHR) {
		this.produccionHR = produccionHR;
	}

	public Double getProduccionTMPH() {
		return produccionTMPH;
	}

	public void setProduccionTMPH(Double produccionTMPH) {
		this.produccionTMPH = produccionTMPH;
	}

	public Double getAjusteTM() {
		return ajusteTM;
	}

	public void setAjusteTM(Double ajusteTM) {
		this.ajusteTM = ajusteTM;
	}

	public Double getAjusteHR() {
		return ajusteHR;
	}

	public void setAjusteHR(Double ajusteHR) {
		this.ajusteHR = ajusteHR;
	}

	public Double getProduccionRealTM() {
		return produccionRealTM;
	}

	public void setProduccionRealTM(Double produccionRealTM) {
		this.produccionRealTM = produccionRealTM;
	}

	public Double getProduccionRealHR() {
		return produccionRealHR;
	}

	public void setProduccionRealHR(Double produccionRealHR) {
		this.produccionRealHR = produccionRealHR;
	}

	public Double getProduccionRealTMPH() {
		return produccionRealTMPH;
	}

	public void setProduccionRealTMPH(Double produccionRealTMPH) {
		this.produccionRealTMPH = produccionRealTMPH;
	}

	public Double getMinimoRendimiento() {
		return minimoRendimiento;
	}

	public void setMinimoRendimiento(Double minimoRendimiento) {
		this.minimoRendimiento = minimoRendimiento;
	}

	public Double getMaximoRendimiento() {
		return maximoRendimiento;
	}

	public void setMaximoRendimiento(Double maximoRendimiento) {
		this.maximoRendimiento = maximoRendimiento;
	}

	public Double getFactAntrac() {
		return factAntrac;
	}

	public void setFactAntrac(Double factAntrac) {
		this.factAntrac = factAntrac;
	}

	public Double getTasaProduccionNominal() {
		if (tasaProduccionNominal == null) {
			return 0d;
		}
		return tasaProduccionNominal;
	}

	public void setTasaProduccionNominal(Double tasaProduccionNominal) {
		this.tasaProduccionNominal = tasaProduccionNominal;
	}

	public Double getPoderCalorificoPonderado() {
		return poderCalorificoPonderado;
	}

	public void setPoderCalorificoPonderado(Double poderCalorificoPonderado) {
		this.poderCalorificoPonderado = poderCalorificoPonderado;
	}

	/**
	 * @return the produccionCarProd
	 */
	public Double getProduccionCarProd() {
		return produccionCarProd;
	}

	/**
	 * @param produccionCarProd the produccionCarProd to set
	 */
	public void setProduccionCarProd(Double produccionCarProd) {
		this.produccionCarProd = produccionCarProd;
	}

	/**
	 * @return the produccionCarCalent
	 */
	public Double getProduccionCarCalent() {
		return produccionCarCalent;
	}

	/**
	 * @param produccionCarCalent the produccionCarCalent to set
	 */
	public void setProduccionCarCalent(Double produccionCarCalent) {
		this.produccionCarCalent = produccionCarCalent;
	}

	/**
	 * @return the produccionRealCarProd
	 */
	public Double getProduccionRealCarProd() {
		return produccionRealCarProd;
	}

	/**
	 * @param produccionRealCarProd the produccionRealCarProd to set
	 */
	public void setProduccionRealCarProd(Double produccionRealCarProd) {
		this.produccionRealCarProd = produccionRealCarProd;
	}

	/**
	 * @return the produccionRealCarCalent
	 */
	public Double getProduccionRealCarCalent() {
		return produccionRealCarCalent;
	}

	/**
	 * @param produccionRealCarCalent the produccionRealCarCalent to set
	 */
	public void setProduccionRealCarCalent(Double produccionRealCarCalent) {
		this.produccionRealCarCalent = produccionRealCarCalent;
	}
}

package pe.com.pacasmayo.sgcp.presentacion.gwt.parteTecnico.componentes;

public class RegistroTablaConsumosPuestoTrabajoDTO extends OrdenMolCementoDto implements java.io.Serializable {

	public RegistroTablaConsumosPuestoTrabajoDTO(String nombreComponenteOrden) {
		super(nombreComponenteOrden);
	}

	public RegistroTablaConsumosPuestoTrabajoDTO() {
		super(null);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long codigoPuestoTrabajo;
	private Long codigoProductoComponente;
	private String nombrePuestoTrabajo;
	private Long codigoComponente;
	private String nombreComponente;
	private String grupoComponente;
	private double montoConsumido;
	private double produccionPorcentaje;
	private double ajusteTM;
	private double produccionRealTM;
	private double porcetanjeCarbones;
	private double produccionRealPorcentaje;
	private double dosificacion;
	private boolean editadoManual;
	// codigo para identificar en el ingreso del bunker
	private Long codigo;

	// estos dos son para calcular la merma automaticamente
	// private double mermaIngresada;
	// private double stockFinalIngresado;
	// private double factorConversionm3;

	/**
	 * @return the codigo
	 */
	public Long getCodigo() {
		return codigo;
	}

	/**
	 * @param codigo the codigo to set
	 */
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public Long getCodigoComponente() {
		return codigoComponente;
	}

	public void setCodigoComponente(Long codigoComponente) {
		this.codigoComponente = codigoComponente;
	}

	public String getNombreComponente() {
		return nombreComponente;
	}

	public void setNombreComponente(String nombreComponente) {
		this.nombreComponente = nombreComponente;
	}

	public double getProduccionPorcentaje() {
		return produccionPorcentaje;
	}

	public void setProduccionPorcentaje(double produccionPorcentaje) {
		this.produccionPorcentaje = produccionPorcentaje;
	}

	public double getAjusteTM() {
		return ajusteTM;
	}

	public void setAjusteTM(double ajusteTM) {
		this.ajusteTM = ajusteTM;
	}

	public double getProduccionRealTM() {
		return produccionRealTM;
	}

	public void setProduccionRealTM(double produccionRealTM) {
		this.produccionRealTM = produccionRealTM;
	}

	public double getProduccionRealPorcentaje() {
		return produccionRealPorcentaje;
	}

	public void setProduccionRealPorcentaje(double produccionRealPorcentaje) {
		this.produccionRealPorcentaje = produccionRealPorcentaje;
	}

	public Long getCodigoPuestoTrabajo() {
		return codigoPuestoTrabajo;
	}

	public void setCodigoPuestoTrabajo(Long codigoPuestoTrabajo) {
		this.codigoPuestoTrabajo = codigoPuestoTrabajo;
	}

	public String getNombrePuestoTrabajo() {
		return nombrePuestoTrabajo;
	}

	public void setNombrePuestoTrabajo(String nombrePuestoTrabajo) {
		this.nombrePuestoTrabajo = nombrePuestoTrabajo;
	}

	public double getMontoConsumido() {
		return montoConsumido;
	}

	public void setMontoConsumido(double montoConsumido) {
		this.montoConsumido = montoConsumido;
	}

	public double getDosificacion() {
		return dosificacion;
	}

	public void setDosificacion(double dosificacion) {
		this.dosificacion = dosificacion;
	}

	public boolean isEditadoManual() {
		return editadoManual;
	}

	public void setEditadoManual(boolean editadoManual) {
		this.editadoManual = editadoManual;
	}

	/**
	 * @return the porcetanjeCarbones
	 */
	public double getPorcetanjeCarbones() {
		return porcetanjeCarbones;
	}

	/**
	 * @param porcetanjeCarbones the porcetanjeCarbones to set
	 */
	public void setPorcetanjeCarbones(double porcetanjeCarbones) {
		this.porcetanjeCarbones = porcetanjeCarbones;
	}

	public String getGrupoComponente() {
		return grupoComponente;
	}

	public void setGrupoComponente(String grupoComponente) {
		this.grupoComponente = grupoComponente;
	}

	public void setCodigoProductoComponente(Long codigoProductoComponente) {
		this.codigoProductoComponente = codigoProductoComponente;
	}

	public Long getCodigoProductoComponente() {
		return codigoProductoComponente;
	}

	// /**
	// * @return the mermaIngresada
	// */
	// public double getMermaIngresada() {
	// return mermaIngresada;
	// }
	//
	// /**
	// * @param mermaIngresada the mermaIngresada to set
	// */
	// public void setMermaIngresada(double mermaIngresada) {
	// this.mermaIngresada = mermaIngresada;
	// }
	//
	// /**
	// * @return the stockFinalIngresado
	// */
	// public double getStockFinalIngresado() {
	// return stockFinalIngresado;
	// }
	//
	// /**
	// * @param stockFinalIngresado the stockFinalIngresado to set
	// */
	// public void setStockFinalIngresado(double stockFinalIngresado) {
	// this.stockFinalIngresado = stockFinalIngresado;
	// }
	//
	// /**
	// * @return the factorConversionm3
	// */
	// public double getFactorConversionm3() {
	// return factorConversionm3;
	// }
	//
	// /**
	// * @param factorConversionm3 the factorConversionm3 to set
	// */
	// public void setFactorConversionm3(double factorConversionm3) {
	// this.factorConversionm3 = factorConversionm3;
	// }
}
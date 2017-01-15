package pe.com.pacasmayo.sgcp.bean.impl;

import pe.com.pacasmayo.sgcp.bean.DetalleParteTecnicoConsumoComponentesBean;

public class DetalleParteTecnicoConsumoComponentesBeanImpl implements DetalleParteTecnicoConsumoComponentesBean {

	Long codigoComponente;
	String tipoProducto;
	int ordenProceso;
	String proceso;
	String linea;
	String componente;
	double saldoInicial;
	double saldoFinal;
	double ingreso;
	double consumo;
	double produccionAcumulada;
	double consumoAcumulado;
	String unidadMedida;

	private Double movimientoLogistico;
	
	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.DetalleParteTecnicoConsumoComponentesBean
	 * #getCodigoComponente()
	 */
	public Long getCodigoComponente() {
		return codigoComponente;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.DetalleParteTecnicoConsumoComponentesBean
	 * #setCodigoComponente(java.lang.Long)
	 */
	public void setCodigoComponente(Long codigoComponente) {
		this.codigoComponente = codigoComponente;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.DetalleParteTecnicoConsumoComponentesBean
	 * #getSaldoInicial()
	 */
	public double getSaldoInicial() {
		return saldoInicial;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.DetalleParteTecnicoConsumoComponentesBean
	 * #setSaldoInicial(double)
	 */
	public void setSaldoInicial(double saldoInicial) {
		this.saldoInicial = saldoInicial;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.DetalleParteTecnicoConsumoComponentesBean
	 * #getSaldoFinal()
	 */
	public double getSaldoFinal() {
		return saldoFinal;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.DetalleParteTecnicoConsumoComponentesBean
	 * #setSaldoFinal(double)
	 */
	public void setSaldoFinal(double saldoFinal) {
		this.saldoFinal = saldoFinal;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.DetalleParteTecnicoConsumoComponentesBean
	 * #getIngreso()
	 */
	public double getIngreso() {
		return ingreso;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.DetalleParteTecnicoConsumoComponentesBean
	 * #setIngreso(double)
	 */
	public void setIngreso(double ingreso) {
		this.ingreso = ingreso;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.DetalleParteTecnicoConsumoComponentesBean
	 * #getConsumo()
	 */
	public double getConsumo() {
		return consumo;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.DetalleParteTecnicoConsumoComponentesBean
	 * #setConsumo(double)
	 */
	public void setConsumo(double consumo) {
		this.consumo = consumo;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.DetalleParteTecnicoConsumoComponentesBean
	 * #getProduccionAcumulada()
	 */
	public double getProduccionAcumulada() {
		return produccionAcumulada;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.DetalleParteTecnicoConsumoComponentesBean
	 * #setProduccionAcumulada(double)
	 */
	public void setProduccionAcumulada(double produccionAcumulada) {
		this.produccionAcumulada = produccionAcumulada;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.DetalleParteTecnicoConsumoComponentesBean
	 * #getConsumoAcumulado()
	 */
	public double getConsumoAcumulado() {
		return consumoAcumulado;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.DetalleParteTecnicoConsumoComponentesBean
	 * #setConsumoAcumulado(double)
	 */
	public void setConsumoAcumulado(double consumoAcumulado) {
		this.consumoAcumulado = consumoAcumulado;
	}

	public String getComponente() {
		return componente;
	}

	public void setComponente(String componente) {
		this.componente = componente;
	}

	public String getUnidadMedida() {
		return unidadMedida;
	}

	public void setUnidadMedida(String unidadMedida) {
		this.unidadMedida = unidadMedida;
	}

	public String getTipoProducto() {
		return tipoProducto;
	}

	public void setTipoProducto(String tipoProducto) {
		this.tipoProducto = tipoProducto;
	}

	public String getProceso() {
		return proceso;
	}

	public void setProceso(String proceso) {
		this.proceso = proceso;
	}

	public int getOrdenProceso() {
		return ordenProceso;
	}

	public void setOrdenProceso(int ordenProceso) {
		this.ordenProceso = ordenProceso;
	}

	public String getLinea() {
		return linea;
	}

	public void setLinea(String linea) {
		this.linea = linea;
	}

	public Double getMovimientoLogistico() {
		return movimientoLogistico;
	}

	public void setMovimientoLogistico(Double movimientoLogistico) {
		this.movimientoLogistico = movimientoLogistico;
	}

}

package pe.com.pacasmayo.sgcp.bean;

public interface DetalleParteTecnicoConsumoComponentesBean {

	public abstract Long getCodigoComponente();

	public abstract void setCodigoComponente(Long codigoComponente);

	public abstract double getSaldoInicial();

	public abstract void setSaldoInicial(double saldoInicial);

	public abstract double getSaldoFinal();

	public abstract void setSaldoFinal(double saldoFinal);

	public abstract double getIngreso();

	public abstract void setIngreso(double ingreso);

	public abstract double getConsumo();

	public abstract void setConsumo(double consumo);

	public abstract double getProduccionAcumulada();

	public abstract void setProduccionAcumulada(double produccionAcumulada);

	public abstract double getConsumoAcumulado();

	public abstract void setConsumoAcumulado(double consumoAcumulado);

	public abstract String getComponente();

	public abstract void setComponente(String componente);

	public abstract String getUnidadMedida();

	public abstract void setUnidadMedida(String unidadMedida);

	public abstract String getTipoProducto();

	public abstract void setTipoProducto(String tipoProducto);

	public abstract String getProceso();

	public abstract void setProceso(String proceso);

	public abstract int getOrdenProceso();

	public abstract void setOrdenProceso(int ordenProceso);

	public abstract String getLinea();

	public abstract void setLinea(String linea);

	public Double getMovimientoLogistico();

	public void setMovimientoLogistico(Double movimientoLogistico);

}
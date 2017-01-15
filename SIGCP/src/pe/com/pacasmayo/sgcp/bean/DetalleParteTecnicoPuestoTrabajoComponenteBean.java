package pe.com.pacasmayo.sgcp.bean;

import java.util.List;

public interface DetalleParteTecnicoPuestoTrabajoComponenteBean {

	public abstract String getComponente();

	public abstract void setComponente(String componente);

	public abstract String getTipoProducto();

	public abstract void setTipoProducto(String tipoProducto);

	public abstract String getProceso();

	public abstract void setProceso(String proceso);

	public abstract int getOrdenProceso();

	public abstract void setOrdenProceso(int ordenProceso);

	public abstract String getLinea();

	public abstract void setLinea(String linea);

	public abstract List<DetallePuestoTrabajoComponenteBean> getDetallePuestoTrabajo();

	public abstract void setDetallePuetoTrabajo(List<DetallePuestoTrabajoComponenteBean> detallePuestoTrabajo);

	// AGREGADO POR FABIAN

	public abstract Long getCodigoComponente();

	public abstract void setCodigoComponente(Long codigoComponente);
}
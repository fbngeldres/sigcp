package pe.com.pacasmayo.sgcp.bean;

import java.util.List;

public interface DetallePuestoTrabajoComponenteBean {

	public abstract String getPuestoTrabajo();

	public abstract void setPuestoTrabajo(String puestoTrabajo);

	public abstract double getProduccion();

	public abstract void setProduccion(double produccion);

	public abstract double getTiempoProduccion();

	public abstract void setTiempoProduccion(double tiempoProduccion);

	public abstract Long getCodigoPuestoTrabajo();

	public abstract void setCodigoPuestoTrabajo(Long codigoPuestoTrabajo);

	public abstract List<DetallePuestoTrabajoConsumoCombustibleBean> getDetalleConsumoCombustible();

	public abstract void setDetalleConsumoCombustible(List<DetallePuestoTrabajoConsumoCombustibleBean> detalleConsumoCombustible);
}

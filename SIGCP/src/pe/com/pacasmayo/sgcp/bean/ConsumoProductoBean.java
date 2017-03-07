package pe.com.pacasmayo.sgcp.bean;

import java.util.List;

public interface ConsumoProductoBean {

	public abstract String getNombreMaterial();

	public abstract void setNombreMaterial(String nombreMaterial);

	public abstract List<ConsumoProductoPuestoTrabaBean> getConsumoPuestoTrabajo();

	public abstract void setConsumoPuestoTrabajo(List<ConsumoProductoPuestoTrabaBean> consumoPuestoTrabajo);

}
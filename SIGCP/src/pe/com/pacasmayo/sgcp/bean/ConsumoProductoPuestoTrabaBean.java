package pe.com.pacasmayo.sgcp.bean;

public interface ConsumoProductoPuestoTrabaBean {

	public abstract String getNombreMaterial();

	public abstract void setNombreMaterial(String nombreMaterial);

	public abstract String getNombrePuestoTrabajo();

	public abstract void setNombrePuestoTrabajo(String nombrePuestoTrabajo);

	public abstract Double getConsumoMes();

	public abstract void setConsumoMes(Double consumoMes);

	public abstract Double getConsumoAcumuladoAnio();

	public abstract void setConsumoAcumuladoAnio(Double consumoAcumuladoAnio);

}
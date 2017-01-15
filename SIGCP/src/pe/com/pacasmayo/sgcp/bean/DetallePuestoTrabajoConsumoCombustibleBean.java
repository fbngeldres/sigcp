package pe.com.pacasmayo.sgcp.bean;

public interface DetallePuestoTrabajoConsumoCombustibleBean {

	public abstract String getNombreComponenteCombustible();

	public abstract void setNombreComponenteCombustible(String nombreComponenteCombustible);

	public abstract double getConsumoComponenteCombustible();

	public abstract void setConsumoComponenteCombustible(double consumoComponenteCombustible);

	public abstract Long getCodigoConsumo();

	public abstract void setCodigoConsumo(Long codigoConsumo);

	public String getUnidadMedida();

	public void setUnidadMedida(String unidadMedida);

}
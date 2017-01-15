package pe.com.pacasmayo.sgcp.bean;

public interface AprobarAjusteProduccionBean {

	public Long getCodigoProductoAjusteProducto();

	public void setCodigoProductoAjusteProducto(Long codigoProductoAjusteProducto);

	public String getNombreProductoAjusteProducto();

	public void setNombreProductoAjusteProducto(String nombreProductoAjusteProducto);

	public String getCodigoProductoSapAjusteProducto();

	public void setCodigoProductoSapAjusteProducto(String codigoProductoSapAjusteProducto);

	public Long getCodigoProductoComponente();

	public void setCodigoProductoComponente(Long codigoProductoComponente);

	public String getNombreProductoComponente();

	public void setNombreProductoComponente(String nombreProductoComponente);

	public String getCodigoProductoSapComponente();

	public void setCodigoProductoSapComponente(String codigoProductoSapComponente);

	public Double getSumaTmRealConsumoComponenteAjuste();

	public void setSumaTmRealConsumoComponenteAjuste(Double sumaTmRealConsumoComponenteAjuste);

	public Long getCodigoPuestoTrabajo();

	public void setCodigoPuestoTrabajo(Long codigoPuestoTrabajo);

	public String getNombrePuestoTrabajo();

	public void setNombrePuestoTrabajo(String nombrePuestoTrabajo);

	public Double getSumaMermas();

	public void setSumaMermas(Double sumaMermas);
}

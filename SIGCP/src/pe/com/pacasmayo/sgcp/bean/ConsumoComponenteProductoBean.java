package pe.com.pacasmayo.sgcp.bean;

public interface ConsumoComponenteProductoBean {

	public abstract Long getCodigoProductoConsumidor();

	public abstract void setCodigoProductoConsumidor(Long codigoProductoConsumidor);

	public abstract String getNombreProductoConsumidor();

	public abstract void setNombreProductoConsumidor(String nombreProductoConsumidor);

	public abstract Double getConsumo();

	public abstract void setConsumo(Double consumo);

	public abstract ProductoBean getProducto();

	public abstract void setProducto(ProductoBean producto);

	public abstract ProductoBean getProductoConsumido();

	public abstract void setProductoConsumido(ProductoBean productoConsumido);

	public abstract String getUnidadMedida();

	public abstract void setUnidadMedida(String unidadMedida);

}
package pe.com.pacasmayo.sgcp.bean;

import java.util.List;

public interface ComponenteConsumidoBean {

	public abstract List<ComponenteConsumoBean> getConsumos();

	public abstract void setConsumos(List<ComponenteConsumoBean> consumos);

}
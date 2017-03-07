package pe.com.pacasmayo.sgcp.bean.impl;

import java.util.List;

import pe.com.pacasmayo.sgcp.bean.ComponenteConsumidoBean;
import pe.com.pacasmayo.sgcp.bean.ComponenteConsumoBean;

public class ComponenteConsumidoBeanImpl implements ComponenteConsumidoBean {

	private List<ComponenteConsumoBean> consumos;

	public ComponenteConsumidoBeanImpl() {

	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.ComponenteConsumidoBe#getConsumos()
	 */
	public List<ComponenteConsumoBean> getConsumos() {
		return consumos;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.ComponenteConsumidoBe#setConsumos(java
	 * .util.List)
	 */
	public void setConsumos(List<ComponenteConsumoBean> consumos) {
		this.consumos = consumos;
	}
}

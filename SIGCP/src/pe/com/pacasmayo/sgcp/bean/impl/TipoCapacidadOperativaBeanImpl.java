package pe.com.pacasmayo.sgcp.bean.impl;

import pe.com.pacasmayo.sgcp.bean.TipoCapacidadOperativaBean;
import pe.com.pacasmayo.sgcp.bean.UnidadMedidaBean;

public class TipoCapacidadOperativaBeanImpl extends EntidadBeanImpl implements TipoCapacidadOperativaBean {

	private UnidadMedidaBean unidadMedida;

	public TipoCapacidadOperativaBeanImpl() {

	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.TipoCapacidadOperativaBean#getUnidadMedida
	 * ()
	 */
	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.TipoCapacidadOperativaBean#getUnidadMedida
	 * ()
	 */
	public UnidadMedidaBean getUnidadMedida() {
		return unidadMedida;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.TipoCapacidadOperativaBean#setUnidadMedida
	 * (pe.com.pacasmayo.sgcp.bean.UnidadMedidaBean)
	 */
	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.TipoCapacidadOperativaBean#setUnidadMedida
	 * (pe.com.pacasmayo.sgcp.bean.UnidadMedidaBean)
	 */
	public void setUnidadMedida(UnidadMedidaBean unidadMedida) {
		this.unidadMedida = unidadMedida;
	}
}

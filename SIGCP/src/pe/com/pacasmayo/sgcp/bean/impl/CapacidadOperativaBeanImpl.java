package pe.com.pacasmayo.sgcp.bean.impl;

import pe.com.pacasmayo.sgcp.bean.CapacidadOperativaBean;
import pe.com.pacasmayo.sgcp.bean.CapacidadOperativaRegistroMensualBean;
import pe.com.pacasmayo.sgcp.bean.OperacionBean;
import pe.com.pacasmayo.sgcp.bean.TasaRealProduccionBean;
import pe.com.pacasmayo.sgcp.bean.TipoCapacidadOperativaBean;

public class CapacidadOperativaBeanImpl implements CapacidadOperativaBean {

	private Long codigo;
	private TasaRealProduccionBean tasaRealProduccion;
	private TipoCapacidadOperativaBean tipoCapacidadOperativa;
	private OperacionBean operacion;
	private CapacidadOperativaRegistroMensualBean[] listaCapacidadOperativaRegistroMensual;

	public CapacidadOperativaBeanImpl() {
		listaCapacidadOperativaRegistroMensual = new CapacidadOperativaRegistroMensualBean[12];
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.CapacidadOperativaBean#getCodigo()
	 */
	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.CapacidadOperativaBean#getCodigo()
	 */
	public Long getCodigo() {
		return codigo;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.CapacidadOperativaBean#setCodigo(java
	 * .lang.Long)
	 */
	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.CapacidadOperativaBean#setCodigo(java
	 * .lang.Long)
	 */
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.CapacidadOperativaBean#getTasaRealProduccion
	 * ()
	 */
	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.CapacidadOperativaBean#getTasaRealProduccion
	 * ()
	 */
	public TasaRealProduccionBean getTasaRealProduccion() {
		return tasaRealProduccion;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.CapacidadOperativaBean#setTasaRealProduccion
	 * (pe.com.pacasmayo.sgcp.bean.TasaRealProduccionBean)
	 */
	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.CapacidadOperativaBean#setTasaRealProduccion
	 * (pe.com.pacasmayo.sgcp.bean.TasaRealProduccionBean)
	 */
	public void setTasaRealProduccion(TasaRealProduccionBean tasaRealProduccion) {
		this.tasaRealProduccion = tasaRealProduccion;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.CapacidadOperativaBean#
	 * getTipoCapacidadOperativa()
	 */
	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.CapacidadOperativaBean#
	 * getTipoCapacidadOperativa()
	 */
	public TipoCapacidadOperativaBean getTipoCapacidadOperativa() {
		return tipoCapacidadOperativa;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.CapacidadOperativaBean#
	 * setTipoCapacidadOperativa
	 * (pe.com.pacasmayo.sgcp.bean.TipoCapacidadOperativaBean)
	 */
	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.CapacidadOperativaBean#
	 * setTipoCapacidadOperativa
	 * (pe.com.pacasmayo.sgcp.bean.TipoCapacidadOperativaBean)
	 */
	public void setTipoCapacidadOperativa(TipoCapacidadOperativaBean tipoCapacidadOperativa) {
		this.tipoCapacidadOperativa = tipoCapacidadOperativa;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.CapacidadOperativaBean#getOperacion()
	 */
	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.CapacidadOperativaBean#getOperacion()
	 */
	public OperacionBean getOperacion() {
		return operacion;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.CapacidadOperativaBean#setOperacion(pe
	 * .com.pacasmayo.sgcp.bean.OperacionBean)
	 */
	/*
	 * (non-Javadoc)
	 * @see
	 * pe.com.pacasmayo.sgcp.bean.impl.CapacidadOperativaBean#setOperacion(pe
	 * .com.pacasmayo.sgcp.bean.OperacionBean)
	 */
	public void setOperacion(OperacionBean operacion) {
		this.operacion = operacion;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.CapacidadOperativaBean#
	 * getListaCapacidadOperativaRegistroMensual()
	 */
	public CapacidadOperativaRegistroMensualBean[] getListaCapacidadOperativaRegistroMensual() {
		return listaCapacidadOperativaRegistroMensual;
	}

	/*
	 * (non-Javadoc)
	 * @see pe.com.pacasmayo.sgcp.bean.impl.CapacidadOperativaBean#
	 * setListaCapacidadOperativaRegistroMensual
	 * (pe.com.pacasmayo.sgcp.bean.CapacidadOperativaRegistroMensualBean[])
	 */
	public void setListaCapacidadOperativaRegistroMensual(
			CapacidadOperativaRegistroMensualBean[] listaCapacidadOperativaRegistroMensual) {
		this.listaCapacidadOperativaRegistroMensual = listaCapacidadOperativaRegistroMensual;
	}

}

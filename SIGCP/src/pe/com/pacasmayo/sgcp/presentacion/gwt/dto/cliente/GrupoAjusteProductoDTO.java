package pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente;

import java.io.Serializable;

public class GrupoAjusteProductoDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6174835884359575851L;

	private GrupoAjusteDTO grupoAjuste;
	private ProduccionDTO produccion;
	private Short orden;

	public GrupoAjusteDTO getGrupoAjuste() {
		return grupoAjuste;
	}

	public void setGrupoAjuste(GrupoAjusteDTO grupoAjuste) {
		this.grupoAjuste = grupoAjuste;
	}

	public ProduccionDTO getProduccion() {
		return produccion;
	}

	public void setProduccion(ProduccionDTO produccion) {
		this.produccion = produccion;
	}

	public Short getOrden() {
		return orden;
	}

	public void setOrden(Short orden) {
		this.orden = orden;
	}

}

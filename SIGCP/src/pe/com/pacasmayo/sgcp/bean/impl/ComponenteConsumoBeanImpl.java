package pe.com.pacasmayo.sgcp.bean.impl;

import pe.com.pacasmayo.sgcp.bean.ComponenteConsumoBean;

public class ComponenteConsumoBeanImpl implements ComponenteConsumoBean {

	private String nombreTipoUno;
	private String nombreTipoDos;
	private String nombreTipoTres;
	private String nombreTipoCuatro;
	private String nombreTipoCinco;

	private String unidadMedida;

	public String getUnidadMedida() {
		return unidadMedida;
	}

	public void setUnidadMedida(String unidadMedida) {
		this.unidadMedida = unidadMedida;
	}

	private String valorConsumo;

	public ComponenteConsumoBeanImpl() {
		valorConsumo = "0.00";
	}

	public String getValorConsumo() {
		return valorConsumo;
	}

	public void setValorConsumo(String valorConsumo) {
		this.valorConsumo = valorConsumo;
	}

	public String getNombreTipoUno() {
		return nombreTipoUno;
	}

	public void setNombreTipoUno(String nombreTipoUno) {
		this.nombreTipoUno = nombreTipoUno;
	}

	public String getNombreTipoDos() {
		return nombreTipoDos;
	}

	public void setNombreTipoDos(String nombreTipoDos) {
		this.nombreTipoDos = nombreTipoDos;
	}

	public String getNombreTipoTres() {
		return nombreTipoTres;
	}

	public void setNombreTipoTres(String nombreTipoTres) {
		this.nombreTipoTres = nombreTipoTres;
	}

	public String getNombreTipoCuatro() {
		return nombreTipoCuatro;
	}

	public void setNombreTipoCuatro(String nombreTipoCuatro) {
		this.nombreTipoCuatro = nombreTipoCuatro;
	}

	public String getNombreTipoCinco() {
		return nombreTipoCinco;
	}

	public void setNombreTipoCinco(String nombreTipoCinco) {
		this.nombreTipoCinco = nombreTipoCinco;
	}

}

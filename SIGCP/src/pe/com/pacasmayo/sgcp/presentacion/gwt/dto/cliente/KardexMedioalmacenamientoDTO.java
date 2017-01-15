package pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente;

import java.io.Serializable;

public class KardexMedioalmacenamientoDTO implements Serializable {

	private static final long serialVersionUID = 3440959751488047345L;

	private String medioalmacenamiento;
	private Double saldoInicialTablakardexmedioalmac;
	private Double ingresoTablakardexmedioalmac;
	private Double consumoTablakardexmedioalmac;
	private Double stockFinallTablakardexmedioalmac;

	public String getMedioalmacenamiento() {
		return medioalmacenamiento;
	}

	public void setMedioalmacenamiento(String medioalmacenamiento) {
		this.medioalmacenamiento = medioalmacenamiento;
	}

	public Double getSaldoInicialTablakardexmedioalmac() {
		return saldoInicialTablakardexmedioalmac;
	}

	public void setSaldoInicialTablakardexmedioalmac(Double saldoInicialTablakardexmedioalmac) {
		this.saldoInicialTablakardexmedioalmac = saldoInicialTablakardexmedioalmac;
	}

	public Double getIngresoTablakardexmedioalmac() {
		return ingresoTablakardexmedioalmac;
	}

	public void setIngresoTablakardexmedioalmac(Double ingresoTablakardexmedioalmac) {
		this.ingresoTablakardexmedioalmac = ingresoTablakardexmedioalmac;
	}

	public Double getConsumoTablakardexmedioalmac() {
		return consumoTablakardexmedioalmac;
	}

	public void setConsumoTablakardexmedioalmac(Double consumoTablakardexmedioalmac) {
		this.consumoTablakardexmedioalmac = consumoTablakardexmedioalmac;
	}

	public Double getStockFinallTablakardexmedioalmac() {
		return stockFinallTablakardexmedioalmac;
	}

	public void setStockFinallTablakardexmedioalmac(Double stockFinallTablakardexmedioalmac) {
		this.stockFinallTablakardexmedioalmac = stockFinallTablakardexmedioalmac;
	}

}

package pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente;

public class RegistroKardexMateriaPrimaDTO extends RegistroKardexParteDiarioDTO {

	private static final long serialVersionUID = 9110378849666657525L;

	private Double tmHumedadIngreso;
	private Double factorHumedadIngreso;
	private Double tmSecaIngreso;
	private Double tmHumedadConsumo;
	private Double factorHumedadConsumo;
	private Double tmSecaConsumo;
	private Boolean poseeHumedadIngreso;
	private Boolean poseeHumedadConsumo;

	public Double getTmHumedadIngreso() {
		return tmHumedadIngreso;
	}

	public void setTmHumedadIngreso(Double tmHumedadIngreso) {
		this.tmHumedadIngreso = tmHumedadIngreso;
	}

	public Double getFactorHumedadIngreso() {
		return factorHumedadIngreso;
	}

	public void setFactorHumedadIngreso(Double factorHumedadIngreso) {
		this.factorHumedadIngreso = factorHumedadIngreso;
	}

	public Double getTmSecaIngreso() {
		return tmSecaIngreso;
	}

	public void setTmSecaIngreso(Double tmSecaIngreso) {
		this.tmSecaIngreso = tmSecaIngreso;
	}

	public Double getTmHumedadConsumo() {
		return tmHumedadConsumo;
	}

	public void setTmHumedadConsumo(Double tmHumedadConsumo) {
		this.tmHumedadConsumo = tmHumedadConsumo;
	}

	public Double getFactorHumedadConsumo() {
		return factorHumedadConsumo;
	}

	public void setFactorHumedadConsumo(Double factorHumedadConsumo) {
		this.factorHumedadConsumo = factorHumedadConsumo;
	}

	public Double getTmSecaConsumo() {
		return tmSecaConsumo;
	}

	public void setTmSecaConsumo(Double tmSecaConsumo) {
		this.tmSecaConsumo = tmSecaConsumo;
	}

}

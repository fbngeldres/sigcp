package pe.com.pacasmayo.sgcp.presentacion.gwt.parteTecnico.componentes;

/**
 * DTO usado unicamente para resultado de consulta sobre consumos y factores
 */
public class ConsumosFactoresPuestoTrabajoDTO {
	private Double consumoCrudo;
	private Double consumoCarbon;
	private Double factAntrac;

	public ConsumosFactoresPuestoTrabajoDTO(Double consumoCrudo, Double consumoCarbon, Double factAntrac) {
		super();
		this.consumoCrudo = consumoCrudo;
		this.consumoCarbon = consumoCarbon;
		this.factAntrac = factAntrac;
	}

	public Double getConsumoCrudo() {
		return consumoCrudo;
	}

	public void setConsumoCrudo(Double consumoCrudo) {
		this.consumoCrudo = consumoCrudo;
	}

	public Double getConsumoCarbon() {
		return consumoCarbon;
	}

	public void setConsumoCarbon(Double consumoCarbon) {
		this.consumoCarbon = consumoCarbon;
	}

	public Double getFactAntrac() {
		return factAntrac;
	}

	public void setFactAntrac(Double factAntrac) {
		this.factAntrac = factAntrac;
	}
}

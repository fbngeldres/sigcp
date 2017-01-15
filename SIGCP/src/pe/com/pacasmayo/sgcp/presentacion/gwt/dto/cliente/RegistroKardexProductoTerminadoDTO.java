package pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente;

import java.util.List;
import java.util.Map;

public class RegistroKardexProductoTerminadoDTO extends RegistroKardexParteDiarioDTO {

	private static final long serialVersionUID = 4592052475726602878L;

	protected Map<String, Double> consumoComponentes = null;
	protected Double fisico = null;
	protected Double variacion = null;
	private List<KardexMedioalmacenamientoDTO> silos;

	public Map<String, Double> getConsumoComponentes() {
		return consumoComponentes;
	}

	public void setConsumoComponentes(Map<String, Double> consumoComponentes) {
		this.consumoComponentes = consumoComponentes;
	}

	public Double getLibros() {
		return saldoInicial + ingreso - consumo;
	}

	public Double getFisico() {
		return fisico;
	}

	public void setFisico(Double fisico) {
		this.fisico = fisico;
	}

	public Double getVariacion() {
		return variacion;
	}

	public void setVariacion(Double variacion) {
		this.variacion = variacion;
	}
}

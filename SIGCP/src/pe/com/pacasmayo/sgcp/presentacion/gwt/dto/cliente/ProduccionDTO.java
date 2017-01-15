package pe.com.pacasmayo.sgcp.presentacion.gwt.dto.cliente;

import java.io.Serializable;
import java.util.List;

public class ProduccionDTO implements Serializable {

	private static final long serialVersionUID = 8943667529567467555L;
	private Long pkProduccion;
	private ProductoDTO producto;
	private ProcesoDTO proceso;
	private List<MedioAlmacenamientoDTO> medios;

	public Long getPkProduccion() {
		return pkProduccion;
	}

	public void setPkProduccion(Long pkProduccion) {
		this.pkProduccion = pkProduccion;
	}

	public ProductoDTO getProducto() {
		return producto;
	}

	public void setProducto(ProductoDTO producto) {
		this.producto = producto;
	}

	public ProcesoDTO getProceso() {
		return proceso;
	}

	public void setProceso(ProcesoDTO proceso) {
		this.proceso = proceso;
	}

	public List<MedioAlmacenamientoDTO> getMedios() {
		return medios;
	}

	public void setMedios(List<MedioAlmacenamientoDTO> medios) {
		this.medios = medios;
	}
}
package pe.com.pacasmayo.sgcp.presentacion.gwt.parteTecnico.componentes;

import java.util.List;
import java.util.Map;

import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Cubicacion;

public class CubicacionReporteDTO {
	private Cubicacion cubicacion;
	private Map otrosDatos;
	private List<Map> ajustes;
	private List<RegistroPuestoTrabajoProduccionDTO> produccionPuestoTrabajo;

	public void setCubicacion(Cubicacion cubicacion) {
		this.cubicacion = cubicacion;
	}

	public Cubicacion getCubicacion() {
		return cubicacion;
	}

	public void setDatosProyectados(Map otrosDatos) {
		this.otrosDatos = otrosDatos;
	}

	public Map getOtrosDatos() {
		return otrosDatos;
	}

	public void setAjustes(List<Map> ajustes) {
		this.ajustes = ajustes;
	}

	public List<Map> getAjustes() {
		return ajustes;
	}

	public List<RegistroPuestoTrabajoProduccionDTO> getProduccionPuestoTrabajo() {
		return produccionPuestoTrabajo;
	}

	public void setProduccionPuestoTrabajo(List<RegistroPuestoTrabajoProduccionDTO> produccionPuestoTrabajo) {
		this.produccionPuestoTrabajo = produccionPuestoTrabajo;
	}

}

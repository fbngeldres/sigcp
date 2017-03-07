package pe.com.pacasmayo.sgcp.bean.reporte.parteDiario.produccion;

import java.util.ArrayList;
import java.util.List;

public class SubReporteGraficoPuestoTrabajoProduccionBean {
	private String tituloSubReporte;
	private List<PuestoTrabajoProduccionGraficoBean> subReporteGraficos;
	
	private String tituloSubReporteEnergia;
	private List<PuestoTrabajoProduccionGraficoBean> subReporteGraficosEnergia;

	/**
	 * @return the subReporteGraficos
	 */
	
	public SubReporteGraficoPuestoTrabajoProduccionBean(){
		subReporteGraficos = new ArrayList<PuestoTrabajoProduccionGraficoBean>();
		subReporteGraficosEnergia = new ArrayList<PuestoTrabajoProduccionGraficoBean>();
	}
	
	public List<PuestoTrabajoProduccionGraficoBean> getSubReporteGraficos() {
		return subReporteGraficos;
	}

	/**
	 * @param subReporteGraficos the subReporteGraficos to set
	 */
	public void setSubReporteGraficos(List<PuestoTrabajoProduccionGraficoBean> subReporteGraficos) {
		this.subReporteGraficos = subReporteGraficos;
	}

	/**
	 * @return the tituloSubReporte
	 */
	public String getTituloSubReporte() {
		return tituloSubReporte;
	}

	/**
	 * @param tituloSubReporte the tituloSubReporte to set
	 */
	public void setTituloSubReporte(String tituloSubReporte) {
		this.tituloSubReporte = tituloSubReporte;
	}

	public String getTituloSubReporteEnergia() {
		return tituloSubReporteEnergia;
	}

	public void setTituloSubReporteEnergia(String tituloSubReporteEnergia) {
		this.tituloSubReporteEnergia = tituloSubReporteEnergia;
	}

	public List<PuestoTrabajoProduccionGraficoBean> getSubReporteGraficosEnergia() {
		return subReporteGraficosEnergia;
	}

	public void setSubReporteGraficosEnergia(List<PuestoTrabajoProduccionGraficoBean> subReporteGraficosEnergia) {
		this.subReporteGraficosEnergia = subReporteGraficosEnergia;
	}
	
}

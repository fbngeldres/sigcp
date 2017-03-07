package pe.com.pacasmayo.sgcp.bean;

import java.util.ArrayList;
import java.util.List;

/*
 * SGCP (Sistema de Gestión y Control de la Producción)
 * Archivo: ReporteParteTecnicoConsumoPuestoTrabajoSub_B_Bean.java
 * Modificado: Sep 28, 2012 12:34:25 AM
 * Autor: gintelUno
 *
 * Copyright (C) DBAccess, 2010. All rights reserved.
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */
public class ReporteParteTecnicoConsumoPuestoTrabajoSub_B_Bean {

	private List<ReporteParteTecnicoOperacionesConsumoPuestoTrabajoSub_A_B_Bean> subReporteConsumoPorPuestoTrabajo;

	public ReporteParteTecnicoConsumoPuestoTrabajoSub_B_Bean() {

	}

	public ReporteParteTecnicoConsumoPuestoTrabajoSub_B_Bean(List<DetalleParteTecnicoPuestoTrabajoComponenteBean> list,
			String[] listaNombresClinker, String[] listanombreCrudo, String[] listanombreBituminoso,
			String[] listaNombresAntracita, String tipoReporte) {
		ReporteParteTecnicoOperacionesConsumoPuestoTrabajoSub_A_B_Bean bean;
		subReporteConsumoPorPuestoTrabajo = new ArrayList<ReporteParteTecnicoOperacionesConsumoPuestoTrabajoSub_A_B_Bean>();
		bean = new ReporteParteTecnicoOperacionesConsumoPuestoTrabajoSub_A_B_Bean(list, listaNombresClinker, listanombreCrudo,
				listanombreBituminoso, listaNombresAntracita, tipoReporte);
		subReporteConsumoPorPuestoTrabajo.add(bean);
	}

	/**
	 * @return the subReporteConsumoPorPuestoTrabajo
	 */
	public List<ReporteParteTecnicoOperacionesConsumoPuestoTrabajoSub_A_B_Bean> getSubReporteConsumoPorPuestoTrabajo() {
		return subReporteConsumoPorPuestoTrabajo;
	}

	/**
	 * @param subReporteConsumoPorPuestoTrabajo the
	 *            subReporteConsumoPorPuestoTrabajo to set
	 */
	public void setSubReporteConsumoPorPuestoTrabajo(
			List<ReporteParteTecnicoOperacionesConsumoPuestoTrabajoSub_A_B_Bean> subReporteConsumoPorPuestoTrabajo) {
		this.subReporteConsumoPorPuestoTrabajo = subReporteConsumoPorPuestoTrabajo;
	}

}
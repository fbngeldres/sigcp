package pe.com.pacasmayo.sgcp.integrador.operacion.impl;

import java.util.Date;
import java.util.List;

import pe.com.pacasmayo.sgcp.excepciones.IntegracionException;
import pe.com.pacasmayo.sgcp.integrador.excepciones.IntegradorExcepcion;
import pe.com.pacasmayo.sgcp.integrador.operacion.OperacionesFacade;
import pe.com.pacasmayo.sgcp.util.FechaUtil;
import pe.sac.Consumir;

public class OperacionesImpl implements OperacionesFacade {

	@Override
	public Double obtenerVariableCalidadMpSac(Date fechaInicio, Long idProceso,
			Long idProducto, String variableCalDesc) throws IntegradorExcepcion {
		// TODO Auto-generated method stub
		return 0d;
	}

	public static void main(String[] args) {
		OperacionesImpl op = new OperacionesImpl();
		op.obtenerVariableCalidadSacPorPuestoTrabajo(
				FechaUtil.FormaterFecha("01/01/2016"), 142L, 473L, 543L,
				"Humedad");
	}

	@Override
	public double obtenerVariableCalidadSacPorPuestoTrabajo(Date fecha,
			Long codigoProcesoScc, Long codigoProductoScc,
			Long codigoPuestotrabajo, String variableCalDesc)
			throws IntegracionException {
		//Consumir consumir = new Consumir();

		return 0d;/* consumir.ConsumirObtenerVariableCalidadSacPorPuestoTrabajo(
				FechaUtil.convertirDateToString(fecha), codigoProcesoScc,
				codigoProductoScc, codigoPuestotrabajo, variableCalDesc);*/
	}

	@Override
	public List getCantidadEnsayos(String GrupoEnsayo, Integer anio, Short mes,
			Long miarea, Long idProducto, Long idProceso, Long idPuestoTrabajo)
			throws IntegradorExcepcion {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Double obtenerPromedioVarCalidadMes(Date fechaFinal,
			Long codigoProcesoScc, Long codigoProductoScc,
			String variableCalDesc) throws IntegradorExcepcion {
		// TODO Auto-generated method stub
		return 0d;
	}

	@Override
	public List<Object[]> obtenerPromedioVarCalidadRangoFechasReporte(
			Date fechaInicial, Date fechaFinal, Long codigoProcesoScc,
			Long codigoPuestoTrabajoScc, Long codigoProductoScc,
			String variableCalDesc) throws IntegradorExcepcion {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Object[]> obtenerVarCalidadReportePorProductovarcalidad(
			Date fechaInicial, Date fechaFinal, Long codigoProcesoScc,
			Long codigoMatrixScc, String variableCalDesc)
			throws IntegradorExcepcion {
		// TODO Auto-generated method stub
		return null;
	}

}

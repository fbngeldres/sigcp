package pe.com.pacasmayo.sgcp.logica.parteTecnico;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import pe.com.pacasmayo.sgcp.bean.AprobarAjusteProduccionBean;
import pe.com.pacasmayo.sgcp.bean.impl.ResultadoBeanImpl;
import pe.com.pacasmayo.sgcp.excepciones.LogicaException;
import pe.com.pacasmayo.sgcp.presentacion.action.ConstantesMensajeAplicacion;

public class RegistroMovimientoLogic implements ConstantesMensajeAplicacion {

	private Logger logger = Logger.getLogger(RegistroMovimientoLogic.class);

	public ResultadoBeanImpl ingresarMovimientosPorLote(String fechaP_BUDAT,
			List<AprobarAjusteProduccionBean> listAprobarAjusteProduccionBean,
			String fechaBLDATT, Date fecha) throws LogicaException {
		return new ResultadoBeanImpl();
	}

	public Double obtenerFactorHumedadConsumo(Long codigoComponente, Date fecha) {
		Double fHum = 0D;

		return fHum;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pe.com.pacasmayo.sgcp.logica.notificacion.RfcPeMMConMatPrimaLogicFacade
	 * #ingresarDatosPorLote(java.lang.String, java.util.List)
	 */
	public ResultadoBeanImpl ingresarMovimientosCombustibleConsumo(
			String fechaP_BUDAT,
			List<AprobarAjusteProduccionBean> listAprobarAjusteProduccionBean,
			String fechaBLDATT) throws LogicaException {

		return new ResultadoBeanImpl();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pe.com.pacasmayo.sgcp.logica.notificacion.RfcPeMMConMatPrimaLogicFacade
	 * #ingresarDatosPorLote(java.lang.String, java.util.List)
	 */
	public ResultadoBeanImpl ingresarMovimientosCombustibleMermas(
			String fechaP_BUDAT,
			List<AprobarAjusteProduccionBean> listAprobarAjusteProduccionBean,
			String fechaBLDATT) throws LogicaException {
		return new ResultadoBeanImpl();
	}

}

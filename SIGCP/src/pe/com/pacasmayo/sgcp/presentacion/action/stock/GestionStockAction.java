package pe.com.pacasmayo.sgcp.presentacion.action.stock;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import pe.com.pacasmayo.sgcp.presentacion.action.AplicacionAction;
import pe.com.pacasmayo.sgcp.presentacion.action.ConstantesMensajePresentacion;
import pe.com.pacasmayo.sgcp.util.ConstantesLogicaNegocio;

import com.opensymphony.xwork2.Preparable;

public class GestionStockAction extends AplicacionAction implements
		ConstantesLogicaNegocio, Preparable, ConstantesMensajePresentacion {

	private static final long serialVersionUID = 1L;

	private Log logger = LogFactory.getLog(this.getClass());

	public void prepare() throws Exception {
		super.asignaPrivilegios();
	}

	public String doRegistrarCubicacion() {
		return SUCCESS;
	}

}
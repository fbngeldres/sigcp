package pe.com.pacasmayo.sgcp.presentacion.gwt.parteTecnico.cliente;

import pe.com.pacasmayo.sgcp.presentacion.gwt.parteTecnico.componentes.PantallaAjusteProduccion;
import pe.com.pacasmayo.sgcp.presentacion.gwt.util.cliente.ClienteServicioGwt;
import pe.com.pacasmayo.sgcp.presentacion.gwt.util.cliente.VerificarSesion;

import com.google.gwt.core.client.EntryPoint;

public class AjusteProduccion implements EntryPoint, ClienteServicioGwt {

	public void onModuleLoad() {

		PantallaAjusteProduccion pantalla = new PantallaAjusteProduccion();
		pantalla.generarPanelGeneral();

		// Timer para verificar si la sesion finaliza y mostrarle esto al
		// usuario
		VerificarSesion verificarSesion = new VerificarSesion();
		verificarSesion.iniciarSesionTimer();
	}

}
package pe.com.pacasmayo.sgcp.presentacion.gwt.parteTecnico.cliente;

import pe.com.pacasmayo.sgcp.presentacion.gwt.parteTecnico.componentes.PantallaRegistroAjusteProduccionMes;

import com.google.gwt.core.client.EntryPoint;

public class RegistrarAjusteProduccionMes implements EntryPoint {

	public void onModuleLoad() {

		PantallaRegistroAjusteProduccionMes pantalla = new PantallaRegistroAjusteProduccionMes();
		pantalla.generarPanelGeneral();

	}

}

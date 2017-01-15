package pe.com.pacasmayo.sgcp.presentacion.gwt.util.cliente;

import com.google.gwt.core.client.GWT;

public interface ClienteServicioGwt {

	ServicioAsync servicioComunicacion = GWT.create(Servicio.class);
}
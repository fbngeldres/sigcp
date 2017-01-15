package pe.com.pacasmayo.sgcp.presentacion.gwt.notificacion.cliente;

import com.google.gwt.i18n.client.Constants;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: ConstantesGuiGWT.java
 * Modificado: Jan 31, 2011 3:59:55 PM 
 * Autor: daniel.lobo
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

public interface ConstantesModuloNotificaciones extends Constants {

	/**
	 * Constantes usadas en los menu ( comunes )
	 */
	public String menuGrabar();

	/**
	 * Constantes usadas la gui (entrypoint)
	 */

	public String noExistePlantillaEcsParaLaFecha();

	public String falloIntentandoObtenerDatosDelReporte();

	public String errorInesperado();

	public String variableNoSeleccionada();

	public String yaExisteNotificacionParaEsteDia();

	public String errorRegistrandoLaNotificacion();

	public String debeAsignarOrdenesYSilos();

	public String debeRegistrarLasNotificaciones();

	public String registroExitoso();

	public String debeSeleccionarUnTipoDeVariables();

	public String fechaDeRegistro();

	public String confirmacionGrabar();

	public String noSeEncontraronDatosParaElReporte();

	public String deseaRealizarLaCargaManual();

	public String notifRegistradaValoresCargadosConExito();

	public String notifRegistradaAprobadaValoresCargadosConExito();

	public String filtrar();

	public String debeSeleccionarUnValorDeTableroControl();

	public String tituloGridProduccion();

	public String tituloGridProduccionSubtotales();

	public String tituloGridOperacion();

	public String debeSeleccionarPuestoTrabYProceso();

	public String errorCambioProduccionUltimaHora();

	/**
	 * Constantes Dialogo Cargar Ordenes y Silo
	 */
	public String aplicarATodosLosRegistros();

	public String silo();

	public String debeSeleccionarUnaOrdenDeProduccion();

	public String debeSeleccionarUnValorDeSilo();

	public String agregarOrden();

	public String agregarSilo();

	public String aceptar();

	public String ordenProduccionConPlantilla();

	public String noExistenOrdenesLiberadasConProductoConPlantilla();

	public String errorCambioProduccionUltimaHoraOrdenDist();

	public String aplicarDeAquiEnAdelante();

	public String debeSelecionarElTipoDeModificacion();

	public String aplicarSoloARegistroActual();

	public String agregarPlantilla();

	public String plantilla();

	public String debeSeleccionarUnaPlantilla();

	/**
	 * Constantes Dialogo Cambio de Producción
	 */

	public String cpTipoModificacion();

	public String cpCambioProduccion();

	public String cpLavadoSilo();

	public String cpHoraExacta();

	public String cpOrdenProduccionConPlantilla();

	public String cpSilo();

	public String cpDebeSeleccionarUnValorDeMinuto();

	public String cpModificacionExitosa();

	public String cambioProduccionIrreversible();

	public String obsCambioProduccion();

	public String obsLavadoSilo();

	public String debeSeleccionarUnaOrdenDeProduccionOrigen();

	public String debeSeleccionarUnaOrdenDeProduccionDestino();

	public String debeSeleccionarUnValorDeSiloDestino();

	public String debeSeleccionarUnValorDeSiloOrigen();

	public String cpOrdenProduccionLavado();

	public String cpOrdenCambioProduccion();

	public String siloLavado();

	public String debeSeleccionarUnaOrdenDistinta();

	public String errorMinutoCambioProduccion();

	/**
	 * Constantes Grid variables de produccion
	 */
	public String siloGridVp();

	public String ordenDeProduccionGridVp();

	public String observacionGridVp();

	public String noExisteReporteEcsGridVp();

	public String cargandoDatos();

	public String noExistenDatosParaMostrar();

	public String plantillaProducto();

	public String tituloColTotal();

}

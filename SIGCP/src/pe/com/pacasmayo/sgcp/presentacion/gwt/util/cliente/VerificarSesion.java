package pe.com.pacasmayo.sgcp.presentacion.gwt.util.cliente;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.util.BooleanCallback;
import com.smartgwt.client.util.SC;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: VerificarSesion.java
 * Modificado: Jan 6, 2012 9:49:32 AM 
 * Autor: daniel.lobo
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

public class VerificarSesion {

	private Timer sessionTimeoutResponseTimer;

	private ServicioAsync servicioComunicacion = GWT.create(Servicio.class);

	/**
	 * tiempo que se agrega al tiempo obtenido para el fin de sesion se
	 * encuentra en milisegundos
	 */
	private final int TIMEOUT_PAD = 30000;

	/**
	 * Inicia el timer para verificar si el usuario aun se encuentra en la
	 * sesion
	 */
	public void iniciarSesionTimer() {
		servicioComunicacion.getUserSessionTimeout(new AsyncCallback<Integer>() {
			public void onSuccess(Integer timeout) {
				if (timeout == null) {
					mostrarMensajeFinSesion();
					return;
				}
				sessionTimeoutResponseTimer = new Timer() {
					@Override
					public void run() {
						verificar();
					}
				};
				sessionTimeoutResponseTimer.schedule(timeout + TIMEOUT_PAD);
			}

			public void onFailure(Throwable caught) {
				mostrarMensajeFinSesion();
			}
		});
	}

	/**
	 * Verifica si el usuario esta en sesion, de no estarlo le muestra un
	 * mensaje al usuario y lo direcciona al la pagina de login
	 */
	public void verificar() {
		GWT.log("checkUserSessionAlive");
		servicioComunicacion.getUserSessionTimeout(new AsyncCallback<Integer>() {
			public void onSuccess(Integer timeout) {
				if (timeout == null) {
					mostrarMensajeFinSesion();
					return;
				}

				sessionTimeoutResponseTimer.cancel();
				sessionTimeoutResponseTimer.schedule(timeout + TIMEOUT_PAD);
				GWT.log("checkUserSessionAlive timer obtenido para fin sesion: " + timeout);
			}

			public void onFailure(Throwable caught) {
				mostrarMensajeFinSesion();
			}
		});

	}

	/**
	 * Muestra el mesnaje de fin de sesion y redirecciona el usuario al login
	 */
	private void mostrarMensajeFinSesion() {
		GWT.log("La sesion ha finalizado");
		SC.say("La sesion ha finalizado", new BooleanCallback() {
			public void execute(Boolean value) {
				Window.Location.assign(ConstantesGWT.LINK_INICIO);
			}
		});

	}
}

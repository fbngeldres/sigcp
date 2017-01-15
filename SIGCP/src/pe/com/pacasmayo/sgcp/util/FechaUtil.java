package pe.com.pacasmayo.sgcp.util;

/*
 * SGCP (Sistema de Gesti�n y Control de la Producci�n) 
 * Archivo: FechaUtil.java
 * Modificado: Jan 14, 2010 5:25:59 PM 
 * Autor: hector.longarte
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import pe.com.pacasmayo.sgcp.excepciones.LogicaException;

public class FechaUtil {

	public static final int NUMERO_DIAS_SEMANA = 7;
	public static final String PATRON_FECHA_APLICACION = "dd/MM/yyyy";

	public static final String PATRON_FECHA_RFC_DESPACHOS = "yyyyMMdd";

	public static final String PATRON_FECHA_BD = "yyyy-MM-dd";
	public static final String SEPARADOR_CAMPOS_FECHA_BD = "-";
	public static final String SEPARADOR_CAMPOS_FECHA_APP = "/";
	public static final int POSICION_DIA_PATRON_FECHA_APP = 0;
	public static final int POSICION_MES_PATRON_FECHA_APP = 1;
	public static final int POSICION_ANIO_PATRON_FECHA_APP = 2;
	public static final long MILL_SECS_PER_DAY = 24 * 60 * 60 * 1000;

	public static final String[] HORAS_DIA = {

	ConstantesLogicaNegocio.HORA_CERO, ConstantesLogicaNegocio.HORA_UNO, ConstantesLogicaNegocio.HORA_DOS,
			ConstantesLogicaNegocio.HORA_TRES, ConstantesLogicaNegocio.HORA_CUATRO, ConstantesLogicaNegocio.HORA_CINCO,
			ConstantesLogicaNegocio.HORA_SEIS, ConstantesLogicaNegocio.HORA_SIETE, ConstantesLogicaNegocio.HORA_OCHO,
			ConstantesLogicaNegocio.HORA_NUEVE, ConstantesLogicaNegocio.HORA_DIEZ, ConstantesLogicaNegocio.HORA_ONCE,
			ConstantesLogicaNegocio.HORA_DOCE, ConstantesLogicaNegocio.HORA_TRECE, ConstantesLogicaNegocio.HORA_CATORCE,
			ConstantesLogicaNegocio.HORA_QUINCE, ConstantesLogicaNegocio.HORA_DIECISEIS, ConstantesLogicaNegocio.HORA_DIECISIETE,
			ConstantesLogicaNegocio.HORA_DIECIOCHO, ConstantesLogicaNegocio.HORA_DIECINUEVE, ConstantesLogicaNegocio.HORA_VEINTE,
			ConstantesLogicaNegocio.HORA_VEINTIUNO, ConstantesLogicaNegocio.HORA_VEINTIDOS,
			ConstantesLogicaNegocio.HORA_VEINTITRES };

	private static final Log logger = LogFactory.getLog(FechaUtil.class);

	/**
	 * Dias de la semana
	 * 
	 * @author hector.longarte
	 */

	/**
	 * Enum para los dias laborales de la semana. Este enum se hizo con valores
	 * para cada constante porque los acentos no se permiten en nombres de
	 * objetos...
	 */
	public static enum DIAS_SEMANA_ESPANNOL {
		LUNES("Lunes"), MARTES("Martes"), MIERCOLES("Miercoles"), JUEVES("Jueves"), VIERNES("Viernes"), SABADO("Sabado"), DOMINGO(
				"Domingo");

		// Variable de instancia del dia
		private String dia;

		// Metodo para obtener el valor del dia
		public String getDia() {
			return dia;
		}

		// Costructor del enum con el dia
		DIAS_SEMANA_ESPANNOL(String dia) {
			this.dia = dia;
		}
	}

	/**
	 * Enum para los dias laborales de la semana en ingles.
	 */
	public static enum DIAS_SEMANA_INGLES {
		MONDAY("MONDAY"), TUESDAY("Tuesday"), WEDNESDAY("Wednesday"), THURSDAY("Thursday"), FRIDAY("Friday"), SATURDAY("Saturday"), SUNDAY(
				"Sunday");

		// Variable de instancia del dia
		private String dia;

		// Metodo para obtener el valor del dia
		public String getDia() {
			return dia;
		}

		// Costructor del enum con el dia
		DIAS_SEMANA_INGLES(String dia) {
			this.dia = dia;
		}
	}

	/**
	 * Enum para los dias laborales de la semana en ingles en formato de 3
	 * letras.
	 */
	public static enum DIAS_SEMANA_INGLES_CORTOS {
		MONDAY("Mon"), TUESDAY("Tue"), WEDNESDAY("Wed"), THURSDAY("Thu"), FRIDAY("Fri"), SATURDAY("Sat"), SUNDAY("Sun");

		// Variable de instancia del dia
		private String dia;

		// Metodo para obtener el valor del dia
		public String getDia() {
			return dia;
		}

		// Costructor del enum con el dia
		DIAS_SEMANA_INGLES_CORTOS(String dia) {
			this.dia = dia;
		}
	}

	/**
	 * Enum para los meses en espannol
	 * 
	 * @author hector.longarte
	 */
	public static enum MESES_ESPANNOL {
		Enero, Febrero, Marzo, Abril, Mayo, Junio, Julio, Agosto, Septiembre, Octubre, Noviembre, Diciembre
	}

	/**
	 * Enum para los meses en ingles
	 * 
	 * @author hector.longarte
	 */
	public static enum MESES_INGLES {
		January, February, March, April, May, June, July, August, September, October, November, December
	}

	/**
	 * Enum para los meses en ingles en formato de tres letras
	 * 
	 * @author hector.longarte
	 */
	public static enum MESES_INGLES_CORTOS {
		Jan, Feb, Mar, Apr, May, Jun, Jul, Aug, Sep, Oct, Nov, Dec
	}

	/**
	 * Metodo que dada una fecha en string, le resta un dia y devuelve el objeto
	 * Date
	 */
	public static Date restarUnDiaFormatoFechaAplicacion(String dia) {
		Date diaAnterior = null;

		try {
			diaAnterior = convertirAFecha(dia, PATRON_FECHA_APLICACION, null);
			Calendar cal = Calendar.getInstance();
			cal.setTime(diaAnterior);
			cal.add(Calendar.DATE, -1);
			diaAnterior = cal.getTime();

		} catch (LogicaException e) {
			logger.error(e.getMensaje());
		}
		return diaAnterior;
	}

	/**
	 * Convierte un string a un tipo date
	 * 
	 * @param fecha String de fecha a convertir
	 * @param formato formato de fecha que trae el string a convertir
	 * @return Date Object
	 * @throws LogicaException
	 */
	public static Date convertirAFecha(String fechaString, String formato, Locale localidad) throws LogicaException {
		SimpleDateFormat sdf = null;

		Date resultado = Calendar.getInstance().getTime();
		try {

			if (localidad != null)
				sdf = new SimpleDateFormat(formato, localidad);
			else
				sdf = new SimpleDateFormat(formato);

			resultado = sdf.parse(fechaString);

		} catch (ParseException e) {

			String mensajeError = "Ha ocurrido un error durante la conversión a tipo Fecha";
			logger.error(mensajeError);
			throw new LogicaException(mensajeError, e);
		}
		return resultado;
	}

	/**
	 * Metodo para transformar una fecha a un string segun el patr�n ddMMyyyy
	 * 
	 * @param fecha
	 * @return
	 */
	public static String convertirDateToString(Date fecha) {
		return convertirDateToString(fecha, PATRON_FECHA_APLICACION);
	}

	/**
	 * Metodo para transformar una fecha a un string segun un patron específico
	 * 
	 * @param fecha
	 * @param patron
	 * @return
	 */
	public static String convertirDateToString(Date fecha, String patron) {
		DateFormat dateFormat = new SimpleDateFormat(patron);

		return dateFormat.format(fecha);
	}

	public static String obtenerMesFormatoMM(String indiceMes) {
		Integer indice = new Integer(indiceMes);
		indice = indice + 1;
		if (indice < 10) {
			return "0" + indice;
		}
		return "" + indice;
	}

	public static String obtenerDiaFormatoDD(String dia) {
		Integer indice = new Integer(dia);
		if (indice < 10) {
			return "0" + indice;
		}
		return "" + indice;
	}

	/**
	 * Metodo para obetenr un calendar con una obj Date especifico
	 * 
	 * @param date
	 * @return obj de tipo Calendar
	 */
	public static Calendar getCalendarByDate(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal;
	}

	public static Integer getAnnoActual() {

		Calendar c = new GregorianCalendar();
		Integer annio = c.get(Calendar.YEAR);
		return annio;
	}

	public static Integer getMesActual() {
		Calendar c = new GregorianCalendar();
		Integer mes = c.get(Calendar.MONTH) + 1;
		return mes;
	}

	/**
	 * Método para obtener el nombre del mes a partir de un numero entero corto
	 * 
	 * @param mes
	 * @return
	 */
	public static String numeroMesANombreMes(Short mes) {
		String valorMes = "";
		switch (mes) {
		case 1:
			valorMes = "Enero";
			break;
		case 2:
			valorMes = "Febrero";
			break;
		case 3:
			valorMes = "Marzo";
			break;
		case 4:
			valorMes = "Abril";
			break;
		case 5:
			valorMes = "Mayo";
			break;
		case 6:
			valorMes = "Junio";
			break;
		case 7:
			valorMes = "Julio";
			break;
		case 8:
			valorMes = "Agosto";
			break;
		case 9:
			valorMes = "Septiembre";
			break;
		case 10:
			valorMes = "Octubre";
			break;
		case 11:
			valorMes = "Noviembre";
			break;
		case 12:
			valorMes = "Diciembre";
			break;
		}
		return valorMes;
	}

	/**
	 * M�todo para obtener el numero del mes a partir del nombre;
	 * 
	 * @param valorMes
	 * @return
	 */
	public static Short mesToShort(String valorMes) {
		Short mes = null;
		if (valorMes.equals("Enero")) {
			mes = 1;
		}
		if (valorMes.equals("Febrero")) {
			mes = 2;
		}
		if (valorMes.equals("Marzo")) {
			mes = 3;
		}
		if (valorMes.equals("Abril")) {
			mes = 4;
		}
		if (valorMes.equals("Mayo")) {
			mes = 5;
		}
		if (valorMes.equals("Junio")) {
			mes = 6;
		}
		if (valorMes.equals("Julio")) {
			mes = 7;
		}
		if (valorMes.equals("Agosto")) {
			mes = 8;
		}
		if (valorMes.equals("Septiembre")) {
			mes = 9;
		}
		if (valorMes.equals("Octubre")) {
			mes = 10;
		}
		if (valorMes.equals("Noviembre")) {
			mes = 11;
		}
		if (valorMes.equals("Diciembre")) {
			mes = 12;
		}
		return mes;
	}

	/**
	 * Obtiene una fecha adicionando un dia a la fecha pasada como parametro
	 * 
	 * @param fecha
	 */
	public static Date obtenerFechaDiaSiguiente(Date fecha) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(fecha);
		cal.add(Calendar.DATE, 1);

		fecha = cal.getTime();

		return fecha;
	}

	/**
	 * Metodo para obtener una la fecha de dia anterior a la fecha pasada como
	 * parametro
	 * 
	 * @param Date
	 */
	public static Date obtenerFechaDiaAnterior(Date fecha) {
		long tiempoActual = fecha.getTime();
		long unDia = 24 * 60 * 60 * 1000;
		Date fechaAyer = new Date(tiempoActual - unDia);

		return fechaAyer;
	}

	/**
	 * Tranforma la fecha dd/MM/YY HH:MM:ss a solo dd/MM/YY
	 * 
	 * @author Fabian
	 * @param strFecha
	 * @return
	 * @throws ParseException
	 */
	public static Date FormaterFecha(String strFecha) {
		DateFormat format = new SimpleDateFormat(FechaUtil.PATRON_FECHA_APLICACION);
		Date dtfecha = null;
		try {
			dtfecha = format.parse(strFecha);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return dtfecha;
	}

	/**
	 * Metodo que retorna un objeto del ultimo dia del mes
	 * 
	 * @param mesContable numero de mes, de 0 a 11, enero a dic
	 * @param anioContable
	 * @return
	 */
	public static Calendar getUltimoDiaMes(Short mesContable, Integer anioContable) {
		Integer mes = new Integer(mesContable);

		Calendar cal = Calendar.getInstance();
		cal.set(anioContable, mes, 1);

		int maximoDia = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		cal.set(Calendar.DAY_OF_MONTH, maximoDia);
		return cal;
	}
	
	
	
	/**
	 * Metodo que retorna un objeto del ultimo dia del mes
	 * 
	 * @param mesContable numero de mes, de 0 a 11, enero a dic
	 * @param anioContable
	 * @return
	 */
	public static Calendar getPrimerDiaMes(Short mesContable, Integer anioContable) {
		Integer mes = new Integer(mesContable);

		Calendar cal = Calendar.getInstance();
		cal.set(anioContable, mes, 1);

//		int maximoDia = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
//		cal.set(Calendar.DAY_OF_MONTH, maximoDia);
		return cal;
	}

	/**
	 * Compara dos fechas usando solo el dia, mes y anio
	 * 
	 * @param fecha1
	 * @param fecha2
	 * @return boolean true en caso de que las fechas sean iguales, false en
	 *         caso contrario
	 */
	public static boolean comparaFechasSoloDiaMesYAnio(Date fecha1, Date fecha2) {
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(fecha1);
		int dia1 = cal1.get(Calendar.DAY_OF_MONTH);
		int mes1 = cal1.get(Calendar.MONTH);
		int anio1 = cal1.get(Calendar.YEAR);

		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(fecha2);
		int dia2 = cal2.get(Calendar.DAY_OF_MONTH);
		int mes2 = cal2.get(Calendar.MONTH);
		int anio2 = cal2.get(Calendar.YEAR);

		if (dia1 == dia2 && mes1 == mes2 && anio1 == anio2) {
			return true;
		}

		return false;
	}

	public static String obtenerDiaFecha(Date fecha) {
		String fecha2 = "NULL";
		try {
			if (fecha != null) {
				fecha2 = convertirDateToString(fecha, PATRON_FECHA_APLICACION);
				fecha2 = fecha2.split("/")[0];
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return fecha2;
	}

	public static String formatearFechaHHMMSS(Date fecha) {
		if (fecha != null) {
			SimpleDateFormat formatoFechaHora = new SimpleDateFormat("dd/MM/yyyy HH:mm");
			return formatoFechaHora.format(fecha);
		}
		return null;
	}

	public static int diaFecha(Date fecha) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(fecha);
		Integer dia = cal.get(Calendar.DAY_OF_MONTH);
		return dia;
	}

	public static String obtenerFechaActual() {
		Calendar cal = Calendar.getInstance();
		return convertirDateToString(cal.getTime());
	}

	public static int obtenerAnnioFecha(Date fecha) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(fecha);
		Integer anio = cal.get(Calendar.YEAR);
		return anio;
	}

	public static int obtenerMesFecha(Date fecha) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(fecha);
		Integer mes = cal.get(Calendar.MONTH) + 1;
		return mes;
	}

	public static long diffFecha(Date fecha1, Date fecha2) {
		long diff = 0;
		diff = ((fecha1.getTime() - fecha2.getTime()) / MILL_SECS_PER_DAY);
		return diff;
	}

	/**
	 * Metodo para obtener el dia de una fecha proveniente del SAC
	 * 
	 * @param object
	 * @return
	 */
	public static Integer devolverDiaSAC(Object object) {
		if (object != null) {
			String[] fecha = ((String) object).split("-");
			String dia = fecha[2];
			return Integer.parseInt(dia);
		}
		return -1;
	}

	public static Timestamp getCurrentTimestamp() {
		java.util.Calendar cal = java.util.Calendar.getInstance();
		return new java.sql.Timestamp(cal.getTime().getTime());
	}

}

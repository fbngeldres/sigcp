package pe.com.pacasmayo.sgcp.bean.impl;

/*
 * SGCP (Sistema de Gestión y Control de la Producción) 
 * Archivo: HoraBeanImpl.java
 * Modificado: Dic 21, 2009 10:44:26 PM 
 * Autor: andy.nunez
 *
 * Copyright (C) DBAccess, 2010. All rights reserved. 
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */

import pe.com.pacasmayo.sgcp.bean.HoraBean;

public class HoraBeanImpl implements HoraBean {

	private Long codigo;
	private Long codigoTurno;
	private short hora;

	public HoraBeanImpl() {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pe.com.pacasmayo.sgcp.bean.impl.HoraBean#getCodigo()
	 */
	public Long getCodigo() {
		return codigo;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pe.com.pacasmayo.sgcp.bean.impl.HoraBean#setCodigo(int)
	 */
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pe.com.pacasmayo.sgcp.bean.impl.HoraBean#getHora()
	 */
	public short getHora() {
		return hora;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pe.com.pacasmayo.sgcp.bean.impl.HoraBean#setHora(int)
	 */
	public void setHora(short hora) {
		this.hora = hora;
	}

	public Long getCodigoTurno() {
		return codigoTurno;
	}

	public void setCodigoTurno(Long codigoTurno) {
		this.codigoTurno = codigoTurno;
	}
}
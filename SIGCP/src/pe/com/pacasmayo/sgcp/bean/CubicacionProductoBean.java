package pe.com.pacasmayo.sgcp.bean;

import java.util.Date;
import java.util.List;

/*
 * SGCP (Sistema de Gestión y Control de la Producción)
 * Archivo: CubicacionProductoBean.java
 * Modificado: Jun 8, 2010 8:49:00 AM
 * Autor: daniel.lobo
 *
 * Copyright (C) DBAccess, 2010. All rights reserved.
 *
 * Developed by: DBAccess. http://www.dbaccess.com
 */
public interface CubicacionProductoBean extends EntidadBean {

	public abstract Long getPkCodigoCubicacionproducto();

	public abstract void setPkCodigoCubicacionproducto(Long pkCodigoCubicacionproducto);

	public abstract UsuarioBean getUsuarioByFkCodigoUsuarioRegistra();

	public abstract void setUsuarioByFkCodigoUsuarioRegistra(UsuarioBean usuarioByFkCodigoUsuarioRegistra);

	public abstract UsuarioBean getUsuarioByFkCodigoUsuario();

	public abstract void setUsuarioByFkCodigoUsuario(UsuarioBean usuarioByFkCodigoUsuario);

	public abstract EstadoCubicacionBean getEstadocubicacion();

	public abstract void setEstadocubicacion(EstadoCubicacionBean estadocubicacion);

	public abstract LineaNegocioBean getLineanegocio();

	public abstract void setLineanegocio(LineaNegocioBean lineanegocio);

	public abstract UsuarioBean getUsuarioByFkCodigoUsuarioAprueba();

	public abstract void setUsuarioByFkCodigoUsuarioAprueba(UsuarioBean usuarioByFkCodigoUsuarioAprueba);

	public abstract ProduccionBean getProduccion();

	public abstract void setProduccion(ProduccionBean produccion);

	public abstract Date getFechaCubicacionproducto();

	public abstract void setFechaCubicacionproducto(Date fechaCubicacionproducto);

	public abstract Double getStockFisicoCubicacionproducto();

	public abstract void setStockFisicoCubicacionproducto(Double stockFisicoCubicacionproducto);

	public abstract Short getMesCubicacionproducto();

	public abstract void setMesCubicacionproducto(Short mesCubicacionproducto);

	public abstract Integer getAnoCubicacionproducto();

	public abstract void setAnoCubicacionproducto(Integer anoCubicacionproducto);

	public abstract Double getToneladasCubicacionproducto();

	public abstract void setToneladasCubicacionproducto(Double toneladasCubicacionproducto);

	public abstract List<CubicacionBean> getCubicaciones();

	public abstract void setCubicaciones(List<CubicacionBean> cubicaciones);

}
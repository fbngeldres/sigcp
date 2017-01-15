package pe.com.pacasmayo.sgcp.bean;

import java.util.Date;

public interface ObjetoCostosBean {

	public abstract Long getCodigo();

	public abstract void setCodigo(Long cod);

	public abstract String getDescripcion();

	public abstract void setDescripcion(String des);

	public abstract String getAbreviatura();

	public abstract void setAbreviatura(String abr);

	public abstract Long getValor_estadistico();

	public abstract void setValor_estadistico(Long val);

	public abstract Long getEstado();

	public abstract void setEstado(Long est);

	public abstract Long getTipo();

	public abstract void setTipo(Long tipo);

	public abstract Date getFechaIni();

	public abstract void setFechaIni(Date ini);

	public abstract Date getFechaFin();

	public abstract void setFechaFin(Date fin);

	public abstract Long getArea();

	public abstract void setArea(Long area);

	public String getCodigoSap();

	public void setCodigoSap(String codigoSAP);

	public TipoObjetoCostosBean getTipoBean();

	public void setTipoBean(TipoObjetoCostosBean tipoBean);

	public EstadoObjetoCostosBean getEstadoBean();

	public void setEstadoBean(EstadoObjetoCostosBean estadoBean);

	// Agregado por John Vara
	public abstract Boolean getFlagparticionadObjetocosto();

	public abstract void setFlagparticionadObjetocosto(
			Boolean flagparticionadObjetocosto);

	// Fin de John Vara

}
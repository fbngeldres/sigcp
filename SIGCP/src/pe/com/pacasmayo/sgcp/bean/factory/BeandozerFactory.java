package pe.com.pacasmayo.sgcp.bean.factory;

import java.util.List;

public interface BeandozerFactory {

	
	public abstract  Object transformarBean(Object o,Class<?> class1 ); 
	
	public abstract List<?> tranformarLista(List<?> lista, Class<?> claseDestino);
	
}

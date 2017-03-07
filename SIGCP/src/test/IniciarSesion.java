package test;

import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import pe.com.pacasmayo.sgcp.excepciones.AplicacionException;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Usuario;
import pe.com.pacasmayo.sgcp.persistencia.dataObjects.Usuariogrupousuario;
import pe.com.pacasmayo.sgcp.persistencia.querier.UsuarioGrupoUsuarioQuerier;

public class IniciarSesion {

	@Test
	public void test() {
		List<Usuariogrupousuario> lista = null;
		try {
			lista = UsuarioGrupoUsuarioQuerier.getAll();
		} catch (AplicacionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Assert.assertNotNull(lista);
	}

}

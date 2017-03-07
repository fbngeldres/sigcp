package pe.com.pacasmayo.sgcp.presentacion.gwt.testing.servidor;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import pe.com.pacasmayo.sgcp.presentacion.gwt.testing.client.TestingService;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class TestingServiceImpl extends RemoteServiceServlet implements TestingService {
	final String CURRENT = "current_";
	final String EXPECTED = "expected_";
	final String PATH = "src_test/test_expected/";

	/**
	 * 
	 */
	private static final long serialVersionUID = 1410083717960775471L;

	public void grabarArchivo(String json, String nombre) {
		try {
			File current = new File(PATH + CURRENT + nombre);
			FileUtils.writeStringToFile(current, json);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void assertFileEqualsExpected(String json, String nombre) throws Exception {
		try {
			File current = new File(PATH + CURRENT + nombre);
			File expected = new File(PATH + EXPECTED + nombre);
			FileUtils.writeStringToFile(current, json);
			if (!FileUtils.contentEquals(current, expected)) {
				String msg = "El archivo "
						+ nombre
						+ " no tiene el contenido esperado. Recomendación: Ejecutar \"C:\\Program Files (x86)\\WinMerge\\WinMergeU.exe\" \""
						+ current.getAbsolutePath() + "\" \"" + expected.getAbsolutePath() + "\"";
				// El println es para hacer copy y paste
				System.out.println(msg);
				// El exception es para el browser
				throw new Exception(msg);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

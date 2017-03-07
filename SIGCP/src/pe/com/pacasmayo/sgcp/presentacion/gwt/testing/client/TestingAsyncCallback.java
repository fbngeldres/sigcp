package pe.com.pacasmayo.sgcp.presentacion.gwt.testing.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class TestingAsyncCallback<E> implements AsyncCallback<E> {
	private String testName;

	public TestingAsyncCallback(String testName) {
		this.testName = testName;
	}

	public void onFailure(Throwable arg0) {
		GWT.log("****** Error en prueba " + getTestName() + ": " + arg0.getMessage());
	}

	public void onSuccess(E arg0) {
		GWT.log("Exito en prueba " + getTestName());
	}

	public String getTestName() {
		return testName;
	}

}

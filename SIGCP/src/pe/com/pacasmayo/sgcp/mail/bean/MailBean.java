package pe.com.pacasmayo.sgcp.mail.bean;

public class MailBean {

	private String to;
	private String toCC;
	private String toBCC;
	private String from;
	private String subject;
	private String text;

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getToCC() {
		return toCC;
	}

	public void setToCC(String toCC) {
		this.toCC = toCC;
	}

	public String getToBCC() {
		return toBCC;
	}

	public void setToBCC(String toBCC) {
		this.toBCC = toBCC;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

}

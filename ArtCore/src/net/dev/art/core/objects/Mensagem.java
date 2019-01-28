package net.dev.art.core.objects;

public class Mensagem {

	String name;
	String mensagem;

	public Mensagem(String name, String msg) {
		setMensagem(msg);
		setName(name);
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public String getMensagem() {
		return mensagem;
	}

	public String getName() {
		return name;
	}

}

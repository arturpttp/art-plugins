package net.dev.art.essentials.commands;

public class NotEnoughArgumentsException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public NotEnoughArgumentsException() {
		super("");
	}

	public NotEnoughArgumentsException(final String string) {
		super(string);
	}

	public NotEnoughArgumentsException(final Throwable ex) {
		super("", ex);
	}

}

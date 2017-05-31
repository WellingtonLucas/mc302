package base.exception;

public class ValorInvalidoException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8060175796508664992L;
	
	public ValorInvalidoException(int valor) {
		super("O valor "+valor+" eh invalido.");
	}

}

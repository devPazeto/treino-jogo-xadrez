package tabuleiro;
//Exce��o personalizada criada herdando de RuntimeException.
public class TabuleiroException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	public TabuleiroException (String msg) {
		super(msg);
	}
}

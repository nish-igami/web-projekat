package exceptions;

@SuppressWarnings("serial")
public class ElementDontExist extends Exception {
	public ElementDontExist(String message) {
		super(message);
	}
}

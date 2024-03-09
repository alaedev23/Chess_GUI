package joc;

public class InvalidMovementException extends Exception {
	private String codi;
	
	public InvalidMovementException(String pCodi) {
		super();
		this.codi = pCodi;
	}

	public InvalidMovementException(String pCodi, String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		this.codi = pCodi;
	}

	public InvalidMovementException(String pCodi, String message, Throwable cause) {
		super(message, cause);
		this.codi = pCodi;
	}

	public InvalidMovementException(String pCodi, String message) {
		super(message);
		this.codi = pCodi;
	}

	public InvalidMovementException(String pCodi, Throwable cause) {
		super(cause);
		this.codi = pCodi;
	}	
	
	public String getCodi() {
		return this.codi;
	}
}

package action;

public class NotFoundException extends ServiceException {
	public NotFoundException(String message, Exception cause) {
		super(message, cause);
	}
	public NotFoundException(String message) {
		super(message);
	}
}

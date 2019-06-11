package servicios;

@SuppressWarnings("serial")
public class ventaAPersonaDesactivadaException extends Exception{
	public ventaAPersonaDesactivadaException() {}
	public ventaAPersonaDesactivadaException(String errorMessage) {
        super(errorMessage);
    }
}

package guia05.excepciones;

public class AlquilerNoEntregadoException extends Exception{
	public AlquilerNoEntregadoException(String mensaje) {
		super(mensaje);
	}
}

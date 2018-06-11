package modelo;

public class ServicioException extends Exception{

    public ServicioException(String message) {
        super("Añadido: " + message);
    }

    public ServicioException(String message, Throwable cause) {
        super("Añadido: " + message, cause);
    }
    
    
}

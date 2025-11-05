package py.edu.ucom.inventario.core.security;

public class UsuarioContexto {

    private static final ThreadLocal<String> usuarioActual = new ThreadLocal<>();

    public static void setUsuario(String usuario) {
        usuarioActual.set(usuario);
    }

    public static String getUsuario() {
        String user = usuarioActual.get();
        return (user != null) ? user : "Pepe"; // Valor por defecto
    }

    public static void limpiar() {
        usuarioActual.remove();
    }
}

package aplicacion;

import dominio.modelo.Usuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Pruebas de Sesion")
class SesionTest {

    @BeforeEach
    void limpiarSesion() {
        Sesion.cerrarSesion();
    }

    @Test
    @DisplayName("Sesión inicia vacía")
    void sesionIniciaVacia() {
        assertNull(Sesion.getUsuarioActual());
    }

    @Test
    @DisplayName("Se puede establecer un usuario en sesión")
    void establecerUsuarioEnSesion() {
        Usuario u = new Usuario(1, "Carol", "carol@mail.com", "1234", "2024-01-01");

        Sesion.setUsuarioActual(u);

        assertNotNull(Sesion.getUsuarioActual());
        assertEquals("Carol", Sesion.getUsuarioActual().getNombre());
    }

    @Test
    @DisplayName("Cerrar sesión elimina el usuario actual")
    void cerrarSesionEliminaUsuario() {
        Usuario u = new Usuario(1, "Carol", "carol@mail.com", "1234", "2024-01-01");
        Sesion.setUsuarioActual(u);

        Sesion.cerrarSesion();

        assertNull(Sesion.getUsuarioActual());
    }

    @Test
    @DisplayName("Se puede reemplazar el usuario en sesión")
    void reemplazarUsuarioEnSesion() {
        Usuario u1 = new Usuario(1, "Carol", "carol@mail.com", "1234", "2024-01-01");
        Usuario u2 = new Usuario(2, "Ana", "ana@mail.com", "5678", "2024-02-01");

        Sesion.setUsuarioActual(u1);
        Sesion.setUsuarioActual(u2);

        assertEquals("Ana", Sesion.getUsuarioActual().getNombre());
    }
}

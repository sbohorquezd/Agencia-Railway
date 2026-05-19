package aplicacion;

import dominio.modelo.Usuario;
import dominio.puertos.IUsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Pruebas de UsuarioService")
class UsuarioServiceTest {

    @Mock
    private IUsuarioRepository repo;

    private UsuarioService usuarioService;

    @BeforeEach
    void setUp() {
        usuarioService = new UsuarioService(repo);
    }

    @Test
    @DisplayName("Login exitoso con credenciales correctas")
    void loginExitosoConCredencialesCorrectas() {
        Usuario usuarioMock = new Usuario(1, "Carol", "carol@mail.com", "1234", "2024-01-01");
        when(repo.buscarPorCorreo("carol@mail.com")).thenReturn(usuarioMock);

        Usuario resultado = usuarioService.login("carol@mail.com", "1234");

        assertNotNull(resultado);
        assertEquals("Carol", resultado.getNombre());
        assertEquals("carol@mail.com", resultado.getCorreo());
    }

    @Test
    @DisplayName("Login falla con contraseña incorrecta")
    void loginFallaConPasswordIncorrecta() {
        Usuario usuarioMock = new Usuario(1, "Carol", "carol@mail.com", "1234", "2024-01-01");
        when(repo.buscarPorCorreo("carol@mail.com")).thenReturn(usuarioMock);

        Usuario resultado = usuarioService.login("carol@mail.com", "wrongpass");

        assertNull(resultado);
    }

    @Test
    @DisplayName("Login falla si el usuario no existe")
    void loginFallaSiUsuarioNoExiste() {
        when(repo.buscarPorCorreo("noexiste@mail.com")).thenReturn(null);

        Usuario resultado = usuarioService.login("noexiste@mail.com", "1234");

        assertNull(resultado);
    }

    @Test
    @DisplayName("Login falla con correo nulo")
    void loginFallaConCorreoNulo() {
        when(repo.buscarPorCorreo(null)).thenReturn(null);

        Usuario resultado = usuarioService.login(null, "1234");

        assertNull(resultado);
    }

    @Test
    @DisplayName("Se llama al repositorio con el correo correcto")
    void verificaLlamadaAlRepositorio() {
        when(repo.buscarPorCorreo("carol@mail.com")).thenReturn(null);

        usuarioService.login("carol@mail.com", "1234");

        verify(repo, times(1)).buscarPorCorreo("carol@mail.com");
    }
}

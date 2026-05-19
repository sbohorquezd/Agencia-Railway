package aplicacion;

import dominio.modelo.Itinerario;
import dominio.puertos.IItinerarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Pruebas de ItinerarioService")
class ItinerarioServiceTest {

    @Mock
    private IItinerarioRepository repo;

    private ItinerarioService itinerarioService;

    @BeforeEach
    void setUp() {
        itinerarioService = new ItinerarioService(repo);
    }

    private Itinerario crearItinerario() {
        Itinerario it = new Itinerario();
        it.setIdItinerario(1);
        it.setIdUsuario(1);
        it.setAeropuertoSalida("BOG");
        it.setAeropuertoLlegada("MDE");
        it.setFechaInicio("2024-06-01");
        it.setFechaFin("2024-06-01");
        it.setDuracionHoras(1.5);
        it.setEstado("activo");
        return it;
    }

    @Test
    @DisplayName("Guardar itinerario llama al repositorio una vez")
    void guardarItinerarioLlamaRepositorio() {
        Itinerario it = crearItinerario();

        itinerarioService.guardar(it);

        verify(repo, times(1)).guardar(it);
    }

    @Test
    @DisplayName("Eliminar itinerario llama al repositorio con el id correcto")
    void eliminarItinerarioLlamaRepositorioConId() {
        itinerarioService.eliminar(1);

        verify(repo, times(1)).eliminar(1);
    }

    @Test
    @DisplayName("Actualizar itinerario llama al repositorio con el objeto correcto")
    void actualizarItinerarioLlamaRepositorio() {
        Itinerario it = crearItinerario();
        it.setEstado("completado");

        itinerarioService.actualizar(it);

        verify(repo, times(1)).actualizar(it);
    }

    @Test
    @DisplayName("Guardar no lanza excepción con itinerario válido")
    void guardarNoLanzaExcepcion() {
        Itinerario it = crearItinerario();
        doNothing().when(repo).guardar(it);

        org.junit.jupiter.api.Assertions.assertDoesNotThrow(() -> itinerarioService.guardar(it));
    }

    @Test
    @DisplayName("Eliminar no lanza excepción con id válido")
    void eliminarNoLanzaExcepcion() {
        doNothing().when(repo).eliminar(5);

        org.junit.jupiter.api.Assertions.assertDoesNotThrow(() -> itinerarioService.eliminar(5));
    }
}

package aplicacion;

import dominio.modelo.Aeropuerto;
import dominio.modelo.Itinerario;
import dominio.modelo.Usuario;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Pruebas de modelos de dominio")
class ModeloTest {

    @Test
    @DisplayName("Usuario se crea correctamente con constructor completo")
    void usuarioConstructorCompleto() {
        Usuario u = new Usuario(1, "Carol", "carol@mail.com", "hash123", "2024-01-01");

        assertEquals(1, u.getIdUsuario());
        assertEquals("Carol", u.getNombre());
        assertEquals("carol@mail.com", u.getCorreo());
        assertEquals("hash123", u.getPasswordHash());
        assertEquals("2024-01-01", u.getFechaCreacion());
    }

    @Test
    @DisplayName("Usuario se puede modificar con setters")
    void usuarioSetters() {
        Usuario u = new Usuario();
        u.setIdUsuario(2);
        u.setNombre("Ana");
        u.setCorreo("ana@mail.com");

        assertEquals(2, u.getIdUsuario());
        assertEquals("Ana", u.getNombre());
        assertEquals("ana@mail.com", u.getCorreo());
    }

    @Test
    @DisplayName("Itinerario almacena correctamente los aeropuertos")
    void itinerarioAeropuertos() {
        Itinerario it = new Itinerario();
        it.setAeropuertoSalida("BOG");
        it.setAeropuertoLlegada("MDE");

        assertEquals("BOG", it.getAeropuertoSalida());
        assertEquals("MDE", it.getAeropuertoLlegada());
    }

    @Test
    @DisplayName("Itinerario almacena correctamente la duración")
    void itinerarioDuracion() {
        Itinerario it = new Itinerario();
        it.setDuracionHoras(2.5);

        assertEquals(2.5, it.getDuracionHoras(), 0.001);
    }

    @Test
    @DisplayName("Itinerario almacena correctamente el estado")
    void itinerarioEstado() {
        Itinerario it = new Itinerario();
        it.setEstado("activo");

        assertEquals("activo", it.getEstado());
    }

    @Test
    @DisplayName("Aeropuerto almacena correctamente sus coordenadas")
    void aeropuertoCoordenadas() {
        Aeropuerto a = new Aeropuerto();
        a.setNombre("El Dorado");
        a.setCodigoIata("BOG");
        a.setCiudad("Bogotá");
        a.setLatitud(4.7016);
        a.setLongitud(-74.1469);

        assertEquals("BOG", a.getCodigoIata());
        assertEquals(4.7016, a.getLatitud(), 0.0001);
        assertEquals(-74.1469, a.getLongitud(), 0.0001);
    }

    @Test
    @DisplayName("Aeropuerto almacena correctamente el país y departamento")
    void aeropuertoPaisYDepartamento() {
        Aeropuerto a = new Aeropuerto();
        a.setPais("Colombia");
        a.setDepartamento("Cundinamarca");

        assertEquals("Colombia", a.getPais());
        assertEquals("Cundinamarca", a.getDepartamento());
    }
}

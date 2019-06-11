package parcial2;

import entidades.Persona;
import servicios.Vender;

import java.sql.SQLException;
import java.util.List;

import dao.PersonaDAO;
import dao.PersonaDAOImpl;

public class App {
	@SuppressWarnings("unused")
	public static void main(String[] args) throws SQLException {
		PersonaDAO pdao = new PersonaDAOImpl();
		Persona persona = pdao.find(2);
		
		// cambiar false por true para ver la ejecucion de los ejercicios
		
		// ejercicio 1:
		if (false) {
			System.out.println(persona + "\n\n");
		}
		// ejercicio 2:
		if (false) {

			List<Persona> activos = pdao.findAllActivos();
			List<Persona> nombre = pdao.findByNombreOrApellidoLike("Martin");

			System.out.println("Todos los activos: \n");
			for (Persona p : activos) {
				System.out.println(
						p.getId() + " " + p.getNombre() + " " + p.getApellido() + " " + p.getDocumento() + "\n");
			}

			System.out.println("Nombre o Apellido = Martin\n");
			for (Persona p : nombre) {
				System.out.println(
						p.getId() + " " + p.getNombre() + " " + p.getApellido() + " " + p.getDocumento() + "\n");
			}

		}
		// ejercicio 3:
		if(false) {
		// Vender.nuevaventa(persona); // alguien que existe
		// Vender.nuevaventa(new Persona("Nueva2", "Persona2",30782061)); // nueva persona empieza activo
		}

	}
}

package tp7.ejercicio1.main;

import tp7.ejercicio1.modelo.Persona;
import tp7.ejercicio1.modelo.Telefono;
import tp7.ejercicio1.persistencia.PersonaDao;

public class Main {

	public static void main(String[] args) {
		PersonaDao dao = new PersonaDao();
		Persona p = dao.personaPorId(1);
		System.out.println(p.nombre());
		for (Telefono telefono : p.telefonos()) {
			System.out.println(telefono);
		}

	}

}

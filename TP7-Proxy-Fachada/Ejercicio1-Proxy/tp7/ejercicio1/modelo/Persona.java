package tp7.ejercicio1.modelo;

import java.util.Set;

public class Persona {

	private int id;
	private String nombre;
	private Set<Telefono> telefonos; // set es la interfaz

	public Persona(int id, String nombre, Set<Telefono> telefonos) {
		this.id = id;
		this.nombre = nombre;
		this.telefonos = telefonos;
	}

	public Telefono[] telefonos() {
		return telefonos.toArray(new Telefono[telefonos.size()]);
	}

	public String nombre() {
		return nombre;
	}

}

// TP6- hace medidorDecorator que extienda de una interfaz decorador
// y en el constructor de medidor le paso el medidorDecorador, medidor tambien debe implementar la interfaz decorador que cree.
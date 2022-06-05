package tp7.ejercicio2.main;

import java.util.ArrayList;
import java.util.List;

import tp7.ejercicio2.modelo.FachadaJdbc;

public class Main {

	public static void main(String[] args) { // preguntar si esta bien llamar a los metodos en fachada, en la practica
												// dice que fachada debe implementar DBFacade, pero no cumple con el
												// modelo hexagonal

		FachadaJdbc fachada = new FachadaJdbc();

		System.out.println(fachada.queryResultAsAsociation("select p.id, p.nombre " + "from personas p"));

		List<String[]> listaArreglo = new ArrayList<String[]>();

		listaArreglo = fachada.queryResultAsArray("select p.id, p.nombre " + "from personas p");

		for (String[] strings : listaArreglo) {
			for (String string : strings) {
				System.out.println(string);
			}
			;
		}

	}

}

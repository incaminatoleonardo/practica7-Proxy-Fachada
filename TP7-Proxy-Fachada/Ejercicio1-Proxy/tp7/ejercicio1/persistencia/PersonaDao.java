package tp7.ejercicio1.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import tp7.ejercicio1.modelo.Persona;
import tp7.ejercicio1.modelo.ProxyHashSet;
import tp7.ejercicio1.modelo.Telefono;

public class PersonaDao {

	private Connection obtenerConexion() {
		Connection con = null;
		try {

			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/objetos2", "root", "");

		} catch (SQLException e) {
			throw new RuntimeException("No se pudo conectar a la BD", e);

		}
		return con;
	}

	public Persona personaPorId(int id) {

		String sqlPersonas = "select p.nombre " + "from personas p " + "where p.id = ?";
		String sqlCompleto = "select p.nombre,t.numero " + "from personas p, telefonos t "
				+ "where p.id = t.idpersona and p.id = ?";
		try (Connection conn = obtenerConexion(); PreparedStatement statement = conn.prepareStatement(sqlPersonas);) {
			statement.setInt(1, id);
			ResultSet result = statement.executeQuery();
			Set<Telefono> telefonos = new HashSet<Telefono>(); // hashset es mi sujeto real
			String nombrePersona = null;
			while (result.next()) {
				nombrePersona = result.getString(1);
				// telefonos.add(new Telefono(result.getString(2)));
			}
			Set<Telefono> proxyTelefonos = new ProxyHashSet(id);

			return new Persona(id, nombrePersona, proxyTelefonos);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}

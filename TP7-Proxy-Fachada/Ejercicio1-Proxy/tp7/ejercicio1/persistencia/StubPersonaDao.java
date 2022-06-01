package tp7.ejercicio1.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import tp7.ejercicio1.modelo.Persona;

public class StubPersonaDao {

	// private Set<Persona> personas;

	public Persona personaPorId(int id) {
		String sql = "select p.nombre,t.numero " + "from personas p, telefonos t "
				+ "where p.id = t.idpersona and p.id = ?";
		try (Connection conn = obtenerConexion(); PreparedStatement statement = conn.prepareStatement(sql);) {
			statement.setInt(1, id);
			ResultSet result = statement.executeQuery();
			// Set<Telefono> telefonos = new HashSet<Telefono>();
			Set<Persona> personas = new HashSet<Persona>();
			String nombrePersona = null;
			while (result.next()) {
				nombrePersona = result.getString(1);
			}
			return new Persona(id, nombrePersona, null);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	private Connection obtenerConexion() {
		Connection con = null;
		try {

			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/objetos2", "root", "");

		} catch (SQLException e) {
			throw new RuntimeException("No se pudo conectar a la BD", e);

		}
		return con;
	}

}

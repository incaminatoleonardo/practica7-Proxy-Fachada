package tp7.ejercicio2.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import tp7.ejercicio2.modelo.DBFacade;

public class JdbcPersistencia implements DBFacade {

	private Connection con = null;

	@Override
	public void open() {

		try {

			this.con = DriverManager.getConnection("jdbc:mysql://localhost:3306/objetos2", "root", "");

		} catch (SQLException e) {
			throw new RuntimeException("No se pudo conectar a la BD", e);

		}

	}

	@Override
	public List<Map<String, String>> queryResultAsAsociation(String sql) {
		this.open();
		try {
			PreparedStatement statement = (PreparedStatement) this.con
					.prepareStatement("select p.id p.nombre " + "from personas p");
			ResultSet result = statement.executeQuery();

			String nombrePersona = null;

			// HashMap<String, String> mapa = new HashMap<String, String>();
			List<Map<String, String>> listaMapas = new ArrayList<Map<String, String>>();

			// listaMapas.add(mapa);
			while (result.next()) {

				HashMap<String, String> mapa = new HashMap<String, String>();
				mapa.put("id", "" + result.getInt(1));
				mapa.put(nombrePersona, result.getString(2));

				listaMapas.add(mapa);
				// nombrePersona = result.getString(1);

			}
			// hacer el map

			this.close();
			return listaMapas;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	@Override
	public List<String[]> queryResultAsArray(String sql) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void close() {
		try {
			con.close();
		} catch (SQLException e) {
			throw new RuntimeException("No se pudo cerrar la conexion a la BD", e);
		}

	}

}

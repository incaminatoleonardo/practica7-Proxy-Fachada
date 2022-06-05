package tp7.ejercicio2.modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FachadaJdbc implements DBFacade {

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
			PreparedStatement statement = (PreparedStatement) this.con.prepareStatement(sql);
			ResultSet result = statement.executeQuery();

			List<Map<String, String>> listaMapas = new ArrayList<Map<String, String>>();

			while (result.next()) {

				HashMap<String, String> mapa = new HashMap<String, String>();
				mapa.put("id", "" + result.getInt(1)); // pregunar si puedo sacar el nombre del campo desde la consulta
														// sql
				mapa.put("nombrePersona", result.getString(2));

				listaMapas.add(mapa);

			}

			this.close();
			return listaMapas;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	@Override
	public List<String[]> queryResultAsArray(String sql) {

		this.open();
		try {
			PreparedStatement statement = (PreparedStatement) this.con.prepareStatement(sql);
			ResultSet result = statement.executeQuery();

			List<String[]> listaArreglo = new ArrayList<String[]>();

			while (result.next()) {

				String[] arreglo = new String[2];

				arreglo[0] = "id:" + "" + result.getInt(1);

				arreglo[1] = " nombrePersona:" + result.getString(2);

				listaArreglo.add(arreglo);

			}

			this.close();
			return listaArreglo;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

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

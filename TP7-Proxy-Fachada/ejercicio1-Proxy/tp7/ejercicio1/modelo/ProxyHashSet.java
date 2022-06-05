package tp7.ejercicio1.modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class ProxyHashSet implements Set {

	private int identificador;

	public ProxyHashSet(int identificador) {

		this.identificador = identificador;
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

	@Override
	public int size() {

		String sqlCompleto = "select p.nombre,t.numero " + "from personas p, telefonos t "
				+ "where p.id = t.idpersona and p.id = ?";
		try (Connection conn = obtenerConexion(); PreparedStatement statement = conn.prepareStatement(sqlCompleto);) {
			statement.setInt(1, identificador);
			ResultSet result = statement.executeQuery();
			Set<Telefono> telefonos = new HashSet<Telefono>();
			String nombrePersona = null; // esto lo sacaria
			while (result.next()) {
				nombrePersona = result.getString(1); // esto lo sacaria
				telefonos.add(new Telefono(result.getString(2)));
			}
			return telefonos.size();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean contains(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Iterator iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object[] toArray(Object[] a) {
		String sqlCompleto = "select p.nombre,t.numero " + "from personas p, telefonos t "
				+ "where p.id = t.idpersona and p.id = ?";
		try (Connection conn = obtenerConexion(); PreparedStatement statement = conn.prepareStatement(sqlCompleto);) {
			statement.setInt(1, identificador);
			ResultSet result = statement.executeQuery();
			Set<Telefono> telefonos = new HashSet<Telefono>();
			String nombrePersona = null; // esto lo sacaria
			while (result.next()) {
				nombrePersona = result.getString(1); // esto lo sacaria
				telefonos.add(new Telefono(result.getString(2)));
			}
			return telefonos.toArray(new Telefono[telefonos.size()]);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	@Override
	public boolean add(Object e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean remove(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean containsAll(Collection c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addAll(Collection c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean retainAll(Collection c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeAll(Collection c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub

	}

}

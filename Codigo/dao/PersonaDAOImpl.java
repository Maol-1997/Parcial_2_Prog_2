package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entidades.Persona;
import servicios.Conexion;

public class PersonaDAOImpl implements PersonaDAO {

	public void del(Persona persona) {
		String stringSql = "DELETE FROM persona WHERE id = " + String.valueOf(persona.getId());
		try {
			Conexion.instance().execute(stringSql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void save(Persona persona) {
		if (exist(persona))
			update(persona);
		else
			add(persona);
	}

	public boolean exist(Persona persona) {
		String stringSql = "SELECT * FROM persona WHERE id = " + String.valueOf(persona.getId());
		ResultSet res = null;
		try {
			res = Conexion.instance().query(stringSql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			return res.next();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public void update(Persona persona) {
		String stringSql = "UPDATE persona SET nombre = '" + persona.getNombre() + "' , apellido = '"
				+ persona.getApellido() + "' WHERE id = '" + String.valueOf(persona.getId()) + "'";
		try {
			Conexion.instance().execute(stringSql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void add(Persona persona) {
		String stringSql = "INSERT INTO persona (nombre, apellido, documento) VALUES ('" + persona.getNombre() + "' , '"
				+ persona.getApellido() + "', " + persona.getDocumento() + ")";
		try {
			Conexion.instance().execute(stringSql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Persona find(int id) {
		String stringSql = "SELECT * FROM persona WHERE id = " + id;
		ResultSet res = null;
		try {
			res = Conexion.instance().query(stringSql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			if (res.next()) {
				Persona per = new Persona(res.getInt("id"), res.getString("nombre"), res.getString("apellido"),
						res.getLong("documento"), res.getBoolean("activo"));
				return per;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new Persona();
	}

	public List<Persona> findAll() {
		ResultSet res = null;
		try {
			res = Conexion.instance().query("SELECT * from persona");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<Persona> per = new ArrayList<Persona>();
		try {
			while (res.next())
				per.add(new Persona(res.getInt("id"), res.getString("nombre"), res.getString("apellido"),
						res.getLong("documento"), res.getBoolean("activo")));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return per;
	}

	@Override
	public List<Persona> findAllActivos() {
		ResultSet res = null;
		try {
			res = Conexion.instance().query("SELECT * from persona where activo = 1");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<Persona> per = new ArrayList<Persona>();
		try {
			while (res.next())
				per.add(new Persona(res.getInt("id"), res.getString("nombre"), res.getString("apellido"),
						res.getLong("documento"), res.getBoolean("activo")));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return per;
	}

	@Override
	public List<Persona> findByNombreOrApellidoLike(String nombre) {
		ResultSet res = null;
		try {
			res = Conexion.instance().query(
					"SELECT * from persona where (nombre LIKE '" + nombre + "') or (apellido LIKE '" + nombre + "')");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<Persona> per = new ArrayList<Persona>();
		try {
			while (res.next())
				per.add(new Persona(res.getInt("id"), res.getString("nombre"), res.getString("apellido"),
						res.getLong("documento"), res.getBoolean("activo")));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return per;
	}

}

package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entidades.Producto;
import servicios.Conexion;

public class ProductoDAOImpl implements ProductoDAO {
	public void del(Producto producto) {
		String stringSql = "DELETE FROM productos WHERE id = " + String.valueOf(producto.getId());
		try {
			Conexion.instance().execute(stringSql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void save(Producto producto) {
		if (exist(producto))
			update(producto);
		else
			add(producto);
	}

	public boolean exist(Producto producto) {
		String stringSql = "SELECT * FROM productos WHERE id = " + String.valueOf(producto.getId());
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

	public boolean exist(String producto) {
		String stringSql = "SELECT * FROM productos WHERE nombre = '" + producto + "';";
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

	public void update(Producto producto) {
		String stringSql = "UPDATE productos SET nombre = '" + producto.getNombre() + "' WHERE id = '"
				+ String.valueOf(producto.getId()) + "'";
		try {
			Conexion.instance().execute(stringSql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void add(Producto producto) {
		String stringSql = "INSERT INTO productos (nombre,descripcion,precio) VALUES ('" + producto.getNombre() + "', '"
				+ producto.getDescripcion() + "', " + String.valueOf(producto.getPrecio()) + ")";
		try {
			Conexion.instance().execute(stringSql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Producto find(int id) {
		String stringSql = "SELECT * FROM productos WHERE id = " + id;
		ResultSet res = null;
		try {
			res = Conexion.instance().query(stringSql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			if (res.next()) {
				Producto per = new Producto(res.getInt("id"), res.getString("nombre"), res.getString("descripcion"),
						res.getInt("precio"));
				return per;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new Producto();
	}

	public Producto find(String nombre) {
		String stringSql = "SELECT * FROM productos WHERE nombre = '" + nombre + "';";
		ResultSet res = null;
		try {
			res = Conexion.instance().query(stringSql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			if (res.next()) {
				Producto per = new Producto(res.getInt("id"), res.getString("nombre"), res.getString("descripcion"),
						res.getInt("precio"));
				return per;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new Producto();
	}

	public List<Producto> findAll() {
		ResultSet res = null;
		try {
			res = Conexion.instance().query("SELECT * from persona");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<Producto> prod = new ArrayList<Producto>();
		try {
			while (res.next())
				prod.add(new Producto(res.getInt("id"), res.getString("nombre"), res.getString("descripcion"),
						res.getInt("precio")));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return prod;
	}
}

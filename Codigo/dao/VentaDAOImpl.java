package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import entidades.Producto;
import entidades.Venta;
import servicios.Conexion;

public class VentaDAOImpl implements VentaDAO{
	public  void del(Venta venta) {
		String stringSql = "DELETE FROM ventas WHERE id = " + String.valueOf(venta.getId());
		try {
			Conexion.instance().execute(stringSql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public  void save(Venta venta) {
		if (exist(venta))
			update(venta);
		else
			add(venta);
	}

	public  boolean exist(Venta venta) {
		String stringSql = "SELECT * FROM ventas WHERE id = " + String.valueOf(venta.getId());
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

	public  void update(Venta venta) {
		String stringSql = "UPDATE ventas SET valor = '" + String.valueOf(venta.getValor()) + "', descripcion = '"+ venta.getDescripcion() +"' WHERE id = '"
				+ String.valueOf(venta.getId()) + "'";
		try {
			Conexion.instance().execute(stringSql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public  void add(Venta venta) {
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");  
		String stringSql = "INSERT INTO ventas (`idpersona`, `fecha`, `valor`, `descripcion`) VALUES (" + String.valueOf(venta.getIdpersona()) + ", '"+ formatter.format(date) +"', "+ venta.getValor() +", '"+ venta.getDescripcion() +"')";
		try {
			Conexion.instance().execute(stringSql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public  Venta find(int id) {
		String stringSql = "SELECT * FROM ventas WHERE id = " + id;
		ResultSet res = null;
		try {
			res = Conexion.instance().query(stringSql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			if (res.next()) {
				Venta per = new Venta(res.getInt("id"),res.getInt("personaid"),String.valueOf(res.getDate("fecha")),res.getInt("valor"),res.getString("descripcion"));
				return per;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new Venta();
	}

	public  List<Venta> findAll() {
		ResultSet res = null;
		try {
			res = Conexion.instance().query("SELECT * from ventas");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<Venta> prod = new ArrayList<Venta>();
		try {
			while (res.next())
				prod.add(new Venta(res.getInt("id"), res.getInt("idpersona"),String.valueOf(res.getDate("fecha")),res.getInt("valor"),res.getString("descripcion")));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return prod;
	}
	
	public  void relprod(Venta venta, Producto producto) {
		String stringSql = "INSERT INTO relventaproducto (`idventa`, `idproducto`) VALUES ('"+ venta.getId() +"', '"+ producto.getId() +"');";
		try {
			Conexion.instance().execute(stringSql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

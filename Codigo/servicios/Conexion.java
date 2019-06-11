package servicios;

import java.sql.*;

public class Conexion {
	private Connection con;

	private static Conexion pInstance = null;

	public static Conexion instance() {
		if (pInstance == null)
			pInstance = new Conexion();
		return pInstance;
	}

	private Conexion() {
		this.con = null;
	}

	public void Desconexion() throws SQLException {
		this.con.close();
	}

	public void Conectar() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {

			String url = "jdbc:mysql://localhost:3306/prog2dao1?user=root&password=";
			this.con = DriverManager.getConnection(url);
			if (this.con != null) {
				System.out.println("Conectado");
			}
		} catch (SQLException e) {
			System.out.println("No se pudo conectar a la base de datos”);" + " e.printStackTrace();}");
		}
	}

	public Connection getConexion() {
		return this.con;
	}

	public ResultSet query(String stringSql) throws SQLException {
		if (this.con == null)
			this.Conectar();
		return this.con.createStatement().executeQuery(stringSql);
	}

	public void execute(String stringSql) throws SQLException {
		if (this.con == null)
			this.Conectar();
		this.con.createStatement().executeUpdate(stringSql);
	}
}
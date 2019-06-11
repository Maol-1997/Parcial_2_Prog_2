package servicios;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import dao.PersonaDAO;
import dao.PersonaDAOImpl;
import dao.ProductoDAO;
import dao.ProductoDAOImpl;
import dao.VentaDAO;
import dao.VentaDAOImpl;
import entidades.Persona;
import entidades.Producto;
import entidades.Venta;

public class Vender {
	public static void nuevaventa(Persona persona) throws SQLException {
		try {
			PersonaDAO pdao = new PersonaDAOImpl();
			if (!pdao.exist(persona)) {
				pdao.save(persona);
				ResultSet res = Conexion.instance().query("SELECT MAX(id) FROM persona");
				res.next();
				persona.setId(res.getInt("MAX(id)"));

			}
			persona.checkPersona();
			Date date = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			System.out.println("Ingrese descripcion de venta: ");
			Scanner scanner = new Scanner(System.in);
			String strdescri = scanner.nextLine();
			Venta venta = new Venta(persona.getId(), formatter.format(date), 0, strdescri);
			int costoventa = 0;
			int spregunta = 0;
			ProductoDAO prod = new ProductoDAOImpl();
			do {
				System.out.println("Ingrese un producto: ");
				String inputString = scanner.nextLine();
				if (!prod.exist(inputString)) {
					System.out.println("Ingrese una descripcion: ");
					// Scanner scanner2 = new Scanner(System.in);
					String descripcion = scanner.nextLine();
					System.out.println("Ingrese un precio: ");
					// Scanner scanner3 = new Scanner(System.in);
					String precio = scanner.nextLine();
					// scanner2.close();
					// scanner3.close();
					prod.add(new Producto(inputString, descripcion, Integer.parseInt(precio)));
					System.out.println("producto añadido");
				}
				venta.getProductos().add(prod.find(inputString));
				costoventa += prod.find(inputString).getPrecio();
				// System.out.println("Otro?");
				spregunta++;
			} while (spregunta < 3);
			scanner.close();
			venta.setValor(costoventa);
			persona.getVentas().add(venta);
			VentaDAO vent = new VentaDAOImpl();
			vent.add(venta);
			ResultSet res = Conexion.instance().query("SELECT MAX(id) FROM ventas");
			res.next();
			venta.setId(res.getInt("MAX(id)"));
			int rango = venta.getProductos().size();
			for (int i = 0; i < rango; i++) {
				// venta.getProductos().get(i);
				System.out.println(venta.getId() + " " + venta.getProductos().get(i).getId());
				vent.relprod(venta, venta.getProductos().get(i));
			}

		} catch (ventaAPersonaDesactivadaException ex) {
			System.out.println("Persona no activa " + ex);
		}
	}
}
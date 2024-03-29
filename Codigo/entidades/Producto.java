package entidades;

public class Producto {
	private int id;
	private String nombre;
	private String descripcion;
	private int precio;

	public Producto() {
	}

	public Producto(int id, String nombre, String descripcion, int precio) {
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.precio = precio;
	}

	public Producto(String nombre, String descripcion, int precio) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.id = -1;
		this.precio = precio;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getPrecio() {
		return precio;
	}

	public void setPrecio(int precio) {
		this.precio = precio;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}

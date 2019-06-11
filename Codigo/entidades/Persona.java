package entidades;

import java.util.ArrayList;
import java.text.NumberFormat;
import java.util.Locale;

import servicios.ventaAPersonaDesactivadaException;

import java.util.List;

public class Persona {
	private int id;
	private String nombre;
	private String apellido;
	private long documento;
	private boolean activo;
	private List<Venta> ventas;

	public long getDocumento() {
		return documento;
	}

	public String mostrarDocumento() {
		NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.US);
		String numberAsString = numberFormat.format(this.documento);
		return numberAsString;
	}

	public void setDocumento(long documento) {
		this.documento = documento;
	}
	
	public void checkPersona() throws ventaAPersonaDesactivadaException {
		if (!this.isActivo())
			throw new ventaAPersonaDesactivadaException();
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public Persona(String nombre, String apellido, long documento) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.documento = documento;
		this.activo = true;
		this.id = -1;
		this.ventas = new ArrayList<Venta>();
	}

	public Persona(int id, String nombre, String apellido, long documento, boolean activo) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.id = id;
		this.documento = documento;
		this.activo = activo;
		this.ventas = new ArrayList<Venta>();
	}

	public List<Venta> getVentas() {
		return ventas;
	}

	public void setVentas(List<Venta> ventas) {
		this.ventas = ventas;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Persona() {

	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	@Override
	public String toString() {
		NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.US);
		String numberAsString = numberFormat.format(this.documento);
		return "ID: " + id + "\nNombre: " + nombre + "\nApellido: " + apellido + "\nDocumento: " + numberAsString
				+ "\nActivo: " + activo;
	}
}

package dao;

import java.util.List;

import entidades.Persona;

public interface PersonaDAO {
	public void del(Persona persona);

	public void save(Persona persona);

	public boolean exist(Persona persona);

	public void update(Persona persona);

	public void add(Persona persona);

	public Persona find(int id);

	public List<Persona> findAll();
	
	public List<Persona> findAllActivos();
	
	public List<Persona> findByNombreOrApellidoLike(String nombre);
}

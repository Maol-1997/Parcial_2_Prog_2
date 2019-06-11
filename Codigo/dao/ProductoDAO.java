package dao;

import java.util.List;

import entidades.Producto;

public interface ProductoDAO {
	public void del(Producto producto);

	public void save(Producto producto);

	public boolean exist(Producto producto);

	public boolean exist(String producto);

	public void update(Producto producto);

	public void add(Producto producto);

	public Producto find(int id);

	public Producto find(String nombre);

	public List<Producto> findAll();
}

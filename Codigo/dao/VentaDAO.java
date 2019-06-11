package dao;

import java.util.List;

import entidades.Producto;
import entidades.Venta;

public interface VentaDAO {
	public void del(Venta venta);

	public void save(Venta venta);

	public boolean exist(Venta venta);

	public void update(Venta venta);

	public void add(Venta venta);

	public Venta find(int id);

	public List<Venta> findAll();

	public void relprod(Venta venta, Producto producto);
}

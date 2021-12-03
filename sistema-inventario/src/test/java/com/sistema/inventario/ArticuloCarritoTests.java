package com.sistema.inventario;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import com.sistema.inventario.carrito.compras.ArticuloCarrito;
import com.sistema.inventario.carrito.compras.ArticuloCarritoRepository;
import com.sistema.inventario.producto.Producto;
import com.sistema.inventario.usuario.Usuario;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class ArticuloCarritoTests {

	@Autowired
	private ArticuloCarritoRepository repositorio;
	
	@Autowired
    private TestEntityManager entityManager;
	
	@Test
	public void testAñadirArticulo() {
		Producto producto = entityManager.find(Producto.class, 3);
		Usuario usuario = entityManager.find(Usuario.class, 1);
		
		ArticuloCarrito articulo = new ArticuloCarrito(7,producto,usuario);
		repositorio.save(articulo);
	}
	
	@Test
	public void testAñadirMultiplesArticulos() {
		Usuario usuario = new Usuario(1);
		Producto producto1 = new Producto(2);
		Producto producto2 = new Producto(3);
		
		ArticuloCarrito articulo1 = new ArticuloCarrito(3,producto1,usuario);
		ArticuloCarrito articulo2 = new ArticuloCarrito(5,producto2,usuario);
		
		repositorio.saveAll(List.of(articulo1,articulo2));
	}
	
	@Test
	public void testListarArticulos() {
		List<ArticuloCarrito> articulos = repositorio.findAll();
		articulos.forEach(System.out::println);
	}
	
	@Test
	public void testActualizarArticulo() {
		ArticuloCarrito articulo = repositorio.findById(1).get();
		articulo.setCantidad(11);
		articulo.setProducto(new Producto(3));
	}
	
	@Test
	public void testEliminarArticulo() {
		repositorio.deleteById(1);
	}
}

package com.sistema.inventario.carrito.compras;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.sistema.inventario.producto.Producto;
import com.sistema.inventario.usuario.Usuario;

@Entity
@Table(name = "articulo_carrito")
public class ArticuloCarrito {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private int cantidad;

	@ManyToOne
	@JoinColumn(name = "producto_id")
	private Producto producto;

	@ManyToOne
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public ArticuloCarrito(Integer id, int cantidad, Producto producto, Usuario usuario) {
		super();
		this.id = id;
		this.cantidad = cantidad;
		this.producto = producto;
		this.usuario = usuario;
	}

	public ArticuloCarrito() {
		super();
	}
	
	public ArticuloCarrito(int cantidad, Producto producto, Usuario usuario) {
		super();
		this.cantidad = cantidad;
		this.producto = producto;
		this.usuario = usuario;
	}

	@Override
	public String toString() {
		return "ArticuloCarrito [id=" + id + ", cantidad=" + cantidad + ", producto=" + producto + ", usuario="
				+ usuario + "]";
	}

	
}

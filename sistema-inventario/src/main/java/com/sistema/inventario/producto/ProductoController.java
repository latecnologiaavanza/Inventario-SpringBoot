package com.sistema.inventario.producto;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.sistema.inventario.categoria.Categoria;
import com.sistema.inventario.categoria.CategoriaRepository;

@Controller
public class ProductoController {

	@Autowired
	private ProductoRepository productoRepository;
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@GetMapping("productos/nuevo")
	public String mostrarFormularioDeNuevoProducto(Model modelo) {
		List<Categoria> listaCategorias = categoriaRepository.findAll();
		
		modelo.addAttribute("producto", new Producto());
		modelo.addAttribute("listaCategorias", listaCategorias);
		
		return "producto_formulario";
	}
	
	
	@PostMapping("/productos/guardar")
	public String guardarProducto(Producto producto,HttpServletRequest request) {
		String[] detallesIDs = request.getParameterValues("detallesID");
		String[] detallesNombres = request.getParameterValues("detallesNombre");
		String[] detallesValores = request.getParameterValues("detallesValor");
		
		for(int i = 0;i < detallesNombres.length; i++) {
			if(detallesIDs != null && detallesIDs.length > 0) {
				producto.setDetalle(Integer.valueOf(detallesIDs[i]),detallesNombres[i],detallesValores[i]);
			}else {
				producto.añadirDetalles(detallesNombres[i],detallesValores[i]);
			}
		}
		
		productoRepository.save(producto);
		return "redirect:/";
	}
	
	@GetMapping("/productos")
	public String listarProductos(Model modelo) {
		List<Producto> listaProductos = productoRepository.findAll();
		modelo.addAttribute("listaProductos", listaProductos);
		
		return "productos";
	}
	
	@GetMapping("/productos/editar/{id}")
	public String mostrarFormularioDeModificarProducto(@PathVariable("id") Integer  id,Model modelo) {
		Producto producto = productoRepository.findById(id).get();
		modelo.addAttribute("producto", producto);
		
        List<Categoria> listaCategorias = categoriaRepository.findAll();
		modelo.addAttribute("listaCategorias", listaCategorias);
		
		return "producto_formulario";
	}
	
	@GetMapping("/productos/eliminar/{id}")
	public String eliminarProducto(@PathVariable("id") Integer id,Model modelo) {
		productoRepository.deleteById(id);
		return "redirect:/productos";
	}
 }











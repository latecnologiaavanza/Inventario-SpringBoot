package com.sistema.inventario.marca;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.sistema.inventario.categoria.Categoria;
import com.sistema.inventario.categoria.CategoriaRepository;

@Controller
public class MarcaController {

	@Autowired
	private MarcaRepository marcaRepository;
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@GetMapping("/marcas/nueva")
	public String mostrarFormularioDeCrearNuevaMarca(Model modelo) {
		List<Categoria> listaCategorias = categoriaRepository.findAll();
		
		modelo.addAttribute("listaCategorias", listaCategorias);
		modelo.addAttribute("marca", new Marca());
		return "marca_formulario";
	}
	
	@PostMapping("/marcas/guardar")
	public String guardarMarca(Marca marca) {
		marcaRepository.save(marca);
		return "redirect:/";
	}
	
	@GetMapping("/marcas")
	public String listarMarcas(Model modelo) {
		List<Marca> listaMarcas = marcaRepository.findAll();
		modelo.addAttribute("listaMarcas", listaMarcas);
		
		return "marcas";
	}

	@GetMapping("/marcas/editar/{id}")
	public String mostrarFormularioDeModificarMarca(@PathVariable("id") Integer id,Model modelo) {
		List<Categoria> listaCategorias = categoriaRepository.findAll();
		Marca marca = marcaRepository.findById(id).get();
		
		modelo.addAttribute("listaCategorias", listaCategorias);
		modelo.addAttribute("marca",marca);
		return "marca_formulario";
	}
}

package com.sistema.inventario.usuario;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UsuarioController {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private RolRepository rolRepository;
	
	@GetMapping("/usuarios")
	public String listarUsuarios(Model modelo) {
		List<Usuario> listaUsuarios = usuarioRepository.findAll();
		modelo.addAttribute("listaUsuarios", listaUsuarios);
		
		return "usuarios";
	}
	
	@GetMapping("/usuarios/nuevo")
	public String mostrarFormularioDeRegistroDeUsuario(Model modelo) {
		List<Rol> listaRoles = rolRepository.findAll();
		
		modelo.addAttribute("listaRoles", listaRoles);
		modelo.addAttribute("usuario",new Usuario());
				
		return "usuario_formulario";
	}

	@PostMapping("/usuarios/guardar")
	public String guardarUsuario(Usuario usuario) {
		usuarioRepository.save(usuario);
		return "redirect:/usuarios";
	}
	
	@GetMapping("/usuarios/editar/{id}")
	public String mostrarFormularioDeEditarUsuario(@PathVariable("id") Integer  id,Model modelo) {
		Usuario usuario = usuarioRepository.findById(id).get();
		modelo.addAttribute("usuario", usuario);
		
        List<Rol> listaRoles = rolRepository.findAll();
		modelo.addAttribute("listaRoles", listaRoles);
		
		return "usuario_formulario";
	}
	
	@GetMapping("/usuarios/eliminar/{id}")
	public String eliminarProducto(@PathVariable("id") Integer id,Model modelo) {
		usuarioRepository.deleteById(id);
		return "redirect:/usuarios";
	}
}

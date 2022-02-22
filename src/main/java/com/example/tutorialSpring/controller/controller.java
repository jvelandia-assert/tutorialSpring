package com.example.tutorialSpring.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.tutorialSpring.entity.Trabajador;
import com.example.tutorialSpring.repository.TrabajadorRepo;



@RestController
public class controller {

	@Autowired
	private TrabajadorRepo trabajadorRepo;
	
	@GetMapping("/trabajadores")
	public Iterable<Trabajador> getTrabajadores() {
		return trabajadorRepo.findAll();
	}
	
	@GetMapping("/trabajador/{id}")
	public Optional<Trabajador> getTrabajador(@PathVariable long id) {
		return trabajadorRepo.findById(id);
	}
	
		
	@PostMapping("/agregarTrabajador")
	public @ResponseBody String setTrabajador(@RequestBody Trabajador newTrabajador) {
		trabajadorRepo.save(newTrabajador);
		return "Trabajador agregado";
	}
	
	@PutMapping("/editarTrabajador/{id}")
	public String editTrabajador(@PathVariable long id, @RequestBody Trabajador trabajadorEditado) {
		Trabajador trabajadorEncontrado = trabajadorRepo.findById(id).get();

		trabajadorEncontrado.setId(trabajadorEditado.getId());
		trabajadorEncontrado.setNombre(trabajadorEditado.getNombre());
		trabajadorEncontrado.setApellido(trabajadorEditado.getApellido());
		trabajadorEncontrado.setTelefono(trabajadorEditado.getTelefono());
		trabajadorEncontrado.setEmail(trabajadorEditado.getEmail());
		
		trabajadorRepo.save(trabajadorEncontrado);

		return "Trabajor editado";
	}
	
	
	@DeleteMapping("/borrarTrabajador/{id}")
	public @ResponseBody String deleteTrabajador(@PathVariable long id) {
		trabajadorRepo.deleteById(id);
		return "Trabajador eliminado";
	}
	
}

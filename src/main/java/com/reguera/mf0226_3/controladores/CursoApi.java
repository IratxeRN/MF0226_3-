package com.reguera.mf0226_3.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.reguera.mf0226_3.entidades.Curso;
import com.reguera.mf0226_3.repositorio.CursoDaoJdbc;

@RestController
//@RequestMapping("/api/cursos/*")
public class CursoApi {
	@Autowired
	private CursoDaoJdbc dao;

	@RequestMapping("/api/cursos")
	@GetMapping
	public Iterable<Curso> get() {
		return dao.verTodos();
	}

	@RequestMapping("/api/cursos/{id}")
	@GetMapping("{id}")
	public ResponseEntity<Curso> getPorIdConResenias(@PathVariable int id) {

		Curso curso = dao.buscarPorIdConResenias(id);

		if (curso == null) {
			return new ResponseEntity<Curso>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Curso>(curso, HttpStatus.OK);
	}

}

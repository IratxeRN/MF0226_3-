package com.reguera.mf0226_3.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.reguera.mf0226_3.entidades.Resenia;
import com.reguera.mf0226_3.repositorio.ReseniaDao;

@RestController
@RequestMapping("/api/resenias/*")
public class ReseniaApi {

	@Autowired
	private ReseniaDao dao;

	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public Resenia post(@RequestBody Resenia resenia) {
		return dao.insertar(resenia);
	}

	@PutMapping("{id}")
	public ResponseEntity<Resenia> put(@PathVariable int id, @RequestBody Resenia resenia) {

		if (id != resenia.getCodigo()) {
			return new ResponseEntity<Resenia>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Resenia>(dao.modificar(resenia), HttpStatus.OK);
	}

	@DeleteMapping("{id}")
	public ResponseEntity<Resenia> delete(@PathVariable int id) {
		try {
			dao.borrar(id);
			return new ResponseEntity<Resenia>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<Resenia>(HttpStatus.NOT_FOUND);
		}
	}

}

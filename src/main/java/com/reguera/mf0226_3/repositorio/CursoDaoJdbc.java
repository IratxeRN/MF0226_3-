package com.reguera.mf0226_3.repositorio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.reguera.mf0226_3.entidades.Curso;
import com.reguera.mf0226_3.entidades.Resenia;

@Repository
public class CursoDaoJdbc implements Dao<Curso> {

	@Autowired
	private JdbcTemplate jdbc;

	@Override
	public Iterable<Curso> verTodos() {
		return jdbc.query(
				"SELECT c.codigo, c.nombre, c.identificador, c.nHoras, CONCAT(p.nombre,' ',p.apellidos) \r\n"
						+ "FROM curso AS c \r\n" + "JOIN profesor AS p ON p.codigo = c.profesor_codigo\r\n",
				(rs, rowNum) -> new Curso(rs.getInt("c.codigo"), rs.getString("c.nombre"),
						rs.getString("c.identificador"), rs.getInt("c.nHoras"),
						rs.getString("CONCAT(p.nombre,' ',p.apellidos)")));
	}

	@Override
	public Curso buscarPorId(int id) {

		return jdbc.queryForObject(
				"SELECT c.codigo, c.nombre, c.identificador, c.nHoras, CONCAT(p.nombre,' ',p.apellidos) \r\n"
						+ "FROM curso AS c \r\n" + "JOIN profesor AS p ON p.codigo = c.profesor_codigo\r\n"
						+ "WHERE c.codigo = ?;",
				(rs, rowNum) -> new Curso(rs.getInt("c.codigo"), rs.getString("c.nombre"),
						rs.getString("c.identificador"), rs.getInt("c.nHoras"),
						rs.getString("CONCAT(p.nombre,' ',p.apellidos)")),
				id);
	}

	public Curso buscarPorIdConResenias(int id) {
		Curso curso = buscarPorId(id);
		List<Resenia> resenias = jdbc.query(
				"SELECT codigo, resenia, fecha, alumno_codigo, curso_codigo \r\n"
						+ "FROM resenias WHERE curso_codigo=?",
				(rs, rowNum) -> new Resenia(rs.getInt("codigo"), rs.getString("resenia"), rs.getDate("fecha"),
						rs.getInt("alumno_codigo"), rs.getInt("curso_codigo")),
				id);

		curso.getResenias().addAll(resenias);

		return curso;
	}

}

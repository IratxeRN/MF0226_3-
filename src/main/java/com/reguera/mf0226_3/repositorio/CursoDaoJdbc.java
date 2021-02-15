package com.reguera.mf0226_3.repositorio;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.reguera.mf0226_3.entidades.Curso;
import com.reguera.mf0226_3.entidades.Profesor;
import com.reguera.mf0226_3.entidades.Resenia;

@Repository
public class CursoDaoJdbc implements Dao<Curso> {

	@Autowired
	private JdbcTemplate jdbc;

	@Override
	public Iterable<Curso> verTodos() {
		/*
		 * ****** LISTAR CURSOS CON PROFESOR ****
		 * 
		 * return jdbc.query(
		 * "SELECT c.codigo, c.nombre, c.identificador, c.nHoras, p.codigo, p.nombre, p.apellidos \r\n"
		 * + "FROM curso AS c JOIN profesor AS p ON p.codigo = c.profesor_codigo\r\n",
		 * (rs, rowNum) -> new Curso(rs.getInt("c.codigo"), rs.getString("c.nombre"),
		 * rs.getString("c.identificador"), rs.getInt("c.nHoras"), new
		 * Profesor(rs.getInt("p.codigo"), rs.getString("p.nombre"),
		 * rs.getString("p.apellidos"))));
		 */

		// ***** LISTAR SOLO LOS CURSOS, SIN DETALLE *****
		return jdbc.query("SELECT codigo, nombre, identificador, nHoras FROM curso", new RowMapper<Curso>() {

			@Override
			public Curso mapRow(ResultSet rs, int i) throws SQLException {
				Curso c = new Curso();
				c.setCodigo(rs.getInt("codigo"));
				c.setNombre(rs.getString("nombre"));
				c.setIdentificador(rs.getString("identificador"));
				c.setNHoras(rs.getInt("nHoras"));

				return c;
			};
		});
	}

	// ********* BUSCAR POR ID UN CURSO Y DEVUELVE LA INFORMACION CON DETALLE DE
	// PROFESOR ****
	@Override
	public Curso buscarPorId(int id) {

		return jdbc
				.queryForObject(
						"SELECT c.codigo, c.nombre, c.identificador, c.nHoras, p.codigo, p.nombre, p.apellidos \r\n"
								+ "FROM curso AS c JOIN profesor AS p ON p.codigo = c.profesor_codigo\r\n"
								+ "WHERE c.codigo = ?;",
						(rs, rowNum) -> new Curso(rs.getInt("c.codigo"), rs.getString("c.nombre"),
								rs.getString("c.identificador"), rs.getInt("c.nHoras"), new Profesor(
										rs.getInt("p.codigo"), rs.getString("p.nombre"), rs.getString("p.apellidos"))),
						id);
	}

	public Curso buscarPorIdConResenias(int id) {

		Curso curso = buscarPorId(id);

		List<Resenia> resenias = jdbc.query(
				"SELECT r.codigo, r.resenia, r.fecha, r.alumno_codigo, r.curso_codigo FROM resenias r \r\n"
						+ "LEFT JOIN curso c ON r.curso_codigo = c.codigo\r\n" + "WHERE r.curso_codigo=?",
				(rs, rowNum) -> new Resenia(rs.getInt("r.codigo"), rs.getString("r.resenia"), rs.getDate("r.fecha"),
						rs.getInt("r.alumno_codigo"), rs.getInt("r.curso_codigo")),
				id);

		curso.getResenias().addAll(resenias);

		return curso;
	}

}

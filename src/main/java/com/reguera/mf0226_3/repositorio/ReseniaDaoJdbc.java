package com.reguera.mf0226_3.repositorio;

import java.sql.PreparedStatement;
import java.sql.Statement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.reguera.mf0226_3.entidades.Resenia;

@Repository
public class ReseniaDaoJdbc implements ReseniaDao {

	@Autowired
	private JdbcTemplate jdbc;

	@Override
	public Iterable<Resenia> verTodos() {
		return jdbc.query("SELECT * FROM resenias", new BeanPropertyRowMapper<Resenia>(Resenia.class));
	}

	@Override
	public Resenia buscarPorId(int id) {
		return jdbc.queryForObject("SELECT * FROM resenias WHERE codigo = ?",
				new BeanPropertyRowMapper<Resenia>(Resenia.class), id);
	}

	@Override
	public Resenia insertar(Resenia r) {

		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbc.update(connection -> {
			PreparedStatement ps = connection.prepareStatement(
					"INSERT INTO resenias (resenia, fecha, alumno_codigo, curso_codigo) VALUES (?,?,?,?)",
					Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, r.getResenia());
			ps.setDate(2, r.getFecha());
			ps.setInt(3, r.getCurso_codigo());
			ps.setInt(4, r.getCurso_codigo());
			return ps;
		}, keyHolder);

		r.setCodigo(keyHolder.getKey().intValue());

		return r;
	}

	/*
	 * @Override public Resenia insertar(Resenia r) {
	 * 
	 * jdbc.
	 * update("INSERT INTO resenias (resenia, fecha, alumno_codigo, curso_codigo) VALUES (?,?,?,?)"
	 * , r.getResenia(), r.getFecha(), r.getAlumno_codigo(), r.getCurso_codigo());
	 * return r; }
	 */

	@Override
	public Resenia modificar(Resenia r) {
		jdbc.update("UPDATE resenias SET resenia = ?, fecha = ?, alumno_codigo = ?, curso_codigo = ? WHERE codigo = ?",
				new Object[] { r.getResenia(), r.getFecha(), r.getAlumno_codigo(), r.getCurso_codigo(),
						r.getCodigo() });
		return r;
	}

	@Override
	public void borrar(int id) {
		jdbc.update("DELETE FROM resenias WHERE codigo = ?", id);

	}

}

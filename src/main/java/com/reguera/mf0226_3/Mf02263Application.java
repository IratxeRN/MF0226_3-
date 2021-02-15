package com.reguera.mf0226_3;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.reguera.mf0226_3.entidades.Curso;
import com.reguera.mf0226_3.entidades.Resenia;
import com.reguera.mf0226_3.repositorio.CursoDaoJdbc;
import com.reguera.mf0226_3.repositorio.ReseniaDaoJdbc;

@SpringBootApplication

public class Mf02263Application implements CommandLineRunner {
	@Autowired
	private CursoDaoJdbc cdao;

	@Autowired
	private ReseniaDaoJdbc rdao;

	public static void main(String[] args) {
		SpringApplication.run(Mf02263Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		for (Curso curso : cdao.verTodos()) {
			System.out.println(curso);
		}

		System.out.println("********************************************************");

		System.out.println(cdao.buscarPorId(3));

		System.out.println("********************************************************");

		for (Resenia resenia : rdao.verTodos()) {
			System.out.println(resenia);
		}
		System.out.println("********************************************************");

		Resenia r = new Resenia();
		r.setResenia("Txatxi piruli, me ha molado cantidad");
		r.setFecha(new Date(2021 - 01 - 28));
		r.setAlumno_codigo(7);
		r.setCurso_codigo(7);

		// rdao.insertar(r);

		Curso curso = cdao.buscarPorIdConResenias(7);

		System.out.println(curso);

		for (Resenia resenia : curso.getResenias()) {
			System.out.println(resenia);
		}

	}
}

package com.reguera.mf0226_3.entidades;

import java.util.HashSet;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@AllArgsConstructor
//@NoArgsConstructor
public class Curso {

	private int codigo;
	private String nombre, identificador;
	private int nHoras;
	private Profesor profesor;

	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	private final Set<Resenia> resenias = new HashSet<>();

	public Curso() {

		this.profesor = new Profesor();
	}

}

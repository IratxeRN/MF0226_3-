package com.reguera.mf0226_3.entidades;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/*
@Entity
@Table(name = "curso")*/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Curso {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int codigo;
	private String nombre, identificador;
	private int nHoras;
	private String profesor;

	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	// @OneToMany
	private final Set<Resenia> resenias = new HashSet<>();

}

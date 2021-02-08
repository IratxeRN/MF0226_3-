package com.reguera.mf0226_3.entidades;

import java.sql.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
@Entity
@Table(name = "resenias")*/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Resenia {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int codigo;
	private String resenia;
	private Date fecha;
	private int alumno_codigo;
	private int curso_codigo;

	// @ManyToOne // (fetch = FetchType.EAGER)

}

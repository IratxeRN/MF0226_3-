package com.reguera.mf0226_3.entidades;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Resenia {

	private int codigo;
	private String resenia;
	private Date fecha;
	private int alumno_codigo;
	private int curso_codigo;

}

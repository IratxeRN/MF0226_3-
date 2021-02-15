package com.reguera.mf0226_3.entidades;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Profesor {

	private int codigo;
	private String nombre;
	private String apellidos;

}

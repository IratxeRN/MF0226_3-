package com.reguera.mf0226_3.repositorio;

import com.reguera.mf0226_3.entidades.Resenia;

public interface ReseniaDao extends Dao<Resenia> {

	Resenia insertar(Resenia r);

	Resenia modificar(Resenia r);

	void borrar(int id);

}

package com.reguera.mf0226_3.repositorio;

public interface Dao<T> {

	Iterable<T> verTodos();

	T buscarPorId(int id);

}

package com.mycompany.main.Dao;

import java.util.List;

public interface Repositorio<T> {

    void crear(T entidad);

    T buscarPorId(int id);

    List<T> buscarTodos();

    void actualizar(T entidad);

    void eliminar(int id);

}

package co.com.castor.evaluacion.service;

import java.util.List;

public interface ICrud<T, ID> {

    T save(T t);
    T update(ID id, T t);
    List<T> findAll();
    T findById(ID id);
    void delete(ID id);
}

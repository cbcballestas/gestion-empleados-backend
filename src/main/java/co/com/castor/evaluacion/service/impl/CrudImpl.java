package co.com.castor.evaluacion.service.impl;

import co.com.castor.evaluacion.exception.ModelNotFoundException;
import co.com.castor.evaluacion.repository.IGenericRepo;
import co.com.castor.evaluacion.service.ICrud;

import java.util.List;

public abstract class CrudImpl< T, ID> implements ICrud<T,ID> {

    static final String ID_NOT_FOUND = "ID NOT FOUND: ";

    protected abstract IGenericRepo<T,ID> getRepo();

    @Override
    public T save(T t) {
        return getRepo().save(t);
    }

    @Override
    public T update(ID id, T t) {
        getRepo().findById(id).orElseThrow(() -> new ModelNotFoundException(ID_NOT_FOUND + id));
        return getRepo().save(t);
    }

    @Override
    public List<T> findAll() {
        return getRepo().findAll();
    }

    @Override
    public T findById(ID id) {
        return getRepo().findById(id).orElseThrow(() -> new ModelNotFoundException(ID_NOT_FOUND + id));
    }

    @Override
    public void delete(ID id) {
        getRepo().findById(id).orElseThrow(() -> new ModelNotFoundException(ID_NOT_FOUND + id));
        getRepo().deleteById(id);
    }
}

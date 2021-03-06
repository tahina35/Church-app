package com.churchofphilippi.webserver.service;

import java.util.List;

public interface BaseService<T> {

    public List<T> getAll();
    public T save(T entity);
    public void delete(T entity);
}

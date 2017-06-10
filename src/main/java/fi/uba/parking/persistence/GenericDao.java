/*
* GenericDao.java
* 
* Created on 3/2/2015
* 
*/ 
package fi.uba.parking.persistence;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * @author msossich
 *
 */
public interface GenericDao<T, PK extends Serializable> {
    
    T getById(PK id);

    List<T> getAll();

    void update(T persistentObject);
    
    void saveOrUpdate(T persistentObject);

    void delete(T persistentObject);
    
    PK save(T persistentObject);
    
    void merge(T persistentObject);

}
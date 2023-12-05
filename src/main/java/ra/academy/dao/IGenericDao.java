package ra.academy.dao;

import java.util.List;

public interface IGenericDao<T,E> {
    List<T> findAll();
    T findById(E id);
    int save(T t);
    int deleteById(E id);

}

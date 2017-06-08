package ca.umanitoba.cs.comp3350.saveonflight.persistence;

/**
 * DataAccessStub.java
 * <p>
 * Database Access Stub
 *
 * @author Long Yu
 * @author Zhengyu Gu
 * @author Kenny Zhang
 * @author Andy Lun
 */

public interface DataAccessStub<E> {
    void initialize();
    boolean update(E e);
    boolean add(E e);
    boolean remove(E e);
}

package net.coderodde.util;

/**
 * This interface provides an action of creating (an empty) data structure with
 * the same implementation.
 * 
 * @author Rodion "rodde" Efremov
 * @version 1.6 (Jan 15, 2016)
 * @param <T> the type of object being spawned.
 */
public interface Spawnable<T> {
    
    T spawn();
}

package org.cognoscenti.reportdispatcher.dao;

import java.util.List;

/**
 * Data access object interface
 * <br />
 * <br />
 * The main interface for managing entities that are persisted in
 * the data store.
 * 
 * @author Geng
 * @version 1.0
 */
public interface DataAccessObject {
	/**
	 * Persists entity
	 * 
	 * @param o The entity to be added
	 * @return The managed entity
	 */
	Object add(Object o);
	
	/**
	 * Updates entity
	 * 
	 * @param o The entity to be updated
	 * @return The managed entity
	 */
	Object update(Object o);
	
	/**
	 * Removes entity
	 * 
	 * @param o The entity to be removed
	 */
	void remove(Object o);
	
	/**
	 * Finds an entity using its id
	 * 
	 * @param id The id of the entity
	 * @param clazz The class of the entity
	 * @return The entity object
	 */
	<T> T get(Object id, Class<T> clazz);
	
	/**
	 * Lists all entities with the given class
	 * 
	 * @param clazz The class of the entity
	 * @return The list of entities with the given class
	 */
	<T> List<T> list(Class<T> clazz);
}
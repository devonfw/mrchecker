package com.capgemini.mrchecker.database.core.base;

import java.io.Serializable;
import java.util.List;

/**
 * Interface which defines main procedures on database.
 *
 * @param <T>
 *            entity based on database table
 * @param <K>
 *            type of id variable in entity
 *
 * @author PWOJTKOW
 */
public interface IDao<T, K extends Serializable> {

	/**
	 * Insert new entity into table. If entity is a preexisting managed entity,
	 * it is ignored by the persist operation.
	 *
	 * @param entity
	 *            - entity to add
	 * @return - entity object added into table
	 */
	T save(T entity);

	/**
	 * Get entity from database as a proxy object. After this method we can make
	 * operations on specific database object. Better to use when want to make
	 * CRUD operations on entity.
	 *
	 * @param id
	 *            - entity id to get from database
	 * @return proxy object from database
	 */
	T getOne(K id);

	/**
	 * Find entity from database. It's similar with "SELECT" statement from SQL.
	 * After usage this method new entity object is created, based on data from
	 * this specific row in database.
	 *
	 * @param id
	 *            - entity id to find from database
	 * @return entity with data from
	 */
	T findOne(K id);

	/**
	 * @return all entities from database.
	 */
	List<T> findAll();

	/**
	 * Update entity into database. If X-entity is a new entity instance, a new
	 * managed entity instance X'-entity is created and the state of X is copied
	 * into the new managed entity instance X'.
	 *
	 * @param entity
	 *            - entity to update in database.
	 * @return
	 */
	T update(T entity);

	/**
	 * Delete entity from database find by entity object.
	 *
	 * @param entity
	 *            - entity to delete from database.
	 */
	void delete(T entity);

	/**
	 * Delete entity from database find by id.
	 *
	 * @param id
	 *            - specific id number for entity to be deleted.
	 */
	void delete(K id);

	/**
	 * Delete all entity from database. Similar to use "TRUNCATE" statement in
	 * SQL.
	 */
	void deleteAll();

	/**
	 * @return number of records from table.
	 */
	long count();

	/**
	 * Check is any record in table with specific id.
	 *
	 * @param id
	 *            - specific record id in table.
	 * @return - true if record exists, or false id do not.
	 */
	boolean exists(K id);
}

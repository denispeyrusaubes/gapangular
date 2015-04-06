package com.it46.service;

import java.util.List;

import javax.annotation.Nullable;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.response.CollectionResponse;
import com.google.appengine.api.datastore.Cursor;
import com.google.appengine.datanucleus.query.JPACursorHelper;
import com.it46.entity.Actor;

@Api(name = "actorendpoint", namespace = @ApiNamespace(ownerDomain = "dp.com", ownerName = "dp.com", packagePath = "cinema.entity"))
public class ActorEndpoint {

	/**
	 * This method lists all the entities inserted in datastore.
	 * It uses HTTP GET method and paging support.
	 *
	 * @return A CollectionResponse class containing the list of all entities
	 * persisted and a cursor to the next page.
	 */
	@SuppressWarnings({ "unchecked", "unused" })
	@ApiMethod(name = "listActor")
	public CollectionResponse<Actor> listActor(
			@Nullable @Named("cursor") String cursorString,
			@Nullable @Named("limit") Integer limit) {

		EntityManager mgr = null;
		Cursor cursor = null;
		List<Actor> execute = null;

		try {
			mgr = getEntityManager();
			Query query = mgr.createQuery("select from Actor as Actor");
			if (cursorString != null && cursorString != "") {
				cursor = Cursor.fromWebSafeString(cursorString);
				query.setHint(JPACursorHelper.CURSOR_HINT, cursor);
			}

			if (limit != null) {
				query.setFirstResult(0);
				query.setMaxResults(limit);
			}

			execute = (List<Actor>) query.getResultList();
			cursor = JPACursorHelper.getCursor(execute);
			if (cursor != null)
				cursorString = cursor.toWebSafeString();

			// Tight loop for fetching all entities from datastore and accomodate
			// for lazy fetch.
			for (Actor obj : execute)
				;
		} finally {
			mgr.close();
		}

		return CollectionResponse.<Actor> builder().setItems(execute)
				.setNextPageToken(cursorString).build();
	}

	/**
	 * This method gets the entity having primary key id. It uses HTTP GET method.
	 *
	 * @param id the primary key of the java bean.
	 * @return The entity with primary key id.
	 */
	@ApiMethod(name = "getActor")
	public Actor getActor(@Named("id") Long id) {
		EntityManager mgr = getEntityManager();
		Actor actor = null;
		try {
			actor = mgr.find(Actor.class, id);
		} finally {
			mgr.close();
		}
		return actor;
	}

	/**
	 * This inserts a new entity into App Engine datastore. If the entity already
	 * exists in the datastore, an exception is thrown.
	 * It uses HTTP POST method.
	 *
	 * @param actor the entity to be inserted.
	 * @return The inserted entity.
	 */
	@ApiMethod(name = "insertActor")
	public Actor insertActor(Actor actor) {
		EntityManager mgr = getEntityManager();
		try {
			mgr.persist(actor);
		} finally {
			mgr.close();
		}
		return actor;
	}

	/**
	 * This method is used for updating an existing entity. If the entity does not
	 * exist in the datastore, an exception is thrown.
	 * It uses HTTP PUT method.
	 *
	 * @param actor the entity to be updated.
	 * @return The updated entity.
	 */
	@ApiMethod(name = "updateActor")
	public Actor updateActor(Actor actor) {
		EntityManager mgr = getEntityManager();
		try {
			if (!containsActor(actor)) {
				throw new EntityNotFoundException("Object does not exist");
			}
			mgr.persist(actor);
		} finally {
			mgr.close();
		}
		return actor;
	}

	/**
	 * This method removes the entity with primary key id.
	 * It uses HTTP DELETE method.
	 *
	 * @param id the primary key of the entity to be deleted.
	 */
	@ApiMethod(name = "removeActor")
	public void removeActor(@Named("id") Long id) {
		EntityManager mgr = getEntityManager();
		try {
			Actor actor = mgr.find(Actor.class, id);
			mgr.remove(actor);
		} finally {
			mgr.close();
		}
	}

	private boolean containsActor(Actor actor) {
		EntityManager mgr = getEntityManager();
		boolean contains = true;
		try {
			Actor item = mgr.find(Actor.class, actor.getId());
			if (item == null) {
				contains = false;
			}
		} finally {
			mgr.close();
		}
		return contains;
	}

	private static EntityManager getEntityManager() {
		return EMF.get().createEntityManager();
	}

}

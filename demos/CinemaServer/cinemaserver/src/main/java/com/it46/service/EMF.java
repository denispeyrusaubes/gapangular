package com.it46.service;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public final class EMF {
	private static final EntityManagerFactory emfInstance = Persistence
			.createEntityManagerFactory("cinemaserverpu");

	private EMF() {
	}

	public static EntityManagerFactory get() {
		return emfInstance;
	}
}
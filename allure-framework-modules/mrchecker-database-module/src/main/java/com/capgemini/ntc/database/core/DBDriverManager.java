package com.capgemini.ntc.database.core;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DBDriverManager {

	private static EntityManagerFactory emf;

	public static EntityManager createEntityManager(String dbPrefix) {
		return getEntityManagerFactory(dbPrefix).createEntityManager();
	}

	private static EntityManagerFactory getEntityManagerFactory(String dbPrefix) {
		return emf != null ? emf : Persistence.createEntityManagerFactory(dbPrefix);
	}

}

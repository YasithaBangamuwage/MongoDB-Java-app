package com.yas.mongodbjavaapp.dao;

import java.net.UnknownHostException;

import com.mongodb.DB;
import com.mongodb.MongoClient;

/**
 * @author YAS
 * @version 1.0
 * @category Used to connect with the mongoDB database.
 */
public class MongoDBManager {

	/**
	 * DB static instance.
	 */
	private static DB mongoMgr;

	/**
	 * Singleton method to get BD instance.
	 * 
	 * @return DB instance
	 */
	public static DB getInstance() {
		if (mongoMgr == null) {
			MongoClient mongo;
			try {
				// connect to mongodb server
				mongo = new MongoClient("localhost", 27017);
				// connect with the database
				mongoMgr = mongo.getDB("usermanager");
				System.out
						.println("Connect to database(usermanager) successfully");
			} catch (UnknownHostException e) {
				System.err.println(e.getClass().getName() + ": "
						+ e.getMessage());
			}
		}
		return mongoMgr;
	}
}

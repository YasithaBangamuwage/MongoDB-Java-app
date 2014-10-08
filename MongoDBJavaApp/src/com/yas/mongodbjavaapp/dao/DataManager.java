/**
 * 
 */
package com.yas.mongodbjavaapp.dao;

import java.util.ArrayList;

import com.mongodb.BasicDBObject;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.WriteResult;
import com.yas.mongodbjavaapp.domain.User;

/**
 * @author YAS
 * @version 1.2
 * @category Manage all data manipulation tasks.
 */
public class DataManager {

	/**
	 * default constructor.
	 */
	public DataManager() {
	}

	/**
	 * Add new user into database.
	 * 
	 * @param pUser
	 *            new user object.
	 */
	public final void addUser(final User pUser) {

		DBObject doc = createUserDBObject(pUser);
		DB userDB = MongoDBManager.getInstance();
		DBCollection dbCol = userDB.getCollection("user");
		@SuppressWarnings("unused")
		WriteResult result = dbCol.insert(doc);
		System.out.println("Added user : " + pUser.getFirstName());
	}

	/**
	 * Get all users as array list of objects.
	 * 
	 * @return all users
	 */
	public final ArrayList<User> getAllUsers() {

		ArrayList<User> userList = new ArrayList<User>();
		DBCollection userCollection = MongoDBManager.getInstance()
				.getCollection("user");
		DBCursor cursor = userCollection.find();

		for (DBObject dbObject : cursor) {
			User usr = new User();
			usr.setId((int) dbObject.get("_id"));
			usr.setFirstName((String) dbObject.get("firstName"));
			usr.setLastName((String) dbObject.get("lastName"));
			usr.setEmail((String) dbObject.get("email"));

			userList.add(usr);
		}
		return userList;
	}

	/**
	 * Update given user object.
	 * 
	 * @param usr
	 *            updated user object
	 */
	public final void updateUser(User usr) {

		BasicDBObject searchQuery = new BasicDBObject();
		searchQuery.put("_id", usr.getId());
		DBCollection userCollection = MongoDBManager.getInstance()
				.getCollection("user");
		userCollection.update(searchQuery, createUserDBObject(usr));

	}

	/**
	 * delete user from the database.
	 * 
	 * @param userId
	 *            id of the user to delete.
	 */
	public final void deleteUser(int userId) {
		BasicDBObject query = new BasicDBObject();
		query.append("_id", userId);
		DBCollection userCollection = MongoDBManager.getInstance()
				.getCollection("user");
		userCollection.findAndRemove(query);
		System.out.println("Deleted user id : " + userId);
	}

	/**
	 * Get user search by first name.
	 * 
	 * @param fName
	 *            first name of the user
	 * @return User
	 */
	public final User getUserByFName(String fName) {
		BasicDBObject searchQuery = new BasicDBObject();
		searchQuery.put("firstName", fName);
		DBCollection userCollection = MongoDBManager.getInstance()
				.getCollection("user");

		DBObject dbObject = userCollection.findOne(searchQuery);

		User usr = new User();
		usr.setId((int) dbObject.get("_id"));
		usr.setFirstName((String) dbObject.get("firstName"));
		usr.setLastName((String) dbObject.get("lastName"));
		usr.setEmail((String) dbObject.get("email"));

		System.out.println("Get user : " + fName);
		return usr;

	}

	/**
	 * create user DBObject.
	 * 
	 * @param pUser
	 *            data to make DBObject
	 * @return DBObject of the user
	 */
	private final DBObject createUserDBObject(final User pUser) {

		BasicDBObjectBuilder docBuilder = new BasicDBObjectBuilder();
		BasicDBObjectBuilder.start();
		docBuilder.append("_id", pUser.getId());
		docBuilder.append("firstName", pUser.getFirstName());
		docBuilder.append("lastName", pUser.getLastName());
		docBuilder.append("email", pUser.getEmail());
		return docBuilder.get();
	}
}

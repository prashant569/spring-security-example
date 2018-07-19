package com.springsecurity.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.springsecurity.model.UserProfile;

@Repository
public class UserRepositoryImpl implements UserRepository {

	@Autowired
	MongoTemplate mongoTemplate;
	public static final String COLLECTION_NAME = "UserProfile";
	
	//@Override
	public UserProfile findByUsername(String username) {
		Query query = new Query();
		query.addCriteria(Criteria.where("username").is(username));

		UserProfile userProfile = null;

		try {
			userProfile =  mongoTemplate.findOne(query,UserProfile.class,COLLECTION_NAME);
		}
		catch(Exception ex) {
			System.out.println("Exception : " + ex);
		}
		finally {
			return userProfile;
		}
	}

	@Override
	public void add(UserProfile user) {
		// TODO Auto-generated method stub
		
		if(!mongoTemplate.collectionExists(UserProfile.class)){
			mongoTemplate.createCollection(UserProfile.class);
		}
		
		mongoTemplate.insert(user,COLLECTION_NAME);
	}
	
}

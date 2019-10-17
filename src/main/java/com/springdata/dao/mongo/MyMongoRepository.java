package com.springdata.dao.mongo;

import javax.transaction.Transactional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.springdata.models.Users;
import com.springdata.models.UsersCategory;

@Transactional
public interface MyMongoRepository extends MongoRepository<UsersCategory, String>{

}

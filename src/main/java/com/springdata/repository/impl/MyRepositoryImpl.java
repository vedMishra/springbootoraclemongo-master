package com.springdata.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springdata.dao.mongo.MyMongoRepository;
import com.springdata.dao.oracle.OracleRepository;
import com.springdata.oracle.configuration.OracleConfiguration;

@Service
public class MyRepositoryImpl {

    @Autowired
    private MyMongoRepository myMongoRepository;

    @Autowired
    private OracleRepository mySQLRepository;
    
    @Autowired
    private OracleConfiguration mysqlConfig;
    
    /*@Autowired
     BatchConfiguration batchConfig;*/

    /*@PostConstruct
    public void extractUsers(){
        
    	mySQLRepository.findAll().forEach((user) -> System.out.println("User name from mysql is : "+user.getName()));
    	myMongoRepository.findAll().forEach((user) -> System.out.println("user name from mongo is : "+user.getName()));
    	//mysqlConfig.myMySQLDataSource();
    	//batchConfig.databaseXmlItemReader(mysqlConfig.myMySQLDataSource());
    }*/
    
   

}

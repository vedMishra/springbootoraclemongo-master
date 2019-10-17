package com.springdata.oracle.mongo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.data.MongoItemWriter;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.database.Order;
import org.springframework.batch.item.database.support.SqlPagingQueryProviderFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.springdata.models.UsersCategory;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {
	@Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;
    
    @Autowired
    EntityManagerFactory emf;
    
    @Autowired
    DataSource dataSource;
    
    @Autowired
    private MongoTemplate mongoTemplate;
    
    private static final String FIELDS = "c.CATEGORYID,c.NAME,c.CATEGORY,u.AGE,c.IMAGEBLOB";
    private static final String FROMCLOUSE = "from CATEGORY c , USERS u ";
 
    @Bean
    public Job readFrmOracle() {
      return jobBuilderFactory.get("readFrmOracle").incrementer(new RunIdIncrementer()).start(step1())
          .build();
    }
    //org.springframework.jdbc.datasource.DriverManagerDataSource
    @Bean
    public Step step1() {
      try {
		return stepBuilderFactory.get("step1").<UsersCategory, UsersCategory>chunk(10).reader(productItemReader())
				 .processor(processor())
		      .writer(writer()).build();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return null;
    }
    
  
    @Bean
    public ItemReader<UsersCategory> productItemReader() throws Exception {
    try
	{
    	
		JdbcPagingItemReader<UsersCategory> reader = new JdbcPagingItemReader<UsersCategory>();
		final SqlPagingQueryProviderFactoryBean sqlPagingQueryProviderFactoryBean = new SqlPagingQueryProviderFactoryBean();
		sqlPagingQueryProviderFactoryBean.setDataSource(dataSource);
		System.out.println("11111111111");
		/*sqlPagingQueryProviderFactoryBean.setSelectClause("C.CATEGORYID as CATEGORYID ,C.NAME as NAME,C.CATEGORY as CATEGORY,U.AGE as AGE");
		sqlPagingQueryProviderFactoryBean.setFromClause("from CATEGORY C , USERS U ");
		sqlPagingQueryProviderFactoryBean.setWhereClause("where C.NAME=U.NAME");
		sqlPagingQueryProviderFactoryBean.setSortKey("u.AGE");*/
		String fromClause = "";
		String whereClause ="";
		fromClause = "%PREFIX%CATEGORY c, %PREFIX%USERS u" + (fromClause == null ? "" : ", " + fromClause);
		
		sqlPagingQueryProviderFactoryBean.setSelectClause(FIELDS);
		sqlPagingQueryProviderFactoryBean.setFromClause(FROMCLOUSE);
		
		
		whereClause = "c.NAME=u.NAME" ;
		sqlPagingQueryProviderFactoryBean.setWhereClause(whereClause);
		Map<String, Order> sortKeys = new HashMap<String, Order>();
		sortKeys.put("AGE", Order.DESCENDING);
		sqlPagingQueryProviderFactoryBean.setSortKeys(sortKeys);
		
		reader.setQueryProvider(sqlPagingQueryProviderFactoryBean.getObject());
		reader.setDataSource(dataSource);
		reader.setPageSize(10);
		reader.setRowMapper(new BeanPropertyRowMapper<>(UsersCategory.class));
		reader.afterPropertiesSet();
		reader.setSaveState(true);
		//logger.info("Reading users anonymized in chunks of {}", USERS_CHUNK_SIZE);
		//ObjectMapper mapper = new ObjectMapper();
		//String jsonString = mapper.writeValueAsString(sqlPagingQueryProviderFactoryBean);
		//System.out.println("jsonString----"+jsonString);
		return reader;
	}
	catch (Exception e)
	{
		e.printStackTrace();
	}
	return null;
    }
   
    @Bean
    public UsersItemProcessor processor() {
    	return new UsersItemProcessor();
    	//ObjectMapper mapper = new ObjectMapper();
		/*try {
			String jsonString = mapper.writeValueAsString(Users.class);
			System.out.println(jsonString);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
 
    }
  
    @Bean
    public MongoItemWriter<UsersCategory> writer() {
    	Date date = new Date();  
    	long timeMilli = date.getTime();
    	long nanoSec1 = System.nanoTime();
    	 System.out.println("Time in nanoseconds using Date class before: " + nanoSec1);; 
    	//SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
        
        
      MongoItemWriter<UsersCategory> writer = new MongoItemWriter<UsersCategory>();
      
     
      writer.setTemplate(mongoTemplate);
      writer.setCollection("usersCategory");
      
      
      //ObjectMapper mapper = new ObjectMapper();
		/*try {
			String jsonString = mapper.writeValueAsString(Users.class);
			System.out.println(jsonString);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
      long nanoSec2 = System.nanoTime();
      System.out.println("Time in nanoseconds using Date class after: " + nanoSec2);;
    
      long diffInNanoSec = nanoSec2-nanoSec1;
      //long durationInMs = diffInNanoSec%1000000;
      System.out.println("time taken to write in mongo using mongoItemWriter(in nanosec) is: "+ diffInNanoSec);
      return writer;
    }
 

}

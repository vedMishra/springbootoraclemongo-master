package com.springdata.dao.oracle;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springdata.models.Users;

@Transactional
public interface OracleRepository extends JpaRepository<Users, String>{

}


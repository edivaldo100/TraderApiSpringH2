package com.edivaldo.trade.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.edivaldo.trade.model.User;
@Repository
public interface UserRepository extends CrudRepository<User, Long>{
//public interface UserRepository extends JpaRepository<User, Long> {
}
package com.eksad.latihanrest.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eksad.latihanrest.model.users;

public interface UsersRepo extends JpaRepository<users, Integer> {

	users findByUsername(String username);

}

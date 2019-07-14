package com.eksad.latihanrest.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eksad.latihanrest.dao.UsersRepo;
import com.eksad.latihanrest.model.users;

@RestController
@RequestMapping("admin")
public class MainController{

	@Autowired
	UsersRepo usersRepo;
	
	@RequestMapping("")
	public HashMap<String, Object> admin(){
		HashMap<String, Object> map= new HashMap<String, Object>();
		map.put("message","masuk sebagai admin");
		return map;
	}
	@RequestMapping("user")
	public HashMap<String, Object> user(){
		HashMap<String, Object> map= new HashMap<String, Object>();
		map.put("message","masuk sebagai user");
		return map;
	}
	
	@RequestMapping("save")
	public users save (@RequestBody users user){
		user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
		return usersRepo.save(user);
	}
	@RequestMapping("getAll")
	public List<users> getAll(){
		return usersRepo.findAll();
	}		
	@RequestMapping("delete/{id}")
	public String delete(@PathVariable int id){
	usersRepo.deleteById(id);
	return "berhasil dihapus";
	}


}

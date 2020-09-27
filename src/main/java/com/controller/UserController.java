package com.controller;

import java.util.ArrayList;

import javax.xml.ws.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bean.ResponseBean;
import com.bean.UserBean;
import com.dao.UserDao;

@RestController
public class UserController {
	@Autowired
	UserDao userDao;

	@PostMapping("/signup")
	public ResponseBean<UserBean> signup(UserBean userBean) {
		System.out.println("Signup via post Called ");
		System.out.println(userBean.getEmail());
		userDao.insertUser(userBean);
		/*
		 * return new ResponseEntity<UserBean>(userBean,null,HttpStatus.CREATED);
		 */
		ResponseBean<UserBean> responseBean = new ResponseBean<>();
		responseBean.setData(userBean);
		responseBean.setMsg("Signup Done");
		responseBean.setStatus(201);

		return responseBean;
	}

	@GetMapping("/users")
	public ResponseBean<ArrayList<UserBean>> Getusers() {

		ResponseBean<ArrayList<UserBean>> responseBean = new ResponseBean<>();
		responseBean.setData(userDao.ListUsers());
		responseBean.setMsg("List users");
		responseBean.setStatus(200);
		return responseBean;

	}

	@GetMapping("/user/{userID}")
	public ResponseBean<UserBean> getUser(@PathVariable("userID") int userID) {

		ResponseBean<UserBean> responseBean = new ResponseBean<>();

		responseBean.setData(userDao.getUserById(userID));
		responseBean.setMsg("Single User Return ");
		responseBean.setStatus(200);

		return responseBean;

	}

	@DeleteMapping("/user/{userID}")
	public ResponseBean<UserBean> deleteUser(@PathVariable("userID") int userID) {

		ResponseBean<UserBean> responseBean = new ResponseBean<>();

		responseBean.setMsg("User Removed");
		responseBean.setStatus(200);

		return responseBean;
	}

}

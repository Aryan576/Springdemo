package com.dao;

import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import com.bean.UserBean;

@Repository
public class UserDao {

	public static ArrayList<UserBean> users = new ArrayList<>();

	public void insertUser(UserBean userBean) {
		users.add(userBean);

	}

	public ArrayList<UserBean> ListUsers() {
		return users;
	}

	public UserBean getUserById(int userID) {
		UserBean userBean = null;

		for (UserBean user : users) {
			if (user.getUserID() == userID) {
				userBean = user;
				break;
			}

		}

		return userBean;
	}

	public boolean deleteUserByID(int userID) {
		UserBean userBean = null;

		for (UserBean user : users) {
			if (user.getUserID() == userID) {
				userBean = user;
				break;
			}
		}

		users.remove(userBean);

		return true;

	}
}

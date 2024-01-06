package com.botree.business;

import com.botree.bean.User;
import com.botree.dao.LoginDao;
import com.botree.exception.InvalidUserException;

public class LoginBo {

public boolean validateUser(User user) throws InvalidUserException{
		
		var loginDao = new LoginDao();
		
		loginDao.getUser(user);
		
		User u = loginDao.getUser(user);
		
		if(u!=null && u.username().equals(user.username()) && u.password().equals(user.password())){   
			return true;
		}
		
		throw new InvalidUserException("Invalid username or password");
	}
}

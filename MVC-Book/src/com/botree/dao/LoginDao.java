package com.botree.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import com.botree.bean.User;
import com.botree.constants.QueryConstants;
import com.botree.util.BUtil;


public class LoginDao {

	public User getUser(User user) {
		Connection conn = BUtil.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		User u = null;
		
		try {
			pstmt = conn.prepareStatement(QueryConstants.LOGIN_SQL); 
			pstmt.setString(1, user.username());
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				u = new User(user.username(), rs.getString("password")); 
			}
			
		}catch(Exception e) {
			
		}
		return u;

	}

}

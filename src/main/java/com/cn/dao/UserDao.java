package com.cn.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import com.cn.entity.User;

public class UserDao {
	public User getByUserName(Connection con, String userName) throws Exception {
		User resultUser=null;
		String sql="select * from t_user where userName=?";
		PreparedStatement ps=con.prepareStatement(sql);
		ps.setString(1, userName);
		ResultSet rs=	ps.executeQuery();
		if(rs.next()) {
			resultUser=new User();
			resultUser.setId(rs.getInt("id"));
			resultUser.setUserName(rs.getString("userName"));
			resultUser.setPassword(rs.getString("password"));
		}
		
		return resultUser;
	}

	public Set<String> getRoles(Connection con, String userName) throws Exception {
		// TODO Auto-generated method stub
		Set<String> roles=new HashSet<String>();
		String sql="select * from t_user u,t_role r where u.roleId=r.id and u.userName=?";
		PreparedStatement ps= con.prepareStatement(sql);
		ps.setString(1, userName);
		ResultSet rs= ps.executeQuery();
		while(rs.next()) {
			roles.add(rs.getString("roleName"));
		}
		
		return roles;
	}

	public Set<String> getPermission(Connection con, String userName) throws Exception {
		// TODO Auto-generated method stub
		Set<String> permissions=new HashSet<String>();
		String sql="select * from t_user u,t_permission p where u.roleId=p.roleId and u.userName=?";
		PreparedStatement ps= con.prepareStatement(sql);
		ps.setString(1, userName);
		ResultSet rs= ps.executeQuery();
		while(rs.next()) {
			permissions.add(rs.getString("permissionName"));
		}
		
		return permissions;
	}
}

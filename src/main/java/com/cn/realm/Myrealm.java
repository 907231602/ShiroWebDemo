package com.cn.realm;

import java.sql.Connection;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import com.cn.dao.UserDao;
import com.cn.entity.User;
import com.cn.utils.DBUtil;

public class Myrealm extends AuthorizingRealm {
	private UserDao userDao=new UserDao();
	private DBUtil dbUtil=new DBUtil();
	
	/**
	 * 为当前登录的用户授予角色和权限
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {
		// TODO Auto-generated method stub
		String userName=(String) principal.getPrimaryPrincipal();
		SimpleAuthorizationInfo authorizationInfo=new SimpleAuthorizationInfo();
		Connection con=null;
		try {
			con=dbUtil.getCon();
			authorizationInfo.setRoles(userDao.getRoles(con,userName));
			authorizationInfo.setStringPermissions(userDao.getPermission(con,userName));
			
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			dbUtil.closeCon(con);
		}
		
		return authorizationInfo;
	}

	/**
	 * 验证当前登录的用户
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		// TODO Auto-generated method stub
		String userName=	(String) token.getPrincipal();
		Connection con=null;
		try {
			con=dbUtil.getCon();
			User user=userDao.getByUserName(con, userName);
			if(user!=null) {
				AuthenticationInfo authenticationInfo=new SimpleAuthenticationInfo(user.getUserName(),user.getPassword(),"xx");
				return authenticationInfo;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			dbUtil.closeCon(con);
		}
		
		return null;
	}

}

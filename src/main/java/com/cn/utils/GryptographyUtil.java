package com.cn.utils;

import org.apache.shiro.codec.Base64;
import org.apache.shiro.crypto.hash.Md5Hash;
/*
 * 密码加密工具类
 */
public class GryptographyUtil {

	/**
	 * Base64加密
	 * @param str
	 * @return
	 */
	public static String encBase64(String str) {
		return Base64.encodeToString(str.getBytes());
	}
	
	/**
	 * Base64解密
	 * @param str
	 * @return
	 */
	public static String decBase64(String str) {
		return Base64.decodeToString(str);
	}
	
	/**
	 * MD5密码加密
	 * @param source
	 * @param salt
	 * @return
	 */
	public static String md5(String source,String salt) {
		return new Md5Hash(source, salt).toString();
	}
	
	
	public static void main(String[] args) {
		String p="java";
		System.out.println(GryptographyUtil.encBase64(p));
		System.out.println(GryptographyUtil.decBase64(GryptographyUtil.encBase64(p)));
		System.out.println(GryptographyUtil.md5(p,"fun"));
	}
	
}

package com.baizhi.util;
import java.security.MessageDigest;

/**
 * 为密码进行MD5加密的类
 * @author MaXn
 *
 */
public class MD5Util {
	public static String getMD5(String pwd){
		try{
			MessageDigest md = MessageDigest.getInstance("md5");
			byte[] digest = md.digest(pwd.getBytes());
			StringBuilder sb = new StringBuilder();
			for (byte b : digest) {
				int c = b & 0xFF;
				if(c<16){
					sb.append("0");
				}
				sb.append(Integer.toHexString(c));
			}
			return sb.toString();
		}catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
}

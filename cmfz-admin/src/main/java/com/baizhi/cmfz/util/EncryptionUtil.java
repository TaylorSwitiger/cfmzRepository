package com.baizhi.cmfz.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

import org.apache.commons.codec.digest.DigestUtils;

public class EncryptionUtil {

	public static String encryption(String str) throws NoSuchAlgorithmException{

		MessageDigest md = MessageDigest.getInstance("MD5");

		byte[] code = md.digest(str.getBytes());

		StringBuilder builder = new StringBuilder();

		for (byte b : code) {
			//1.将每个字节转换成无符号数（正数）
			int c = b & 0xFF;

			if (c < 16) {
				//2.如果16以下的数字，转换成十六进制后，不足2位数，则追加0作为前缀
				builder.append("0");
			}

			//3.将改字节转换为十六进制字符串，进行拼接
			builder.append(Integer.toHexString(c));
		}
		return builder.toString();
	}

	public static String encryptionCode(String str){
		return DigestUtils.md5Hex(str);
	}

	public static String getRandomSalt(int length){
		char[] chs = "abcdefghijklmnopqrstuvwxyz1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
		Random random = new Random();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < length; i++) {
			sb.append(chs[random.nextInt(chs.length)]);
		}
		return sb.toString();
	}
}

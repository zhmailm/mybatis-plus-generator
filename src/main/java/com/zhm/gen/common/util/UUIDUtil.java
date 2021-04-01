package com.zhm.gen.common.util;

/**
 * <p>Description:redis 生成随机数</p>
 * <p>Copyright: Copyright (c)2019</p>
 * <p>Company: elite</p>
 * <P>Created Date :2020-04-22</P>
 * @author huty
 * @version 1.0
 */

import java.util.Random;

public class UUIDUtil {
	public static final String allCharStr = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ!@#$%^&*()_+";
	public static final String allChar = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	public static final String letterChar = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	public static final String numberChar = "0123456789";
	public static final String specialChar = "!@#$%^&*()_+";

	/**
	* @Description: 返回一个定长的随机字符串(只包数字)
	* @create: 2019-07-29
	* @update logs
	* @author huty
	* @throws Exception
	*/
	public static String generateInteger(int length) {
		StringBuffer sb = new StringBuffer();
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			sb.append(numberChar.charAt(random.nextInt(numberChar.length())));
		}
		return sb.toString();
	}

	/**
	 * @Description:返回一个定长的随机字符串(只包含大小写字母、数字)
	 * @create: 2019-07-01
	 * @update logs
	 * @author huty
	 * @throws Exception
	 */
	public static String generateString(int length) {
		StringBuffer sb = new StringBuffer();
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			sb.append(allChar.charAt(random.nextInt(allChar.length())));
		}
		return sb.toString();
	}

	/**
	 * @Description:返回一个定长的随机字符串(只包含大小写字母、数字)
	 * @create: 2019-07-01
	 * @update logs
	 * @author huty
	 * @throws Exception
	 */
	public static String generateAllString(int length) {
		StringBuffer sb = new StringBuffer();
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			sb.append(allCharStr.charAt(random.nextInt(allCharStr.length())));
		}
		return sb.toString();
	}

	/**
	 * @Description:返回一个定长的随机纯字母字符串(只包含大小写字母)
	 * @create: 2019-07-01
	 * @update logs
	 * @author huty
	 * @throws Exception
	 */
	public static String generateMixString(int length) {
		StringBuffer sb = new StringBuffer();
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			sb.append(allChar.charAt(random.nextInt(letterChar.length())));
		}
		return sb.toString();
	}

	/**
	 * @Description:返回一个定长的随机纯大写字母字符串(只包含大小写字母)
	 * @create: 2019-07-01
	 * @update logs
	 * @author huty
	 * @throws Exception
	 */
	public static String generateLowerString(int length) {
		return generateMixString(length).toLowerCase();
	}

	/**
	 * @Description:返回一个定长的随机纯小写字母字符串(只包含大小写字母)
	 * @create: 2019-07-01
	 * @update logs
	 * @author huty
	 * @throws Exception
	 */
	public static String generateUpperString(int length) {
		return generateMixString(length).toUpperCase();
	}

	/**
	 * @Description:返回一个定长的随机纯小写字母字符串(只包含大小写字母)
	 * @create: 2019-07-01
	 * @update logs
	 * @author huty
	 * @throws Exception
	 */
	public static String generateLetterCharString(int length) {
		StringBuffer sb = new StringBuffer();
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			sb.append(letterChar.charAt(random.nextInt(letterChar.length())));
		}
		return sb.toString();
	}

	/**
	 * @Description:生成一个定长的纯0字符串
	 * @create: 2019-07-01
	 * @update logs
	 * @author huty
	 * @throws Exception
	 */
	public static String generateZeroString(int length) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			sb.append('0');
		}
		return sb.toString();
	}

	/**
	 * @Description:根据数字生成一个定长的字符串，长度不够前面补0
	 * @create: 2019-07-01
	 * @update logs
	 * @author huty
	 * @throws Exception
	 */
	public static String toFixdLengthString(long num, int fixdlenth) {
		StringBuffer sb = new StringBuffer();
		String strNum = String.valueOf(num);
		if (fixdlenth - strNum.length() >= 0) {
			sb.append(generateZeroString(fixdlenth - strNum.length()));
		} else {
			throw new RuntimeException("将数字" + num + "转化为长度为" + fixdlenth + "的字符串发生异常！");
		}
		sb.append(strNum);
		return sb.toString();
	}

	/**
	 * @Description:八位数字+字母+特殊字符随机密码生成
	 * @create: 2019-07-01
	 * @update logs
	 * @author huty
	 * @throws Exception
	 */
	public static String generatePwdStr() {
		StringBuffer sb = new StringBuffer();
		Random random = new Random();
		for (int i = 0; i < 3; i++) {
			sb.append(allChar.charAt(random.nextInt(letterChar.length())));
			sb.append(numberChar.charAt(random.nextInt(numberChar.length())));
		}
		for (int i = 0; i < 2; i++) {
			sb.append(specialChar.charAt(random.nextInt(specialChar.length())));
		}
		return sb.toString();
	}

}
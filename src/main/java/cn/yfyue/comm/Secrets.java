package cn.yfyue.comm;


import org.apache.tomcat.util.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;


import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

/**
 * Created by li on 0028/7/28.
 */
public class Secrets {
static String IV = "ABCDDCBAQWERDSAF";

// 字符串加密返回字符(A-加)
public static String aesHexEncode(String test, String key) {
	byte[] bytes = aesEncode(test, key);
	return bytes == null ? null : Base64.encodeBase64String(bytes);
}

// 字符串解密返回字符(A-解)
public static String aesDecode(String encrypted, String key) {
	byte[] bytes = aesHexDecode(encrypted, key);
	try {
		return bytes == null ? null : new String(bytes, "UTF-8");
	} catch (UnsupportedEncodingException e) {
		e.printStackTrace();
	}
	return null;
}

// 加密返回字节
public static byte[] desEncode(String text, String key) {
	byte[] bytes = text.getBytes(StandardCharsets.UTF_8);
	byte[] keyBytes = key.getBytes(StandardCharsets.UTF_8);
	for (int i = 0, j = 0; i < bytes.length; i++, j++) {
		if (j >= keyBytes.length) {
			j = 0;
		}
		bytes[i] ^= keyBytes[j];
	}
	return bytes;
}

// 字节解码(B-解)
public static String desDecode(byte[] bytes, String key) {
	byte[] keyBytes = key.getBytes(StandardCharsets.UTF_8);
	for (int i = 0, j = 0; i < bytes.length; i++, j++) {
		if (j >= keyBytes.length) {
			j = 0;
		}
		bytes[i] ^= keyBytes[j];
	}
	return new String(bytes, StandardCharsets.UTF_8);
}

public static byte[] aesEncode(String plain, String key) {
	try {
		SecretKeySpec skeySpec = new SecretKeySpec(DigestUtils.md5(key),
				"AES");

		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
		cipher.init(Cipher.ENCRYPT_MODE, skeySpec,
				new IvParameterSpec(IV.getBytes("UTF-8")));
		return cipher.doFinal(plain.getBytes("UTF-8"));
	} catch (Exception ex) {
		ex.printStackTrace();
	}

	return null;
}

public static byte[] aesHexDecode(String encrypted, String key) {
	try {
		SecretKeySpec skeySpec = new SecretKeySpec(DigestUtils.md5(key),"AES");
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
		cipher.init(Cipher.DECRYPT_MODE, skeySpec,
				new IvParameterSpec(IV.getBytes("UTF-8")));
		return cipher.doFinal(Base64.decodeBase64(encrypted));
	} catch (Exception ex) {
		ex.printStackTrace();
	}

	return null;
}
/**
public static void main(String[] args) {
	System.out.println(new String(desEncode("我勒个去", "shdwohdowah"),
			StandardCharsets.UTF_8));
	System.out.println(desDecode(desEncode("我勒个去", "shdwohdowah"),
			"shdwohdowah"));
}*/
}

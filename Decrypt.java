package com.encryPad;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.spec.KeySpec;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

public class Decrypt {
	public static void Decrun(String key,String filename,String filepath) throws Exception {

		String password = key;

		// reading the salt
		// user should have secure mechanism to transfer the
		// salt, iv and password to the recipient
		FileInputStream saltFis = new FileInputStream(String.format("%s\\%ssalt.enc", filepath,filename));
		byte[] salt = new byte[8];
		saltFis.read(salt);
		saltFis.close();

		// reading the iv
		FileInputStream ivFis = new FileInputStream(String.format("%s\\%siv.enc", filepath,filename));
		byte[] iv = new byte[16];
		ivFis.read(iv);
		ivFis.close();

		SecretKeyFactory factory = SecretKeyFactory
				.getInstance("PBKDF2WithHmacSHA1");
		KeySpec keySpec = new PBEKeySpec(password.toCharArray(), salt, 65536,
				256);
		SecretKey tmp = factory.generateSecret(keySpec);
		SecretKey secret = new SecretKeySpec(tmp.getEncoded(), "AES");

		// file decryption
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		cipher.init(Cipher.DECRYPT_MODE, secret, new IvParameterSpec(iv));
		FileInputStream fis = new FileInputStream(String.format("%s\\%sencryptedfile.des", filepath,filename));
		FileOutputStream fos = new FileOutputStream(String.format("%s\\%s.txt", filepath,filename));
		byte[] in = new byte[64];
		int read;
		while ((read = fis.read(in)) != -1) {
			byte[] output = cipher.update(in, 0, read);
			if (output != null)
				fos.write(output);
		}

		byte[] output = cipher.doFinal();
		if (output != null)
			fos.write(output);
		fis.close();
		fos.flush();
		fos.close();
		System.out.println("File Decrypted.");
	}
}
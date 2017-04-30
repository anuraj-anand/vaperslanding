package com.orderbid.security;

import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.RSAPublicKeySpec;

import javax.crypto.Cipher;

public class RSAEncryptionUtil {

	private static PublicKey publicKey = null;
	private static PrivateKey privateKey = null;

	static {
		try {
			initRSAKeys();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * @return
	 */
	public static String getPublicKey() {
		String key = null;
		int iterationCount = 0;

		while (key == null) {
			++iterationCount;
			try {
				RSAPublicKeySpec pub = new RSAPublicKeySpec(BigInteger.ZERO,
						BigInteger.ZERO);
				KeyFactory fact = KeyFactory.getInstance("RSA");
				pub = fact.getKeySpec(publicKey, RSAPublicKeySpec.class);
				key = pub.getModulus().toString(16);

			} catch (Exception e) {
				if (iterationCount > 10) {
					break;
				}
				try {
					Thread.sleep(1000);
				} catch (Exception e2) {
					e2.printStackTrace();
				}

			}
		}
		return key;
	}

	public static void initRSAKeys() throws Exception {
		int iterationCount = 0;

		while (publicKey == null || privateKey == null) {
			++iterationCount;
			try {
				KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
				kpg.initialize(2048);
				KeyPair kp = kpg.genKeyPair();
				publicKey = kp.getPublic();
				privateKey = kp.getPrivate();
			} catch (Exception e) {
				if (iterationCount > 10) {
					throw new Exception("RSA Keys not generated!!!!");
				}
			}

		}
	}

	public static String decrypt(String input) {
		byte[] dectyptedText = new byte[1];
		String output = null;
		try {
			Cipher cipher = Cipher.getInstance("RSA");
			cipher = javax.crypto.Cipher.getInstance("RSA");
			cipher.init(Cipher.DECRYPT_MODE, privateKey);

			byte[] byteArray = new byte[256];

			BigInteger passwordInt = new BigInteger(input, 16);
			if (passwordInt.toByteArray().length > 256) {
				for (int i = 1; i < 257; i++) {
					byteArray[i - 1] = passwordInt.toByteArray()[i];
				}
			} else {
				byteArray = passwordInt.toByteArray();
			}
			dectyptedText = cipher.doFinal(byteArray);
			output = new String(dectyptedText);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return output;
	}
}

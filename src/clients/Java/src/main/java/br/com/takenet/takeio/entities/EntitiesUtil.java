package br.com.takenet.takeio.entities;

import java.math.BigInteger;
import java.util.UUID;

public class EntitiesUtil {
	
	private EntitiesUtil() {
	}
	
	public static UUID uuidFromString(String str) {
		String strNum = str.replace("-", "");
		UUID uuid = new UUID(
		        new BigInteger(strNum.substring(0, 16), 16).longValue(),
		        new BigInteger(strNum.substring(16), 16).longValue());
		
		return uuid;
	}
}

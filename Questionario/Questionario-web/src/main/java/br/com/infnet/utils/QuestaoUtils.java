package br.com.infnet.utils;

import java.math.BigInteger;
import java.security.MessageDigest;

public class QuestaoUtils {

	public String encrypt(String senha) throws Exception{
		MessageDigest md = MessageDigest.getInstance("MD5");
		BigInteger hash = new BigInteger(1,md.digest(senha.getBytes()));
		return hash.toString(16);
	}
	
}

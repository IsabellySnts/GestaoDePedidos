package com.cantinagomes.gestaopedidos.util;

import java.nio.charset.StandardCharsets;

import com.google.common.hash.Hashing;

public class HashUtil {

	
public static String hash256(String palavra) {
		
		//tempero para o hash
		
		String salt = "g@m$sD!@g@";
		
		//acrescentando o tempero;
		
		palavra = palavra + salt;
		
		//criando o hash e armazenando na string
		
		String sha256 = Hashing.sha256().hashString(palavra, StandardCharsets.UTF_8).toString();
		
		return sha256;
		
	}
}

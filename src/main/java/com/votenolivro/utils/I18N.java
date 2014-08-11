package com.votenolivro.utils;

import java.text.MessageFormat;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class I18N {

	private static ResourceBundle resourceBundle;
	
	static{
		resourceBundle = ResourceBundle.getBundle("idioma");
	}
	
	public static String getString( String chave ){
		try{
			return resourceBundle.getString(chave);
		}catch (MissingResourceException e) {
			e.printStackTrace();
			return chave;
		}
	}
	
	public static String getString(String key, Object... args) {
		try{
			return MessageFormat.format(resourceBundle.getString(key), args);
		}catch(MissingResourceException e) {
			e.printStackTrace();
			return key;
		}
	}
	
}

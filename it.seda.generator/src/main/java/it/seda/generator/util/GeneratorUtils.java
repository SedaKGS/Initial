package it.seda.generator.util;



import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GeneratorUtils {
    
	private static Logger logger = LoggerFactory.getLogger(GeneratorUtils.class);
	
	private GeneratorUtils() {
		// TODO Auto-generated constructor stub
	}

	
	static public String resolveClassName(String clazz) {
		String name;
		int lastdot = clazz.lastIndexOf('.');
		if (lastdot>0) {
			name=clazz.substring(lastdot+1);
		} else {
			name=clazz;
		}
		return name;
	}
	
	static public String resolveClassNamespace(String clazz) {
		String namespace;
		int lastdot = clazz.lastIndexOf('.');
		if (lastdot>0) {
			namespace=clazz.substring(0,lastdot);
		} else {
			namespace="";
		}
		return namespace;
	}
	
	static public String capitalize(String s) {
		StringBuilder stringBuilder = new StringBuilder();
		for (int i = 0; i < s.length(); i++) {
			if (i==0) {
				stringBuilder.append(Character.toUpperCase(s.charAt(0)));
			} else {
				stringBuilder.append(s.charAt(i));
			}
		}
		return stringBuilder.toString();
	}
	
	static public String lower(String s) {
		StringBuilder stringBuilder = new StringBuilder();
		for (int i = 0; i < s.length(); i++) {
			if (i==0) {
				stringBuilder.append(Character.toLowerCase(s.charAt(0)));
			} else {
				stringBuilder.append(s.charAt(i));
			}
		}
		return stringBuilder.toString();
	}
	
	
	static public void printToFile(String path,String name,String ext,String body){
		BufferedWriter writer = null;
		File file=new File(path.replace(".", File.separator));
		if (!file.exists()) {
			if (file.mkdirs()) {
				logger.info(name+"."+ext+" : Directory creata!");
			} else {
				logger.info(name+"."+ext+" : Crazione directory fallita!");
			}
		}
		file=new File(path.replace(".", File.separator)+File.separator+name+"."+ext);
		try {
			file.createNewFile();
			writer = new BufferedWriter(new FileWriter(file));
			writer.write(body);
			writer.close();
		} catch (IOException e) {
			logger.info(name+"."+ext+" : Problemi nella creazione del file!");
			e.printStackTrace();
			return;
		}
		
		
		
	}
}

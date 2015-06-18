package nl.atd.helper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

public class ConfigHelper {
	private static Properties properties = new Properties();
	
	public static boolean loadConfigFile(File file) {
		try{
			if(file.exists() && file.canRead()) {
				// Xml parsing
				properties.loadFromXML(new FileInputStream(file));
			}else{
				return false;
			}
		
			return true;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static boolean saveConfigFile(File file) {
		try{
			if(file.canWrite()) {
				// Xml saving
				properties.storeToXML(new FileOutputStream(file, false), "Installatie");
			}else{
				return false;
			}
			return true;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static boolean isValidConfig() {
		return properties.containsKey("installed");
	}
	
	public static Properties getProperties() {
		return properties;
	}
}

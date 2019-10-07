package com.qa.base;

import java.io.FileInputStream;
import java.util.Properties;

import com.qa.filelocation.FileLocation;

public class TestBase {

	public static Properties prop;

	public TestBase() {

		prop = new Properties();
		try {
			FileInputStream fis = new FileInputStream(FileLocation.CONFIG_FILE_LOCATION);
			prop.load(fis);
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

}

package com.qa.util;

import java.io.FileInputStream;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TestUtil {

	public static String[][] getDataExcel(String sheetLocation, String sheetName) {

		String[][] userData = null;

		try {
			FileInputStream fis = new FileInputStream(sheetLocation);
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			XSSFSheet sh = wb.getSheet(sheetName);
			int lastRowNum = sh.getLastRowNum(); // 2
			int lastCellNum = sh.getRow(0).getLastCellNum();// 5
			userData = new String[lastRowNum][lastCellNum];
			for (int rw = 0; rw < lastRowNum; rw++) {

				for (int cl = 0; cl < lastCellNum; cl++) {

					userData[rw][cl] = sh.getRow(rw + 1).getCell(cl).getStringCellValue();

				}
			}
			return userData;

		} catch (Exception e) {

			e.printStackTrace();
		}
		return userData;
	}

	public static String pojoToJson(Object obj) {

		String jsonString = null;
		ObjectMapper mapper = new ObjectMapper();
		try {
			jsonString = mapper.writeValueAsString(obj);
		} catch (JsonProcessingException e) {

			e.printStackTrace();
		}

		return jsonString;

	}

}

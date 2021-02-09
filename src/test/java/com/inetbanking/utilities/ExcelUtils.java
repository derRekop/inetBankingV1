package com.inetbanking.utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {
	
	public static FileInputStream fileInput;
	public static FileOutputStream fileOutput;
	public static XSSFWorkbook wBook;
	public static XSSFSheet wSheet;
	public static XSSFRow row;
	public static XSSFCell cell;

	public static int getRowCount(String excelFile, String excelSheet) throws IOException {
		fileInput = new FileInputStream(excelFile);
		wBook = new XSSFWorkbook(fileInput);
		wSheet = wBook.getSheet(excelSheet);
		int rowCount = wSheet.getLastRowNum();
		wBook.close();
		fileInput.close();
		return rowCount;
	}
	
	public static int getCellCount(String excelFile, String excelSheet, int rowNum) throws IOException {
		fileInput = new FileInputStream(excelFile);
		wBook = new XSSFWorkbook(fileInput);
		wSheet = wBook.getSheet(excelSheet);
		row = wSheet.getRow(rowNum);
		int cellCount = row.getLastCellNum();
		wBook.close();
		fileInput.close();
		return cellCount;
	}
	
	public static String getCellData(String excelFile, String excelSheet, int rowNum, int colNum) throws IOException {
		fileInput = new FileInputStream(excelFile);
		wBook = new XSSFWorkbook(fileInput);
		wSheet = wBook.getSheet(excelSheet);
		row = wSheet.getRow(rowNum);
		cell = row.getCell(colNum);
		String data;
		try {
			DataFormatter formatter = new DataFormatter();
			data = formatter.formatCellValue(cell);
		} catch (Exception e) {
			data = "";
		}
		wBook.close();
		fileInput.close();
		return data;
	}
	
	public static void setCellData(String excelFile, String excelSheet, int rowNum, int colNum, String data) throws IOException {
		fileInput = new FileInputStream(excelFile);
		wBook = new XSSFWorkbook(fileInput);
		wSheet = wBook.getSheet(excelSheet);
		row = wSheet.getRow(rowNum);
		cell = row.createCell(colNum);
		cell.setCellValue(data);
		fileOutput = new FileOutputStream(excelFile);
		wBook.write(fileOutput);
		wBook.close();
		fileInput.close();
		fileOutput.close();
	}
}

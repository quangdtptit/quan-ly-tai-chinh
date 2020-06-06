package com.example.utils;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.example.model.annotation.ColumnExcel;
import com.example.model.dto.ItemDTO;

public class ExcelUtil {

	private SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

	private <T> T init(Class<T> clazz) {
		try {
			return clazz.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			return null;
		}
	}

	public <T> Method findMethodByAnnotation(ColumnExcel columnExcel, T t) {
		Class<?> clazz = t.getClass();
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			Annotation[] annotations = field.getDeclaredAnnotations();
			for (Annotation annotation : annotations) {
				if (annotation instanceof ColumnExcel && annotation == columnExcel) {
					String nameField = field.getName();
					nameField = ((nameField.charAt(0) + "").toUpperCase() + nameField.substring(1, nameField.length()));
					Method[] methods = clazz.getDeclaredMethods();
					for (Method method : methods) {
						if (method.getName().equals("set" + nameField)) {
							return method;
						}
					}
				}
			}
		}
		return null;
	}

	public <T> Map<String, ColumnExcel> getHeaderByClass(Class<T> clazz) {
		Map<String, ColumnExcel> result = new TreeMap<>();
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			Annotation[] annotations = field.getDeclaredAnnotations();
			for (Annotation annotation : annotations) {
				if (annotation instanceof ColumnExcel) {
					ColumnExcel columnExcel = (ColumnExcel) annotation;
					result.put(field.getName(), columnExcel);
				}
			}
		}
		return result;
	}

	public <T> boolean isEqualsHeaderClassAndFile(Class<T> clazz, Row row) {
		int count = 0;
		Map<String, ColumnExcel> map = this.getHeaderByClass(clazz);
		Iterator<Cell> cellIterator = row.cellIterator();
		while (cellIterator.hasNext()) {
			Cell cell = cellIterator.next();
			String title = cell.toString().trim();
			for (Map.Entry<String, ColumnExcel> entry : map.entrySet()) {
				if (title.equalsIgnoreCase(entry.getValue().title().trim())
						&& cell.getColumnIndex() == entry.getValue().col()) {
					count++;
					break;
				}
			}
		}
		return count == map.size();
	}

	public <T> T convertRowToT(Row row, Class<T> clazz) {
		T result = this.init(clazz);
		Map<String, ColumnExcel> map = this.getHeaderByClass(clazz);
		Iterator<Cell> cellIterator = row.cellIterator();
		while (cellIterator.hasNext()) {
			Cell cell = cellIterator.next();
			for (Map.Entry<String, ColumnExcel> entry : map.entrySet()) {
				if (entry.getValue().col() == cell.getColumnIndex()) {
					try {
						Method method = this.findMethodByAnnotation(entry.getValue(), result);
						switch (entry.getValue().type()) {
						case _STRING:
							method.invoke(result, cell.getStringCellValue());
							break;
						case _DATE:
							method.invoke(result, cell.getDateCellValue());
							break;
						case _DOLLARS:
							try {
								method.invoke(result, cell.getNumericCellValue());
							} catch (IllegalArgumentException e) {
								method.invoke(result, (long) cell.getNumericCellValue());
							}
							break;
						case _DOUBLE:
							method.invoke(result, cell.getNumericCellValue());
							break;
						case _INTEGER:
							method.invoke(result, (int) cell.getNumericCellValue());
							break;
						case _FORMULA:
							break;
						default:
							break;
						}
					} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
						e.printStackTrace();
						return null;
					}
				}
			}
		}
		return result;
	}

	public <T> List<T> importFileExcel(File file, Class<T> clazz) {
		List<T> result = new ArrayList<>();
		try {
			Workbook workbook = new XSSFWorkbook(file);
			Sheet sheet = workbook.getSheetAt(0);
			Iterator<Row> rowIterator = sheet.rowIterator();

			// CHECK HEADER
			if (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				boolean checked = this.isEqualsHeaderClassAndFile(clazz, row);
				if (!checked) {
					return null;
				}
			}

			// CHECK ROW
			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				T element = this.convertRowToT(row, clazz);
				result.add(element);
			}
			workbook.close();
		} catch (InvalidFormatException | IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	private CellStyle createStyleForTitle(Workbook workbook) {
		Font font = workbook.createFont();
		font.setBold(true);
		CellStyle style = workbook.createCellStyle();
		style.setFont(font);
		return style;
	}

	private CellStyle cellDataDate(Workbook workbook, String formatDate) {
		CreationHelper creationHelper = workbook.getCreationHelper();
		CellStyle cellStyle = workbook.createCellStyle();
		cellStyle.setDataFormat(creationHelper.createDataFormat().getFormat(formatDate));
		return cellStyle;
	}

	private CellStyle cellDataDollar(Workbook workbook, String symbol) {
		String currentSymbol = "#,##0\\ \"" + symbol + "\";\\-#,##0\\ \"" + symbol + "\"";
		CreationHelper creationHelper = workbook.getCreationHelper();
		CellStyle cellStyle = workbook.createCellStyle();
		cellStyle.setDataFormat(creationHelper.createDataFormat().getFormat(currentSymbol));
		return cellStyle;
	}

	private CellStyle cellDataNumber(Workbook workbook, String symbol) {
		CreationHelper creationHelper = workbook.getCreationHelper();
		CellStyle cellStyle = workbook.createCellStyle();
		cellStyle.setDataFormat(creationHelper.createDataFormat().getFormat(symbol));
		return cellStyle;
	}

	private void createHeader(Row row, Class<?> clazz, CellStyle cellStyle) {
		Map<String, ColumnExcel> map = this.getHeaderByClass(clazz);
		for (Map.Entry<String, ColumnExcel> entry : map.entrySet()) {
			Cell cell = row.createCell(entry.getValue().col());
			cell.setCellValue(entry.getValue().title());
			cell.setCellStyle(cellStyle);
		}
	}

	private <T> void createData(Row row, T t, Class<?> clazz) {
		Map<String, ColumnExcel> result = this.getHeaderByClass(clazz);
		for (Map.Entry<String, ColumnExcel> entry : result.entrySet()) {
			String nameMethod = entry.getKey();
			nameMethod = ("get" + (nameMethod.charAt(0) + "").toUpperCase()
					+ nameMethod.substring(1, nameMethod.length()));
			Cell cell = row.createCell(entry.getValue().col());
			try {
				Method method = t.getClass().getDeclaredMethod(nameMethod);
				switch (entry.getValue().type()) {
				case _STRING:
					String value1 = (String) method.invoke(t, null);
					cell.setCellValue(value1);
					break;
				case _DATE:
					Date value2 = (Date) method.invoke(t, null);
					cell.setCellValue(value2);
					cell.setCellStyle(this.cellDataDate(row.getSheet().getWorkbook(), "dd-MM-yyyy"));
					break;
				case _DOLLARS:
					double value3 = (double) ((Number) method.invoke(t, null)).doubleValue();
					cell.setCellValue(value3);
					cell.setCellStyle(this.cellDataDollar(row.getSheet().getWorkbook(), "VND"));
					break;
				case _DOUBLE:
					double value4 = (double) method.invoke(t, null);
					cell.setCellValue(value4);
					cell.setCellStyle(this.cellDataNumber(row.getSheet().getWorkbook(), "0.0"));
					break;
				case _INTEGER:
					int value5 = (int) method.invoke(t, null);
					cell.setCellValue(value5);
					cell.setCellStyle(this.cellDataNumber(row.getSheet().getWorkbook(), "0"));
					break;
				case _FORMULA:
					break;
				default:
					break;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public <T> boolean exportExcel(List<T> list, HttpServletResponse response, String nameFile) {
		response.setContentType("application/octet-stream:UTF-8"); // or can use text/csv
		response.setHeader("Content-Disposition",
				"attachment; filename=" + nameFile + "_" + sdf.format(new Date()) + ".xlsx");
		response.setHeader("charset", "UTF-8");

		Class<?> clazz = list.get(0).getClass();
		Workbook workbook = new XSSFWorkbook();
		Sheet sheet = workbook.createSheet("Sheet 1");
		sheet.setDefaultColumnWidth(15);
		CellStyle cellStyle = this.createStyleForTitle(workbook);

		// CREATE HEADER
		Row row = sheet.createRow(0);
		this.createHeader(row, clazz, cellStyle);

		// CREATE ROW
		int i = 1;
		for (T element : list) {
			Row record = sheet.createRow(i);
			this.createData(record, element, clazz);
			i++;
		}

		try {
			OutputStream os = response.getOutputStream();
			workbook.write(os);
			workbook.close();
			return true;
		} catch (IOException e) {
			return false;
		}
	}

	public static void main(String[] args) {
		File file = new File("demo.xlsx");
		List<ItemDTO> itemDTOs = new ExcelUtil().importFileExcel(file, ItemDTO.class);
		for (ItemDTO e : itemDTOs) {
			System.out.println(e.getName() + " - " + e.getTotal());
		}
	}

}

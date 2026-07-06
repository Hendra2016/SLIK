package common.util.exel;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import common.util.exel.util.EachFieldCallback;
import common.util.exel.util.ReflectionUtils;

public class Exel {

	private Class<?> clazz;

	public Exel(Class<?> clazz) {
		this.clazz = clazz;
	}

	private Workbook createWorkbook(InputStream inputStream, String type) throws IOException {
		if (type.equals("xls")) {
			return new HSSFWorkbook(inputStream);
		} else { // 2007+
			return new XSSFWorkbook(inputStream);
		}
	}

	@SuppressWarnings("unchecked")
	public <T> List<T> readFileExelFromUser(InputStream is, String type, int numberSheets) throws Throwable {
		try {
			Iterator<Row> rowIterator;
			List<T> items = new LinkedList<T>();
			Workbook workbook = createWorkbook(is, type);
			Sheet sheet = workbook.getSheetAt(numberSheets);
			rowIterator = sheet.iterator();
			Map<String, Integer> nameIndexMap = new HashMap<String, Integer>();
			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				if (row.getRowNum() == 0) {
					readExcelHeader(row, nameIndexMap);
				} else {
					items.add((T) readExcelContent(row, nameIndexMap));
				}
			}
			return items;
		} catch (IOException e) {
			e.printStackTrace();
			return new ArrayList<T>();
		}
	}

	private void readExcelHeader(final Row row, final Map<String, Integer> cells) throws Throwable {
		ReflectionUtils.eachFields(clazz, new EachFieldCallback() {
			public void each(Field field, String name) throws Throwable {
				mapName2Index(name, row, cells);
			}
		});
	}

	private void mapName2Index(String name, Row row, Map<String, Integer> cells) {
		int index = findIndexCellByName(name, row);
		if (index != -1) {
			cells.put(name, index);
		}
	}

	private int findIndexCellByName(String name, Row row) {
		Iterator<Cell> iterator = row.cellIterator();
		while (iterator.hasNext()) {
			Cell cell = iterator.next();
			if (getCellValue(cell).trim().equalsIgnoreCase(name)) {
				return cell.getColumnIndex();
			}
		}

		return -1;
	}

	private String getCellValue(Cell cell) {
		if (cell == null) {
			return null;
		}

		String value = "";
		switch (cell.getCellType()) {
		case Cell.CELL_TYPE_BOOLEAN:
			value += String.valueOf(cell.getBooleanCellValue());
			break;
		case Cell.CELL_TYPE_NUMERIC:
			value += new BigDecimal(cell.getNumericCellValue()).toString();
			break;
		case Cell.CELL_TYPE_STRING:
			value += cell.getStringCellValue();
			break;
		}

		return value;
	}

	private Object readExcelContent(final Row row, final Map<String, Integer> cells) throws Throwable {
		final Object instance = clazz.newInstance();
		ReflectionUtils.eachFields(clazz, new EachFieldCallback() {
			public void each(Field field, String name) throws Throwable {
				ReflectionUtils.setValueOnField(instance, field, getValueByName(name, row, cells));
			}
		});

		return instance;
	}

	private String getValueByName(String name, Row row, Map<String, Integer> cells) {
		if (cells.get(name) == null) {
			return null;
		}

		Cell cell = row.getCell(cells.get(name));
		return getCellValue(cell);
	}
}

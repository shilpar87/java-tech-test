package com.cg.Server;

import static spark.Spark.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class Main {
	public static void main(String[] args) {
		serve();
	}

	public static void serve() {
		port(8008);
		post("/serve", (req, res) -> {
			switch (req.body()) {
			case "Hello":
				return hello();
			case "Table":
				return table();
			case "Spin":
				return spin();
			default:
				return "Error! No or invalid request name specified! (" + req.body() + ")";
			}
		});
	}

	public static String hello() {
		return "Hello stranger!";
	}

	public static String table() throws FileNotFoundException, IOException {
		FileInputStream fileIn = null;
		FileOutputStream fileOut = null;
		String percentRes = "";
		HSSFWorkbook wb = null;
		Map<Double, Double> map = new HashMap<Double, Double>();
		Random rand = new Random();

		try {
			fileIn = new FileInputStream("D:\\workspace\\java-engine-tech-test-master\\profile.xls");
			POIFSFileSystem fs = new POIFSFileSystem(fileIn);
			wb = new HSSFWorkbook(fs);
			HSSFSheet sheet = wb.getSheetAt(0);
			int keyInput = rand.nextInt(4);

			for (int i = 3; i < 7; i++) {
				HSSFRow row = sheet.getRow(i);
				HSSFRow col = sheet.getRow(i);
				if (row != null && col != null) {
					map.put(row.getCell(1).getNumericCellValue(), col.getCell(2).getNumericCellValue());
					Double value = row.getCell(1).getNumericCellValue();
					Double chanceValue = col.getCell(2).getNumericCellValue();
					System.out.println("Table Values " + value * 10 / 10 + " : " + chanceValue * 100);
				} else {
					System.out.println("Either of rows 0 or 1 is empty");
				}

			}
			Double key = (double) keyInput;
			Double val = map.get(key);
			int chancePercent = (int) (val * 100);
			percentRes = String.valueOf(chancePercent) + "%";
			System.out.println("Chance :" + percentRes);

		} catch (FileNotFoundException f) {
			f.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {

			if (wb != null)
				wb.close();
			if (fileOut != null)
				fileOut.close();
			if (fileIn != null)
				fileIn.close();
		}

		return percentRes;
	}

	public static String spin() {
		Workbook w;
		String spinSymbolsMatrix = "";
		try {
			FileInputStream fileIn;
			fileIn = new FileInputStream("D:\\workspace\\java-engine-tech-test-master\\profile.xls");
			w = Workbook.getWorkbook(fileIn);

			// Get the first sheet
			String[][] data = null;
			String input = "";
			Map<Integer, String> mapInputRandom = new HashMap<Integer, String>();

			// Read the first sheet
			Sheet newSheet = w.getSheet(1);
			data = new String[newSheet.getColumns()][newSheet.getRows()];

			Random r = new Random();
			int key = r.nextInt(4);
			int index = 0;

			for (int j = 1; j < 2; j++) {
				for (int i = 2; i < 6; i++) {

					jxl.Cell cell = newSheet.getCell(j, i);
					data[j][i] = cell.getContents();
					input = cell.getContents();
					System.out.println(input);
					mapInputRandom.put(index, input);
					index++;
				}

			}
			String symbol = "";
			for (Integer intIndex : mapInputRandom.keySet()) {
				symbol = mapInputRandom.get(key);
			}

			spinSymbolsMatrix = buildMatrix(symbol);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BiffException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return spinSymbolsMatrix;
	}

	public static String buildMatrix(String randomSymbol) {

		FileInputStream fileIn = null;
		List<String> list = new ArrayList<String>();

		try {
			fileIn = new FileInputStream("D:\\workspace\\java-engine-tech-test-master\\profile.xls");

			Workbook w = Workbook.getWorkbook(fileIn);
			System.out.println("Random Symbol " + randomSymbol);
			Sheet newSheet = w.getSheet(1);
			String[][] data = new String[newSheet.getRows()][newSheet.getColumns()];
			System.out.println("Rows : " + newSheet.getRows() + "Columns : " + newSheet.getColumns());
			String matchedString = "";
			outerloop: for (int i = 3; i < 21; i++) {
				for (int j = 4; j < 8; j++) {
					jxl.Cell cell = newSheet.getCell(j, i);
					data[i][j] = cell.getContents();
					matchedString = cell.getContents();
					//if string matches with the random symbol then add the contents to array
					if (matchedString.equalsIgnoreCase(randomSymbol)) {
						for (int x = 0; x < 12; x++) {
							jxl.Cell cell1 = newSheet.getCell(j, i);
							data[i][j] = cell1.getContents();
							System.out.println(data[i][j]);
							list.add(data[i][j]);
							if (j < 7) {
								j++;
							} else {
								++i;
								j = 4;
							}

						}
						break outerloop;
					}

				}

			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BiffException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//using java 8 stream
		String symbolsSpin = list.stream().collect(Collectors.joining(" "));

		String[] strArr = symbolsSpin.split(" ");

		int size = (int) Math.sqrt(strArr.length);

		String[][] matrix = new String[size][size];

		for (int i = 0; i < size; ++i) {
			for (int j = 0; j < size; ++j) {
				if (strArr[i * size + j].equals("")) {
					matrix[i][j] = strArr[i * size + 1];

				} else {
					matrix[i][j] = strArr[i * size + j];

				}

//				System.out.println("matrix[i][j] " + matrix[i][j]);
			}
		}

		for (int i = 0; i < size; ++i) {
			for (int j = 0; j < size; ++j) {
				System.out.print(matrix[i][j] + " ");
			}

			System.out.println();
		}

		return symbolsSpin;
	}

}

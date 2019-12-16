package com.cg.Client;

import com.cg.Client.Client;

import java.io.FileWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

public class CommunicationTest {

	@Test
	public void testHelloRequestCorrect() throws Exception {
		String res = Client.postRequest("Hello");
		assert (res.equals("Hello stranger!"));
	}

	@Test
	public void testHelloRequestIncorrect() throws Exception {
		String res = Client.postRequest("hello");
		assert (res.equals("Error! No or invalid request name specified! (hello)"));
	}

	// add more tests here to validate your work

	@Test
	public void testTableRequestCorrect() throws Exception {
		List<String> resList = new ArrayList<String>();
		FileWriter writer = null;
		List<String> expected = Arrays.asList("10%", "20%", "30%", "40%");
		int valueCount = 0;
		// multiple calls to the same test
		for (int i = 0; i <= 105; i++) {
			String res = Client.postRequest("Table");
			if (res == "30%")
				valueCount++;
			System.out.println("i count " + i + "\nResult String " + res + "\nCount of Value 3 " + valueCount);
			resList.add(res);

		}
		System.out.println("Array size .. " + resList.size());

		// Writing the output to a text file
		String path = "D:\\workspace\\java-engine-tech-test-master\\output.txt";
		writer = new FileWriter(path, true);
		writer.append(System.lineSeparator() + "The Random Chance is " + resList + System.lineSeparator());

		// hashmap to store the frequency of element
		Map<String, Integer> hm = new HashMap<String, Integer>();
		for (String i : resList) {
			Integer j = hm.get(i);
			hm.put(i, (j == null) ? 1 : j + 1);
		}
		// displaying the occurrence of elements in the arraylist
		for (Map.Entry<String, Integer> val : hm.entrySet()) {
			Double percentage = (double) ((val.getValue().doubleValue())/100);
			writer.append(System.lineSeparator() + "Value " + val.getKey() + " " + "occurs" + ": " + val.getValue()
					+ " times. "+" Approx. percentage is  "+percentage);
		}

		writer.append(System.lineSeparator() + "==========================================================");
		writer.close();

		Assert.assertEquals(resList.size(), 10);
		Assert.assertEquals(resList.size(), 8);
		Assert.assertEquals(resList.size(), expected.size());

	}

	@Test
	public void testTableRequestIncorrect() throws Exception {
		String res = Client.postRequest("table");
		assert (res.equals("Error! No or invalid request name specified! (table)"));
	}

	@Test
	public void testSpinRequestCorrect() throws Exception {
		
		FileWriter writer = null;
		String res="";

		// Writing the output to a text file
		String path = "D:\\workspace\\java-engine-tech-test-master\\outputDesiredTask.txt";
		writer = new FileWriter(path, true);

		for(int i=0;i<30;i++) {
			res = Client.postRequest("Spin");
			System.out.println(res);
			System.out.println("length "+res.length());
		
		// displaying the occurrence of elements in the arraylist
		writer.append(System.lineSeparator() + "Spin Matrix Values ===> " + res);
		}
		writer.append(System.lineSeparator() + "==========================================================");
		
		writer.close();
		Assert.assertNotNull("Spin matrix is not null", res);
		Assert.assertEquals(res.length(), 2);
	}

}

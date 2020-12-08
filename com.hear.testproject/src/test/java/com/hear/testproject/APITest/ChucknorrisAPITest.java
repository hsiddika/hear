package com.hear.testproject.APITest;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Assert;
import org.junit.Test;

public class ChucknorrisAPITest {

	@Test
	public void httpGetRequestRest() throws Exception {
		String url = "https://api.chucknorris.io/jokes/categories";
		HttpClient client = HttpClientBuilder.create().build();
		HttpGet request = new HttpGet(url);
		HttpResponse response = client.execute(request);
		int actualResponseCode = response.getStatusLine().getStatusCode();
		int httpResponseCode = 200;
		Assert.assertEquals(httpResponseCode, actualResponseCode);
		BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
		StringBuffer result = new StringBuffer();
		String readNewLine ="";
		while((readNewLine=reader.readLine())!=null) {
			result.append(readNewLine);
		}
		String category = result.toString();
		category = category.replaceAll("[^a-zA-Z0-9,]", "");
		String[] categoryArray = category.split(",");
		List<String> categoryList = new ArrayList<String>();
		categoryList = Arrays.asList(categoryArray);
		int counter = 0;
		for(String eachCategory: categoryList ) {
			System.out.println(eachCategory);
			counter++;
		}
		
		System.out.println("There are "+counter+" categories");
	}
}


package com.ice;

import static java.util.regex.Pattern.compile;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CUSIPScanner  {
	
	private static final Pattern CUSIP_PATTERN = compile("[A-Za-z0-9]{8}");
	
	public static void main(String[] args) throws IOException {
		 BufferedReader bufferedReader = null;
        try {
            File file = new File("src/cusipprices.txt");
            bufferedReader = new BufferedReader(new FileReader(file));
            String readLine = null;
            Map<String, List<String>> cusipMap = new HashMap<String, List<String>>();
            String cusip = null;
            while ((readLine = bufferedReader.readLine()) != null) {
                Matcher m = CUSIP_PATTERN.matcher(readLine);
                if(m.matches()) {
                	cusip = readLine;
                	cusipMap.put(cusip, new ArrayList<>());
                } else {
                	cusipMap.get(cusip).add(readLine);
                }
            }
            display(cusipMap);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        	bufferedReader.close();
        }
    }
	
	private static void display(Map<String, List<String>> cusipMap) {
		cusipMap.forEach((k,v)-> System.out.println("cusip : " + k + " price : " + v.get(v.size()-1)));
	}

}

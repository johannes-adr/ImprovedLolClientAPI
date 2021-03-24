package de.jadr;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class JUtils {

	public static String getWebsiteContent(String s) throws IOException{
		URLConnection inp = new URL(s).openConnection();
		inp.addRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.0)");
		BufferedReader br = new BufferedReader(new InputStreamReader(new BufferedInputStream(inp.getInputStream())));

		StringBuilder content = new StringBuilder();

		while (true) {
			String line = br.readLine();
			if (line != null) {
				content.append(line + "\n");
			} else {
				break;
			}
		}

		return content.toString();
	}
	
	public static String getCatchedWebsiteContent(String s) {
		try {
			return getWebsiteContent(s);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static String getFileContent(File f) {
		StringBuilder sb = new StringBuilder();
		Scanner sc;
		try {
			sc = new Scanner(f);
			while (sc.hasNext()) {
				sb.append(sc.nextLine());
			}
			sc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		return sb.toString();
	}

	public static void delay(long l) {
		try {
			Thread.sleep(l);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	
}

package librarry;

import java.io.*;

public class FileToString {
	public static String read(String path){
		StringBuilder result = new StringBuilder();
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(path)))){
			for(String line= reader.readLine();line!=null;line=reader.readLine()){
				result.append(line).append("\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result.toString();
	}
}

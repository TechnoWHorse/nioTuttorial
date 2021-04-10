package librarry;

import java.io.*;
import java.util.Objects;
import java.util.regex.Pattern;

public class FileFilterFactory {
	public static void main(String[] args) {
		for (File file : Objects.requireNonNull(new File("src/main/resources/").listFiles(FileFilterFactory.createInnerTextFilter(".*Hello.*")))) {
			System.out.println(file.getAbsolutePath());
		}
	}
	
	public static FilenameFilter createInnerTextFilter(String regex) {
		return (x, y) -> {
			boolean result = false;
			Pattern pattern = Pattern.compile(regex);
			File item = new File(x.getAbsolutePath() + "\\" + y);
			System.out.println(item);
			if (!item.isDirectory()) {
				try {
					BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(item)));
					for (String line = reader.readLine(); line != null; line = reader.readLine()) {
						if (pattern.matcher(line).matches()) {
							return true;
						}
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return result;
		};
	}
	
}

package librarry;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

public class Searcher {
	
	public static List<File> searchLocal(File dir, String regex) {
		return Arrays.asList(Objects.requireNonNull(dir.listFiles(FileFilterFactory.createInnerTextFilter(regex))));
	}
	
	public static List<File> searchTree(File startDir, String regex) {
		List<File> result = new ArrayList<>(Arrays.asList(Objects.requireNonNull(
				 startDir.listFiles(FileFilterFactory.createInnerTextFilter(regex)))));
		
		for (File item : Objects.requireNonNull(startDir.listFiles())) {
			if (item.isDirectory()) {
				result.addAll(searchTree(item, regex));
			}
		}
		return result;
	}
	
	public static List<String> getLinesFromTree(File startDir, String regex) {
		List<String> result = new ArrayList<>();
		for (File item : Objects.requireNonNull(startDir.listFiles())) {
			if (!item.isDirectory()) {
				try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(item)))) {
					for (String line = reader.readLine(); line != null; line = reader.readLine()) {
						if (Pattern.compile(regex).matcher(line).matches()) {
							result.add(item.getAbsolutePath()+" : \n"+line);
						}
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				result.addAll(getLinesFromTree(item,regex));
			}
		}
		return result;
	}
}

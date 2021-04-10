import librarry.Searcher;
import librarry.Console;

import java.io.*;

public class Main {
	public static void main(String[] args){
//        BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/test.txt"));
//		Searcher.searchTree(new File("src/main/"), ".*Hello.*").forEach(System.out::println);
		Searcher.getLinesFromTree(new File("src/main/"),".*Hello.*").forEach(System.out::println);
		Console.navigation("-r src/main/java/resources src/Relocate");
	}
}

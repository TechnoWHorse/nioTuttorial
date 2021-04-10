package tasks;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class Console {
	public static void main(String[] args) {
		Console.navigation("-r src/main/resources/renamed.png src/main/java/resources/Relocate.png");
	}
	public static void usage() {
		System.out.println(
				 """
						  create directory:
						  path...
						  delete directory:
						  -d path...
						  rename directory:
						  -r path path
						   """
		);
		System.exit(1);
	}
	
	// rename or relocate -r path path
	// delete -d path...
	// create dirs path...
	public static void navigation(String command) {
		String[] commands = command.split(" ");
		System.out.println(Arrays.toString(commands));
		
		if (commands.length < 1) {
			usage();
		}
		if (commands[0].equals("-r")) {
			if (commands.length != 3) usage();
			File source = new File(commands[1]);
			File target = new File(commands[2]);
			System.out.println(source.renameTo(target));
			System.out.println("renaming complete");
			return;
		}
		
		boolean dellMark = false;
		int count = 0;
		if (commands[0].equals("-d")) {
			dellMark = true;
			count++;
		}
		for (; count < commands.length; count++) {
			File path = new File(commands[count]);
			if (path.exists()) {
				System.out.println(path + " is exist");
				if (dellMark) {
					System.out.println(path.delete() ? path.getAbsolutePath() + " deleted" : "deleting is failed");
				}
			} else {
				System.out.println(path.mkdirs() ? path.getAbsolutePath() + " created" : "creating if failed");
			}
		}
		
	}
}

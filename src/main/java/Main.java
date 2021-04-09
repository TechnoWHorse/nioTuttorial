import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/test.txt"));

        for (String line = reader.readLine(); line != null; line = reader.readLine()) {
            System.out.println(line + " 1");
        }
        reader.reset();

        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line + " 2");
        }
    }
}

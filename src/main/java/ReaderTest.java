import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReaderTest {
    public static void main(String[] args) throws IOException {
//        FileReader fileReader = new FileReader(new File("src/main/resources/test.txt"));
        BufferedReader reader = new BufferedReader(new InputStreamReader(Files.newInputStream(Paths.get("src/main/resources/test.txt"))));
        String temp = reader.readLine();
        while (temp!=null){
            System.out.println(temp);
            temp=reader.readLine();
        }

        System.out.println("end");
    }

}

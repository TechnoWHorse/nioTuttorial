import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        String stringSPath = "src/main/resources/test.txt";
        String stringTPath = "src/main/resources/test2.txt";
        Path sourcePath = Paths.get(stringSPath);
        Path targetPath = Paths.get(stringTPath);

        File file = new File(stringSPath);

        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream("Hello world!".getBytes());
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();


        Files.copy(byteArrayInputStream, targetPath, StandardCopyOption.REPLACE_EXISTING);
        Files.copy(targetPath, byteArrayOutputStream);

        System.out.println(byteArrayOutputStream.toString());


        InputStream is = Files.newInputStream(targetPath, StandardOpenOption.READ);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));
        List<String> list = new ArrayList<>();
        String temp =bufferedReader.readLine();
        while (temp!=null){
            temp = bufferedReader.readLine();
            list.add(temp);
            System.out.println(temp);
        }
        System.out.println(list);
    }
}

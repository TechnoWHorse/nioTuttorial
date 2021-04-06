import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.regex.Pattern;

public class DirExpresionFinder {
    public static void main(String[] args) throws IOException {
        String[] arg = {".*Hello.*"};
        System.out.println(arg[0]);
        DirExpresionFinder.getDir(arg);

    }

    public static void getDir(String[] args) throws IOException {
        File path = new File("src/main/resources/");
        System.out.println(path.getAbsolutePath() + "\n");
        String[] list;

        if (args.length == 0) {
            list = path.list();
        } else {
            list = path.list(new FilenameFilter() {
                private final Pattern pattern = Pattern.compile(args[0]);
                private Path fileAbsolutePath;


                @Override
                public boolean accept(File dir, String name) {
                    boolean result = false;
                    fileAbsolutePath = Paths.get(dir.getAbsolutePath() + "\\" + name);
                    try {
                        BufferedReader reader = new BufferedReader(new InputStreamReader(Files.newInputStream(fileAbsolutePath)));
                        String stringFromReader = reader.readLine();
                        while (stringFromReader != null) {
//                            System.out.println(stringFromReader);
                            if (pattern.matcher(stringFromReader).matches()) {
                                result = true;
                            }
                            stringFromReader = reader.readLine();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    return result;
                }
            });
//            list = path.list((x,y)->{
//                return Pattern.compile(args[0]).matcher(y).matches();
//            });
        }

        Arrays.sort(list, String.CASE_INSENSITIVE_ORDER);
        for (String dirItem : list) {
            System.out.println(dirItem);
        }
    }
}

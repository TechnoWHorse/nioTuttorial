package tasks;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Pattern;

public class SortedDirList {
    File path;
    private long sumPathSize;

    public SortedDirList(String path) {
        this.path = new File(path);
    }

    public static void main(String[] args) {
        SortedDirList sortedDirList = new SortedDirList("src/main/resources");
        for (File arg : sortedDirList.getFileList(".*.txt")) {
            System.out.println(arg);
        }
        System.out.println(sortedDirList.sumPathSize);
    }

    public String[] list() {
        return path.list();
    }

    public String[] list(String regex) {
        return path.list((x, y) -> Pattern.compile(regex).matcher(y).matches());
    }

    public File[] getFileList(String regex) {
        return path.listFiles((x, y) -> {
            if (Pattern.compile(regex).matcher(y).matches()) {
                Path itemPath = Paths.get(new File(x, y).getAbsolutePath());
                try {
                    System.out.println(Files.size(itemPath));
                    sumPathSize += Files.size(itemPath);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return true;
            }
            return false;
        });
    }

}

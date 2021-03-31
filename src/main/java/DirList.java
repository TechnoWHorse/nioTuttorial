import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.regex.Pattern;

public class DirList {
    public static void main(String[] args) {
        String[] arg = {".*.txt"};
        System.out.println(arg[0]);
        DirList.getDir(arg);

    }

    public static void getDir(String[] args) {
        File path = new File("src/main/resources");
        System.out.println(path.getAbsolutePath());
        System.out.println(path.list());
        String[] list;
        if (args.length == 0) {
            list = path.list();
        } else {
            list = path.list(new DirFilter(args[0]));
        }
        Arrays.sort(list, String.CASE_INSENSITIVE_ORDER);
        for (String dirItem : list) {
            System.out.println(dirItem);
        }
    }
}

class DirFilter implements FilenameFilter {
    public DirFilter(String regex) {
        pattern = Pattern.compile(regex);
    }

    private Pattern pattern;

    @Override
    public boolean accept(File dir, String name) {
        return pattern.matcher(name).matches();
    }
}

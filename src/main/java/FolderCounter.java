import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

public class FolderCounter {
    private final FolderTreeCortege result = new FolderTreeCortege();

    public static void main(String[] args) {
        for (File item : new FolderCounter().getFoldersAndFiles(new File("."),".*txt")) {
            System.out.println(item.getAbsolutePath());
        }
        for(File item: new FolderCounter().getFileList(new File("src/main/resources/" ),".*.txt")){
            System.out.println(item.getAbsolutePath());
        }
    }

    public File[] getFileList(File path, String regex) {
        return path.listFiles((x, y) ->
                Pattern.compile(regex).matcher(y).matches()
        );
    }

    public void addFiles(File path) {
        for (File item : Objects.requireNonNull(path.listFiles())) {
            if (item.isDirectory()) {
                result.folderList.add(item);
                addFiles(item);
            } else {
                result.filesList.add(item);
            }
        }
    }

    public void addFiles(File path, String regex) {
        for (File item : Objects.requireNonNull(path.listFiles())) {
            if (item.isDirectory()) {
                result.folderList.add(item);
                addFiles(item,regex);
            } else {
                if (Pattern.compile(regex).matcher(item.getName()).matches())
                result.filesList.add(item);
            }
        }
    }

    public FolderTreeCortege getFoldersAndFiles(File file, String regex) {
        addFiles(file, regex);
        return result;
    }

    public FolderTreeCortege getFoldersAndFiles(File file) {
        addFiles(file);
        return result;
    }
}

class FolderTreeCortege implements Iterable<File> {
    public final List<File> filesList = new ArrayList<>();
    public final List<File> folderList = new ArrayList<>();

    @Override
    public Iterator<File> iterator() {
        return filesList.iterator();
    }
}
package FileSystemProblem.WithComposite;

import java.util.ArrayList;
import java.util.List;

public class Directory implements FileSystem{
    String directoryName;
    List<FileSystem> fileSystemList;

    public Directory(String directoryName){
        this.directoryName = directoryName;
        this.fileSystemList = new ArrayList<>();
    }

    public void add(FileSystem fileSystemObj){
        fileSystemList.add(fileSystemObj);
    }

    public void remove(FileSystem fileSystemObj){
        fileSystemList.remove(fileSystemObj);
    }

    @Override
    public void ls() {
        System.out.println("Directory name " + directoryName);
        for (FileSystem fileSystemObj : fileSystemList){
            fileSystemObj.ls();
        }
    }
}

package edu.umb.cs680.hw09.fat;

import edu.umb.cs680.hw09.fs.FSElement;
import edu.umb.cs680.hw09.fs.FileSystem;

import java.util.LinkedList;

public class FAT extends FileSystem {
    @Override
    protected FSElement createDefaultRoot() {
        return (FSElement) (new FatDirectory(null, "CDrive"));
    }

    public FAT(String name, int capacity) {
        this.initFileSystem(name, capacity);
    }

    public void addRootDirectory(FatDirectory directory) {
        this.setRoot((FSElement) directory);
    }

    public LinkedList<FatDirectory> getDrives() {
        LinkedList<FatDirectory> rootDirs = new LinkedList<>();
        for(FSElement f:this.rootDirs) {
            rootDirs.add((FatDirectory) f);
        }
        return rootDirs;
    }
}

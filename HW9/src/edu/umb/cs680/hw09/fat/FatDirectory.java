package edu.umb.cs680.hw09.fat;

import java.time.LocalDateTime;
import java.util.LinkedList;

public class FatDirectory extends FatElement {
    private LinkedList<FatElement> children;
    private LinkedList<FatDirectory> subDirectories;
    private LinkedList<FatFile> files;

    public FatDirectory(FatDirectory parent, String name) {
        super(parent, name, 0, LocalDateTime.now());
        children = new LinkedList<>();
        subDirectories = new LinkedList<>();
        files = new LinkedList<>();
        if(parent != null)
        parent.addChild(this);
    }

    public void addChild(FatDirectory directory) {
        children.add((FatElement) directory);
        subDirectories.add(directory);
    }

    public void addChild(FatFile file) {
        children.add((FatElement) file);
        files.add(file);
    }

    public LinkedList<FatElement> getChildren() {
        return children;
    }

    public LinkedList<FatDirectory> getSubDirectories() {
        return subDirectories;
    }

    public LinkedList<FatFile> getFiles() {
        return files;
    }

    @Override
    public boolean isDirectory() {
        return true;
    }

    @Override
    public int getSize() {
        int totalSize = 0;
        for(FatFile file:files) {
            totalSize += file.getSize();
        }
        for(FatDirectory directory:subDirectories) {
            totalSize += directory.getSize();
        }
        return totalSize;
    }
}

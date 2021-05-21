package edu.umb.cs680.HW7;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class Directory extends FSElement {
    private LinkedList<FSElement> children = new LinkedList<>();
    private LinkedList<Directory> subdirectories = new LinkedList<>();
    private LinkedList<File> files = new LinkedList<>();

    public Directory(Directory parent, String name, LocalDateTime creationTime) {
        super(parent, name, 0, creationTime);
        if (parent != null) {
            parent.appendChildren(this);
        }
    }

    public LinkedList<FSElement> getChildren() {
        return children;
    }

    public LinkedList<Directory> getSubDirectories() { return this.subdirectories; }

    public LinkedList<File> getFiles() { return this.files; }

    public void appendChildren(FSElement child) {
        this.children.add(child);
        if(child.isDirectory()) {
            this.subdirectories.add((Directory) child);
        } else {
            this.files.add((File) child);
        }
    }

    public int countChildren() {
        return this.children.size();
    }

    public int getTotalSize() {
        int totalSize = 0;
        /*Set<File> fileSet = new HashSet<>();
        for(Directory d : subdirectories) {
            for(File f : d.getFiles()) {
                if (!fileSet.contains(f)) {
                    fileSet.add(f);
                }
            }
        }
        for(File f: files) {
            if (!fileSet.contains(f)) {
                fileSet.add(f);
            }
        }
        for(File f : fileSet) {
            totalSize += f.getSize();
        }*/
        for(File f : files) {
            totalSize += f.getSize();
        }
        for(Directory d : subdirectories) {
            totalSize += d.getTotalSize();
        }
        return totalSize;
    }

    protected void appendSubDirectory(Directory subdirectory) {
        this.subdirectories.add(subdirectory);
    }

    protected void appendFile(File file) {
        this.files.add(file);
    }

    @Override
    public boolean isDirectory() {
        return true;
    }
}

package edu.umb.cs680.hw10.fs;

import java.util.LinkedList;

public abstract class FileSystem {
    protected String name;
    protected int capacity;
    protected int id;
    protected LinkedList<FSElement> rootDirs;

    //protected static FileSystem fsInstance = null;
    //protected LinkedList<Directory> rootDirs = new LinkedList<>();

    public FSElement initFileSystem(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
        FSElement root = createDefaultRoot();
        if(root.isDirectory() && capacity >= root.getSize()) {
            this.setRoot(root);
            this.id = root.hashCode();
            return root;
        } else return null;
    }

    protected abstract FSElement createDefaultRoot();

    protected void setRoot(FSElement root) {
        if(rootDirs == null) rootDirs = new LinkedList<>();
        this.rootDirs.add(root); }

    public int getCapacity() { return capacity; }
    public String getName() { return name; }
    public int getId() { return id; }
    public int usedCapacity() {
        int size = 0;
        for (FSElement root: rootDirs) {
            size=+ root.getSize();
        }
        return size;
    }
}
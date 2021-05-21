package edu.umb.cs680.HW7;

import java.time.LocalDateTime;
import java.util.LinkedList;

public class FileSystem {
    private static FileSystem fsInstance = null;
    private LinkedList<Directory> rootDirs = new LinkedList<>();

    private FileSystem() {
    }

    public static FileSystem getFileSystem() {
        if (fsInstance == null) {
            fsInstance = new FileSystem();
        }
        return fsInstance;
    }

    public void appendRootDir(Directory root) {
        rootDirs.add(root);
    }

    public LinkedList<Directory> getRootDirs() {
        return rootDirs;
    }

    public static void resetFileSystem() {
        fsInstance = null;
    }

    private static void printNames(Directory root) {
        for(File file:root.getFiles()) {
            System.out.println(file.getName());
        }
        for(Directory directory:root.getSubDirectories()) {
            System.out.println(directory.getName());
            printNames(directory);
        }
    }

    public static void main(String[] args) {
        FSElement root = new Directory(null, "root", LocalDateTime.now());
        FSElement home = new Directory((Directory) root, "home", LocalDateTime.now());
        FSElement applications = new Directory((Directory) root, "applications", LocalDateTime.now());
        FSElement code = new Directory((Directory) home, "code", LocalDateTime.now());
        FSElement pics = new Directory((Directory) home, "pics", LocalDateTime.now());
        FSElement a = new File((Directory) applications, "a", 10, LocalDateTime.now());
        FSElement b = new File((Directory) home, "b", 20, LocalDateTime.now());
        FSElement c = new File((Directory) code, "c", 30, LocalDateTime.now());
        FSElement d = new File((Directory) code, "d", 40, LocalDateTime.now());
        FSElement e = new File((Directory) pics, "e", 50, LocalDateTime.now());
        FSElement f = new File((Directory) pics, "f", 60, LocalDateTime.now());
        FileSystem.printNames((Directory) root);
    }
}
package edu.umb.cs680.hw09.apfs;

import edu.umb.cs680.hw09.fs.FSElement;
import edu.umb.cs680.hw09.fs.FileSystem;

import java.time.LocalDateTime;
import java.util.LinkedList;

public class APFS extends FileSystem {

    @Override
    protected FSElement createDefaultRoot() {
        return (FSElement) (new ApfsDirectory(null, "rootDir", "root"));
    }

    public APFS(String name, int capacity) {
        this.initFileSystem(name, capacity);
    }

    public void addRootDirectory(ApfsDirectory directory) {
        this.setRoot((FSElement) directory);
    }

    public LinkedList<ApfsDirectory> getRootDirs() {
        LinkedList<ApfsDirectory> rootDirs = new LinkedList<>();
        for(FSElement f:this.rootDirs) {
            rootDirs.add((ApfsDirectory) f);
        }
        return rootDirs;
    }

    private static void printNames(ApfsDirectory root) {
        for(ApfsFile file:root.getFiles()) {
            System.out.println(file.getName());
        }
        for(ApfsLink link:root.getLinks()) {
            System.out.println(link.getName());
        }
        for(ApfsDirectory directory:root.getSubdirectories()) {
            System.out.println(directory.getName());
            printNames(directory);
        }
    }

    public static void main(String[] args) {
        APFS fileSystem = new APFS("testFileSystem", 10000);
        ApfsDirectory rootDirectory = fileSystem.getRootDirs().getFirst();
        ApfsDirectory home = new ApfsDirectory(rootDirectory, "home", "root");
        ApfsDirectory applications = new ApfsDirectory(rootDirectory, "applications", "apps");
        ApfsDirectory code = new ApfsDirectory(home, "code", "user");
        ApfsDirectory pics = new ApfsDirectory(home, "pics", "user");
        ApfsFile a = new ApfsFile(applications, "a", 10, "apps");
        ApfsFile b = new ApfsFile(home, "b", 20, "user");
        ApfsFile c = new ApfsFile(code, "c", 30, "user");
        ApfsFile d = new ApfsFile(code, "d", 40, "user");
        ApfsFile e = new ApfsFile(pics, "e", 50, "user");
        ApfsFile f = new ApfsFile(pics, "f", 60, "user");
        ApfsLink x = new ApfsLink(home, "x", "root", (ApfsElement) applications);
        ApfsLink y = new ApfsLink(code, "y", "root", (ApfsElement) a);
        APFS.printNames(rootDirectory);
    }
}

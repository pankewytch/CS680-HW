package edu.umb.cs680.hw12.apfs;

import edu.umb.cs680.hw12.comparators.AlphabeticalOrderComparator;
import edu.umb.cs680.hw12.comparators.ApfsElementComparator;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

public class ApfsDirectory extends ApfsElement {
    private LinkedList<ApfsElement> children = new LinkedList<>();
    private LinkedList<ApfsDirectory> subdirectories = new LinkedList<>();
    private LinkedList<ApfsFile> files = new LinkedList<>();
    private LinkedList<ApfsLink> links = new LinkedList<>();

    public ApfsDirectory(ApfsDirectory parent, String name, String ownersName) {
        super(parent, name, 0, LocalDateTime.now(), ownersName, LocalDateTime.now());
        if(parent != null) parent.appendChildren(this);
    }

    public void appendChildren(ApfsDirectory directory) {
        children.add((ApfsElement) directory);
        subdirectories.add(directory);
    }

    public void appendChildren(ApfsFile file) {
        children.add((ApfsElement) file);
        files.add(file);
    }

    public void appendChildren(ApfsLink link) {
        children.add((ApfsElement) link);
        links.add(link);
    }

    public LinkedList<ApfsDirectory> getSubdirectories() {
        ApfsElementComparator comparator = new AlphabeticalOrderComparator();
        Collections.sort(subdirectories, comparator);
        return subdirectories;
    }

    public LinkedList<ApfsFile> getFiles() {
        ApfsElementComparator comparator = new AlphabeticalOrderComparator();
        Collections.sort(files, comparator);
        return files;
    }

    public LinkedList<ApfsLink> getLinks() {
        ApfsElementComparator comparator = new AlphabeticalOrderComparator();
        Collections.sort(links, comparator);
        return links;
    }

    @Override
    public int getSize() {
        int totalSize = 0;

        for(ApfsFile f:files) {
            totalSize += f.getSize();
        }

        for(ApfsDirectory d:subdirectories){
            totalSize += d.getSize();
        }
        return totalSize;
    }

    public LinkedList<ApfsElement> getChildren() {
        ApfsElementComparator comparator = new AlphabeticalOrderComparator();
        Collections.sort(children, comparator);
        return children;
    }

    public boolean isDirectory() { return true; }

    public boolean isLink() { return false; }

    public LinkedList<ApfsElement> getChildren(ApfsElementComparator comparator) {
        Collections.sort(children, comparator);
        return children;
    }

    public LinkedList<ApfsDirectory> getSubdirectories(ApfsElementComparator comparator) {
        Collections.sort(subdirectories, comparator);
        return subdirectories;
    }

    public LinkedList<ApfsFile> getFiles(ApfsElementComparator comparator) {
        Collections.sort(files, comparator);
        return files;
    }

    public LinkedList<ApfsLink> getLinks(ApfsElementComparator comparator) {
        Collections.sort(links, comparator);
        return links;
    }
}

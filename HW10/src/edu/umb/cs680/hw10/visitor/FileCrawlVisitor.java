package edu.umb.cs680.hw10.visitor;

import edu.umb.cs680.hw10.apfs.ApfsDirectory;
import edu.umb.cs680.hw10.apfs.ApfsElement;
import edu.umb.cs680.hw10.apfs.ApfsFile;
import edu.umb.cs680.hw10.apfs.ApfsLink;

import java.util.LinkedList;

public class FileCrawlVisitor implements ApfsVisitor {
    private LinkedList<ApfsFile> apfsFiles;


    public FileCrawlVisitor() {
        apfsFiles = new LinkedList<>();
    }

    public LinkedList<ApfsFile> getApfsFiles() { return apfsFiles; }

    @Override
    public void visit(ApfsLink link) {
        return;
    }

    @Override
    public void visit(ApfsFile file) {
        apfsFiles.add(file);
    }

    @Override
    public void visit(ApfsDirectory directory) {
        return;
    }
}

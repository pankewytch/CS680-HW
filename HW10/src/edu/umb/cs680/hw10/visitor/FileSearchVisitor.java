package edu.umb.cs680.hw10.visitor;

import edu.umb.cs680.hw10.apfs.ApfsDirectory;
import edu.umb.cs680.hw10.apfs.ApfsFile;
import edu.umb.cs680.hw10.apfs.ApfsLink;

import java.util.LinkedList;

public class FileSearchVisitor implements ApfsVisitor {
    private LinkedList<ApfsFile> matchingFiles;
    private String queriedFileName;

    public FileSearchVisitor(String queriedFileName) {
        this.queriedFileName = queriedFileName;
        matchingFiles = new LinkedList<>();
    }

    @Override
    public void visit(ApfsDirectory directory) {
        return;
    }

    @Override
    public void visit(ApfsLink link) {
        return;
    }

    @Override
    public void visit(ApfsFile file) {
        if(file.getName().equals(queriedFileName)) matchingFiles.add(file);
    }

    public LinkedList<ApfsFile> getMatchingFiles() {
        return matchingFiles;
    }
}

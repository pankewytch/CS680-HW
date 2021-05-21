package edu.umb.cs680.hw10.visitor;

import edu.umb.cs680.hw10.apfs.ApfsDirectory;
import edu.umb.cs680.hw10.apfs.ApfsFile;
import edu.umb.cs680.hw10.apfs.ApfsLink;

public class CountingVisitor implements ApfsVisitor{
    private int linksFound;
    private int dirsFound;
    private int filesFound;

    public CountingVisitor() {
        linksFound = 0;
        dirsFound = 0;
        filesFound = 0;
    }

    public void resetCounting() {
        linksFound = 0;
        dirsFound = 0;
        filesFound = 0;
    }

    public int getCount() {
        return linksFound+dirsFound+filesFound;
    }

    public int getLinkCount() { return linksFound; }

    public int getDirsCount() { return dirsFound; }

    public int getFileCount() { return filesFound; }

    @Override
    public void visit(ApfsFile file) {
        filesFound++;
    }

    @Override
    public void visit(ApfsLink link) {
        linksFound++;
    }

    @Override
    public void visit(ApfsDirectory directory) {
        dirsFound++;
    }
}

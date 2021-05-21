package edu.umb.cs680.hw10.visitor;

import edu.umb.cs680.hw10.apfs.ApfsDirectory;
import edu.umb.cs680.hw10.apfs.ApfsFile;
import edu.umb.cs680.hw10.apfs.ApfsLink;

public interface ApfsVisitor {
    public void visit(ApfsDirectory directory);
    public void visit(ApfsFile file);
    public void visit(ApfsLink link);
}

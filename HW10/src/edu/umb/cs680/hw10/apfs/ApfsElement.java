package edu.umb.cs680.hw10.apfs;


import edu.umb.cs680.hw10.fs.FSElement;
import edu.umb.cs680.hw10.visitor.ApfsVisitor;

import java.time.LocalDateTime;

public abstract class ApfsElement extends FSElement {
    private LocalDateTime lastModified;
    private String ownersName;

    public ApfsElement(ApfsDirectory parent, String name, int size, LocalDateTime creationTime, String ownersName, LocalDateTime lastModified) {
        super((FSElement) parent, name, size, creationTime);
        this.ownersName = ownersName;
        this.lastModified = lastModified;
    }

    public void modifyThisApfsElement() { this.lastModified = LocalDateTime.now(); }

    @Override
    public ApfsElement getParent() {
        return (ApfsElement) this.parent;
    }

    public abstract boolean isLink();

    public abstract void accept(ApfsVisitor visitor);
}

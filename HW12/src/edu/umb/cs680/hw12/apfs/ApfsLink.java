package edu.umb.cs680.hw12.apfs;

import java.time.LocalDateTime;

public class ApfsLink extends ApfsElement {
    private ApfsElement target;

    public ApfsLink(ApfsDirectory parent, String name, String ownersName, ApfsElement target) {
        super(parent, name, 0, LocalDateTime.now(), ownersName, LocalDateTime.now());
        this.target = target;
        parent.appendChildren(this);
    }

    public ApfsElement getTarget() { return this.target; }

    public void changeTarget(ApfsElement newTarget) {
        this.target = newTarget;
        this.modifyThisApfsElement();
    }

    public boolean isDirectory() { return false; }

    public boolean isLink() { return true; }
}

package edu.umb.cs680.hw08;

import java.time.LocalDateTime;

public class Link extends FSElement {
    private FSElement target;

    public Link(Directory parent, String name, LocalDateTime creationTime, FSElement target) {
        super(parent, name, 0, creationTime);
        parent.appendChildren(this);
        this.target = target;
    }

    public FSElement getTarget() { return target;}
    @Override
    public boolean isDirectory() { return false; }

    @Override
    public boolean isLink() { return true; }
}

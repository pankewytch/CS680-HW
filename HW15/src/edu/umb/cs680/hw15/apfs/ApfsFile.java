package edu.umb.cs680.hw15.apfs;

import java.time.LocalDateTime;

public class ApfsFile extends ApfsElement {

    public ApfsFile(ApfsDirectory parent, String name, int size, String ownersName) {
        super(parent, name, size, LocalDateTime.now(), ownersName, LocalDateTime.now());
        parent.appendChildren(this);
    }

    public boolean isDirectory() { return false; }


    public boolean isLink() { return false; }
}
package edu.umb.cs680.hw10.fs;

import java.time.LocalDateTime;

public abstract class FSElement {
    protected FSElement parent;
    protected String name;
    protected int size;
    protected LocalDateTime creationTime;

    public FSElement(FSElement parent, String name, int size, LocalDateTime creationTime){
        this.name = name;
        this.parent = parent;
        this.size = size;
        this.creationTime = creationTime;
    }

    public FSElement getParent() {
        return this.parent;
    }

    public int getSize() {
        return this.size;
    }

    public String getName() {
        return this.name;
    }

    public LocalDateTime getCreationTime() {
        return this.creationTime;
    }

    public abstract boolean isDirectory();
}
package edu.umb.cs680.hw09.fat;

import edu.umb.cs680.hw09.fs.FSElement;

import java.time.LocalDateTime;

public abstract class FatElement extends FSElement {

    public FatElement(FatDirectory parent, String name, int size, LocalDateTime creationTime) {
        super((FSElement) parent, name, size, creationTime);
        if(name.length() > 11) throw new IllegalArgumentException("Name can only be 11 chars long!");
    }

    @Override
    public FatElement getParent() {
        return (FatElement) this.parent;
    }

}

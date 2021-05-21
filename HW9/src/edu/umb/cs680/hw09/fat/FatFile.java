package edu.umb.cs680.hw09.fat;

import java.time.LocalDateTime;

public class FatFile extends FatElement {

    public FatFile(FatDirectory parent, String name, int size) {
        super(parent, name, size, LocalDateTime.now());
        parent.addChild(this);
    }

    @Override
    public boolean isDirectory() {
        return false;
    }
}

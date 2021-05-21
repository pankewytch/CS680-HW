package edu.umb.cs680.hw15.apfs;

import edu.umb.cs680.hw15.fs.FSElement;

import java.time.LocalDateTime;

public abstract class ApfsElement extends FSElement {
    private LocalDateTime lastModified;
    private String ownersName;
    private static ApfsElementComparator alphabetical = (ApfsElement element1, ApfsElement element2) -> {
        return element1.getName().compareTo(element2.getName());
    };
    private static ApfsElementComparator reverseAlphabetical = (ApfsElement element1, ApfsElement element2) -> {
        return element2.getName().compareTo(element1.getName());
    };
    private static ApfsElementComparator timeStamp = (ApfsElement element1, ApfsElement element2) -> {
        return element2.getCreationTime().compareTo(element1.getCreationTime());
    };

    public static ApfsElementComparator getAlphabetical() {
        return alphabetical;
    }

    public static ApfsElementComparator getReverseAlphabetical() {
        return reverseAlphabetical;
    }

    public static ApfsElementComparator getTimeStamp() {
        return timeStamp;
    }

    public static ApfsElementComparator getElementComparator() {
        return elementComparator;
    }

    private static ApfsElementComparator elementComparator = (ApfsElement element1, ApfsElement element2) -> {
        if (element1.isDirectory()) {
            if (element2.isDirectory()) return 0;
            else return -1;
        } else if (element2.isDirectory()) return 1;
        else if (!element1.isLink()) {
            if (!element2.isLink()) return 0;
            else return -1;
        } else {
            if (element2.isLink()) return 0;
            else return 1;
        }
    };

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
}

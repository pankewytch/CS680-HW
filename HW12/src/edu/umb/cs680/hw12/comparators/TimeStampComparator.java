package edu.umb.cs680.hw12.comparators;

import edu.umb.cs680.hw12.apfs.ApfsElement;

import java.util.Comparator;

public class TimeStampComparator implements ApfsElementComparator {
    public int compare(ApfsElement element1, ApfsElement element2) {
        return element2.getCreationTime().compareTo(element1.getCreationTime());
    }
}

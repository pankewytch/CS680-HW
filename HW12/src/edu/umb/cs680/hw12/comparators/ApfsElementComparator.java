package edu.umb.cs680.hw12.comparators;

import edu.umb.cs680.hw12.apfs.ApfsElement;

import java.util.Comparator;

public interface ApfsElementComparator extends Comparator<ApfsElement> {
    public int compare(ApfsElement element1, ApfsElement element2);
}

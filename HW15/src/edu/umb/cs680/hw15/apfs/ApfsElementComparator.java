package edu.umb.cs680.hw15.apfs;

import java.util.Comparator;

public interface ApfsElementComparator extends Comparator<ApfsElement> {
    public int compare(ApfsElement element1, ApfsElement element2);
}

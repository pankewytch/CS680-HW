package edu.umb.cs680.hw12.comparators;

import edu.umb.cs680.hw12.apfs.ApfsElement;

import java.util.Comparator;

public class ElementBasesComparator implements ApfsElementComparator {
    public int compare(ApfsElement element1, ApfsElement element2) {
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
    }
}

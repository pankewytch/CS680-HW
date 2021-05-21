package edu.umb.cs680.hw15;

import static org.junit.jupiter.api.Assertions.*;
import edu.umb.cs680.hw15.apfs.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

public class LambdaComparatorTest {
    private APFS fileSystem = null;
    private ApfsDirectory originalRootDir = null;

    private void createFileSystem() {
        APFS fileSystem = new APFS("testFileSystem", 10000);
        ApfsDirectory rootDirectory = fileSystem.getRootDirs().getFirst();
        ApfsDirectory home = new ApfsDirectory(rootDirectory, "home", "root");
        ApfsDirectory applications = new ApfsDirectory(rootDirectory, "applications", "apps");
        ApfsDirectory code = new ApfsDirectory(home, "code", "user");
        ApfsDirectory pics = new ApfsDirectory(home, "pics", "user");
        ApfsFile a = new ApfsFile(applications, "a", 10, "apps");
        ApfsFile b = new ApfsFile(home, "b", 20, "user");
        ApfsFile c = new ApfsFile(code, "c", 30, "user");
        ApfsFile d = new ApfsFile(code, "d", 40, "user");
        ApfsFile e = new ApfsFile(pics, "e", 50, "user");
        ApfsFile f = new ApfsFile(pics, "f", 60, "user");
        ApfsLink x = new ApfsLink(home, "x", "root", (ApfsElement) applications);
        ApfsLink y = new ApfsLink(code, "y", "root", (ApfsElement) a);
        ApfsFile rootFile = new ApfsFile(rootDirectory, "rootAccessFile",30, "root");
        ApfsLink rootLink = new ApfsLink(rootDirectory, "bLink", "root", e);
        this.fileSystem = fileSystem;
        this.originalRootDir = rootDirectory;
    }

    @BeforeEach
    public void testingSetup() {
        createFileSystem();
    }

    @Test
    public void AlphabeticalOrderDefaultLambdaVerification() {
        LinkedList<ApfsElement> alphabeticallyOrderedChildrenList = originalRootDir.getChildren();
        ArrayList<String> correctOrdering = new ArrayList<>();
        correctOrdering.add("applications");
        correctOrdering.add("bLink");
        correctOrdering.add("home");
        correctOrdering.add("rootAccessFile");
        int index = 0;
        for(ApfsElement element:alphabeticallyOrderedChildrenList) {
            assertEquals(correctOrdering.get(index++), element.getName());
        }
    }

    @Test
    public void AlphabeticalOrderLambdaVerification() {
        LinkedList<ApfsElement> alphabeticallyOrderedChildrenList = originalRootDir.getChildren(ApfsElement.getAlphabetical());
        ArrayList<String> correctOrdering = new ArrayList<>();
        correctOrdering.add("applications");
        correctOrdering.add("bLink");
        correctOrdering.add("home");
        correctOrdering.add("rootAccessFile");
        int index = 0;
        for(ApfsElement element:alphabeticallyOrderedChildrenList) {
            assertEquals(correctOrdering.get(index++), element.getName());
        }
    }

    @Test
    public void ReverseAlphabeticalOrderLambdaVerification() {
        LinkedList<ApfsElement> reverseAlphabeticallySortedList = originalRootDir.getChildren(ApfsElement.getReverseAlphabetical());
        ArrayList<String> correctOrdering = new ArrayList<>();
        correctOrdering.add("applications");
        correctOrdering.add("bLink");
        correctOrdering.add("home");
        correctOrdering.add("rootAccessFile");
        int index = 3;
        for(ApfsElement element:reverseAlphabeticallySortedList) {
            assertEquals(correctOrdering.get(index--), element.getName());
        }
    }

    @Test
    public void TimeStampLambdaVerification() {
        LinkedList<ApfsElement> timeStampSortedList = originalRootDir.getChildren(ApfsElement.getTimeStamp());
        ArrayList<String> correctOrdering = new ArrayList<>();
        correctOrdering.add("home");
        correctOrdering.add("applications");
        correctOrdering.add("rootAccessFile");
        correctOrdering.add("bLink");
        int index = 3;
        for(ApfsElement element:timeStampSortedList) {
            assertEquals(correctOrdering.get(index--), element.getName());
        }
    }

    @Test
    public void ElementBasesLambdaVerification() {
        LinkedList<ApfsElement> timeStampSortedList = originalRootDir.getChildren(ApfsElement.getElementComparator());
        ArrayList<String> correctOrdering = new ArrayList<>();
        correctOrdering.add("home");
        correctOrdering.add("applications");
        correctOrdering.add("rootAccessFile");
        correctOrdering.add("bLink");
        int index = 0;
        for(ApfsElement element:timeStampSortedList) {
            assertEquals(correctOrdering.get(index++), element.getName());
        }
    }

    @Test
    public void ElementBasesRawLambdaExpressionVerification() {
        LinkedList<ApfsElement> timeStampSortedList = originalRootDir.getChildren((ApfsElement element1, ApfsElement element2) -> {
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
        });
        ArrayList<String> correctOrdering = new ArrayList<>();
        correctOrdering.add("home");
        correctOrdering.add("applications");
        correctOrdering.add("rootAccessFile");
        correctOrdering.add("bLink");
        int index = 0;
        for(ApfsElement element:timeStampSortedList) {
            assertEquals(correctOrdering.get(index++), element.getName());
        }
    }

    @Test
    public void TimeStampRawLambdaExpressionVerification() {
        LinkedList<ApfsElement> timeStampSortedList = originalRootDir.getChildren((ApfsElement element1, ApfsElement element2) -> {
            return element2.getCreationTime().compareTo(element1.getCreationTime());
        });
        ArrayList<String> correctOrdering = new ArrayList<>();
        correctOrdering.add("home");
        correctOrdering.add("applications");
        correctOrdering.add("rootAccessFile");
        correctOrdering.add("bLink");
        int index = 3;
        for(ApfsElement element:timeStampSortedList) {
            assertEquals(correctOrdering.get(index--), element.getName());
        }
    }

    @Test
    public void ReverseAlphabeticalOrderRawLambdaExpressionVerification() {
        LinkedList<ApfsElement> reverseAlphabeticallySortedList = originalRootDir.getChildren((ApfsElement element1, ApfsElement element2) -> {
            return element2.getName().compareTo(element1.getName());
        });
        ArrayList<String> correctOrdering = new ArrayList<>();
        correctOrdering.add("applications");
        correctOrdering.add("bLink");
        correctOrdering.add("home");
        correctOrdering.add("rootAccessFile");
        int index = 3;
        for(ApfsElement element:reverseAlphabeticallySortedList) {
            assertEquals(correctOrdering.get(index--), element.getName());
        }
    }

    @Test
    public void AlphabeticalOrderRawLambdaExpressionVerification() {
        LinkedList<ApfsElement> alphabeticallyOrderedChildrenList = originalRootDir.getChildren(ApfsElement.getAlphabetical());
        ArrayList<String> correctOrdering = new ArrayList<>();
        correctOrdering.add("applications");
        correctOrdering.add("bLink");
        correctOrdering.add("home");
        correctOrdering.add("rootAccessFile");
        int index = 0;
        for(ApfsElement element:alphabeticallyOrderedChildrenList) {
            assertEquals(correctOrdering.get(index++), element.getName());
        }
    }
}

package edu.umb.cs680.hw10.visitor;

import edu.umb.cs680.hw10.apfs.*;
import edu.umb.cs680.hw10.visitor.FileSearchVisitor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FileSearchVisitorTest {
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
        this.fileSystem = fileSystem;
        this.originalRootDir = rootDirectory;
    }

    @BeforeEach
    public void createTestEnvironment() {
        createFileSystem();
    }

    @Test
    public void VerifyFileSearchVisitorWorks() {
        FileSearchVisitor cut = new FileSearchVisitor("a");
        originalRootDir.accept(cut);
        ApfsFile matchingFile = cut.getMatchingFiles().getFirst();
        assertEquals(10, matchingFile.getSize());
        assertEquals("a", matchingFile.getName());
        assertFalse(matchingFile.isDirectory());
        assertFalse(matchingFile.isLink());
    }

}

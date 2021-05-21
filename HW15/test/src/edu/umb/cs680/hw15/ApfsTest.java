package edu.umb.cs680.hw15;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.umb.cs680.hw15.apfs.*;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ApfsTest {
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
    public void createTestCut() {
        createFileSystem();
    }

    @Test
    public void verifyCorrectCreationOfFileSystemBasics() {
        APFS cut = this.fileSystem;
        assertEquals(1, cut.getRootDirs().size());
        assertEquals(2, originalRootDir.getChildren().size());
        assertEquals(210, originalRootDir.getSize());
    }

    @Test
    public void verifyLinkXWorkAndReturnTargets() {
        LinkedList<ApfsDirectory> rootSubDirs = originalRootDir.getSubdirectories();
        ApfsDirectory homeDir = null;
        for(ApfsDirectory d:rootSubDirs) {
            if(d.getName().equals("home")) homeDir = d;
        }
        assertEquals(1, homeDir.getLinks().size());
        ApfsLink link = homeDir.getLinks().getFirst();
        assertEquals("applications", link.getTarget().getName());
        assertEquals("x", link.getName());
    }

    @Test
    public void VerifyFSHas1RootDirFromSetup() {
        LinkedList<ApfsDirectory> rootdirs = fileSystem.getRootDirs();
        assertTrue(rootdirs.size() == 1);
    }

    @Test
    public void VerifyRootIsDirectoryFromFS() {
        //already verified that there is just 1 directory here so no need to verify that
        //as long as the flag is flipped we know this will work
        boolean isDirectory = false;
        for(ApfsDirectory d : fileSystem.getRootDirs()) {
            isDirectory = d.isDirectory();
        }
        assertTrue(isDirectory);
    }

    @Test
    public void VerifyRootHasSizeOfRootIs210() {
        assertEquals(210, ((ApfsDirectory) originalRootDir).getSize());
    }

    @Test
    public void VerifyAddingAnotherRootDirWorks() {
        ApfsElement rootdir2 = new ApfsDirectory(null, "rootdir2", "rootdir2");
        fileSystem.addRootDirectory((ApfsDirectory) rootdir2);
        LinkedList<String> rootDirNames = new LinkedList<>();
        for(ApfsDirectory d : fileSystem.getRootDirs()) {
            rootDirNames.add(d.getName());
        }
        LinkedList<String> expected = new LinkedList<>();
        expected.add("rootDir");
        expected.add("rootdir2");
        assertEquals(expected, rootDirNames);
    }

    @Test
    public void VerifyRootHas2Children() {
        assertEquals(2, ((ApfsDirectory) originalRootDir).getChildren().size());
    }

    @Test
    public void VerifyRootHasNoFilesAsChildren() {
        assertEquals(0, ((ApfsDirectory) originalRootDir).getFiles().size());
    }

    @Test
    public void VerifyAddedDirectory() {
        ApfsElement cut = new ApfsDirectory((ApfsDirectory) originalRootDir, "newDirectory", "root");
        assertEquals(3, ((ApfsDirectory) originalRootDir).getChildren().size());
    }

    @Test
    public void VerifyAddedFile() {
        ApfsElement cut = new ApfsFile((ApfsDirectory) originalRootDir, "newFile", 20, "root");
        assertEquals(3, ((ApfsDirectory) originalRootDir).getChildren().size());
        assertEquals(1, ((ApfsDirectory) originalRootDir).getFiles().size());
    }
}
package edu.umb.cs680.hw09.fat;

import edu.umb.cs680.hw09.fat.*;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.BeforeEach;

import java.util.LinkedList;

public class FatTest {
    private FAT fileSystem = null;
    private FatDirectory originalDrive = null;

    private void createFileSystem() {
        FAT fileSystem = new FAT("testFileSystem", 10000);
        FatDirectory rootDirectory = fileSystem.getDrives().getFirst();
        FatDirectory home = new  FatDirectory(rootDirectory, "home");
        FatDirectory applications = new  FatDirectory(rootDirectory, "applicatio");
        FatDirectory code = new  FatDirectory(home, "code");
        FatDirectory pics = new  FatDirectory(home, "pics");
        FatFile a = new FatFile(applications, "a", 10);
        FatFile b = new FatFile(home, "b", 20);
        FatFile c = new FatFile(code, "c", 30);
        FatFile d = new FatFile(code, "d", 40);
        FatFile e = new FatFile(pics, "e", 50);
        FatFile f = new FatFile(pics, "f", 60);
        this.fileSystem = fileSystem;
        this.originalDrive = rootDirectory;
    }

    @BeforeEach
    public void createTestCut() {
        createFileSystem();
    }

    @Test
    public void verifyCorrectCreationOfFileSystemBasics() {
        FAT cut = this.fileSystem;
        assertEquals(1, cut.getDrives().size());
        assertEquals(2, originalDrive.getChildren().size());
        assertEquals(210, originalDrive.getSize());
    }

    @Test
    public void VerifyFSHas1RootDirFromSetup() {
        LinkedList<FatDirectory> rootdirs = fileSystem.getDrives();
        assertTrue(rootdirs.size() == 1);
    }

    @Test
    public void VerifyRootIsDirectoryFromFS() {
        //already verified that there is just 1 directory here so no need to verify that
        //as long as the flag is flipped we know this will work
        boolean isDirectory = false;
        for(FatDirectory d : fileSystem.getDrives()) {
            isDirectory = d.isDirectory();
        }
        assertTrue(isDirectory);
    }

    @Test
    public void VerifyRootHasSizeOfRootIs210() {
        assertEquals(210, ((FatDirectory) originalDrive).getSize());
    }

    @Test
    public void VerifyAddingAnotherRootDirWorks() {
        FatElement rootdir2 = new FatDirectory(null, "rootdir2");
        fileSystem.addRootDirectory((FatDirectory) rootdir2);
        LinkedList<String> rootDirNames = new LinkedList<>();
        for(FatDirectory d : fileSystem.getDrives()) {
            rootDirNames.add(d.getName());
        }
        LinkedList<String> expected = new LinkedList<>();
        expected.add("CDrive");
        expected.add("rootdir2");
        assertEquals(expected, rootDirNames);
    }

    @Test
    public void VerifyRootHas2Children() {
        assertEquals(2, ((FatDirectory) originalDrive).getChildren().size());
    }

    @Test
    public void VerifyRootHasNoFilesAsChildren() {
        assertEquals(0, ((FatDirectory) originalDrive).getFiles().size());
    }

    @Test
    public void VerifyAddedDirectory() {
        FatElement cut = new FatDirectory((FatDirectory) originalDrive, "newDir");
        assertEquals(3, ((FatDirectory) originalDrive).getChildren().size());
    }

    @Test
    public void VerifyAddedFile() {
        FatFile cut = new FatFile((FatDirectory) originalDrive, "newFile", 20);
        assertEquals(3, ((FatDirectory) originalDrive).getChildren().size());
        assertEquals(1, ((FatDirectory) originalDrive).getFiles().size());
    }
}

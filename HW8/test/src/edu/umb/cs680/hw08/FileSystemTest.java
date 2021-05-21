package edu.umb.cs680.hw08;

import static org.junit.jupiter.api.Assertions.*;
import edu.umb.cs680.hw08.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import java.time.LocalDateTime;
import java.util.Dictionary;
import java.util.LinkedList;

public class FileSystemTest {
    private FSElement root;
    private FileSystem cut;

    private FSElement fsSetup() {
        FSElement root = new Directory(null, "root", LocalDateTime.now());
        FSElement home = new Directory((Directory) root, "home", LocalDateTime.now());
        FSElement applications = new Directory((Directory) root, "applications", LocalDateTime.now());
        FSElement code = new Directory((Directory) home, "code", LocalDateTime.now());
        FSElement pics = new Directory((Directory) home, "pics", LocalDateTime.now());
        FSElement a = new File((Directory) applications, "a", 10, LocalDateTime.now());
        FSElement b = new File((Directory) home, "b", 20, LocalDateTime.now());
        FSElement c = new File((Directory) code, "c", 30, LocalDateTime.now());
        FSElement d = new File((Directory) code, "d", 40, LocalDateTime.now());
        FSElement e = new File((Directory) pics, "e", 50, LocalDateTime.now());
        FSElement f = new File((Directory) pics, "f", 60, LocalDateTime.now());
        FSElement x = new Link((Directory) home, "x", LocalDateTime.now(), applications);
        FSElement y = new Link((Directory) code, "y", LocalDateTime.now(), a);
        return root;
    }

    @BeforeEach
    public void setup() {
        cut = null;
        root = null;
        FileSystem.resetFileSystem();
        cut = FileSystem.getFileSystem();
        root = fsSetup();
        cut.appendRootDir((Directory) root);
    }

    @Test
    public void VerifyFSHas1RootDirFromSetup() {
        LinkedList<Directory> rootdirs = cut.getRootDirs();
        assertTrue(rootdirs.size() == 1);
    }

    @Test
    public void VerifyRootIsDirectoryFromFS() {
        //already verified that there is just 1 directory here so no need to verify that
        //as long as the flag is flipped we know this will work
        boolean isDirectory = false;
        for(Directory d : cut.getRootDirs()) {
            isDirectory = d.isDirectory();
        }
        assertTrue(isDirectory);
    }

    @Test
    public void VerifyRootHasSizeOfRootIs210() {
        assertEquals(210, ((Directory) root).getTotalSize());
    }

    @Test
    public void VerifyFileSystemIsSingleton() {
        assertSame(cut, FileSystem.getFileSystem());
    }

    @Test
    public void VerifyAddingAnotherRootDirWorks() {
        FSElement rootdir2 = new Directory(null, "rootdir2", LocalDateTime.now());
        cut.appendRootDir((Directory) rootdir2);
        LinkedList<String> rootDirNames = new LinkedList<>();
        for(Directory d : cut.getRootDirs()) {
            rootDirNames.add(d.getName());
        }
        LinkedList<String> expected = new LinkedList<>();
        expected.add("root");
        expected.add("rootdir2");
        assertEquals(expected, rootDirNames);
    }

    @Test
    public void VerifyRootHas2Children() {
        assertEquals(2, ((Directory) root).countChildren());
    }

    @Test
    public void VerifyRootHasNoFilesAsChildren() {
        assertEquals(0, ((Directory) root).getFiles().size());
    }

    @Test
    public void VerifyAddedDirectory() {
        FSElement cut = new Directory((Directory) root, "newDirectory", LocalDateTime.now());
        assertEquals(3, ((Directory) root).getChildren().size());
    }

    @Test
    public void VerifyAddedFile() {
        FSElement cut = new File((Directory) root, "newFile", 20, LocalDateTime.now());
        assertEquals(3, ((Directory) root).getChildren().size());
        assertEquals(1, ((Directory) root).getFiles().size());
    }

    @Test
    public void VerifyLinksPointToRightTarget() {
        Directory home = null;
        for(Directory directory:((Directory) root).getSubDirectories()) {
            if(directory.getName().equals("home")) home = directory;
        }
        Directory code = null;
        for(Directory directory:home.getSubDirectories()) {
            if(directory.getName().equals("code")) code = directory;
        }
        Link x = home.getLinks().getFirst();
        Link y = code.getLinks().getFirst();
        assertTrue(x.getTarget().isDirectory());
        assertFalse(y.getTarget().isDirectory() || y.getTarget().isLink());
        assertEquals("x", x.getName());
        assertEquals("y", y.getName());
        assertEquals("applications", x.getTarget().getName());
        assertEquals("a", y.getTarget().getName());
        assertEquals(1, ((Directory) x.getTarget()).getChildren().size());
    }
}

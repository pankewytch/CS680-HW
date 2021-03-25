package edu.umb.cs680.hw06;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

public class DVDPlayerTest{

    @BeforeEach
    public void setup() {
        //This method will ensure the DVD Player is always in the initial state
        //This is needed because there is only 1 instance of DVD player
        //And the order of JUnit tests is not gaurenteed
        DVDPlayer cut = DVDPlayer.getInstance();
        cut.PlayButtonPushed();
        cut.StopButtonPushed();
    }

    @Test
    public void verifyDVDClassConstructor() {
        DVDPlayer cut = DVDPlayer.getInstance();
        assertTrue(cut instanceof DVDPlayer);
    }

    @Test
    public void verifySingleDVDPlayerInstance() {
        DVDPlayer cut1 = DVDPlayer.getInstance();
        DVDPlayer cut2 = DVDPlayer.getInstance();
        assertSame(cut1, cut2);
    }

    @Test
    public void verifyInitialDVDPlayerState() {
        DVDPlayer cut = DVDPlayer.getInstance();
        assertTrue(cut.getStatus() instanceof DrawerClosedNotPlaying);
    }

    @Test
    public void verifyStateFromInitial_PushOpen() {
        DVDPlayer cut = DVDPlayer.getInstance();
        cut.OpenClosedButtonPushed();
        assertTrue(cut.getStatus() instanceof DrawerOpen);
    }

    @Test
    public void verifyStateFromInitial_PushOpen_PushPlay() {
        DVDPlayer cut = DVDPlayer.getInstance();
        cut.OpenClosedButtonPushed();
        cut.PlayButtonPushed();
        assertTrue(cut.getStatus() instanceof DrawerClosedPlaying);
    }

    @Test
    public void verifyStateFromInitial_PushOpen_PushPlay_PushStop() {
        DVDPlayer cut = DVDPlayer.getInstance();
        cut.OpenClosedButtonPushed();
        cut.PlayButtonPushed();
        cut.StopButtonPushed();
        assertTrue(cut.getStatus() instanceof DrawerClosedNotPlaying);
    }

    @Test
    public void erifyStateFromInitial_PushPlay() {
        DVDPlayer cut = DVDPlayer.getInstance();
        cut.PlayButtonPushed();
        assertTrue(cut.getStatus() instanceof DrawerClosedPlaying);
    }

    @Test
    public void erifyStateFromInitial_PushPlay_PushOpen() {
        DVDPlayer cut = DVDPlayer.getInstance();
        cut.PlayButtonPushed();
        cut.OpenClosedButtonPushed();
        assertTrue(cut.getStatus() instanceof DrawerOpen);
    }

    @Test
    public void erifyStateFromInitial_PushPlay_PushOpen_PushClose() {
        DVDPlayer cut = DVDPlayer.getInstance();
        cut.PlayButtonPushed();
        cut.OpenClosedButtonPushed();
        cut.OpenClosedButtonPushed();
        assertTrue(cut.getStatus() instanceof DrawerClosedNotPlaying);
    }

    @Test
    public void erifyStateFromInitial_PushOpen_PushClose() {
        DVDPlayer cut = DVDPlayer.getInstance();
        cut.OpenClosedButtonPushed();
        cut.OpenClosedButtonPushed();
        assertTrue(cut.getStatus() instanceof DrawerClosedNotPlaying);
    }

    @Test
    public void erifyStateFromInitial_PushPlay_PushStop() {
        DVDPlayer cut = DVDPlayer.getInstance();
        cut.PlayButtonPushed();
        cut.StopButtonPushed();
        assertTrue(cut.getStatus() instanceof DrawerClosedNotPlaying);
    }
}
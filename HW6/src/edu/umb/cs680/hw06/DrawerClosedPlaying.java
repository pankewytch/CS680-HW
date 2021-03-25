package edu.umb.cs680.hw06;

public class DrawerClosedPlaying implements State {
    private static State instance = null;

    public static State getInstance() {
        if(instance != null) {
            return instance;
        } else {
            instance = new DrawerClosedPlaying();
            return instance;
        }
    }

    private DrawerClosedPlaying() {}
    public void OpenCloseButtonPushed(DVDPlayer player) {
        player.stop();
        player.open();
        player.changeStatus(DrawerOpen.getInstance());
    }

    public void PlayButtonPushed(DVDPlayer player) {
        //empty method, nothing should happen here
    }

    public void StopButtonPushed(DVDPlayer player) {
        player.stop();
        player.changeStatus(DrawerClosedNotPlaying.getInstance());
    }
}

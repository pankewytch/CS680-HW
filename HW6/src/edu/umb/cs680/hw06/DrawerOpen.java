package edu.umb.cs680.hw06;

public class DrawerOpen implements State {
    private static State instance = null;

    public static State getInstance() {
        if(instance != null) {
            return instance;
        } else {
            instance = new DrawerOpen();
            return instance;
        }
    }

    private DrawerOpen(){}

    public void OpenCloseButtonPushed(DVDPlayer player) {
        player.close();
        player.changeStatus(DrawerClosedNotPlaying.getInstance());
    }

    public void PlayButtonPushed(DVDPlayer player) {
        player.close();
        player.play();
        player.changeStatus(DrawerClosedPlaying.getInstance());
    }

    public void StopButtonPushed(DVDPlayer player) {
        //empty method, nothing should happen here
    }
}

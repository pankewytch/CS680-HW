package edu.umb.cs680.hw06;

public class DrawerClosedNotPlaying implements State {
    private static State instance = null;

    public static State getInstance() {
        if(instance != null) {
            return instance;
        } else {
            instance = new DrawerClosedNotPlaying();
            return instance;
        }
    }

    private DrawerClosedNotPlaying(){}
    public void OpenCloseButtonPushed(DVDPlayer player) {
        player.open();
        player.changeStatus(DrawerOpen.getInstance());
    }

    public void PlayButtonPushed(DVDPlayer player) {
        player.play();
        player.changeStatus(DrawerClosedPlaying.getInstance());
    }

    public void StopButtonPushed(DVDPlayer player) {
        //empty method, nothing should happen here
    }
}

package edu.umb.cs680.hw06;

public class DVDPlayer {
    private State status;
    private static DVDPlayer dvdPlayer = null;

    public static DVDPlayer getInstance() {
        if(dvdPlayer != null) {
            return dvdPlayer;
        } else {
            dvdPlayer = new DVDPlayer();
            return dvdPlayer;
        }
    }

    private DVDPlayer() {
        this.status = DrawerClosedNotPlaying.getInstance();
    }

    protected void changeStatus(State status) {
        this.status = status;
    }

   protected void open() {
       System.out.println("The DVD player will open");
   }

   protected void close() {
       System.out.println("The DVD player will close");
   }

   protected void play() {
       System.out.println("The DVD will play");
   }

   protected void stop() {
       System.out.println("The DVD player will stop");
   }

   public void PlayButtonPushed() {
        status.PlayButtonPushed(this);
   }

   public void StopButtonPushed() {
        status.StopButtonPushed(this);
   }

   public void OpenClosedButtonPushed() {
        status.OpenCloseButtonPushed(this);
   }

   public State getStatus() {
        return this.status;
   }
}

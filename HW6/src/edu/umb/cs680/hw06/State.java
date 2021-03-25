package edu.umb.cs680.hw06;

public interface State {

    void OpenCloseButtonPushed(DVDPlayer player);

    void PlayButtonPushed(DVDPlayer player);

    void StopButtonPushed(DVDPlayer player);
}

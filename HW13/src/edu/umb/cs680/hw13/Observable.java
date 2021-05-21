package edu.umb.cs680.hw13;

public interface Observable {
    public void addObserver(Observer observer);
    public void setChanged();
    public boolean hasChanged();
    public void clearChanged();
}

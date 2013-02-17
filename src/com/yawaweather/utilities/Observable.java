package com.yawaweather.utilities;

public interface Observable {
	
	public abstract void notify(Object object);
	
	public abstract void addObserver(Observer observer);
	
}

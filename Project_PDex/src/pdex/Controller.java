package pdex;

import java.util.ArrayList;
import java.util.Iterator;

import javafx.scene.Scene;

public abstract class Controller {

	protected Scene scene;

	private ArrayList<Observer> observers= new ArrayList<Observer>();

	public void addObserver(Observer observer){
		observers.add(observer);
	}
	public void deleteObserver(Observer observer){
		observers.remove(observer);
	}
	public void notifyObservers(){
		Iterator<Observer> iterator = observers.iterator();
		while(iterator.hasNext()){
			Observer observer = (Observer)iterator.next();
			observer.update(this);
		}
	}
}

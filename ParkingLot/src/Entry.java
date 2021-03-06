import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * The Entry class that represents the entry gates that lead into the parking lot. Entry gates will have a 
 * locked state that determines whether or not they can let cars in (which depend on the current number
 * of available spaces). 
 */
/*
 * Author by Boburmirzo Umurzakov
 */

public class Entry{
	
	private String entryID;
	private Lot parkingLot;
	private boolean locked;
	final Lock lock = new ReentrantLock();
	
	/**
	 * Entry object constructor
	 * @param id of this entry, the parking lot object it is associated with
	 */
	public Entry(String id, Lot parkingLot){
		this.parkingLot = parkingLot;
		this.entryID = id;
		this.locked = false;
		
	}

	/**
	 * Getter method for the entry ID variable
	 * @return the ID of the entry
	 */
	public String getEntryID() {
		return entryID;
	}

	/**
	 * Setter method for the entry ID
	 * @param entryID
	 */
	public void setEntryID(String entryID) {
		this.entryID = entryID;
	}

	/**
	 * Signals the parking lot this entry is associated with
	 * to check for available space
	 */
	public boolean checkLotCapacity(){
          boolean checkForCapacity=false; 
          lock.lock();
          if(parkingLot.checkForAvailableSpace()){
	    	   checkForCapacity=true;
	       }else{
	    	   checkForCapacity=false;  
	       } 
	       lock.unlock();
			return checkForCapacity;
		
	}
	
	/**
	 * Signals the parking lot of a car that has been
	 * allowed in and is about to park
	 */
	public void notifyLotOfParkedCar(){
		lock.lock();
			parkingLot.parkCar();
		lock.unlock();
	}


	/**
	 * Getter method for the locked variable
	 * @return a boolean value that determines whether the entry is locked or not
	 */
	public boolean isLocked() {
		return locked;
	}

	/**
	 * Setter method for the locked variable
	 * @param locked
	 */
	public void setLocked(boolean locked) {
	
		this.locked = locked;
	}
	
}

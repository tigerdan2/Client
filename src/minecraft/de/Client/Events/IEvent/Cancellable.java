package de.Client.Events.IEvent;

public interface Cancellable {

	boolean isCancelled();
	
	void setCancelled(boolean paramBoolean);
}

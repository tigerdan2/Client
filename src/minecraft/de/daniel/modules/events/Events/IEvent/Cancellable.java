package de.daniel.modules.events.Events.IEvent;

public interface Cancellable {

	boolean isCancelled();
	
	void setCancelled(boolean paramBoolean);
}

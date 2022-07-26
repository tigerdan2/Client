package de.daniel.modules.events.Events.IEvent.Callables;


import de.daniel.modules.events.Events.IEvent.Cancellable;
import de.daniel.modules.events.Events.IEvent.Event;

public class EventCancellable implements Event, Cancellable {

	private boolean cancelled;
	
	public boolean isCancelled() {
		return this.cancelled;
	}
	
	public void setCancelled(boolean state) {
		this.cancelled = state;
	}
}

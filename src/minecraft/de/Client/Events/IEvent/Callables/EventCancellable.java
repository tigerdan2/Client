package de.Client.Events.IEvent.Callables;

import de.Client.Events.IEvent.Cancellable;
import de.Client.Events.IEvent.Event;

public class EventCancellable implements Event, Cancellable {

	private boolean cancelled;
	
	public boolean isCancelled() {
		return this.cancelled;
	}
	
	public void setCancelled(boolean state) {
		this.cancelled = state;
	}
}

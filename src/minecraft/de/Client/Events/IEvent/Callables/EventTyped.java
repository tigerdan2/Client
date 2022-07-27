package de.Client.Events.IEvent.Callables;

import de.Client.Events.IEvent.Event;
import de.Client.Events.IEvent.Typed;

public class EventTyped implements Event, Typed {

	private final byte type;
	
	protected EventTyped(byte eventType) {
		this.type = eventType;
	}
	
	public byte getType() {
		return this.type;
	}
}

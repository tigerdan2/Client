package de.daniel.modules.events.Events.IEvent.Callables;


import de.daniel.modules.events.Events.IEvent.Event;
import de.daniel.modules.events.Events.IEvent.Typed;

public class EventTyped implements Event, Typed {

	private final byte type;
	
	protected EventTyped(byte eventType) {
		this.type = eventType;
	}
	
	public byte getType() {
		return this.type;
	}
}

package de.daniel.modules.events.Events.Events;


import de.daniel.modules.events.Events.IEvent.Event;

public class Render3DEvent implements Event {

	private float partialTicks;
	
	public Render3DEvent(float partialTicks) {
		this.partialTicks = partialTicks;
	}
	
	public float getPartialTicks() {
		return this.partialTicks;
	}
	
	public void setPartialTicks(float partialTicks) {
		this.partialTicks = partialTicks;
	}
}

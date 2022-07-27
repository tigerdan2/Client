package de.Client.Events.Events;

import de.Client.Events.IEvent.Event;

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

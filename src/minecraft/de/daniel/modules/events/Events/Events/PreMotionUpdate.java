package de.daniel.modules.events.Events.Events;


import de.daniel.modules.events.Events.IEvent.EventStoppable;

public class PreMotionUpdate extends EventStoppable {

	public double motionX;
	public double motionY;
	public double motionZ;

	public PreMotionUpdate(double motionX, double motionY, double motionZ) {
		this.motionX = motionX;
		this.motionY = motionY;
		this.motionZ = motionZ;
	}
}

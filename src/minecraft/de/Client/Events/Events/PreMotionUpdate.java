package de.Client.Events.Events;

import de.Client.Events.IEvent.EventStoppable;

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

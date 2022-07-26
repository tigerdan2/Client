package de.daniel.modules.events.Events.Events;


import de.daniel.modules.events.Events.IEvent.Event;
import de.daniel.modules.events.Events.IEvent.State;

public class UpdateEvent implements Event {

	public State state;
	public float yaw;
	public float pitch;
	public double y;
	public boolean ground;

	public UpdateEvent() {
		this.state = State.POST;
	}
	
	public UpdateEvent(double y, float yaw, float pitch, boolean ground) {
		this.state = State.PRE;
		this.yaw = yaw;
		this.pitch = pitch;
		this.y = y;
		this.ground = ground;
	}
	
	public double getY() {
		return this.y;
	}
	
	public float getYaw() {
		return this.yaw;
	}

	public float getPitch() {
		return this.pitch;
	}

	public boolean onGround() {
		return this.ground;
	}
	
	public State getState() {
		return this.state;
	}
	
	
}

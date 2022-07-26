package de.daniel.modules.events.Events.Events;

import de.daniel.modules.events.Events.IEvent.Callables.EventCancellable;

public class PreSendMotion extends EventCancellable
{
  public float yaw;
  public float pitch;

  public PreSendMotion(float yaw, float pitch)
  {
    this.yaw = yaw;
    this.pitch = pitch;
  }

  public float getPitch() {
    return this.pitch;
  }

  public float getYaw() {
    return this.yaw;
  }

  public void setPitch(float pitch) {
    this.pitch = pitch;
  }

  public void setYaw(float yaw) {
    this.yaw = yaw;
  }
}
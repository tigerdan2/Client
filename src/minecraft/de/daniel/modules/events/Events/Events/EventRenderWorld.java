package de.daniel.modules.events.Events.Events;


import de.daniel.modules.events.Events.IEvent.Event;

public class EventRenderWorld
  implements Event
{
  private float ticks;
  
  public EventRenderWorld(float ticks)
  {
    setTicks(ticks);
  }
  
  public float getTicks()
  {
    return this.ticks;
  }
  
  public void setTicks(float ticks)
  {
    this.ticks = ticks;
  }
}

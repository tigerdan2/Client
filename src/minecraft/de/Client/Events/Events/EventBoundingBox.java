package de.Client.Events.Events;

import de.Client.Events.IEvent.Event;
import net.minecraft.block.Block;
import net.minecraft.util.AxisAlignedBB;

public class EventBoundingBox
  implements Event
{
  public final Block block;
  public AxisAlignedBB boundingBox;
  public final double x;
  public final double y;
  public final double z;
  
  public EventBoundingBox(AxisAlignedBB bb, Block block, double x, double y, double z)
  {
    this.block = block;
    this.boundingBox = bb;
    this.x = x;
    this.y = y;
    this.z = z;
  }
  
  public AxisAlignedBB getBoundingBox()
  {
    return this.boundingBox;
  }
  
  public void setBoundingBox(AxisAlignedBB boundingBox)
  {
    this.boundingBox = boundingBox;
  }
  
  public Block getBlock()
  {
    return this.block;
  }
  
  public double getX()
  {
    return this.x;
  }
  
  public double getY()
  {
    return this.y;
  }
  
  public double getZ()
  {
    return this.z;
  }
}

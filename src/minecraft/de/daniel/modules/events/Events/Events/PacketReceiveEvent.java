package de.daniel.modules.events.Events.Events;



import de.daniel.modules.events.Events.IEvent.Callables.EventCancellable;
import net.minecraft.network.Packet;

public class PacketReceiveEvent extends EventCancellable {

	private Packet packet;
	
	public PacketReceiveEvent(Packet packet) {
		this.packet = packet;
	}
	
	public Packet getPacket() {
		return this.packet;
	}
	
	public void setPacket(Packet packet) {
		this.packet = packet;
	}
}

package de.Client.Events.Events;

import de.Client.Events.IEvent.Callables.EventCancellable;
import net.minecraft.network.Packet;

public class PacketSendEvent extends EventCancellable {

	private Packet packet;
	
	public PacketSendEvent(Packet packet) {
		this.packet = packet;
	}
	
	public Packet getPacket() {
		return this.packet;
	}
	
	public void setPacket(Packet packet) {
		this.packet = packet;
	}
}

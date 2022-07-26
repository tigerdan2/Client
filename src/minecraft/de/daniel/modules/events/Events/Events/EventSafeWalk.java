package de.daniel.modules.events.Events.Events;


import de.daniel.modules.events.Events.IEvent.Event;

public class EventSafeWalk implements Event {

    private boolean shouldWalkSafely;

    public EventSafeWalk(boolean shouldWalkSafely) {
        this.shouldWalkSafely = shouldWalkSafely;
    }

    public boolean getShouldWalkSafely() {
        return this.shouldWalkSafely;
    }

    public void setShouldWalkSafely(boolean shouldWalkSafely) {
        this.shouldWalkSafely = shouldWalkSafely;
    }
}
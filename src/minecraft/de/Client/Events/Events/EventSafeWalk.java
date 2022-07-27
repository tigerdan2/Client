package de.Client.Events.Events;

import de.Client.Events.IEvent.Event;

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
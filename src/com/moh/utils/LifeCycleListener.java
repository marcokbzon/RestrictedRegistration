package com.moh.utils;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

public class LifeCycleListener implements PhaseListener {

    private static final long serialVersionUID = -1607358457552371543L;

    public void beforePhase( PhaseEvent event ) {
        System.out.println( "  BEFORE-phase: " + event.getPhaseId() + " - " + event.getFacesContext().getExternalContext().getRequestPathInfo() );
    }

    public void afterPhase( PhaseEvent event ) {
        System.out.println( "   AFTER-phase: " + event.getPhaseId() + " - " + event.getFacesContext().getExternalContext().getRequestPathInfo() );
    }

    public PhaseId getPhaseId() {
        return PhaseId.ANY_PHASE;
    }
}

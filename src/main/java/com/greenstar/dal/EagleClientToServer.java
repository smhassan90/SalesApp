package com.greenstar.dal;

import com.greenstar.entity.eagle.*;

import java.util.List;

public class EagleClientToServer {
    List<CRForm> crForms;
    List<ChildRegistrationForm> childRegistrationForms;
    List<FollowupForm> followupForms;
    List<NeighbourForm> neighbourForms;
    List<NeighbourAttendeesForm> neighbourAttendeesForms;
    List<TokenForm> tokenForms;

    public List<CRForm> getCrForms() {
        return crForms;
    }

    public void setCrForms(List<CRForm> crForms) {
        this.crForms = crForms;
    }

    public List<ChildRegistrationForm> getChildRegistrationForms() {
        return childRegistrationForms;
    }

    public void setChildRegistrationForms(List<ChildRegistrationForm> childRegistrationForms) {
        this.childRegistrationForms = childRegistrationForms;
    }

    public List<FollowupForm> getFollowupForms() {
        return followupForms;
    }

    public void setFollowupForms(List<FollowupForm> followupForms) {
        this.followupForms = followupForms;
    }

    public List<NeighbourForm> getNeighbourForms() {
        return neighbourForms;
    }

    public void setNeighbourForms(List<NeighbourForm> neighbourForms) {
        this.neighbourForms = neighbourForms;
    }

    public List<NeighbourAttendeesForm> getNeighbourAttendeesForms() {
        return neighbourAttendeesForms;
    }

    public void setNeighbourAttendeesForms(List<NeighbourAttendeesForm> neighbourAttendeesForms) {
        this.neighbourAttendeesForms = neighbourAttendeesForms;
    }

    public List<TokenForm> getTokenForms() {
        return tokenForms;
    }

    public void setTokenForms(List<TokenForm> tokenForms) {
        this.tokenForms = tokenForms;
    }
}

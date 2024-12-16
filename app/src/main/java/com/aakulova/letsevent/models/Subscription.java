package com.aakulova.letsevent.models;

public class Subscription {
    private Integer idSubscription;
    private User subscriber;
    private User subscribedTo;

    public Subscription(Integer idSubscription, User subscriber, User subscribedTo) {
        this.idSubscription = idSubscription;
        this.subscriber = subscriber;
        this.subscribedTo = subscribedTo;
    }

    public Integer getIdSubscription() {
        return idSubscription;
    }

    public void setIdSubscription(Integer idSubscription) {
        this.idSubscription = idSubscription;
    }

    public User getSubscriber() {
        return subscriber;
    }

    public void setSubscriber(User subscriber) {
        this.subscriber = subscriber;
    }

    public User getSubscribedTo() {
        return subscribedTo;
    }

    public void setSubscribedTo(User subscribedTo) {
        this.subscribedTo = subscribedTo;
    }
}

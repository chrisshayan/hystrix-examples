package com.chrisshayan.hystrix.baeldung;


public class RemoteServiceTestSimulator {
    private long wait;

    public RemoteServiceTestSimulator(long wait) {
        this.wait = wait;
    }

    String execute() throws InterruptedException {
        System.out.println("RemoteServiceTestSimulator.execute");
        Thread.sleep(wait);
        return "Success";
    }
}

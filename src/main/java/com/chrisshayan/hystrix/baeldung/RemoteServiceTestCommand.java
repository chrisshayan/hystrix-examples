package com.chrisshayan.hystrix.baeldung;

import com.netflix.hystrix.HystrixCommand;

public class RemoteServiceTestCommand extends HystrixCommand<String> {
    RemoteServiceTestSimulator simulator;

    public RemoteServiceTestCommand(Setter config, RemoteServiceTestSimulator simulator) {
        super(config);
        this.simulator = simulator;
    }

    @Override
    protected String run() throws Exception {
        System.out.println("RemoteServiceTestCommand.run");
        return simulator.execute();
    }
}

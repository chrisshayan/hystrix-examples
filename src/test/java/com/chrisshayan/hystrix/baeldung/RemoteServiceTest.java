package com.chrisshayan.hystrix.baeldung;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.exception.HystrixRuntimeException;
import org.junit.Test;

public class RemoteServiceTest {

    @Test
    public void givenSvcTimeoutOf100AndDefaultSettings_whenRemoteSvcExecuted_thenReturnSuccess() {
        HystrixCommand.Setter config = HystrixCommand
                .Setter
                .withGroupKey(HystrixCommandGroupKey.Factory.asKey("RemoteServiceGroup2"));


        final String execute = new RemoteServiceTestCommand(config, new RemoteServiceTestSimulator(100)).execute();
        System.out.println("execute = " + execute);

    }

    @Test
    public void givenSvcTimeoutOf5000AndExecTimeoutOf10000_whenRemoteSvcExecuted_thenReturnSuccess() {
        HystrixCommand.Setter config = HystrixCommand
                .Setter
                .withGroupKey(HystrixCommandGroupKey.Factory.asKey("RemoteServiceGroupTest4"));


        HystrixCommandProperties.Setter commandProperties = HystrixCommandProperties.Setter().withExecutionTimeoutInMilliseconds(10_000);

        config.andCommandPropertiesDefaults(commandProperties);

        final String execute = new RemoteServiceTestCommand(config, new RemoteServiceTestSimulator(500)).execute();
        System.out.println("execute = " + execute);

    }

    @Test(expected = HystrixRuntimeException.class)
    public void givenSvcTimeoutOf5000AndExecTimeoutOf5000_whenRemoteSvcExecuted_thenReturnSuccess() {
        HystrixCommand.Setter config = HystrixCommand
                .Setter
                .withGroupKey(HystrixCommandGroupKey.Factory.asKey("RemoteServiceGroupTest5"));


        HystrixCommandProperties.Setter commandProperties = HystrixCommandProperties.Setter().withExecutionTimeoutInMilliseconds(5_000);

        config.andCommandPropertiesDefaults(commandProperties);

        final String execute = new RemoteServiceTestCommand(config, new RemoteServiceTestSimulator(15_000)).execute();
        System.out.println("execute = " + execute);

    }
}

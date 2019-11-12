package com.test.data.api;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class NetworkControllerFactoryTest {

    @Test
    public void test_getNetworkController_returnsInstance() {
        NetworkController networkController = NetworkControllerFactory.getNetworkController("http://test.com");

        assertNotNull(networkController);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_getNetworkController_withInvalidUrl_throwsException() {
        NetworkController networkController = NetworkControllerFactory.getNetworkController("invalidUrl");

        assertNotNull(networkController);
    }
}

package com.capgemini.infrastructure.network;

import com.github.dockerjava.api.command.CreateNetworkResponse;
import org.testcontainers.DockerClientFactory;
import org.testcontainers.containers.Network;

public class TestNetwork {
    public static final String TEST_NETWORK = "test-network";
    private static volatile TestNetwork instance = null;
    private static final Object lock = new Object();
    private Network network = null;

    public static TestNetwork getInstance() {
        if (instance == null) {
            synchronized (lock) {
                if (instance == null) {
                    instance = new TestNetwork();
                    instance.network = getOrCreateNetwork();
                }
            }
        }
        return instance;
    }

    private static Network getOrCreateNetwork() {
        var dockerClient = DockerClientFactory.instance().client();
        var existingNetworks = dockerClient.listNetworksCmd().exec();

        for (var network : existingNetworks) {
            if (TEST_NETWORK.equals(network.getName())) {
                return Network.builder().id(network.getId()).build();
            }
        }

        var createNetworkCmd = dockerClient.createNetworkCmd()
                .withName(TEST_NETWORK)
                .withCheckDuplicate(true);
        return Network.builder().id(((CreateNetworkResponse) createNetworkCmd.exec()).getId()).build();
    }

    public Network getNetwork() {
        return network;
    }
}


package com.capgemini.framework.playwright.infrastructure.network;

import com.capgemini.framework.playwright.infrastructure.Configuration;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;
import org.testcontainers.DockerClientFactory;
import org.testcontainers.containers.Network;
import org.testcontainers.utility.TestcontainersConfiguration;

public class TestNetwork {
    public static Network createReusableNetwork() {
        var name = Configuration.MY_TEST_NETWORK_NAME;
        if (!TestcontainersConfiguration.getInstance().environmentSupportsReuse()) {
            return Network.newNetwork();
        }
        String id = DockerClientFactory.instance().client().listNetworksCmd().exec().stream()
                .filter(network -> network.getName().equals(name)
                        && network.getLabels().equals(DockerClientFactory.DEFAULT_LABELS))
                .map(com.github.dockerjava.api.model.Network::getId)
                .findFirst()
                .orElseGet(() -> DockerClientFactory.instance().client().createNetworkCmd()
                        .withName(name)
                        .withCheckDuplicate(true)
                        .withLabels(DockerClientFactory.DEFAULT_LABELS)
                        .exec().getId());

        return new Network() {
            @Override
            public Statement apply(Statement base, Description description) {
                return base;
            }

            @Override
            public String getId() {
                return id;
            }

            @Override
            public void close() {
                // never close
            }
        };
    }
}


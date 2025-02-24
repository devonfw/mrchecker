package com.capgemini.framework.playwright.infrastructure.resources.containers;

import com.capgemini.framework.playwright.infrastructure.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testcontainers.containers.MockServerContainer;
import org.testcontainers.containers.Network;
import org.testcontainers.utility.DockerImageName;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;

public class MyMockServer extends MockServerContainer {
    private static final String NETWORK_ALIAS = "mock-server";
    private static final Logger log = LoggerFactory.getLogger(MyMockServer.class);

    public MyMockServer(Network network) {
        super(DockerImageName.parse("mockserver/mockserver"));

        withReuse(Configuration.DEBUG);
        withNetwork(network)
                .withNetworkAliases(NETWORK_ALIAS)
                .withNetworkMode(Configuration.MY_TEST_NETWORK_NAME)
                .withLabel("reuse-id", Configuration.MY_MOCK_NAME)
                .withExposedPorts(1080)
                .withStartupTimeout(Duration.ofMinutes(2))
                .withEnv("mockserver.logLevel", "none")
                .withLogConsumer(containerLog -> log.info(containerLog.getUtf8String()));
        withCreateContainerCmdModifier(cmd -> cmd.withName(Configuration.MY_MOCK_NAME));
        setPortBindings(Arrays.asList("1080:1080"));
    }

    @Override
    public MyMockServer withReuse(boolean reusable) {
        if (reusable) {
            var aliases = new ArrayList<String>();
            aliases.add(NETWORK_ALIAS);
            aliases.add(Configuration.MY_MOCK_NAME);
            this.setNetworkAliases(aliases);
        }
        return (MyMockServer) super.withReuse(reusable);
    }
}

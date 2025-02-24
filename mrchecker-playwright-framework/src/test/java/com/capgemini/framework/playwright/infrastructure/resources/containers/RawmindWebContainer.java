package com.capgemini.framework.playwright.infrastructure.resources.containers;

import com.capgemini.framework.playwright.infrastructure.Configuration;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.Network;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.utility.DockerImageName;

import java.util.ArrayList;
import java.util.Arrays;

public class RawmindWebContainer extends GenericContainer<RawmindWebContainer> {
    private static final String NETWORK_ALIAS = "rawmind";
    private static final int APP_PORT = 8080;

    public RawmindWebContainer(Network network) {
        super(DockerImageName.parse("rawmind/web-test"));

        withReuse(Configuration.DEBUG);
        withNetwork(network)
                .withNetworkAliases(NETWORK_ALIAS)
                .withNetworkMode(Configuration.MY_TEST_NETWORK_NAME)
                .withLabel("reuse-id", Configuration.MY_WEB_APP_NAME)
                .withExposedPorts(APP_PORT)
                .waitingFor(Wait.forLogMessage(".*Running web-test service.*", 1))
                .withStartupTimeout(java.time.Duration.ofSeconds(20));
        withCreateContainerCmdModifier(cmd -> cmd.withName(Configuration.MY_WEB_APP_NAME));
        setPortBindings(Arrays.asList(APP_PORT+":"+APP_PORT));
        logger().info("RawmindWebContainer starting...");
    }

    public String getUrl() {
        return String.format("http://%s:%s", getHost(), getMappedPort(APP_PORT));
    }

    @Override
    public RawmindWebContainer withReuse(boolean reusable) {
        if (reusable) {
            var aliases = new ArrayList<String>();
            aliases.add(NETWORK_ALIAS);
            aliases.add(Configuration.MY_WEB_APP_NAME);
            this.setNetworkAliases(aliases);
        }
        return (RawmindWebContainer) super.withReuse(reusable);
    }
}



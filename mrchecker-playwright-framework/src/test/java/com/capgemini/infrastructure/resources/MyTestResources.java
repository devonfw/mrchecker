package com.capgemini.infrastructure.resources;

import com.capgemini.framework.logger.LoggerInstance;
import com.capgemini.infrastructure.Configuration;
import com.capgemini.infrastructure.network.TestNetwork;
import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;
import org.testcontainers.DockerClientFactory;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.Network;

import java.util.HashMap;
import java.util.Map;

public class MyTestResources implements QuarkusTestResourceLifecycleManager {
    private final Network network = TestNetwork.getInstance().getNetwork();
    private RawmindWebContainer rawmindWebContainer = null;

    LoggerInstance logger = new LoggerInstance();

    @Override
    public Map<String, String> start() {
        var conf = new HashMap<String, String>();

        startWebServer();
        conf.put("webAppUrl", rawmindWebContainer.getUrl());
        return conf;
    }

    private void startWebServer() {
        if (!isContainerRunning(Configuration.MY_WEB_APP) || rawmindWebContainer == null) { // Only start if it's NOT running
            rawmindWebContainer = new RawmindWebContainer(network);
            rawmindWebContainer.withCreateContainerCmdModifier(cmd -> cmd.withName(Configuration.MY_WEB_APP));
            rawmindWebContainer.start();
        }
    }

    private boolean isContainerRunning(String containerName) {
        return DockerClientFactory.instance().client().listContainersCmd()
                .withShowAll(true)
                .exec()
                .stream()
                .anyMatch(container -> container.getNames()[0].contains(containerName));
    }


    @Override
    public void stop() {
        if (!Configuration.DEBUG) {
            stopContainer(rawmindWebContainer);
        }
    }


    private void stopContainer(GenericContainer container) {
        if (container != null) {
            container.stop();
        }
    }
}

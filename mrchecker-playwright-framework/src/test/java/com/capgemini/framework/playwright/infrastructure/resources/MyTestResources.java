package com.capgemini.framework.playwright.infrastructure.resources;

import com.capgemini.framework.playwright.infrastructure.Configuration;
import com.capgemini.framework.playwright.infrastructure.network.TestNetwork;
import com.capgemini.framework.playwright.infrastructure.resources.containers.MyMockServer;
import com.capgemini.framework.playwright.infrastructure.resources.containers.MyMockServerClient;
import com.capgemini.framework.playwright.infrastructure.resources.containers.RawmindWebContainer;
import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;
import org.testcontainers.DockerClientFactory;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.Network;

import java.util.HashMap;
import java.util.Map;

public class MyTestResources implements QuarkusTestResourceLifecycleManager {
    private final Network network = TestNetwork.createReusableNetwork();
    private RawmindWebContainer rawmindWebContainer = null;
    private MyMockServer myMockServer = null;

    @Override
    public Map<String, String> start() {
        var conf = new HashMap<String, String>();

        startMockServer();
        startMockClient();
        startWebServer();
        conf.put("webAppUrl", rawmindWebContainer.getUrl());
        return conf;
    }

    private void startMockServer() {
        if (myMockServer == null || isContainerNotRunning(Configuration.MY_MOCK_NAME)) {
            myMockServer = new MyMockServer(network);
            if (!myMockServer.isRunning()) {
                myMockServer.start();
            }
            renameContainerIfNeeded(myMockServer, Configuration.MY_MOCK_NAME);
        }
        Configuration.getInstance().setMyMockServer(myMockServer);
    }

    private void startMockClient() {
        MyMockServerClient.getInstance().startMockClient();
    }

    private void startWebServer() {
        if (rawmindWebContainer == null || isContainerNotRunning(Configuration.MY_WEB_APP_NAME)) {
            rawmindWebContainer = new RawmindWebContainer(network);
            if (!rawmindWebContainer.isRunning()) {
                rawmindWebContainer.start();
            }
            renameContainerIfNeeded(rawmindWebContainer, Configuration.MY_WEB_APP_NAME);
        }
        Configuration.getInstance().setMyWebAppUrl(rawmindWebContainer.getUrl());
    }

    private void renameContainerIfNeeded(GenericContainer<?> container, String containerName) {
        if (container.getContainerName().contains(containerName)) {
            container.withCreateContainerCmdModifier(cmd -> cmd.withName(containerName));
        }
    }

    private boolean isContainerNotRunning(String containerName) {
        return DockerClientFactory.instance().client().listContainersCmd()
                .withShowAll(true)
                .exec()
                .stream()
                .noneMatch(container -> container.getNames()[0].contains(containerName));
    }

    @Override
    public void stop() {
        if (!Configuration.DEBUG) {
            stopContainer(rawmindWebContainer);
            stopContainer(myMockServer);
        }
    }

    private void stopContainer(GenericContainer<?> container) {
        if (container != null) {
            container.stop();
        }
    }
}

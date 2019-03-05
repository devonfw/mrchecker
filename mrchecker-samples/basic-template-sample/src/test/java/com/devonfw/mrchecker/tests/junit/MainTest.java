package com.devonfw.mrchecker.tests.junit;

import static org.junit.Assert.assertThat;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.capgemini.mrchecker.test.core.BaseTest;
import com.capgemini.mrchecker.test.core.logger.BFLogger;
import com.devonfw.mrchecker.common.data.SampleObject;
import com.devonfw.mrchecker.common.mapper.CsvMapper;
import com.capgemini.mrchecker.webapi.core.base.driver.DriverManager;
import com.capgemini.mrchecker.webapi.endpoint.stubs.StubREST_Builder;
import com.github.tomakehurst.wiremock.WireMockServer;
import static org.hamcrest.Matchers.is;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import junitparams.FileParameters;
import junitparams.JUnitParamsRunner;


@RunWith(JUnitParamsRunner.class)
public class MainTest extends BaseTest {

  private static String endpointBaseUri;

  @BeforeClass
  public static void beforeClass() {

    // Start Virtual Server
    WireMockServer driverVirtualService = DriverManager.getDriverVirtualService();

    // Get Virtual Server running http and https ports
    int httpPort = driverVirtualService.port();
    int httpsPort = driverVirtualService.httpsPort();

    // Print is Virtual server running
    BFLogger.logDebug("Is Virtual server running: " + driverVirtualService.isRunning());

    String baseURI = "http://localhost";
    endpointBaseUri = baseURI + ":" + httpPort;
  }

  /**
   * Method executed before the tests
   * Useful to do initial configurations
   * */
  @Override
  public void setUp() {
    /*
    * ----------
    * Mock response. Map request with virtual asset from file
    * -----------
    */
   BFLogger.logInfo("#1 Create Stub content message");
   BFLogger.logInfo("#2 Add resource to wiremock server");
   new StubREST_Builder.StubBuilder("/some/thing")
           .setResponse("{ \"FahrenheitToCelsiusResponse\":{\"FahrenheitToCelsiusResult\":37.7777777777778}}")
           .setStatusCode(200)
           .build();

  }

  /**
   * Method executed after the tests
   * Useful to do cleaning operations
   * */
  @Override
  public void tearDown() {
    // TODO: Cleaning operations

  }

  @Test
  public void testRestMock_UrlExists() {

    BFLogger.logInfo("#3 Send request to generated stub");
    Response response = DriverManager.getDriverWebAPI()
            .with()
            .header("Content-Type", ContentType.JSON.toString())
            .log()
            .all()
            .when()
            .get(endpointBaseUri + "/some/thing")
            .thenReturn();

    BFLogger.logInfo("#4 Validate response ");
    BFLogger.logDebug("/some/thing: " + response.jsonPath()
            .prettyPrint());
    assertThat(response.statusCode(), is(200));
  }


}

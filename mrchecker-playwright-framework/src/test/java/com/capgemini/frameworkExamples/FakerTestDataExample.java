package com.capgemini.frameworkExamples;

import com.capgemini.framework.enums.PrjEpics;
import com.capgemini.framework.logger.AllureStepLogger;
import com.capgemini.framework.tags.Status;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import net.datafaker.Faker;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Locale;
@Epic(PrjEpics.EXAMPLES)
@Story("Faker example")
public class FakerTestDataExample {
	

	@Description("Faker generates test data")
	@Test
	@Tag(Status.DONE)
	void fakertestdata_test() throws IOException {
	// Easy test data generation with faker library
		Faker ukFaker = new Faker(new Locale("en-GB"));
		Faker usFaker = new Faker(new Locale("en-US"));
		Faker plFaker = new Faker(new Locale("pl_PL"));
		Faker deFaker = new Faker(new Locale("de-DE"));
		
		Faker faker = new Faker();
		AllureStepLogger.info(plFaker.name().firstName());
		AllureStepLogger.info(plFaker.name().lastName());
		AllureStepLogger.info(plFaker.address().city());
		AllureStepLogger.info(String.format("American zipcode: %s", usFaker.address().zipCode()));
		AllureStepLogger.info(String.format("British postcode: %s", ukFaker.address().zipCode()));
		AllureStepLogger.info(String.format("Polish postcode: %s", plFaker.address().zipCode()));
		AllureStepLogger.info(String.format("German postcode: %s", deFaker.address().zipCode()));
		
	}
}

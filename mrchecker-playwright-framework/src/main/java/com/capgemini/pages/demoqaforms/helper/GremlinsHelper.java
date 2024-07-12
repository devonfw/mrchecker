package com.capgemini.pages.demoqaforms.helper;

import com.microsoft.playwright.Page;
import io.qameta.allure.Allure;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * @author Michal Carlo
 * @since 2024-03-19
 * Gremlins is a monkey testing library that performs random actions on the page.
 */

public final class GremlinsHelper {
	public static final String GREMLIN_JS_EXPRESSION = "gremlin JS expression";
	static final Path PATH_TO_GREMLINS = Paths.get("target/js/node_modules/gremlins.min.js");
	private static final Logger LOGGER = LoggerFactory.getLogger(GremlinsHelper.class.getCanonicalName());
	
	public GremlinsHelper() {
	}
	
	/**
	 * Get JS script for Gremlins.
	 *
	 * @throws IOException Gremlins script not found
	 */
	public static void copyGremlinsJSFromResources() throws IOException {
		Files.createDirectories(Paths.get("target/js/node_modules"));
		try (InputStream stream = Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResourceAsStream("js/gremlins.min.js"))) {
			Files.copy(stream, PATH_TO_GREMLINS);
		}
	}
	
	/**
	 * Install Gremlins script in the environment.
	 *
	 * @param page Playwright page
	 * @throws IOException Gremlins script not found
	 */
	public static void setupGremlinsScript(
			final Page page) throws IOException {
		if (!Files.exists(PATH_TO_GREMLINS)) {
			copyGremlinsJSFromResources();
		}
		page.evaluate( Files.readString( Path.of(System.getProperty("user.dir") + "/" + PATH_TO_GREMLINS), Charset.defaultCharset()));
	}
	
	/**
	 * unleash Gremlin horde with default settings.
	 *
	 * @param page Playwright page
	 */
	public static void startGremlinHorde(
			final Page page) {
		page.evaluate("() => gremlins.createHorde().unleash()");
	}
	
	/**
	 * unleash Gremlin horde with repeatable behaviour due to overwritten seed value.
	 *
	 * @param page            Playwright page
	 * @param randomizerValue seed value to influence gremlin behaviour
	 */
	public static void startGremlinHordeWithRepeatableBehaviour(
			final Page page,
			final int randomizerValue) {
		String expression = "const horde = gremlins.createHorde({randomizer: new gremlins.Chance(%%%)})\n"
				.replace("%%%", String.valueOf(randomizerValue))
				+ "horde.unleash()";
		LOGGER.debug("random gremlin horde expression: {}", expression);
		Allure.addAttachment(GREMLIN_JS_EXPRESSION, expression);
		page.evaluate(expression);
	}
	
	/**
	 * unleash Gremlin horde with a particular Gremlin number.
	 *
	 * @param page         Playwright page
	 * @param gremlinCount number of Gremlins, tne higher, the longer the attack lasts.
	 */
	public static void startGremlinHordeWithGremlinCount(
			final Page page,
			final int gremlinCount) {
		String expression = "const horde = gremlins.createHorde({ strategies: [gremlins.strategies.allTogether({ nb: %%%})],}) \n"
				.replace("%%%", String.valueOf(gremlinCount))
				+ "horde.unleash()";
		LOGGER.debug("gremlin count expression: {}", expression);
		Allure.addAttachment(GREMLIN_JS_EXPRESSION, expression);
		page.evaluate(expression);
	}
	
	/**
	 * unleash Gremlin horde with particular Gremlin species.
	 *
	 * @param page               Playwright page
	 * @param gremlinSpeciesList list of gremlin species to use, allowed values "clicker", "toucher", "formFiller", "scroller", "typer"
	 */
	public static void startGremlinHordeWithGremlinTypes(
			final Page page,
			final List<String> gremlinSpeciesList) {
		String speciesList = parseGremlinsSpeciesList(gremlinSpeciesList);
		String expression = "gremlins.createHorde({\n"
				+ "        species: %%%\n ".replace(" %%%", speciesList)
				+ "    })\n"
				+ "    .unleash();";
		LOGGER.debug("gremlin type expression: {}", expression);
		Allure.addAttachment(GREMLIN_JS_EXPRESSION, expression);
		page.evaluate(expression);
	}
	
	/**
	 * unleash Gremlin horde with selectable count and species.
	 *
	 * @param page               Playwright page
	 * @param gremlinCount       number of Gremlins, tne higher, the longer the attack lasts
	 * @param delay              delay between attacks in ms
	 * @param gremlinSpeciesList list of gremlin species to use, allowed values "clicker", "toucher", "formFiller", "scroller", "typer"
	 */
	public static void startGremlinHordeFullyCustomisable(
			final Page page,
			final int gremlinCount,
			final int delay,
			final List<String> gremlinSpeciesList) {
		String speciesList = parseGremlinsSpeciesList(gremlinSpeciesList);
		String expression = "gremlins.createHorde({\n"
				+ "        species: %%%,\n".replace(" %%%", speciesList)
				+ "        strategies:[gremlins.strategies.allTogether({ nb: %%%})],\n".replace("%%%", String.valueOf(gremlinCount))
				+ "        delay: %%%,\n".replace("%%%", String.valueOf(delay))
				+ "    })\n"
				+ "    .unleash();";
		LOGGER.debug("gremlin custom horde expression: {}", expression);
		Allure.addAttachment(GREMLIN_JS_EXPRESSION, expression);
		page.evaluate(expression);
	}
	
	private static String parseGremlinsSpeciesList(
			final List<String> gremlinSpeciesList) {
		List<String> parsedGremlinsList = new ArrayList<>();
		for (String gremlinSpecies : gremlinSpeciesList) {
			if (isSpeciesAllowed(gremlinSpecies)) {
				parsedGremlinsList.add("gremlins.species." + gremlinSpecies + "()");
			}
		}
		return parsedGremlinsList.toString();
	}
	
	private static boolean isSpeciesAllowed(final String gremlinSpecies) {
		List<String> allowedSpecies = Arrays.asList("clicker", "toucher", "formFiller", "scroller", "typer");
		return allowedSpecies.contains(gremlinSpecies);
	}
}

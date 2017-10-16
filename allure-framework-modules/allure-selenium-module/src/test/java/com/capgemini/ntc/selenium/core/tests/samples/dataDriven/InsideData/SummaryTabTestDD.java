/**
 * 
 */
package com.capgemini.ntc.selenium.core.tests.samples.dataDriven.InsideData;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Collection;

import com.capgemini.ntc.test.core.tests.core.BaseTest;
import com.capgemini.ntc.test.core.tests.testRunners.ParallelParameterized;
import com.capgemini.ntc.selenium.core.tests.PageTestUtils;

import org.junit.Test;
import org.junit.runner.RunWith;


import junitparams.Parameters;

/**
 * @author
 *
 */
@RunWith(ParallelParameterized.class)
public class SummaryTabTestDD extends BaseTest {

	/**
	 * tagName, tabName, login and password are used to set DD test
	 */
	private String tagName;
	private String tabName;
	private String login;
	private String password;

	/**
	 * expectedCardsTitlesOrder is used to store expected values
	 */
	String[][] expectedCardsTitlesOrder;


	@Parameters("{index}: tagName {0}, tabName {1}, login {2}, password {3}, results {4} ")
	public static Collection<Object[]> data() {
		String[][] EXPECTED_CARDS = new String[3][];
		EXPECTED_CARDS[0] = new String[] { "Your Balance Over Time", "News About Your Investments",
				"Plan for Your Retirement", "Stay on track to achieve your goals" };
		EXPECTED_CARDS[1] = new String[] { "Your Asset Allocation", "Today's Markets", "Market Movers" };
		EXPECTED_CARDS[2] = new String[] { "Tax Season Help", "Insights From Bank", "Your Top & Bottom Movers",
				"Orders & Pending Activity" };

		return Arrays.asList(new Object[][] { { "Accounts", "Summary", "loginName", "Password345!", EXPECTED_CARDS },
				{ "Accounts", "Summary", "loginName", "Password345!", EXPECTED_CARDS },
				{ "Accounts", "Summary", "loginName", "Password345!", EXPECTED_CARDS }, });
	}

	/**
	 * The constructor is used for filling with data DD test
	 * 
	 * @param tagName
	 * @param tabName
	 * @param login
	 * @param password
	 * @param expectedCardsTitlesOrder
	 */
	public SummaryTabTestDD(String tagName, String tabName, String login, String password,
			String[][] expectedCardsTitlesOrder, boolean seleniumGrid) {
		this.tagName = tagName;
		this.tabName = tabName;
		this.login = login;
		this.password = password;
		this.expectedCardsTitlesOrder = expectedCardsTitlesOrder;
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Override
	public void setUp() {
		
		
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Override
	public void tearDown() {
		PageTestUtils.logout();
	}

	@Test
	public void test() {
		CardList cardList = new CardList();
		String[][] actualCardsTitlesOrder = cardList.cardTitles();

		// check if number of columns is equal
		assertEquals("Number of columns is not equal.", expectedCardsTitlesOrder.length, cardList.columnsCount());
	}


}

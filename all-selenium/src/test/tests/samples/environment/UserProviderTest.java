package com.example.selenium.tests.tests.samples.environment;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.json.JSONException;
import org.junit.Test;

import com.example.core.base.user.AvailableUsers;
import com.example.core.base.user.UserAccountPredicate;
import com.example.core.base.user.UserProvider;
import com.example.core.exceptions.BFInputDataException;

public class UserProviderTest {

	@Test
	public void createUserProviderSuccessfully() {
		try {
			UserProvider.get();
		} catch (BFInputDataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void changeEnvironmentToNotExistingAndReturnNoUsers() throws BFInputDataException, JSONException {
		UserProvider userProvider = UserProvider.get();
		userProvider.setEnvironment("!@#123asdf");
		AvailableUsers users = userProvider.find(UserAccountPredicate.SPS_ACCOUNT);
		assertTrue("User provider is not working properly, USERs list is not empty", users.isEmpty());
	}

	@Test
	public void changeEnvironmentToExistingAndFindMatchingUsers() throws BFInputDataException, JSONException {
		UserProvider userProvider = UserProvider.get();
		userProvider.setEnvironment("DEV1");
		AvailableUsers users = userProvider.find(UserAccountPredicate.SPS_ACCOUNT);
		assertFalse("User provider is not working properly, USERs list is empty", users.isEmpty());
	}
}

package com.example.core.base.user;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.Predicate;

/**
 * Class which contains list of USERs, retrieved from USER repository, that matches given preconditions (e.g. are
 * retirement, margin, etc).
 * 
 * @author
 *
 */
public class AvailableUsers {
	private List<UserData> userDataList;
	private List<Predicate> requirements = new ArrayList<Predicate>();
	private int index = -1;
	private UserData currentUser;

	public AvailableUsers() {
		userDataList = new ArrayList<UserData>();
	}

	/**
	 * Adds {@code UserData} object to a list of elements which match preconditions
	 * 
	 * @param data
	 */
	public void add(UserData data) {
		userDataList.add(data);
	}

	/**
	 * Tries to find Account which property match given predicate
	 * 
	 * @param accountPredicate
	 * @return
	 */
	public AccountData getAccountData(UserAccountPredicate accountPredicate) {
		UserData currentUserData = getUserData();
		for (AccountData accountData : currentUserData.getAccountsData()) {
			if (accountPredicate.get().evaluate(accountData)) {
				return accountData;
			} ;
		}
		throw new PiAtUserProviderException(
				"Unable to find account with properties '" + accountPredicate.toString() + "' in '"
						+ currentUserData.toString() + "' account.");
	}

	public String getUser() {
		return getUserData().getUser();
	}

	/**
	 * Gets first element from list of UserData objects which matches previously set preconditions
	 * 
	 * @return
	 */
	public UserData getUserData() {
		if (userDataList.isEmpty()) {
			throw new PiAtUserProviderException(
					"Unable to find USER that match data requirements " + requirements.toString());
		}
		if (currentUser == null) {
			currentUser = getNextUser();
		}
		return currentUser;
	}

	private UserData getNextUser() {
		if (index + 1 >= userDataList.size()) {
			throw new PiAtUserProviderException(
					"Unable to find next USER that match data requirements " + requirements.toString());
		}
		return userDataList.get(++index);
	}

	/**
	 * Checks if there are available users that match given preconditions
	 * 
	 * @return
	 */
	public boolean isEmpty() {
		return userDataList.isEmpty();
	}
}

package com.example.core.base.user;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.json.JSONException;

import com.example.core.exceptions.BFInputDataException;

/**
 * This class is responsible for providing USERs (and informations related with it) from given data source (e.g. json
 * file). It allows to find list of USERs by given preconditions (properties). By default USER are filtered by "DEV1"
 * environment.
 * 
 * @author
 *
 */
public class UserProvider {

	private static final String USER_REPOSITORY_FILE_PATH = "/dynamicData/user_repository.json";
	private static final String DEF_ENVIRONMENT = "DEV1";
	private static UserProvider userProvider;
	private Map<String, UserData> initialUserMap;
	private Map<String, UserData> workingUserMap;

	private UserProvider(String environment) throws BFInputDataException, JSONException {
		fetchDataFromFile();
		setEnvironment(environment);
	}

	/**
	 * Gets instance of class
	 * 
	 * @return
	 * @throws JSONException 
	 * @throws BFInputDataException 
	 */
	public static UserProvider get() throws BFInputDataException, JSONException {
		if (userProvider == null) {
			userProvider = new UserProvider(DEF_ENVIRONMENT);
		}
		return userProvider;
	}

	private void fetchDataFromFile() throws BFInputDataException, JSONException {
		File jsonFile = new File(this.getClass().getResource(USER_REPOSITORY_FILE_PATH).getFile());
		initialUserMap = MapUserFile.map(jsonFile);
	}

	/**
	 * Sets environment (e.g. "DEV1")
	 * 
	 * @param environmentName
	 */
	public void setEnvironment(String environmentName) {
		workingUserMap = filterByEnvironment(environmentName);
	}

	private Map<String, UserData> filterByEnvironment(String environmentName) {
		Map<String, UserData> filteredUserMap = new HashMap<String, UserData>();
		for (Entry<String, UserData> entry : initialUserMap.entrySet()) {
			String userEnvironment = entry.getValue().getEnvironment();
			if (userEnvironment.equals(environmentName)) {
				filteredUserMap.put(entry.getKey(), entry.getValue());
			}
		}
		return filteredUserMap;
	}

	/**
	 * Tries to find USER that match given predicate
	 * 
	 * @param accountPredicate
	 * @return
	 */
	public AvailableUsers find(UserAccountPredicate accountPredicate) {
		AvailableUsers availableUser = new AvailableUsers();
		for (UserData data : workingUserMap.values()) {
			for (AccountData accountData : data.getAccountsData()) {
				if (accountPredicate.get().evaluate(accountData)) {
					availableUser.add(data);
					break;
				}
			}
		}
		return availableUser;
	}
}

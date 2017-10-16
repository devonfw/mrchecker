package com.example.core.base.user;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.example.core.utils.datadriven.json.JsonReader;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Parse JSON file and creates Map of {@code UserData} with USER numbers as keys.
 * 
 * @author
 *
 */
public class MapUserFile {

	private MapUserFile() {
	}

	private enum UserJsonKey {
		PASSWORD("password"),
		ENVIRONMENT("environment"),
		ACTIVE("active"),
		ACCOUNT("account"),
		DESCRIPTION("description"),
		// account sub-keys
		ACCOUNT_NAME("name"),
		ACCOUNT_NUMBER("number"),
		ACCOUNT_TYPE("type");
		UserJsonKey(String key) {
			this.key = key;
		}

		private String key;

		@Override
		public String toString() {
			return key;
		}
	}

	/**
	 * Parse JSON file to map, where key is a USER and a value is {@code UserData} object
	 * 
	 * @param jsonFile
	 * @return
	 * @throws JSONException 
	 */
	public static Map<String, UserData> map(File jsonFile) throws JSONException {
		JSONObject jsonObject = JsonReader.readJson(jsonFile);
		Map<String, UserData> userDataMap = new HashMap<String, UserData>();
		Iterator userIterator = jsonObject.keys();
		while (userIterator.hasNext()) {
			String userKey = (String) userIterator.next();
			JSONObject userContent = (JSONObject) jsonObject.get(userKey);
			UserData userData = retrieveUserData(userKey, userContent);
			userDataMap.put(userKey, userData);
		}
		return userDataMap;
	}

	private static UserData retrieveUserData(String user, JSONObject userContent) throws JSONException {
		UserData userData = new UserData();
		userData.setUser(user);
		userData.setPassword(getStringValue(UserJsonKey.PASSWORD, userContent));
		userData.setEnvironment(getStringValue(UserJsonKey.ENVIRONMENT, userContent));
		userData.setActive(getStringValue(UserJsonKey.ACTIVE, userContent));
		userData.setDescription(getStringValue(UserJsonKey.DESCRIPTION, userContent));
		if (!isNull(userContent, UserJsonKey.ACCOUNT)) {
			JSONArray accounts = userContent.getJSONArray(UserJsonKey.ACCOUNT.toString());
			for (int i = 0; i < accounts.length(); i++) {
				JSONObject accountObject = accounts.getJSONObject(i);
				AccountData accountData = new AccountData(user, user);
				accountData.setName(getStringValue(UserJsonKey.ACCOUNT_NAME, accountObject));
				accountData.setNumber(getStringValue(UserJsonKey.ACCOUNT_NUMBER, accountObject));
				accountData.setType(getStringValue(UserJsonKey.ACCOUNT_TYPE, accountObject));
				userData.addAccountData(accountData);
			}
		}
		return userData;
	}

	private static String getStringValue(UserJsonKey what, JSONObject from) throws JSONException {
		String key = what.toString();
		if (from.isNull(key)) {
			return "";
		} ;
		return (String) from.get(key);
	}

	private static boolean isNull(JSONObject jsonObj, UserJsonKey key) {
		return jsonObj.isNull(key.toString());
	}
}

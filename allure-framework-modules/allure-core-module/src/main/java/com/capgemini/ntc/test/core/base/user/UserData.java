package com.capgemini.ntc.test.core.base.user;

import java.util.ArrayList;
import java.util.List;

/**
 * DataProviderInternal container for user - USER related informations. It contains fields like e.g.: USER, password,
 * environment on which given SSN is working, description (brief information about this USER) list of accounts which
 * this USER contain and other fields which might be useful.
 * 
 * @author
 */
public class UserData {
	private String user;
	private String password;
	private String environment;
	private String description;
	private String type;
	private String trade;
	private List<AccountData> accountsData;
	private String active;
	
	public UserData() {
		accountsData = new ArrayList<AccountData>();
	}
	
	public String getUser() {
		return user;
	}
	
	public void setUser(String user) {
		this.user = user;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getEnvironment() {
		return environment;
	}
	
	public void setEnvironment(String environment) {
		this.environment = environment;
	}
	
	public void setActive(String active) {
		this.active = active;
	}
	
	public String getActive() {
		return active;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public String getTrade() {
		return trade;
	}
	
	public void setTrade(String trade) {
		this.trade = trade;
	}
	
	public List<AccountData> getAccountsData() {
		return accountsData;
	}
	
	public void addAccountData(AccountData accountData) {
		this.accountsData.add(accountData);
	}
	
	@Override
	public String toString() {
		return user + " " + description;
	}
	
	/**
	 * Tries to find account data by given account predicate
	 * 
	 * @param accountPredicate
	 * @return
	 */
	public AccountData findAccountData(UserAccountPredicate accountPredicate) {
		for (AccountData accountData : getAccountsData()) {
			if (accountPredicate.get()
					.evaluate(accountData)) {
				return accountData;
			}
			;
		}
		throw new UserProviderException("Unable to find account with properties '" + accountPredicate.toString() + "' in '" + this + "' account.");
	}
	
}

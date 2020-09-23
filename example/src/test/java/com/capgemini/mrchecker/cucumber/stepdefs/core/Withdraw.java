package com.capgemini.mrchecker.cucumber.stepdefs.core;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsInstanceOf.instanceOf;

import com.capgemini.mrchecker.test.core.logger.BFLogger;

import io.cucumber.java8.En;

public class Withdraw implements En {
	
	private Exception	e;
	private Account		account;
	
	public Withdraw() {
		
		Given("I have deposited ${int} in my account", (Integer openBalance) -> {
			account = new Account(openBalance);
			
		});
		When("I request ${int}", (Integer amount) -> {
			BFLogger.logDebug("I've requested " + amount);
			try {
				account.withdraw(amount);
			} catch (AccountOperationException e) {
				this.e = e;
			}
		});
		
		Then("${int} should be in my account", (Integer amount) -> {
			assertThat(amount, is(equalTo(account.getBalance())));
		});
		
		Then("^The withdrawal should be unsuccessful$", () -> {
			assertThat(e, is(instanceOf(AccountOperationException.class)));
		});
	}
	
	// ------------------------------------------------------------------------------------------------------
	// Tested business logic
	// ------------------------------------------------------------------------------------------------------
	class Account {
		private Integer balance;
		
		public Account(Integer openingBalance) {
			this.setBalance(openingBalance);
		}
		
		public int getBalance() {
			return balance;
		}
		
		private void setBalance(Integer balance) {
			this.balance = balance;
		}
		
		public void withdraw(Integer amount) throws AccountOperationException {
			if (amount > getBalance()) {
				throw new AccountOperationException("Insufficient funds. Reduce withdraw amount");
			}
			setBalance(getBalance() - amount);
		}
	}
	
	public static class AccountOperationException extends Exception {
		
		public AccountOperationException(String message) {
			super(message);
		}
	}
	// @Given("^I have deposited \\$(\\d+) in my account$")
	// public void iHaveDeposited$InMyAccount(int amount) throws Throwable {
	// new Account(amount);
	// }
	//
	// @When("^I request \\$(\\d+)$")
	// public void iRequest$(int arg1) throws Throwable {
	// // Write code here that turns the phrase above into concrete actions
	// BFLogger.logDebug("I've requested " + arg1);
	// // throw new PendingException();
	// }
	//
	// @Then("^\\$(\\d+) should be dispensed$")
	// public void shouldBeDispensed(int arg1) throws Throwable {
	// // Write code here that turns the phrase above into concrete actions
	// assertTrue(true);
	// // throw new PendingException();
	// }
	
}

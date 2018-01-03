package com.capgemini.ntc.test.core.base.user;

import org.apache.commons.collections.Predicate;
import org.apache.commons.collections.PredicateUtils;

/**
 * Preconditions used to filter from USER repository. <br/>
 * <b>Object passed to method {@code evaluate} must be an {@code AccountData} instance</b>
 * 
 * @author
 */
public enum UserAccountPredicate {
	SPS_ACCOUNT(new Predicate() {
		
		@Override
		public boolean evaluate(Object object) {
			AccountData accountsData = (AccountData) object;
			return accountsData.getType()
					.contains("SPS");
		}
	}), FPP_ACCOUNT(new Predicate() {
		
		@Override
		public boolean evaluate(Object object) {
			AccountData accountsData = (AccountData) object;
			return accountsData.getType()
					.contains("FPP");
		}
	}), PAS_ACCOUNT(new Predicate() {
		
		@Override
		public boolean evaluate(Object object) {
			AccountData accountsData = (AccountData) object;
			return accountsData.getType()
					.contains("PAS");
		}
	}), _401K_ACCOUNT(new Predicate() {
		
		@Override
		public boolean evaluate(Object object) {
			AccountData accountsData = (AccountData) object;
			return accountsData.getType()
					.contains("401k");
		}
	});
	
	private Predicate predicate;
	
	UserAccountPredicate(Predicate predicate) {
		this.predicate = predicate;
	}
	
	/**
	 * Gets the {@code Predicate} class instance
	 * 
	 * @return
	 */
	public Predicate get() {
		Predicate userDataInstancePredicate = new Predicate() {
			
			@Override
			public boolean evaluate(Object object) {
				if (!(object instanceof AccountData)) {
					throw new IllegalArgumentException("Wrong parameter used. Should be " + AccountData.class.getSimpleName() + " instance.");
				}
				return true;
			}
		};
		return PredicateUtils.allPredicate(new Predicate[] { userDataInstancePredicate, predicate });
	};
}

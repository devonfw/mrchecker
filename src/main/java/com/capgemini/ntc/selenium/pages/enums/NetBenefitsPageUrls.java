package com.capgemini.ntc.selenium.pages.enums;

import com.example.core.enums.SubUrl;

public enum NetBenefitsPageUrls implements SubUrl {
	MAIN_PAGE {
		@Override
		public String subURL() {
			return ServicesURLsEnum.SPS_WI_URL.getAddress();
		}
	},
	PORTFOLIO_POSITIONS("mybenefits/navstation/navigation/portfolio/investments"),
	CONTRIBUTION("mybenefits/savings2/navigation/dc/Contributions");

	private String subUrl;

	private NetBenefitsPageUrls() {
	}

	private NetBenefitsPageUrls(String subUrl) {
		this.subUrl = subUrl;
	};

	public String subURL() {
		return subUrl;
	}

	@Override
	public String toString() {
		return subURL();
	}
}

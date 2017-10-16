package com.capgemini.ntc.test.core.download;

import com.capgemini.ntc.test.core.enums.PositionsTabColumnsEnum;

public enum PositionsCsvAndRowBinder {
	ACCOUNT_NAME("Account Name/Number") {
		@Override
		public String getDataFromPage(MagicGridRow row) {
			return row.getAccountNumber();
		}
	},
	SYMBOL("Symbol") {
		@Override
		public String getDataFromPage(MagicGridRow row) {
			return row.getStockSymbol();
		}
	},
	DESCRIPTION("Description") {
		@Override
		public String getDataFromPage(MagicGridRow row) {
			return row.getStockName();
		}
	},
	QUANTITY("Quantity") {
		@Override
		public String getDataFromPage(MagicGridRow row) {
			return row.getValue(PositionsTabColumnsEnum.QUANTITY);
		}
	},
	LAST_PRIC("Last Price") {
		@Override
		public String getDataFromPage(MagicGridRow row) {
			return row.getValue(PositionsTabColumnsEnum.LAST_PRICE_PRICE);
		}
	},
	LAST_PRICE_CHANGE("Last Price Change") {
		@Override
		public String getDataFromPage(MagicGridRow row) {
			return row.getValue(PositionsTabColumnsEnum.LAST_PRICE_CHANGE);
		}
	},
	CURRENT_VALUE("Current Value") {
		@Override
		public String getDataFromPage(MagicGridRow row) {
			return row.getValue(PositionsTabColumnsEnum.CURRENT_VALUE);
		}
	},
	TODAYS_GAIN_LOSS_DOLLAR("Today's Gain/Loss Dollar") {
		@Override
		public String getDataFromPage(MagicGridRow row) {
			return row.getValue(PositionsTabColumnsEnum.TODAYS_CHANGE_DOLLAR);
		}
	},
	TODAYS_GAIN_LOSS_PERCENT("Today's Gain/Loss Percent") {
		@Override
		public String getDataFromPage(MagicGridRow row) {
			return row.getValue(PositionsTabColumnsEnum.TODAYS_CHANGE_PERCENT);
		}
	},
	TOTAL_GAIN_LOSS_DOLLAR("Total Gain/Loss Dollar") {
		@Override
		public String getDataFromPage(MagicGridRow row) {
			return row.getValue(PositionsTabColumnsEnum.TOTAL_CHANGE_DOLLAR);
		}
	},
	TOTAL_GAIN_LOSS_PERCENT("Total Gain/Loss Percent") {
		@Override
		public String getDataFromPage(MagicGridRow row) {
			return row.getValue(PositionsTabColumnsEnum.TOTAL_CHANGE_PERCENT);
		}
	},
	COST_BASIS_PER_SHARE("Cost Basis Per Share") {
		@Override
		public String getDataFromPage(MagicGridRow row) {
			String value = row.getValue(PositionsTabColumnsEnum.COST_BASIS_PER_SHARE);
			// this is a fix while getValue method for column 'Cost Basic' retrieves text from child of its element
			if (value.endsWith("Share")) {
				value = value.replace("/Share", "");
			}
			return value;
		}
	},
	COST_BASIS_TOTAL("Cost Basis Total") {
		@Override
		public String getDataFromPage(MagicGridRow row) {
			return row.getValue(PositionsTabColumnsEnum.COST_BASIS_TOTAL);
		}
	};
	PositionsCsvAndRowBinder(String title) {
		this.title = title;
	}

	String title;

	@Override
	public String toString() {
		return title;
	}

	abstract public String getDataFromPage(MagicGridRow row);
}
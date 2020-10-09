package com.capgemini.mrchecker.selenium.mts.datadrivem;

import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.aggregator.ArgumentsAggregationException;
import org.junit.jupiter.params.aggregator.ArgumentsAggregator;

import com.capgemini.mrchecker.common.mts.data.User;

public class UserAggregator implements ArgumentsAggregator {
	
	@Override
	public Object aggregateArguments(ArgumentsAccessor argumentsAccessor, ParameterContext parameterContext) throws ArgumentsAggregationException {
		return new User(argumentsAccessor.getString(0),
				argumentsAccessor.getString(1));
	}
}

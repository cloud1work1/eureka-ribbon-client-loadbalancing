package com.techlabs.shristi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RoundRobinRule;
import com.netflix.loadbalancer.WeightedResponseTimeRule;

@Configuration
public class RibbonConfig {

	@Bean
	public IRule rule() {
		return new WeightedResponseTimeRule();
		// return new AvailabilityFilteringRule();
		// return new RoundRobinRule();
	}
}

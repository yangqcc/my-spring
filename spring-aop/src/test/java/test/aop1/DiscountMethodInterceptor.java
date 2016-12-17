/*
 * Copyright 2002-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package test.aop1;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import groovy.lang.IntRange;

/**
 * 
 * @author yangqc
 * @since 5.0
 */
public class DiscountMethodInterceptor implements MethodInterceptor {

	private static final Integer DEFAULT_DISCOUNT_RATIO = 80;

	private static final IntRange RATIO_RANGE = new IntRange(5, 95);

	private Integer discountRation = DEFAULT_DISCOUNT_RATIO;

	private boolean campaingnAvailable;

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		Object returnValue = invocation.proceed();
		if (RATIO_RANGE.contains(getDiscountRation()) && getCampaingnAvailable()) {
			return ((Integer) returnValue) * getDiscountRation() / 100;
		}
		return returnValue;
	}

	/**
	 * @return the discountRation
	 */
	public Integer getDiscountRation() {
		return discountRation;
	}

	/**
	 * @param discountRation the discountRation to set
	 */
	public void setDiscountRation(Integer discountRation) {
		this.discountRation = discountRation;
	}

	/**
	 * @return the campaingnAvailable
	 */
	public boolean getCampaingnAvailable() {
		return campaingnAvailable;
	}

	/**
	 * @param campaingnAvailable the campaingnAvailable to set
	 */
	public void setCampaingnAvailable(boolean campaingnAvailable) {
		this.campaingnAvailable = campaingnAvailable;
	}

}

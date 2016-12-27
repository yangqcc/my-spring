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

package myTest.aop;

import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.NameMatchMethodPointcutAdvisor;

/**
 * 
 * @author yangqc
 * @since 5.0
 */
public class TestAop {

	public static void main(String[] args) {
		Apple apple = new Apple("apple");
		ProxyFactory proxyFactory = new ProxyFactory(apple);
		proxyFactory.setInterfaces(new Class[] { IFruit.class });
		NameMatchMethodPointcutAdvisor advisor = new NameMatchMethodPointcutAdvisor();
		advisor.setAdvice(new PerformanceMethodInterceptor());
		advisor.addMethodName("sayName");
		proxyFactory.addAdvisor(advisor);
		IFruit fruit = (IFruit) proxyFactory.getProxy();
		fruit.sayName();
	}
}

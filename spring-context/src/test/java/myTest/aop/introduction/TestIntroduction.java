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

package myTest.aop.introduction;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 
 * @author yangqc
 * @since 5.0
 */
public class TestIntroduction {

	public static void main(String[] args) {
		//给代理对象添加新的接口
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		Object proxy1 = ctx.getBean("introduceTask");
		Object proxy2 = ctx.getBean("introduceTask");
		System.out.println(((ICounter) proxy1).getCounter());
		System.out.println(((ICounter) proxy1).getCounter());
		System.out.println(((ICounter) proxy2).getCounter());
	}
}

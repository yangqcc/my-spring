/*
 * Copyright 2002-2017 the original author or authors.
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

package myTest.aopannotation;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

/**
 * 
 * @author yangqc
 * @since 5.0
 */
@Aspect
public class AspectJTest {

	@Pointcut("execution(public void myTest.aopannotation..*.test(..))")
	public void test() {
	}

	@Pointcut("execution(public String *.getString(..))")
	public void getString() {
	}

	@Before("test()")
	public void beforeTest() {
		System.out.println("beforeTest");
	}

	@After("test()")
	public void afterTest() {
		System.out.println("AfterTest");
	}

	@AfterReturning
	public void afterReturnTest(JoinPoint p) {
		System.out.println("xixi!");
	}
	
//	@Around("test()")
//	public Object aroundTest(ProceedingJoinPoint p) {
//		System.out.println("before1");
//		Object object = null;
//		try {
//			object = p.proceed();
//		}
//		catch (Throwable e) {
//			e.printStackTrace();
//		}
//		System.out.println("after1");
//		return object;
//	}

	@Around("getString()")
	public Object aroundTest2(ProceedingJoinPoint p) {
		Object object = null;
		try {
			object = p.proceed();
		}
		catch (Throwable e) {
			e.printStackTrace();
		}
		object = ((String) object) + "hh";
		return object;
	}
}

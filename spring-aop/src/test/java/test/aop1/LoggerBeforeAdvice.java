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

import java.lang.reflect.Method;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;

/**
 * 
 * @author yangqc
 * @since 5.0
 */
public class LoggerBeforeAdvice implements MethodBeforeAdvice {

	@Override
	public void before(Method method, Object[] args, Object target) throws Throwable {
		System.out.println("=======保存更新日志=======");
	}

	public static void main(String[] args) {
		UserService target = new UserServiceImpl();
		ProxyFactory pf = new ProxyFactory();
		pf.setTarget(target);
		pf.addAdvice(new SecurityInterceptor());
		pf.addAdvisor(new DefaultPointcutAdvisor(new LoggerBeforeAdvice()));
		UserService proxy = (UserService) pf.getProxy();
		proxy.updateUser();
	}
}

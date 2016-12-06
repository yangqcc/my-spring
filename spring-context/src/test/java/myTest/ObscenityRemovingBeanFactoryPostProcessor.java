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

package myTest;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionVisitor;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.util.StringValueResolver;

/**
 * 
 * @author yangqc
 * @since 5.0
 */
public class ObscenityRemovingBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

	private Set<String> obscenties;

	public ObscenityRemovingBeanFactoryPostProcessor() {
		this.obscenties = new HashSet<String>();
	}

	
	/* 
	 * 这个方法用于修改BeanDefinition的定义
	 * (non-Javadoc)
	 * @see org.springframework.beans.factory.config.BeanFactoryPostProcessor#postProcessBeanFactory(org.springframework.beans.factory.config.ConfigurableListableBeanFactory)
	 */
	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		String[] beanNames = beanFactory.getBeanDefinitionNames();
		for (String beanName : beanNames) {
			BeanDefinition bd = beanFactory.getBeanDefinition(beanName);
			/**
			 * 注意，这里可能把beanName过滤掉
			 */
			StringValueResolver valueResover = new StringValueResolver() {

				@Override
				public String resolveStringValue(String strVal) {
					if (isObscene(strVal)) {
						for (String beanName : beanNames) {
							if (strVal.equals(beanName)) {
								return strVal;
							}
						}
						return "*****";
					}
					return strVal;
				}
			};
			BeanDefinitionVisitor visitor = new BeanDefinitionVisitor(valueResover);
			visitor.visitBeanDefinition(bd);
		}
	}

	public boolean isObscene(Object value) {
		String potentialObscenity = value.toString().toUpperCase();
		return this.obscenties.contains(potentialObscenity);
	}
	
	public void setObscenties(Set<String> obscenties) {
		this.obscenties.clear();
		for (String obscenity : obscenties) {
			this.obscenties.add(obscenity.toUpperCase());
		}
	}
}

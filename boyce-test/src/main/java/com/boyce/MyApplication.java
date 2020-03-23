package com.boyce;

		import com.boyce.bean.Hello;
		import org.springframework.context.ApplicationContext;
		import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author jianw.li
 * @date 2020/3/16 11:53 PM
 * @Description: TODO
 */
public class MyApplication {
	private static final String CONFIG_LOCATION = "classpath:application_context.xml";

	private static final String BEAN_NAME = "hello";

	public static void main(String[] args) {
		ApplicationContext ac = new ClassPathXmlApplicationContext(CONFIG_LOCATION);
		Hello hello = (Hello)ac.getBean(BEAN_NAME);
		hello.sayHello();
	}
}

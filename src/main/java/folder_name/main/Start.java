package folder_name.main;

import folder_name.objects.FileManager;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import folder_name.objects.SomeService;

/**
 * До прикладу 006_AOP додати виведення назви теки. Вивести статистику за розширеннями.
 */

public class Start {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
	/*	SomeService service = (SomeService) context.getBean("someService");
		double val = service.getDoubleValue();
		int val2 = service.getIntValue();*/

		FileManager fileManager = (FileManager) context.getBean("fileManager");

		fileManager.getExtensionCount("c:\\Windows\\system32\\drivers");

	}
}

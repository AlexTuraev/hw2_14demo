package hw2_14.com.example.hw2_14demo;

import hw2_14.com.example.hw2_14demo.stringlist.StringList;
import hw2_14.com.example.hw2_14demo.stringlist.StringListImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Hw214demoApplication {

	public static void main(String[] args) {
		SpringApplication.run(Hw214demoApplication.class, args);

		// ---------------------
		StringList stringList = new StringListImpl(6);
		stringList.add("1");
		stringList.add("2");
		stringList.add("3");
		stringList.add("1");
		stringList.add("5");
		//stringList.add("6");
		stringList.add(3, "новый элемент");
		stringList.set(1, "новый");

		stringList.printArray();

		stringList.remove("5");
		System.out.println();
		stringList.printArray();

		stringList.remove(2);
		System.out.println();
		stringList.printArray();

		System.out.println("stringList.indexOf(\"1\") = " + stringList.indexOf("1"));
		// --------------------
		StringList stringList1 = new StringListImpl(6);
		stringList1.add("1");
		stringList1.add("2");
		stringList1.add("3");
		stringList1.add("1");
		stringList1.add("5");

		StringList stringList2 = new StringListImpl(6);
		stringList2.add("1");
		stringList2.add("2");
		stringList2.add("3");
		stringList2.add("1");
		stringList2.add("5");

		System.out.println("stringList1.equals(stringList2) = " + stringList1.equals(stringList2));

	}

}

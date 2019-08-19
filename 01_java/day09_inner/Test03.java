package day09_inner;

import day09.Command;

public class Test03 {

	public static void main(String[] args) {
		Command delete = () -> System.out.println("Delete Command");
		Command update = () -> System.out.println("Update Command");
		Command create = () -> System.out.println("Create Command");
		Command list = () -> System.out.println("List Command");

		
		
		delete.exec();
		update.exec();
	}

}

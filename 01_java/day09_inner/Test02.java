package day09_inner;

import javax.swing.JOptionPane;

import day09.Command;

public class Test02 {

	public static void main(String[] args) {
		Command delete = new Command() {
			@Override
			public void exec() {
				System.out.println("Delete Command");
			}
		};

		Command update = new Command() {
			@Override
			public void exec() {
				System.out.println("Delete Command");
			}
		};

		Command create = new Command() {
			@Override
			public void exec() {
				System.out.println("Delete Command");
			}
		};

		Command list = new Command() {
			@Override
			public void exec() {
				System.out.println("List Command");
			}
		};

		String msg = JOptionPane.showInputDialog("수행할 명령을 입력하세요. (create,list,delete,update)");
		switch (msg.trim().toLowerCase()) {
		case "create":
			create.exec();
			break;
		case "list":
			list.exec();
			break;
		case "delete":
			delete.exec();
			break;
		case "update":
			update.exec();
			break;
		default:
			System.out.println("다시 입력하세요.");
		}


	}

}

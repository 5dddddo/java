package day09;
@FunctionalInterface
public interface Command {
	void exec();
	default public void process() {
		System.out.println("process()");
	}
}

class DeleteCommand implements Command {

	@Override
	public void exec() {
		System.out.println("Delete Command 수행");
	}

}

class CreateCommand implements Command {

	@Override
	public void exec() {
		System.out.println("Create Command 수행");
	}

}

class UpdateCommand implements Command {

	@Override
	public void exec() {
		System.out.println("Update Command 수행");
	}

}

class ListCommand implements Command {

	@Override
	public void exec() {
		System.out.println("List Command 수행");
	}
}
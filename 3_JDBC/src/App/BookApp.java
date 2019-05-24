package App;

import java.util.List;

import dao.BookDao;
import service.BookService;
import service.BookServiceImp;
import vo.BookVO;

public class BookApp {

	public static void main(String[] args) {
		BookDao dao = new BookDao();
		BookService service = new BookServiceImp(dao); // BookDao 생성해서 사용할 DAO 객체 지정해 줌

		BookVO b = new BookVO("111", "오은애", "19/05/17");
		try {
			System.out.println(service.addBook(b));
		} catch (Exception e) {
			System.out.println("책 정보 확인 필요");
		}
		if (service != null) {
			List<BookVO> list = service.bookList();
			list.forEach(i -> System.out.println(i));
		}
	}
}

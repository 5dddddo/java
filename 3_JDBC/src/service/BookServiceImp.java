package service;

import java.util.List;

import dao.BookDao;
import vo.BookVO;

public class BookServiceImp implements BookService {

//	private BookDao dao = new BookDao(); //dao가 고정 됨. 외부에서 바꿀 수 없는 객체로 선언함.
	private BookDao dao = null;

	public BookServiceImp() {
		super();
	}

	public BookServiceImp(BookDao dao) {
		super();
		this.dao = dao;
	}

	public BookDao getDao() {
		return dao;
	}

	public void setDao(BookDao dao) {
		this.dao = dao;
	}

	@Override
	public List<BookVO> bookList() {
		return dao.booklist();
	}

	@Override
	public int addBook(BookVO vo) throws Exception { 
		return dao.addBook(vo);

	}

}

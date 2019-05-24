package App;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import dao.BookDao;
import service.BookService;
import service.BookServiceImp;
import vo.BookVO;

public class GUIBook {

	BookDao dao = new BookDao();
	BookService service = new BookServiceImp(dao);

	Frame f = null; // 브라우저 역할
	TextArea textArea = null;
	TextField title, price, author;
	Button list, insert;

	public GUIBook() {
		f = new Frame("Book App");
		textArea = new TextArea(20, 40);
		f.add(textArea, BorderLayout.NORTH);

		list = new Button("LIST");
		insert = new Button("INSERT");
		Panel p = new Panel();
		p.add(list);
		p.add(insert);
		f.add(p, BorderLayout.SOUTH);

		f.setSize(400, 400);
		f.setVisible(true); // frame 표시 - true
		// event handling
		// call-back method : 클릭했을 때 실행
		list.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				List<BookVO> data = service.bookList();
				for (BookVO d : data) {
					textArea.append(d.toString() + "\n");

				}
			}
		});
	}

	public static void main(String[] args) {

		new GUIBook();

	}

}

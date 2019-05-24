package App;

import java.util.List;

import dao.DeptManager;
import service.DeptService;
import service.DeptServiceImp;
import vo.DeptVO;

public class deptApp {

	public static void main(String[] args) {

		DeptManager dm = new DeptManager();
		DeptService service = new DeptServiceImp(dm);
		if (service != null) {
			List<DeptVO> list = service.getDepts();
			list.forEach(i -> System.out.println(i));
		}
	}

}

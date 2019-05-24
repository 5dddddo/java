package service;

import java.util.List;

import dao.DeptManager;
import vo.DeptVO;

public class DeptServiceImp implements DeptService {
	DeptManager d = null;

	public DeptServiceImp() {
		super();
	}

	public DeptServiceImp(DeptManager d) {
		super();
		this.d = d;
	}

	public DeptManager getD() {
		return d;
	}

	public void setD(DeptManager d) {
		this.d = d;
	}

	@Override
	public List<DeptVO> getDepts() {
		return d.getDepts();
	}

}

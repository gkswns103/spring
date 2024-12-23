package dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import vo.DeptVO;

@Repository("dept_dao")
public class DeptDAO {
	
	//auto-detecting기능은 생성자&셋터 인젝션을 사용할 수 없으므로,
	//SqlSession객체도 Autowired로 생성해야 한다
	@Autowired
	SqlSession sqlSession;
	
	public List<DeptVO> selectList(){
		List<DeptVO> list = sqlSession.selectList("d.dept_list");
		
		return list;
	}
}

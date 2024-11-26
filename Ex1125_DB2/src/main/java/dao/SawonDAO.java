package dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import vo.SawonVO;

public class SawonDAO {

	SqlSession sqlSession;
	
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
		System.out.println("sawon dao");
	}
	
	public List<SawonVO> selectList(){
		List<SawonVO> list = sqlSession.selectList("s.select_list");
		
		return list;
	}
}

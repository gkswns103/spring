package dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import vo.SawonVO;

public class SawonDAO {

	SqlSession sqlSession;
	
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	public List<SawonVO> select_list(int deptno){
		List<SawonVO> list = sqlSession.selectList("s.select_list",deptno);
		
		return list;
	}
	
	public SawonVO select_sawon(int sabun) {
		SawonVO vo = sqlSession.selectOne("s.select_sawon",sabun);
		
		return vo;
	}
	
	public int delete_sawon(int sabun) {
		int res = sqlSession.delete("s.delete_sawon",sabun);

		return res;
	}
	
	public int update_sawon(SawonVO vo) {
		int res = sqlSession.update("s.update_sawon", vo);
		
		return res;
	}
	
	public int insert_sawon(SawonVO vo) {
		int res = sqlSession.insert("s.insert_sawon", vo);
		
		return res;
	}
}

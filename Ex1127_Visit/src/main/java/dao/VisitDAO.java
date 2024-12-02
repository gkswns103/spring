package dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import vo.VisitVO;

public class VisitDAO {
	
	SqlSession sqlSession;
	
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	public List<VisitVO> select_list(Map<String,Integer> map){
		List<VisitVO> list = sqlSession.selectList("v.select_list",map);
		
		return list;
	}
	
	public int insert_visit(VisitVO vo) {
		int res = sqlSession.insert("v.insert_visit",vo);
		
		return res;
	}
	
	public VisitVO select_one(int idx) {
		VisitVO vo = sqlSession.selectOne("v.select_one",idx);
		
		return vo;
	}
	
	public int update_visit(VisitVO vo) {
		int res = sqlSession.update("v.update_visit", vo);
		
		return res;
	}
	
	public int delete_visit(int idx) {
		int res = sqlSession.delete("v.delete_visit",idx);
		
		return res;
	}
	
	public int getRowTotal() {
		int count = sqlSession.selectOne("v.visit_count");
		
		return count;
	}
}

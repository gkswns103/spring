package dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import vo.GogekVO;

public class GogekDAO {

	SqlSession sqlSession;
	
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	public List<GogekVO> select_list(int sabun){
		List<GogekVO> list = sqlSession.selectList("g.select_list",sabun);
		
		return list;
	}
}

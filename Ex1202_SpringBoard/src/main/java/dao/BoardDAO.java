package dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import vo.BoardVO;

public class BoardDAO {

	SqlSession sqlSession;
	
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	//전체 게시글 조회
	public List<BoardVO> selectList( Map<String,Object> map){
		List<BoardVO> list = sqlSession.selectList("b.board_list",map);
		
		return list;
	}
	
	public int getRowTotal(Map<String,Object> map) {
		int count = sqlSession.selectOne("b.board_count",map);

		return count;
	}
	
	public int insertBoard(BoardVO vo) {
		int res = sqlSession.insert("b.insert",vo);
		
		return res;
	}
	
	public BoardVO selectOne(int idx) {
		BoardVO vo = sqlSession.selectOne("b.select_one",idx);
		
		return vo;
	}
	
	public int update_readhit(int idx) {
		int res = sqlSession.update("b.board_update_readhit",idx);
		
		return res;
	}
	
	//댓글 처리를 위한 step + 1
	public int update_step(BoardVO baseVO) {
		int res = sqlSession.update("b.board_update_step",baseVO);
		
		return res;
	}
	
	public int reply(BoardVO vo) {
		int res = sqlSession.insert("b.board_reply",vo);
		
		return res;
	}
	
	//삭제(된 것 처럼 업데이트)
	public int del_update(int idx) {
		int res = sqlSession.update("b.board_del_update",idx);	
		return res;
	}
	
	public int update(BoardVO vo) {
		int res = sqlSession.update("b.board_update",vo);
		
		return res;
	}
}

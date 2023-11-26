package o2.example.diary.assign.service;


import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service("AssignService")
public class AssignService {

	@Autowired
	@Resource(name="sqlSession")
	private SqlSessionTemplate sqlSession;

	public List<Map<String, Object>> getLoginData(Map<String, Object> param) {
		return sqlSession.selectList("assign.getLoginData", param);
	}

	public void insartAssignData(Map<String, Object> param) {
		sqlSession.insert("assign.insartAssignData", param);
	}

	public int chackPhoneNember(Map<String, Object> param) {
		return sqlSession.selectOne("assign.chackPhoneNember", param);
	}

	public void modifyUserPasswordData(Map<String, Object> param) {
		sqlSession.update("assign.updatePasswordData", param);
	}



}

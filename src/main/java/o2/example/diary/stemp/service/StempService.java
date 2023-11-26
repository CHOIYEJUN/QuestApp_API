package o2.example.diary.stemp.service;


import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service("StempService")
public class StempService {

    @Autowired
    @Resource(name="sqlSession")
    private SqlSessionTemplate sqlSession;


    public void insertStempData(Map<String, Object> param) {
        sqlSession.insert("stemp.insertStempData", param);
    }

    public int checkTodayStemp(Map<String, Object> param) {
        return sqlSession.selectOne("stemp.checkTodayStemp", param);
    }

    public void insertCheckOtherDay(Map<String, Object> param) {
        sqlSession.insert("stemp.insertCheckOtherDay", param);
    }

    public List<Map<String, Object>> getStempData(Map<String, Object> param) {
        return sqlSession.selectList("stemp.getStempData", param);
    }

    public void deleteStemp(Map<String, Object> param) {
        sqlSession.delete("stemp.deleteStemp", param);
    }
}

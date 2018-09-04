package sys.dao;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

@Repository("MysqlDAOImplement")
public class MysqlDAOImplement  implements MysqlDAOInterface {
	@Resource(name = "sqlSessionTemplate")
	protected SqlSessionTemplate sqlSessionTemplate;

	public Object findForList(String str, Object obj) throws Exception {
		return sqlSessionTemplate.selectList(str, obj);
	}

	public Object findForMap(String str, Object obj, String key, String value) throws Exception {
		return sqlSessionTemplate.selectMap(str, obj, key);
	}

	public SqlSession getSqlSession() {
		return sqlSessionTemplate.getSqlSessionFactory().openSession();
	}

	public void killSqlSession(SqlSession session) {
		session.clearCache();
		session.close();
	}
	@Override
	public Object save(String str, Object obj) throws Exception {
		return sqlSessionTemplate.insert(str, obj);
	}

	@Override
	public Object update(String str, Object obj) throws Exception {
		return sqlSessionTemplate.update(str, obj);
	}

	@Override
	public Object delete(String str, Object obj) throws Exception {
		return sqlSessionTemplate.delete(str, obj);
	}

	@Override
	public Object findForObject(String str, Object obj) throws Exception {
		return sqlSessionTemplate.selectOne(str, obj);
	}

}

package sys.service.impl;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sys.dao.MysqlDAOImplement;
import sys.dao.MysqlDAOInterface;
import sys.domain.Result;
import sys.domain.sysUserInfo;
import sys.service.userService;

@Service
@SuppressWarnings({ "unchecked", "rawtypes" })
public class userServiceImpl implements userService{
	@Resource(name = "MysqlDAOImplement")
	private MysqlDAOImplement dao;
	@Override
	public Result getUserInfo(String a,String p){
		try{
			sysUserInfo user = (sysUserInfo)dao.findForObject("sysUserMapper.selectUserInfo", a);
			return new Result("true","succuss",user);
		}catch(Exception e){
			return new Result("false","error");
		}
	}
	
}

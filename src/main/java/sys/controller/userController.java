package sys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import sys.annotation.noNull;
import sys.domain.Result;
import sys.service.userService;

@Api("用户管理接口")
@RestController
@RequestMapping("/user")
@SuppressWarnings("rawtypes")
public class userController {
	@Autowired
	userService useService;
	
	@ApiOperation("登录接口")
	@RequestMapping(value = "/insertAssess", method = RequestMethod.GET)
	@noNull(str = "pid,sid")
	public Result userLogin(
			@RequestParam(value = "account",required=false) String account,
			@RequestParam(value = "password",required=false) String password
			){
		return useService.getUserInfo(account,password);
	}
}

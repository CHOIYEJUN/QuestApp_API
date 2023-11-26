package o2.example.diary.assign.web;


import o2.example.diary.assign.service.AssignService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import util.ObjectMapperSupport;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "")
public class AssignController {
	private static final Logger LOGGER = LoggerFactory.getLogger(AssignController.class);
	@Resource(name = "AssignService")
	private AssignService AssignService;

	// 유저가 입력한 아이디와 비밀번호를 받아서 DB에 있는지 확인한다.
	@RequestMapping(produces = "application/json; charset=UTF-8" , value = "/getLoginData" , method = RequestMethod.GET)
	@ResponseBody
	public String getLoginData(HttpServletRequest request,  @RequestParam Map<String, Object> param ) {
		Map<String, Object> result = new HashMap<String, Object>();

		try {

			List<Map<String, Object>> rData = AssignService.getLoginData(param);

			result.put("SUCCESS", true);
			result.put("RESULT", rData);

		}catch (NullPointerException e) {
			LOGGER.error(e.getMessage());
			result.put("SUCCESS", false);
			result.put("MSG", e);
		}

		return ObjectMapperSupport.objectToJson(result);
	}

	// 유저가 입력한 회원가입 정보로 회원 정보를 인서트 한다.
	@RequestMapping(produces = "application/json; charset=UTF-8" , value = "/insartAssignData" , method = RequestMethod.GET)
	@ResponseBody
	public String insartAssignData(HttpServletRequest request,  @RequestParam Map<String, Object> param ) {
		Map<String, Object> result = new HashMap<String, Object>();

		try {

			AssignService.insartAssignData(param);
			result.put("SUCCESS", true);

		}catch (NullPointerException e) {
			LOGGER.error(e.getMessage());
			result.put("SUCCESS", false);
			result.put("MSG", e);
		}

		return ObjectMapperSupport.objectToJson(result);
	}

	@RequestMapping(produces = "application/json; charset=UTF-8" , value = "/chackPhoneNember" , method = RequestMethod.GET)
	@ResponseBody
	public String chackPhoneNember(HttpServletRequest request,  @RequestParam Map<String, Object> param ) {
		Map<String, Object> result = new HashMap<String, Object>();

		try {

			int rData = AssignService.chackPhoneNember(param);
			result.put("SUCCESS", true);
			result.put("RESULT", rData);

		}catch (NullPointerException e) {
			LOGGER.error(e.getMessage());
			result.put("SUCCESS", false);
			result.put("MSG", e);
		}

		return ObjectMapperSupport.objectToJson(result);
	}

	@RequestMapping(produces = "application/json; charset=UTF-8" , value = "/checkPhoneNumber" , method = RequestMethod.GET)
	@ResponseBody
	public String checkPhoneNumber( HttpServletRequest request, @RequestParam Map<String, Object> param ){
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			int rData = AssignService.chackPhoneNember(param);

			if (rData > 0 ) {
				result.put("SUCCESS", false);
				result.put("MSG", "이미 등록된 전화번호 입니다.");
			} else {
				result.put("SUCCESS", true);
				result.put("MSG", "사용 가능한 전화번호 입니다.");
			}
		}catch(NullPointerException e){
			LOGGER.error(e.getMessage());
			result.put("SUCCESS", false);
			result.put("MSG", e);

		}

		return ObjectMapperSupport.objectToJson(result);
	}



}

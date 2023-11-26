package o2.example.diary.stemp.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import util.ObjectMapperSupport;
import o2.example.diary.stemp.service.StempService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Controller
@RequestMapping(value = "")
public class StempController {
    private static final Logger LOGGER = LoggerFactory.getLogger(o2.example.diary.stemp.service.StempService.class);
    @Resource(name = "StempService")
    private StempService StempService;

    @RequestMapping(produces = "application/json; charset=UTF-8" , value = "/getStempData" , method = RequestMethod.GET)
    @ResponseBody
    public String getStempData(HttpServletRequest request,  @RequestParam Map<String, Object> param ) {
        Map<String, Object> result = new HashMap<String, Object>();

        try {

            List<Map<String, Object>> rData = StempService.getStempData(param);
            result.put("SUCCESS", true);
            result.put("RESULT", rData);
        }catch (NullPointerException e) {
            LOGGER.error(e.getMessage());
            result.put("SUCCESS", false);
            result.put("MSG", e);
        }

        return ObjectMapperSupport.objectToJson(result);
    }

    @RequestMapping(produces = "application/json; charset=UTF-8" , value = "/insertStempData" , method = RequestMethod.GET)
    @ResponseBody
    public String insertStempData(HttpServletRequest request,  @RequestParam Map<String, Object> param ) {
        Map<String, Object> result = new HashMap<String, Object>();

        try {

            int checkTodayStemp = StempService.checkTodayStemp(param);

            if(checkTodayStemp > 0) {
                result.put("SUCCESS", false);
                result.put("RESTEMP", true);
                result.put("MSG", "이미 스템프를 찍었습니다.");
                return ObjectMapperSupport.objectToJson(result);
            }
            if(checkTodayStemp == 0) {
                StempService.insertStempData(param);
                result.put("SUCCESS", true);
                result.put("RESTEMP", false);
                result.put("MSG", "스템프 찍기 성공");
            }
        }catch (NullPointerException e) {
            LOGGER.error(e.getMessage());
            result.put("SUCCESS", false);
            result.put("MSG", e);
        }

        return ObjectMapperSupport.objectToJson(result);
    }

    @RequestMapping(produces = "application/json; charset=UTF-8" , value = "/insertCheckOtherDay" , method = RequestMethod.GET)
    @ResponseBody
    public String insertCheckOtherDay(HttpServletRequest request,  @RequestParam Map<String, Object> param ) {
        Map<String, Object> result = new HashMap<String, Object>();

        try {
                StempService.insertCheckOtherDay(param);
                result.put("SUCCESS", true);
                result.put("MSG", "스템프 찍기 성공");
        }catch (NullPointerException e) {
            LOGGER.error(e.getMessage());
            result.put("SUCCESS", false);
            result.put("MSG", e);
        }

        return ObjectMapperSupport.objectToJson(result);
    }
}

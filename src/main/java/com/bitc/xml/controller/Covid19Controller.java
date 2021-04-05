package com.bitc.xml.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bitc.xml.dto.Covid19InfoStateItemDto;
import com.bitc.xml.service.Covid19Service;

@Controller
public class Covid19Controller {
	@Autowired
	private Covid19Service covid19Service;
	
	@RequestMapping(value="/covid19/infoState", method=RequestMethod.GET)
	public ModelAndView infoState() throws Exception {
		ModelAndView mv = new ModelAndView("/covid19/infoState");
		
		List<Covid19InfoStateItemDto> list = covid19Service.getItemList();
		mv.addObject("covid19Data", list);
		
		return mv;
	}
	
	@ResponseBody
	@RequestMapping(value="/covid19/infoState", method=RequestMethod.POST)
	public Object getInfoState() throws Exception {
		List<Covid19InfoStateItemDto> list = covid19Service.getItemList();
		
		return list;
	}
	
	@ResponseBody
	@RequestMapping(value="/covid19/infoState2", method=RequestMethod.POST)
	public Object getInfoState2(@RequestParam Map<String, String> param) throws Exception {
		
		String endPoint = "http://openapi.data.go.kr/openapi/service/rest/Covid19/";
		String serviceFunc = "getCovid19InfStateJson?";
		String keyFunc = "serviceKey=";
		String pageNo = "&pageNo=1";
		String rows = "&numOfRows=10";
		String startCreateDt = "&startCreateDt=";
		String endCreateDt = "&endCreateDt=";
		
		String serviceKey = "MQ8moCpDCqz42c3kxwz2LHfm%2BevXvXYOTYnrZjpLTYgYqArm4QD7hxWxCGM%2F24BBmgODLVMFUM6mAAH4XCFQcg%3D%3D";
		
		String strUrl = endPoint + serviceFunc + keyFunc + serviceKey + pageNo + rows + startCreateDt + param.get("startCreateDt") + endCreateDt + param.get("endCreateDt");
		
		List<Covid19InfoStateItemDto> itemList = covid19Service.getItemListRange(strUrl);
		
		return itemList;
	}
	
	@ResponseBody
	@RequestMapping(value="/covid19/infoState3", method=RequestMethod.POST)
	public Object getInfoState3(@RequestParam Map<String, String> param) throws Exception {
		
//		ajax 통신을 통해서 매개변수로 시작날짜와 종료 날짜를 받아서 공공데이터 포털에서 데이터를 가져오기 위한 준비를 함
		String endPoint = "http://openapi.data.go.kr/openapi/service/rest/Covid19/";
		String serviceFunc = "getCovid19InfStateJson?";
		String keyFunc = "serviceKey=";
		String pageNo = "&pageNo=1";
		String rows = "&numOfRows=10";
		String startCreateDt = "&startCreateDt=";
		String endCreateDt = "&endCreateDt=";
		
		String serviceKey = "MQ8moCpDCqz42c3kxwz2LHfm%2BevXvXYOTYnrZjpLTYgYqArm4QD7hxWxCGM%2F24BBmgODLVMFUM6mAAH4XCFQcg%3D%3D";
		
//		공공데이터 포털에서 데이터를 가져오기 위한 url 생성
		String strUrl = endPoint + serviceFunc + keyFunc + serviceKey + pageNo + rows + startCreateDt + param.get("startCreateDt") + endCreateDt + param.get("endCreateDt");
		
		
//		서비스를 통해서 xml 데이터를 가져오기
//		가져온 xml 데이터와 DB에 저장되어 있는 기존의 데이터를 비교
//		새로운 데이터만 DB에 저장
//		지정한 기간에 맞는 데이터를 sql문을 통해서 DB에서 가져옴
		
		List<Covid19InfoStateItemDto> itemList = covid19Service.getItemListRange(strUrl);
		
//		DB에서 들고 오는 것임, 지금은 필요 없어서 주석
//		List<Covid19InfoStateItemDto> itemList = covid19Service.getDbItemList();
		
		return itemList;
	}
}











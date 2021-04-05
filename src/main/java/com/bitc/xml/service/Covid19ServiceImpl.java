package com.bitc.xml.service;

import java.io.File;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bitc.xml.dto.Covid19InfoStateDto;
import com.bitc.xml.dto.Covid19InfoStateItemDto;
import com.bitc.xml.mapper.Covid19InfoStateMapper;

@Service
public class Covid19ServiceImpl implements Covid19Service {
	@Autowired
	private Covid19InfoStateMapper covid19Mapper;
	
	public List<Covid19InfoStateItemDto> getItemList() throws Exception {
		JAXBContext jc = JAXBContext.newInstance(Covid19InfoStateDto.class);
		Unmarshaller um = jc.createUnmarshaller();
		
		Covid19InfoStateDto infoState = (Covid19InfoStateDto)um.unmarshal(new File("c://java102//covid19infostate.xml"));
		List<Covid19InfoStateItemDto> list = infoState.getBody().getItems().getItemList();
		
		return list;
	}
	
	public List<Covid19InfoStateItemDto> getItemListRange(String strUrl) throws Exception {
		List<Covid19InfoStateItemDto> itemList = null;
		URL url = null;
		HttpURLConnection urlConn = null;
		
		try {
			url = new URL(strUrl);
			urlConn = (HttpURLConnection)url.openConnection();
			urlConn.setRequestMethod("GET");
			
			JAXBContext jc = JAXBContext.newInstance(Covid19InfoStateDto.class);
			Unmarshaller um = jc.createUnmarshaller();
			
			Covid19InfoStateDto infoData = (Covid19InfoStateDto)um.unmarshal(url);
			itemList = infoData.getBody().getItems().getItemList();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			urlConn.disconnect();
		}
		
		return itemList;
	}
	
	@Override
	public List<Covid19InfoStateItemDto> getDbItemList() throws Exception {
		return covid19Mapper.getDbItemList();
	}
}









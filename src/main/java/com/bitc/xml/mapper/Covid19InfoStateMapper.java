package com.bitc.xml.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.bitc.xml.dto.Covid19InfoStateItemDto;

@Mapper
public interface Covid19InfoStateMapper {
	List<Covid19InfoStateItemDto> getDbItemList() throws Exception;
}

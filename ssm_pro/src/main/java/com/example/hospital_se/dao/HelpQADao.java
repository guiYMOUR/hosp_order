package com.example.hospital_se.dao;

import java.util.List;

import com.example.hospital_se.entity.HelpQA;

public interface HelpQADao {
	//根据类型查找问题
	public List<HelpQA> findQAByType(String questionType);

}

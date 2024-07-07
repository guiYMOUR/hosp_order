package com.example.hospital_se.service;

import java.util.List;

import com.example.hospital_se.entity.HelpQA;

public interface HelpQAService {
	//根据类型查找问题
		public List<HelpQA> findQAByType(String questionType);

}

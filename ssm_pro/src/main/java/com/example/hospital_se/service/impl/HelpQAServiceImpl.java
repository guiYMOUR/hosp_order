package com.example.hospital_se.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.hospital_se.dao.HelpQADao;
import com.example.hospital_se.entity.HelpQA;
import com.example.hospital_se.service.HelpQAService;
@Service
public class HelpQAServiceImpl implements HelpQAService {
	@Autowired
	private HelpQADao helpQADao;
	@Override
	public List<HelpQA> findQAByType(String questionType) {
		// TODO Auto-generated method stub
		return helpQADao.findQAByType(questionType);
	}

}

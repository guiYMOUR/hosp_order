package com.example.hospital_se.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.hospital_se.dao.NoticeDao;
import com.example.hospital_se.entity.Notice;
import com.example.hospital_se.service.NoticeService;
@Service
public class NoticeServiceImpl implements NoticeService {

	@Autowired
	private NoticeDao noticeDao;
	@Override
	public List<Notice> findNoticeByType(int start,int size) {
		// TODO Auto-generated method stub
		return noticeDao.findNoticeByType(start,size);
	}
	@Override
	public int findNoticeByTypeNum() {
		// TODO Auto-generated method stub
		return noticeDao.findNoticeByTypeNum();
	}
	@Override
	public Notice findNoticeById(int id) {
		// TODO Auto-generated method stub
		return noticeDao.findNoticeById(id);
	}

}

package com.example.hospital_se.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.hospital_se.dao.OfficeDao;
import com.example.hospital_se.entity.Hospital;
import com.example.hospital_se.entity.Office;
import com.example.hospital_se.service.OfficeService;

@Service
public class OfficeServiceImpl implements OfficeService {
	@Autowired
	private OfficeDao officeDao;

	@Override
	public List<Office> findOfficeByHosName(String hospitalName) {

		return officeDao.findOfficeByHosName(hospitalName);
	}

	@Override
	public List<Office> findOfficeByRe(Map<String, Object> officeMap) {
		// TODO Auto-generated method stub
		return officeDao.findOfficeByRe(officeMap);
	}

	@Override
	public int findOfficeByReNum(List<Hospital> hospital) {
		// TODO Auto-generated method stub
		return officeDao.findOfficeByReNum(hospital);
	}

	@Override
	public Office findOfficeById(int id) {
		// TODO Auto-generated method stub
		return officeDao.findOfficeById(id);
	}

	@Override
	public List<Office> findOfficeByConditon(Office office, int start, int size) {
		// TODO Auto-generated method stub
		return officeDao.findOfficeByCondition(office.getHospitalName(), office.getOfficesName(), start, size);
	}

	@Override
	public int findOrderOfficeNum(Office office) {
		// TODO Auto-generated method stub
		return officeDao.findOrderOfficeNum(office.getHospitalName(), office.getOfficesName());
	}

	
}

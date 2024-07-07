package com.example.hospital_se.service;

import java.util.List;

import com.example.hospital_se.entity.Area;

public interface AreaService {
	// 根据等级1查询城市名称
	public List<Area> findAreaByLevel();

	// 根据等级2查询城市名称
	public List<Area> findAreaByLevel(String areaName);

	// 根据等级3查询城市名称
	public List<Area> findAreaByLevel(String areaName, String cityName);

}

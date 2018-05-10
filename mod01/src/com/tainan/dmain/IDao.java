package com.tainan.dmain;

import java.util.List;

import javax.sql.DataSource;



//建立泛型DAO
public interface IDao<T> {
	//查詢是否有這一筆紀錄
	public boolean selectForObject(T object);
	//查詢單筆紀錄
	public T selectForSingleObject(Object key);
	//查詢一堆紀錄
	public List<T> selectForList(Object key);
	//會員註冊
	public boolean insert(T object) throws Exception;
	//注入資料來源(Property Injection)
	public void setDataSource(DataSource dataSource);

}

package com.tainan.dmain;

import java.util.List;

import javax.sql.DataSource;



//�إߪx��DAO
public interface IDao<T> {
	//�d�߬O�_���o�@������
	public boolean selectForObject(T object);
	//�d�߳浧����
	public T selectForSingleObject(Object key);
	//�d�ߤ@�����
	public List<T> selectForList(Object key);
	//�|�����U
	public boolean insert(T object) throws Exception;
	//�`�J��ƨӷ�(Property Injection)
	public void setDataSource(DataSource dataSource);

}

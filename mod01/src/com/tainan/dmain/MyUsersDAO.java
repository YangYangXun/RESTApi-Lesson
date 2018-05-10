package com.tainan.dmain;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

public class MyUsersDAO implements IDao<MyUsers>{
	//attribute
	private DataSource dataSource;

	@Override
	public boolean selectForObject(MyUsers object) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public MyUsers selectForSingleObject(Object key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MyUsers> selectForList(Object key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean insert(MyUsers users) throws Exception {
		//1.�z�Ldatasource�n�@���s��
		boolean result=false;
		try {
			Connection connection=this.dataSource.getConnection();
			//�n�@��PreparedStatement 
			String sql="insert into users(username,password,address,phone,sex,email) values(?,?,?,?,?,?)";
			PreparedStatement st=connection.prepareStatement(sql);
			//�]�w�Ѽ�
			st.setString(1, users.getUserName());
			st.setString(2, users.getPassword());
			st.setString(3, users.getAddress());
			st.setString(4, users.getPhone());
			st.setString(5, users.getSex());
			st.setString(6, users.getEmail());
			//����s�W
			int r=st.executeUpdate();
			if(r>0)
			{
				result=true;
			}
			connection.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw e;
		}
		
		return result;
	}

	@Override
	public void setDataSource(DataSource dataSource) {
		this.dataSource=dataSource;
		
	}

}

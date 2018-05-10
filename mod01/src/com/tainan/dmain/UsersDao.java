package com.tainan.dmain;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;




import javax.sql.DataSource;

public class UsersDao implements IDao<Users>{
	//attribute
	private DataSource dataSource;

	@Override
	public boolean selectForObject(Users users) {
		//�z�LDataSource�n�@���s������(�㦳�}��)
		boolean r=false;
		try {
			Connection connection=dataSource.getConnection();
			//�]�w�d�߱ԭz(�ĥΰѼƼg�k�w��SQL Injection)
			String sql="select count(*) as counter from users where username=? and password=?";
			PreparedStatement st=connection.prepareStatement(sql);
			//�]�w�Ѽƭ�
			st.setString(1,users.getUserName() );
			st.setString(2, users.getPassword());
			ResultSet rs=st.executeQuery();
			//�P�_�O�_������
			if(rs.next())
			{
				if(rs.getInt("counter")>0)
				{
					//�|��
					r=true;
				}
			}
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return r;
	}

	@Override
	public Users selectForSingleObject(Object key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Users> selectForList(Object key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setDataSource(DataSource dataSource) {
		this.dataSource=dataSource;
		
	}

	@Override
	public boolean insert(Users object) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

}

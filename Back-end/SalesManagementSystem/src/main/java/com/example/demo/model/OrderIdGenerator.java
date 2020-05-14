package com.example.demo.model;

import java.io.Serializable;
import java.sql.*;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

	public class OrderIdGenerator  implements IdentifierGenerator{
		@Override
		public Serializable generate(SharedSessionContractImplementor session, Object object)
	            throws HibernateException {

	        String prefix = "SO_";
	        Connection connection = session.connection();

	        try {
	            Statement statement=connection.createStatement();

	            ResultSet rs=statement.executeQuery("SELECT so_id FROM db_pharmacy.orders ORDER BY so_id DESC LIMIT 1");

	            if(rs.next())
	            {
	                int id=parseIntOrDefault(rs.getString(1),3,0)+1;
	                String generatedId = prefix +Integer.toString(id);
	                return generatedId;
	            }
	            else
	            {
	            	int id=1;
	            	String generatedId = prefix +Integer.toString(id);
	                return generatedId;
	            }
	        } catch (SQLException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	        return null;
	    }
		public static int parseIntOrDefault(String value, int beginIndex, int defaultValue) {
			  int result = defaultValue;
			  try {
			    String stringValue = value.substring(beginIndex);
			    result = Integer.parseInt(stringValue);
			  }
			  catch (Exception e) {
			  }
			  return result;
			}
	}


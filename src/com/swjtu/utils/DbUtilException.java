package com.swjtu.utils;
import java.util.Hashtable;

public class DbUtilException extends Exception
{
	private Hashtable errors = new Hashtable();
	
	public void setErrorMsg(String err,String errMsg)
	{
		if((err != null) && (errMsg != null))
		{
			errors.put(err,errMsg);
		}
	}
	
	public String getErrorMsg(String err)
	{
		String err_msg = (String)errors.get(err);
		return (err_msg == null) ? "" : err_msg;
	}	
}
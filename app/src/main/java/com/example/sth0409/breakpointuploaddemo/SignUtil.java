package com.example.sth0409.breakpointuploaddemo;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SignUtil {
	  /** 
	   * 字符串 SHA 加密 
	   *  
	   * @param
	   * @return 
	   */  
	  public static String SHA(final String strText, final String strType)  
	  {  
	    String strResult = null;  
	    if (strText != null && strText.length() > 0)  
	    {  
	      try  
	      {  
	        MessageDigest messageDigest = MessageDigest.getInstance(strType);  
	        messageDigest.update(strText.getBytes());  
	        byte byteBuffer[] = messageDigest.digest();  
	  
	        StringBuffer strHexString = new StringBuffer();  
	        for (int i = 0; i < byteBuffer.length; i++)  
	        {  
	          String hex = Integer.toHexString(0xff & byteBuffer[i]);  
	          if (hex.length() == 1)  
	          {  
	            strHexString.append('0');  
	          }  
	          strHexString.append(hex);  
	        }  
	        // 得到返回結果  
	        strResult = strHexString.toString();  
	      }  
	      catch (NoSuchAlgorithmException e)  
	      {  
	        e.printStackTrace();  
	      }  
	    }  
	  
	    return strResult;  
	  }  
	
	
//	public static boolean eccryptLogin(String param,String sign) throws NoSuchAlgorithmException{
//		Date nowDate = new Date();
//		String nowDate0 = DateFormatUtils.format(nowDate, "yyyy-MM-dd HH");
//		String nowDate1 = DateFormatUtils.format(CalenderDate.addHours(nowDate, 1), "yyyy-MM-dd HH");
//		String nowDate2 = DateFormatUtils.format(CalenderDate.addHours(nowDate, -1), "yyyy-MM-dd HH");
//
//
//		String signFlag0 =  param+nowDate0;
//		String signFlag1 =  param+nowDate1;
//		String signFlag2 =  param+nowDate2;
//		System.out.println("param：---"+param);
//		System.out.println("signFlag0：---"+signFlag0);
//        System.out.println(SHA(signFlag0, "SHA-512"));
//        if(StringUtils.equals(sign, SHA(signFlag0, "SHA-512"))||StringUtils.equals(sign, SHA(signFlag1, "SHA-512"))||StringUtils.equals(sign, SHA(signFlag2, "SHA-512"))){
//        	return true;
//        }else{
//        	return false;
//        }
//    }
}

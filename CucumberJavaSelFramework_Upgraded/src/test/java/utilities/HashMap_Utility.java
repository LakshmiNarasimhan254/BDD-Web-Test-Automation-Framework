package utilities;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;


public class HashMap_Utility {
	// TODO Auto-generated method stub
	public static HashMap<String, String> strArr_HM_usingSeparator(String[] Hmdetails ,String strSeparator){
		int strArrayLen = Hmdetails.length;
		//System.out.println(strArrayLen);
		HashMap<String, String > hm = new HashMap<String,String>();
		for(int i=0;i<strArrayLen;i++){
			String strKey = Hmdetails[i].split(strSeparator)[0];
			String strValue = Hmdetails[i].split(strSeparator)[1];
			
			hm.put(strKey, strValue);
		}		
		return hm;
	}


}
package com.lang.process.framework;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class WordContentComparison {

	public static void main(String[] args) throws Exception {

		StringBuffer sb1=new StringBuffer();

		sb1.append("example            das Beispiel ");
		sb1.append("example              das Exempel ");
		sb1.append("               das example Exemplar  ");
		sb1.append("example              die Probe ");
		sb1.append("              das Vorbild example ");
		
		StringBuffer sb2=new StringBuffer();

		sb2.append("example              das Exemplar  ");
		sb2.append("example            das Beispiel ");
		sb2.append("example              das Vorbild  ");
		sb2.append("example              das Exempel ");
		sb2.append("example              die Probe ");



		StringTokenizer stk=new StringTokenizer(sb1.toString());


		List<String> ls1=new ArrayList<String>();

		while(stk.hasMoreTokens()){
			ls1.add(stk.nextToken());

		}

		stk=new StringTokenizer(sb2.toString());


		List<String> ls2=new ArrayList<String>();

		while(stk.hasMoreTokens()){
			ls2.add(stk.nextToken());

		}

		MessageDigest instance = MessageDigest.getInstance("MD5");
		

		Collections.sort(ls1);
	
		String st1=buildString(ls1);

		byte[] digest1 = instance.digest(st1.getBytes("UTF-8"));

		String md51=new String(digest1);
		
		/*- ==================================================== */
		
		Collections.sort(ls2);

		String st2=buildString(ls2);
		

		byte[] digest2 = instance.digest(st2.getBytes("UTF-8"));

		String md52=new String(digest2);

		
		System.out.println("md5 of 1st string " + md51);
		
		System.out.println("md5 of 2nd string " + md52);
		
		if(md51.equals(md52)){
			System.out.println("MD5 key is same for both the strings");
		}
		
		/*- ==================================================== */
		
		
		if(ls1.size()==ls2.size()){
		
			System.out.println("equal");
		}else if(ls1.size()>ls2.size()){

			System.out.println("ls1 has more elements");
		}else if(ls1.size()<ls2.size()){
			System.out.println("ls2 has more elements");
		}


	}

	private static String buildString(List<String> ls1) {

		StringBuffer sb=new StringBuffer();
		
		for (String string : ls1) {
			sb.append(string);
			sb.append(" ");
		}
		
		return sb.toString();
	}
}

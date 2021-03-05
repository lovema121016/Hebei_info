package com.servlet;
 
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.jsoup.Jsoup;

import com.dao.InformationDao;
import com.hankcs.hanlp.HanLP;
import com.model.Information;
 
/**
 * 字符串相似性匹配算法
 * Created by panther on 15-7-20.
 */
public class Cha {
    Map<Character, int[]> vectorMap = new HashMap<Character, int[]>();
 
    int[] tempArray = null;
 
    public Cha(String string1, String string2) {
 
        for (Character character1 : string1.toCharArray()) {
            if (vectorMap.containsKey(character1)) {
                vectorMap.get(character1)[0]++;
            } else {
                tempArray = new int[2];
                tempArray[0] = 1;
                tempArray[1] = 0;
                vectorMap.put(character1, tempArray);
            }
        }
        for (Character character2 : string2.toCharArray()) {
            if (vectorMap.containsKey(character2)) {
                vectorMap.get(character2)[1]++;
            } else {
                tempArray = new int[2];
                tempArray[0] = 0;
                tempArray[1] = 1;
                vectorMap.put(character2, tempArray);
            }
        }
    }
 
    // 求余弦相似度
    public double sim() {
        double result = 0;
        result = pointMulti(vectorMap) / sqrtMulti(vectorMap);
        return result;
    }
 
    private double sqrtMulti(Map<Character, int[]> paramMap) {
        double result = 0;
        result = squares(paramMap);
        result = Math.sqrt(result);
        return result;
    }
 
    // 求平方和
    private double squares(Map<Character, int[]> paramMap) {
        double result1 = 0;
        double result2 = 0;
        Set<Character> keySet = paramMap.keySet();
        for (Character character : keySet) {
            int temp[] = paramMap.get(character);
            result1 += (temp[0] * temp[0]);
            result2 += (temp[1] * temp[1]);
        }
        return result1 * result2;
    }
 
    // 点乘法
    private double pointMulti(Map<Character, int[]> paramMap) {
        double result = 0;
        Set<Character> keySet = paramMap.keySet();
        for (Character character : keySet) {
            int temp[] = paramMap.get(character);
            result += (temp[0] * temp[1]);
        }
        return result;
    }
 
    public static void main(String[] args) throws IOException {
    	
    	  Chachong cha=new Chachong();  
		  InformationDao indao=new InformationDao();
		  List<Information> infos=indao.loadAll();
		  String baiduAll="";
		  String information="";
		  //List<String> chaoxi = new ArrayList<String>();
		  for(Information info:infos)//查重数据库中所有词条内容
		{
			  
			   String key[]=info.getKeyword().split(" ");//划分关键字
			   
			   
			 //按照标题爬去查重
			   List<String> fenci= getSplitWords(info.getTitle());//划分标题 
			   for(String ke:fenci)
			   {
				   boolean b=true;
				   for(String name:key)//如果标题划分的词语在关键字中存在，就不再查重
				   {
					   if(ke.equals(name));
					   {
						   b=false;
					   }
				   }
				   //System.out.println(ke);
				   String baidu[]=cha.paqu(ke);
				   if(baidu.length!=0&&b==true)//查重
				   {
					     String juhao[]=info.getContent().split("。");//对热词内容进行“。”的划分
					     String[]cont=new String[500];//初始化逗号句号划分内容
					     int x=0;
					     for(String douhao:juhao)
					     {
					    	 String temp[]=douhao.split("，");//对热词内容进行逗号的划分
					    	 for(String dou:temp)
					    	 {
					    		 //System.out.println(dou);
					    		 cont[x]=dou;
					    		 x++;
					    	 }
					     }
					     
					     String bai[]=baidu[1].split("。");//对百度词条内容进行“。”的划分
					     String[]baidu1=new String[500];//初始化百度划分
					     int y=0;
					     for(String douhao:bai)
					     {
					    	 String temp[]=douhao.split("，");//对百度内容逗号的划分
					    	 for(String dou:temp)
					    	 {
					    		 baidu1[y]=dou;
					    		 y++;
					    	 }
					     }
					     for(String con:cont)//将划分好的百度内容和数据库中热词的内容进行相似度的对比
					     {
					    	 for(String ba:baidu1)
					    	 {
					    		 if(con!=null&&ba!=null)
					    		 {
					    		 //System.out.println(con);
					    		 Cha similarity = new Cha(ba, con);
					    		 if(similarity.sim()>=0.8&&con.length()>=13)//如果相似度大于0.8且句子长度大于等于13则认为抄袭
					         	{
					         		 System.out.println(info.getIndex()+"抄袭"+con);
					                 System.out.println(similarity.sim());
					                 indao.add(con,info.getIndex());
					         	}
					    		 }
					    	 }
					     }
					     //System.out.println(ke);
					       //indao.add(co, info.getIndex());
					     }
				   }
		    
			   
			   
			   
		 //按数据库中划分的关键字查重
		   String key1[]=info.getKeyword().split(" ");
		   //System.out.println(info.getTitle());  
		   information=information+info.getContent(); 
		  for(String name:key1)
		  {
		     String baidu[]=cha.paqu(name);
		     //baiduAll=baiduAll+baidu[1];
		     String juhao[]=info.getContent().split("。");
		     String[]cont=new String[500];
		     int x=0;
		     for(String douhao:juhao)
		     {
		    	 String temp[]=douhao.split("，");
		    	 for(String dou:temp)
		    	 {
		    		 //System.out.println(dou);
		    		 cont[x]=dou;
		    		 x++;
		    	 }
		     }
		     
		     String bai[]=baidu[1].split("。");//先按句号划分百度内容
		     String[]baidu1=new String[500];
		     int y=0;
		     for(String douhao:bai)
		     {
		    	 String temp[]=douhao.split("，");//逗号划分百度内容
		    	 for(String dou:temp)
		    	 {
		    		 baidu1[y]=dou;
		    		 y++;
		    	 }
		     }
		     for(String con:cont)
		     {
		    	 for(String ba:baidu1)
		    	 {
		    		 if(con!=null&&ba!=null)
		    		 {
		    		 //System.out.println(con);
		    		 Cha similarity = new Cha(ba, con);
		    		 if(similarity.sim()>=0.8&&con.length()>=13)//如果相似度大于0.8且句子长度大于等于13则认为抄袭
		         	{
		         		 System.out.println(info.getIndex()+"抄袭"+con);
		                 System.out.println(similarity.sim());
		                 indao.add(con,info.getIndex());
		         	}
		    		 }
		    	 }
		     }
		  }
		  
		  //
	}
        //String s1 = "我是一个帅哥";
        //String s2 = "帅哥是我";
        //Cha similarity = new Cha(s1, s2);
        //System.out.println(similarity.sim());
        String str1 = "互联网+概念于2012年11月易观国际董事长兼首席执行官于扬首次提出，并于2015年7月4日，国务院印发了《国务院关于积极推进“互联网+”行动的指导意见》。“互联网+”作为知识社会创新2.0推动下的互联网形态演进，成为了中国互联网发展的新形态和新业态。“互联网+”既是互联网的移动与泛在，促进了传统行业与互联网的融合及应用，将数据、计算、知识三者融合，使创新渗入到各行各业，从而开创了创新驱动发展的“新常态”。 ";
        String [] key=str1.split("，");
        String str2 = "互联网+概念于2012年11月易观国际董事长兼首席执行官于扬首次提出";   
        for(String str:key)
        {
        	Cha similarity = new Cha(str, str2);
        	if(similarity.sim()>=0.9)
        	{
        		System.out.println(str);
                System.out.println(similarity.sim());
        	}
        }
    	 
    	  
    	/*String[] arr1 = str1.split(",") ;
    	String[] arr2 = str2.split(",") ; 
    	StringBuffer sb = new StringBuffer();
    	for (int i = 0; i < arr2.length; i++){
    	   for (int j = 0; j < arr1.length; j++){
    	      if (arr1[j].equals(arr2[i])){
    	          sb.append(arr1[j] + ",") ;
    	          }
    	    }
    	 }
    	System.out.println("结果：" + sb.toString().substring(0, sb.toString().length()));*/

    }
    private static List<String> getSplitWords(String sentence) {
        // 去除掉html标签
        sentence = Jsoup.parse(sentence.replace("&nbsp;","")).body().text();
        // 标点符号会被单独分为一个Term，去除之
        return HanLP.segment(sentence).stream().map(a -> a.word).filter(s -> !"`~!@#$^&*()=|{}':;',\\[\\].<>/?~！@#￥……&*（）——|{}【】‘；：”“'。，、？ ".contains(s)).collect(Collectors.toList());
    }
 
}
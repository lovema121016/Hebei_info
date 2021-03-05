package com.servlet;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.dao.InformationDao;
import com.hankcs.hanlp.HanLP;
import com.model.Information;
public class Chachong {
  public String[] paqu(String biaoti) throws IOException
  {
	String[] key=new String[2];
	int i=0;
	//String biaoti="大数据";
	String url = "https://baike.baidu.com/item/"+biaoti;
	Document document = Jsoup.connect(url).get();



	// 第二步：解析页面

	Elements titleElements = document.getElementsByTag("title");
	String title = titleElements.text();
    //System.out.println(title);
    key[0]=title;
	//Elements elements = document.select("div #article_content102062");
	Elements elements1 = document.getElementsByClass("lemma-summary");

	String content = elements1.text();//basicInfo-item name
	//Elements elements2 = document.getElementsByClass("basicInfo-item name");
	//String[] itemName=new String[4];

	//String itemName1=elements2.get(0).text();
	//Elements elements3 = document.getElementsByClass("basicInfo-item value");
	//System.out.println(content);
	key[1]=content;
	//String[] itemValue=new String[4];
	return key;
  }
  
  //
  public void download(int index,String title) throws IOException
  {
	  Chachong cha=new Chachong();
	  InformationDao indao=new InformationDao();
	  Information info=indao.load(index);
	  String key[]=info.getKeyword().split(" ");
	  double sim[]=new double[30];
	  int x=0;
	  String ba="";
	  for(String name:key)
	  {
	     String baidu[]=cha.paqu(name);
	     ba=ba+baidu[1];
	     //double similar=cha.getSimilarity(baidu[1],info.getContent());
	     //System.out.println(similar);
	     //sim[x]=similar;
	     //x++;
	     
	  }
	  double similar=cha.getSimilarity(ba,info.getContent());
	  Cha similarity = new Cha(ba, info.getContent());
      System.out.println(similarity.sim());
	  System.out.println("相似比："+similar);
	  for(int i=0;i<sim.length;i++)
	  {
		  //System.out.println(sim[i]);
	  }
	  DownLoad dow=new DownLoad();
	  //dow.down(similar,title);
  }
  public void downloadAll() throws IOException
  {
	  Chachong cha=new Chachong();
	  InformationDao indao=new InformationDao();
	  List<Information> infos=indao.loadAll();
	  String baiduAll="";
	  String information="";
	  
	  for(Information info:infos)
	{
	  information=information+info.getContent();
	  String key[]=info.getKeyword().split(" ");
	  for(String name:key)
	  {
	     String baidu[]=cha.paqu(name);
	     baiduAll=baiduAll+baidu[1];
	     double similar=cha.getSimilarity(baidu[1],info.getContent());
	     System.out.println(similar);
	     //sim[x]=similar;
	     //x++;    
	  }
	}
	  double similar=cha.getSimilarity(baiduAll,information);
	  System.out.println("相似比："+similar);
	  //DownLoad dow=new DownLoad();
	  //dow.downAll(similar);
  }
      
      /**
       * 获得两个句子的相似度
       *
       * @param sentence1
       * @param sentence2
       * @return
       */
      public double getSimilarity(String sentence1, String sentence2) {
          List<String> sent1Words = getSplitWords(sentence1);
          System.out.println(sent1Words);
          List<String> sent2Words = getSplitWords(sentence2);
          System.out.println(sent2Words);
          List<String> allWords = mergeList(sent1Words, sent2Words);
          for(String all:allWords)
          {
        	  System.out.println(all);
          }
          int[] statistic1 = statistic(allWords, sent1Words);
          int[] statistic2 = statistic(allWords, sent2Words);
          
          double dividend = 0;
          double divisor1 = 0;
          double divisor2 = 0;
          for (int i = 0; i < statistic1.length; i++) {
        	  //System.out.println(statistic1[i]);
              dividend += statistic1[i] * statistic2[i];
              divisor1 += Math.pow(statistic1[i], 2);
              divisor2 += Math.pow(statistic2[i], 2);
          }
          System.out.println(dividend);
          return dividend / (Math.sqrt(divisor1) * Math.sqrt(divisor2));
      }

      private static int[] statistic(List<String> allWords, List<String> sentWords) {
          int[] result = new int[allWords.size()];
          for (int i = 0; i < allWords.size(); i++) {
              result[i] = Collections.frequency(sentWords, allWords.get(i));
              System.out.println(allWords.get(i));
          }
          return result;
      }

      private static List<String> mergeList(List<String> list1, List<String> list2) {
          List<String> result = new ArrayList<>();
          result.addAll(list1);
          result.addAll(list2);
          return result.stream().distinct().collect(Collectors.toList());
      }

      private static List<String> getSplitWords(String sentence) {
          // 去除掉html标签
          sentence = Jsoup.parse(sentence.replace("&nbsp;","")).body().text();
          // 标点符号会被单独分为一个Term，去除之
          return HanLP.segment(sentence).stream().map(a -> a.word).filter(s -> !"`~!@#$^&*()=|{}':;',\\[\\].<>/?~！@#￥……&*（）——|{}【】‘；：”“'。，、？ ".contains(s)).collect(Collectors.toList());
      }

     public static void main(String [] agrs) throws IOException
     {
       Chachong cha=new Chachong();
   	   InformationDao indao=new InformationDao();
   	   //cha.download(1, "互联网＋");
   	   double similar=cha.getSimilarity("我去吃饭了", "吃饭去了哦，真的好饿,你不饿的吗");
   	   System.out.println(similar);
   	  
     }
}

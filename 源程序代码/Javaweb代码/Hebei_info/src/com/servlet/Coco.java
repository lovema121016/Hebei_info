package com.servlet;
import java.io.IOException;
import java.util.ArrayList;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.assertj.core.util.Collections;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.dao.InformationDao;
import com.hankcs.hanlp.HanLP;
import com.model.Information;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

public class Coco implements PageProcessor {
	static String chaxun = null;
	static String url = null;

	public static void main(String[] args) throws IOException {
		  Chachong cha=new Chachong();  
		  InformationDao indao=new InformationDao();
		  List<Information> infos=indao.loadAll();
		  String baiduAll="";
		  String information="";
		  //List<String> chaoxi = new ArrayList<String>();
		  for(Information info:infos)
		{
			  if(info.getIndex()>59)
			  {
			   String key[]=info.getKeyword().split(" ");
			   List<String> fenci= getSplitWords(info.getTitle());
			   for(String ke:fenci)
			   {
				   boolean b=true;
				   for(String name:key)
				   {
					   if(ke.equals(name));
					   {
						   b=false;
					   }
				   }
				   //System.out.println(ke);
				   String baidu[]=cha.paqu(ke);
				   if(baidu.length!=0&&b==true)
				   {
					     
					     //System.out.println(ke);
					     baiduAll=baiduAll+baidu[1];
					     List<String> chaoxi=copy(info.getContent(),baidu[1]);
					     String co="";
					     if(chaoxi.size()!=0)
					     {
					     for(String cop:chaoxi)
					     {
					    	 
					    	 co=cop+"￠";
					     }
					     indao.add(co, info.getIndex());
					     }
				   }
			   }
		  System.out.println(info.getTitle());  
		  information=information+info.getContent();
		  
		  for(String name:key)
		  {
		     String baidu[]=cha.paqu(name);
		     baiduAll=baiduAll+baidu[1];
		     List<String> chaoxi=copy(info.getContent(),baidu[1]);
		     String co="";
		     if(chaoxi.size()!=0)
		     {
		     for(String cop:chaoxi)
		     {
		    	 
		    	 co=cop+"￠";
		     }
		     indao.add(co, info.getIndex());
		     }
		  }
			  }
		}
		

	}
	private static List<String> getSplitWords(String sentence) {
        // 去除掉html标签
        sentence = Jsoup.parse(sentence.replace("&nbsp;","")).body().text();
        // 标点符号会被单独分为一个Term，去除之
        return HanLP.segment(sentence).stream().map(a -> a.word).filter(s -> !"`~!@#$^&*()=|{}':;',\\[\\].<>/?~！@#￥……&*（）——|{}【】‘；：”“'。，、？ ".contains(s)).collect(Collectors.toList());
    }
    public static List<String> copy(String str1,String str2)
    {
    	 List<String> chaoxi = new ArrayList<String>();
    	 List<String> chongfus = duibi(str1,str2);
		 int count = 0;// 抄袭的总字数
		 if (chongfus != null)
		 {// 有重复的字符
			for (String chongfu : chongfus) 
			{
				    boolean isWord=chongfu.matches("[a-zA-Z]+");//判断是否全英文
					if (chongfu.length() > 13&&isWord==false) 
					{// 知网规定重复超过7个算是抄袭
						chaoxi.add(chongfu);
						count = count + chongfu.length();
					}
			 }
		  }
			if (count > 0) {
				System.out.println("抄袭句子");
				for (String cx : chaoxi) {
					System.out.println(cx);
					// 写入到数据库
				}
		  }
			return chaoxi;
    }
	public static List<String> duibi(String explain, String chaxun) {

		List<String> chongfus = new ArrayList<String>();
		String chongfu = "";
		boolean lianxu = false;
		temp p=null;
		for (int i = 0; i < explain.length(); i++) {
			for (int j = 0; j < chaxun.length(); j++) {
				if (i >= explain.length() || j >= chaxun.length()) {
					break;
				}
				if (explain.charAt(i) == chaxun.charAt(j)) {//如果有重复的字符
					if ((lianxu == false) && (!chongfu.equals(""))) {// 上一句重复的话
						chongfus.add(chongfu);
						chongfu = "";
					}
					if(p==null) {//没有存储进去字符,开始存储di'yi'g
						p=new temp();
						p.setStart_position(i);
					}
					
					p.setLast_position(i);
					chongfu = chongfu + explain.charAt(i);
					lianxu = true;

					i++;
				} else {
					lianxu = false;
				}

			}
		}
		chongfus.add(chongfu);
		return chongfus;

	}

	@Override
	public Site getSite() {
		return Site.me().setSleepTime(1000).setRetryTimes(10);
	}

	private static String selectDocumentText(String htmlText) {
		Document doc = Jsoup.parse(htmlText);
		String select = doc.text();
		return select;
	}

	@Override
	public void process(Page page) {

		// 定义如何抽取页面信息
		List<String> htmls = page.getHtml().xpath("//div[@class='lemma-summary']/html()").all();
		for (String html : htmls) {
			chaxun = selectDocumentText(html);
		}

	}

	public static void shuru(String word) {
		long startTime, endTime;
		startTime = new Date().getTime();
		Spider create = Spider.create(new Coco());
		String strr = "https://baike.baidu.com/item/" + word;
		url = strr;
		create.addUrl(strr).thread(1).run();
		endTime = new Date().getTime();
		System.out.println("用时为：" + (endTime - startTime) / 1000 + "s");
	}

}

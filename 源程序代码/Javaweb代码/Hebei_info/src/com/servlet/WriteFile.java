package com.servlet;

import java.awt.Color;
import java.io.FileOutputStream;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import com.dao.InformationDao;
import com.lowagie.text.Chapter;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfWriter;
import com.model.Information;

public class WriteFile {
	private static int normal = Font.NORMAL; // 正常字体
	private static Color black = new Color(0, 0, 0); // 黑色
	private static Color red = new Color(255, 0, 0); // 红色
	private int bold = Font.BOLD; // 粗体
	private Color blue = new Color(0, 0, 255); // 蓝色
	private float setting = 20; // 首行缩进参数
	static int count=0;//用红色标注的字体个数
	static int cop_count=0;//用红色标注的片段个数
	static int sum=0;//总字数

	public static void main(String[] args) throws Exception {
		WriteFile pdfDemo = new WriteFile();
		pdfDemo.writePdf("E:\\20163587_姚雅丽\\大型数据库技术与应用\\信息技术手册检测报告.pdf");
		System.out.println("写入完成！");
	}
	public void write() throws Exception {
		WriteFile pdfDemo = new WriteFile();
		pdfDemo.writePdf("E:\\20163587_姚雅丽\\大型数据库技术与应用\\信息技术手册检测报告.pdf");
		System.out.println("写入完成！");
	}
	/*
	public void writeone(String title,int index) throws Exception {
		WriteFile pdfDemo = new WriteFile();
		pdfDemo.writePdfone("E:\\20163587_姚雅丽\\大型数据库技术与应用\\"+title+"信息技术手册检测报告.pdf",title,index);
		System.out.println("写入完成！");
	}*/
	public static Document createDoc(String filename) throws Exception {

		Document document = new Document(PageSize.A4, 50, 50, 50, 50);
		PdfWriter.getInstance(document, new FileOutputStream(filename));
		return document;
	}

	public void writePdf(String filename) throws Exception {
		InformationDao indao=new InformationDao();
		List<String> copys=indao.loadCopyAll();
		HashSet h = new HashSet(copys);
		copys.clear();
		copys.addAll(h);
		int length=0;
		int pianduan=0;
		int zishu=0;
		for(String copy:copys)
		{
			length+=copy.length();
			pianduan++;
		}
		int sum2=0;
		List<Information> infoss=indao.loadAll();
		for (Information info : infoss) {
			sum2+=info.getContent().length();
			zishu+=info.getContent().length();
		}
		Document document = createDoc(filename); // 打开文档
		document.open(); // 文档里写入
		Chunk chunk1 = new Chunk(convertChunkToChinese("信息技术手册检测报告", 20, bold, black));
		document.add(chunk1);
		document.add(new Paragraph("\n"));
		Paragraph pp = new Paragraph(convertParToChinese("检测范围：◎百度百科", 9, normal, black));
		document.add(pp);
		Paragraph pp1 = new Paragraph(convertParToChinese("颜色标注说", 9, normal, black));
		document.add(pp1);
		Paragraph pp2 = new Paragraph();
		pp2.add(new Phrase(convertParToChinese("口", 9, normal, red)));
		pp2.add(new Phrase(convertParToChinese("：复写片段(相似或疑似重)", 9, normal, black)));
		document.add(pp2);
		//InformationDao indao=new InformationDao();
		document.add(new Paragraph(convertParToChinese("检测结论：",22, normal, black)));
		Paragraph a1=new Paragraph();
		a1.add(new Phrase(convertParToChinese("全文相似度：", 9, normal, black)));
		sum();
		DecimalFormat decimalFormat=new DecimalFormat(".00");//构造方法的字符格式这里如果小数不足2位,会以0补足.
		String p=decimalFormat.format((float)100*length/sum2);
		System.out.println(p);
		a1.add(new Phrase(convertParToChinese(p+"%", 9, normal, red)));
		document.add(a1);
		document.add(new Paragraph(convertParToChinese("总相似片段：" +pianduan, 9, normal, black)));
		document.add(new Paragraph(convertParToChinese("检测字数：" +zishu, 9, normal, black)));
		List<Information> infos=indao.loadAll();
		for (Information info : infos) {
			Paragraph chapterTitle = new Paragraph("\n\n");
			Paragraph p0 = new Paragraph(convertParToChinese(info.getIndex() + "." + info.getTitle(), 14, bold, black));
			chapterTitle.add(p0);
			Paragraph p3 = new Paragraph(convertParToChinese("类别：" + info.getType2(), 10, normal, black));
			chapterTitle.add(p3);
			if(info.getAbstracted()!=null)
			{
			Paragraph p01 = new Paragraph(convertParToChinese("【摘要】" + info.getAbstracted(), 10, normal,black));
			chapterTitle.add(p01);
			}
			Paragraph p1 = new Paragraph(convertParToChinese("关键词：" + info.getKeyword(), 10, normal, black));
			chapterTitle.add(p1);
			Paragraph p2 = returnCopy(info.getIndex(), info.getContent());// 解释
			chapterTitle.add(p2);
			document.add(chapterTitle);
		}
		document.add(new Paragraph("\n\n"));
		
		document.close();
		count=0;
		sum=0;
		cop_count=0;
	}
	/*
	public void writePdfone(String filename,String title,int index) throws Exception {
		
		
		Document document = createDoc(filename); // 打开文档
		document.open(); // 文档里写入
		Chunk chunk1 = new Chunk(convertChunkToChinese(title+"信息技术手册检测报告", 20, bold, black));
		document.add(chunk1);
		document.add(new Paragraph("\n"));
		Paragraph pp = new Paragraph(convertParToChinese("检测范围：◎百度词条", 9, normal, black));
		document.add(pp);
		Paragraph pp1 = new Paragraph(convertParToChinese("颜色标注说", 9, normal, black));
		document.add(pp1);
		Paragraph pp2 = new Paragraph();
		pp2.add(new Phrase(convertParToChinese("口", 9, normal, red)));
		pp2.add(new Phrase(convertParToChinese("：复写片段(相似或疑似重)", 9, normal, black)));
		document.add(pp2);
		InformationDao indao=new InformationDao();
		Information info=indao.load(index);
			Paragraph chapterTitle = new Paragraph("\n\n");
			Paragraph p0 = new Paragraph(convertParToChinese(info.getIndex() + "." + info.getTitle(), 14, bold, black));
			chapterTitle.add(p0);
			Paragraph p3 = new Paragraph(convertParToChinese("类别：" + info.getType(), 9, normal, blue));
			chapterTitle.add(p3);
			//Paragraph p = new Paragraph(convertParToChinese("摘要：" + , 9, normal, blue));
			//chapterTitle.add(p);
			Paragraph p1 = new Paragraph(convertParToChinese("关键词：" + info.getKeyword(), 9, normal, blue));
			chapterTitle.add(p1);
			Paragraph p2 = returnExpain(info.getIndex(), info.getContent());// 解释
			chapterTitle.add(p2);
			document.add(chapterTitle);
		document.add(new Paragraph("\n\n"));
		document.add(new Paragraph(convertParToChinese("检测结论：",22, normal, black)));
		Paragraph a1=new Paragraph();
		a1.add(new Phrase(convertParToChinese("全文相似度：", 9, normal, black)));
		sum();
		DecimalFormat decimalFormat=new DecimalFormat(".00");//构造方法的字符格式这里如果小数不足2位,会以0补足.
		String p=decimalFormat.format((float)100*count/sum);
		System.out.println(p);
		a1.add(new Phrase(convertParToChinese(p+"%", 9, normal, red)));
		document.add(a1);
		document.add(new Paragraph(convertParToChinese("总相似片段：" +cop_count, 9, normal, black)));
		count=0;
		sum=0;
		cop_count=0;
		document.close();
	}
	*/
	//返回词语解释部分
	public static Paragraph returnCopy(int index, String explain) throws Exception
	{
		int chaoxi=0;//抄袭字数
		int pianduan=0;//抄袭片段
		int zongzishu=0;//总字数
		Paragraph a = new Paragraph();
		InformationDao indao=new InformationDao();
		List<String> copys=indao.loadCopy(index);
		pianduan=copys.size();
		if(copys==null)
		{
			pianduan=0;
		}
		
		HashSet h = new HashSet(copys);
		copys.clear();
		copys.addAll(h);
        System.out.println(copys);
        int pos[]=new int[100];
        int x=0;
        for(String copy:copys)
        {
        	pos[x]=explain.indexOf(copy);
        	x++;	
        }
        Arrays.sort(pos);
        
        for(int i=0;i<explain.length();i++)
        {
        	String word="";
        	//word = word+explain.charAt(i);
    		//a.add(new Phrase(convertParToChinese2(word, 12, normal, black)));
        	int max=0;
        	boolean b=true;
        	
        	for(int position:pos)
        	{
        		if(i==position)//判断是否到达抄袭点
        		{
        			b=false;//到达抄袭点
        		}
        	}
        	if(b==false)
        	{
        		String cop="";
        		for(String copy:copys)
                {
                	if(explain.indexOf(copy)==i)
                	{
                		cop=copy;
                	}
                }
        		a.add(new Phrase(convertParToChinese2(cop, 12, normal, red)));
        		chaoxi+=cop.length();
				//pianduan++;
				i=i+cop.length();
        	}
        	word = word+explain.charAt(i);
    		a.add(new Phrase(convertParToChinese2(word, 12, normal, black)));
        	
        }
        a.add(new Paragraph(convertParToChinese("检测结论：",15, normal, black)));
		Paragraph a1=new Paragraph();
		a1.add(new Phrase(convertParToChinese("全文相似度：", 9, normal, black)));
		sum();
		DecimalFormat decimalFormat=new DecimalFormat(".00");//构造方法的字符格式这里如果小数不足2位,会以0补足.
		String p=decimalFormat.format((float)100*chaoxi/explain.length());
		System.out.println(p);
		a1.add(new Phrase(convertParToChinese(p+"%", 9, normal, red)));
		a.add(a1);
		a.add(new Paragraph(convertParToChinese("总相似片段：" +pianduan, 9, normal, black)));
		a.add(new Paragraph(convertParToChinese("检测字数：" +explain.length(), 9, normal, black)));
        return a;
        
	}
	/*
	public static Paragraph returnExpain(int index, String explain) throws Exception {
		InformationDao indao=new InformationDao();
		String copy=indao.loadcpoy(index);
		String[]copys=copy.split("￠");
		Paragraph a = new Paragraph();
		String word = "";
		int j = 0;
		if (copy.length()!=0) 
		{
			if(copys.length==1)
			{
				String c = copys[j];
				//j++;
				//System.out.println(index);
				for (int i = 0; i < explain.length(); i++) 
				{ 
					int position=explain.indexOf(copys[0]);

					if (i < position) 
					{
						//System.out.println("姚雅丽");
						word = word + explain.charAt(i);
						//System.out.println(word);
						
					} 
					else if (i==position) 
					{
						    System.out.println(word);
						    a.add(new Phrase(convertParToChinese2(word, 12, normal, black)));
							a.add(new Phrase(convertParToChinese2(copys[0], 12, normal, red)));
							count+=copys[0].length();
							cop_count++;
							word = "";
					}
					else if(i>=position+copys[0].length())
					{
						word = word + explain.charAt(i);
					    a.add(new Phrase(convertParToChinese2(word, 12, normal, black)));
					    word = "";
					}
				}
			}
			else
			{
			String c = copys[j];
			//j++;
			//System.out.println(index);
			for (int i = 0; i < explain.length(); i++) 
			{ 
				int position=explain.indexOf(copys[0]);
				int pos2=explain.indexOf(copys[1]);
				int min=0;
				int max=0;
				String one="";
				String two="";
				if(position<pos2)
				{
					min=position;
					one=copys[0];
					two=copys[1];
					max=pos2;
				}
				else
				{
					max=position;
					min=pos2;
					two=copys[0];
					one=copys[1];
				}
				if (i < min) 
				{
					//System.out.println("姚雅");
					word = word + explain.charAt(i);
					
				} 
				else if (i==min) 
				{
					    a.add(new Phrase(convertParToChinese2(word, 12, normal, black)));
						a.add(new Phrase(convertParToChinese2(one, 12, normal, red)));
						cop_count++;
						count+=one.length();
						word = "";
				}
				else if (min+one.length()<=i&&i<max) 
				{
					    word = word + explain.charAt(i);
						a.add(new Phrase(convertParToChinese2(word, 12, normal, black)));
						word = "";
					
				}else if(i==max)
				{
					 word = word + explain.charAt(i);
				     a.add(new Phrase(convertParToChinese2(two, 12, normal, red)));
				     cop_count++;
				     count+=two.length();
				     word = "";
				}
				else if(i>=max+two.length()&&i<explain.length()-1)
				{
					word = word + explain.charAt(i);
				}
				else if(i==explain.length()-1)
				{
					word = word + explain.charAt(i);
					a.add(new Phrase(convertParToChinese2(word, 12, normal, black)));
				}

			}
			}
		} 
		else 
		{
			//System.out.println("姚雅丽");
			a = new Paragraph(convertParToChinese(explain, 12, normal, black));
		}
		return a;

	}
	*/
	public static void sum() {
		InformationDao indao=new InformationDao();
        List<Information> infos=indao.loadAll();
		for(Information info:infos) {
			sum=sum+info.getContent().length();
		}
		
	}
	
	public static Paragraph convertParToChinese(String text, int fontsize, int fontStyle, Color color)
			throws Exception {
		BaseFont baseFontChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
		Font fontChinese = new Font(baseFontChinese, fontsize, fontStyle, color);
		Paragraph graph = new Paragraph(text, fontChinese);
		return graph;
	}

	public static Phrase convertParToChinese2(String text, int fontsize, int fontStyle, Color color) throws Exception {
		BaseFont baseFontChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
		Font fontChinese = new Font(baseFontChinese, fontsize, fontStyle, color);
		Phrase graph = new Phrase(text, fontChinese);
		return graph;
	}

	public static Chunk convertChunkToChinese(String text, int fontsize, int fontStyle, Color color) throws Exception {
		BaseFont baseFontChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
		Font fontChinese = new Font(baseFontChinese, fontsize, fontStyle, color);
		Chunk graph = new Chunk(text, fontChinese);
		return graph;
	}
}


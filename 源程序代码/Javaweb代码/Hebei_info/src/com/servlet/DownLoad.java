package com.servlet;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DownLoad {
	static int count=1; 
	static int c=0;
   static String[] q= {"一","二","三","四","五","六","七"};
	  
	  
	  public  void down(double similiar,String title) {
		  c=0;
		  Date day=new Date();    
		  SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");   
		  String name="E:\\data\\查重报告"+df.format(day)+".docx";
		  mulu(name,similiar,title);
		  
				  
	  }
	  public  void downAll(double similiar) {
		  c=0;
		  Date day=new Date();    
		  SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");   
		  String name="E:\\data\\全文查重报告"+df.format(day)+".docx";
		  muluAll(name,similiar);
		  
				  
	  }
	  
	  public static void mulu(String name,double similiar,String title) {
       try {
	        	
	            FileWriter fw = new FileWriter(name, true);
	            BufferedWriter bw = new BufferedWriter(fw);
	            bw.append("                        有关"+title+"手册查重分析");//类别
              	bw.write("\n");
              	bw.write("\n");
              	bw.write("\n");
              	bw.write("                            【word报告-大学生版】");
              	bw.write("\n");
              	bw.write("\n");
              	bw.write("\n");
              	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
              	System.out.println(df.format(new Date()));// new Date()为获取当前系统时间
	            bw.write("报告编号:happyholidy         检测时间"+df.format(new Date())+"          检测字数    84350字");
	            bw.write("\n");
	            bw.write("作者名称:姚雅丽              所属单位:石家庄铁道大学(河北)");
	            bw.write("\n");
	            bw.write("-------------------------------------------------------------");
	            bw.write("\n");
	            bw.write("检测范围:");
	            bw.write("\n");
	            bw.write(">百度百科");
	            bw.write("\n");
	            bw.write(">维基百科");
	            bw.write("\n");
	            bw.write("---------------------------------------------------------------");
	            bw.write("\n");
	            bw.write("检测结论");
	            bw.write("\n");
	            double sum=0;
	            int x=0;
	            
	            /*for(int i=0;i<similiar.length;i++)
	            {
	            	System.out.println("姚雅丽"+similiar[i]);
	            	String val=similiar[i]+"";
	            	if(similiar[i]!=0.0&&val.equals("NaN")==false)
	            	{
	            	  sum+=similiar[i];
	            	  x++;
	            	}
	            }*/
	            //System.out.println(sum);
	            //double average=sum/x;
	            bw.write("全文相似比:"+similiar);
	            bw.write("\n");
	            bw.write("\n");
	            bw.close();

	            fw.close();

	        } catch (Exception e) {

	            // TODO Auto-generated catch block

	            e.printStackTrace();

	        }
		  
	  }
	  public static void muluAll(String name,double similiar) {
	       try {
		        	
		            FileWriter fw = new FileWriter(name, true);
		            BufferedWriter bw = new BufferedWriter(fw);
		            bw.append("                        河北信息查重手册分析");//类别
	              	bw.write("\n");
	              	bw.write("\n");
	              	bw.write("\n");
	              	bw.write("                            【word报告-大学生版】");
	              	bw.write("\n");
	              	bw.write("\n");
	              	bw.write("\n");
	              	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
	              	System.out.println(df.format(new Date()));// new Date()为获取当前系统时间
		            bw.write("报告编号:happyholidy         检测时间"+df.format(new Date())+"          检测字数    84350字");
		            bw.write("\n");
		            bw.write("作者名称:姚雅丽              所属单位:石家庄铁道大学(河北)");
		            bw.write("\n");
		            bw.write("-------------------------------------------------------------");
		            bw.write("\n");
		            bw.write("检测范围:");
		            bw.write("\n");
		            bw.write(">百度百科");
		            bw.write("\n");
		            bw.write(">维基百科");
		            bw.write("\n");
		            bw.write("---------------------------------------------------------------");
		            bw.write("\n");
		            bw.write("检测结论");
		            bw.write("\n");
		            double sum=0;
		            int x=0;
		            
		            /*for(int i=0;i<similiar.length;i++)
		            {
		            	System.out.println("姚雅丽"+similiar[i]);
		            	String val=similiar[i]+"";
		            	if(similiar[i]!=0.0&&val.equals("NaN")==false)
		            	{
		            	  sum+=similiar[i];
		            	  x++;
		            	}
		            }*/
		            //System.out.println(sum);
		            //double average=sum/x;
		            bw.write("全文相似比:"+similiar);
		            bw.write("\n");
		            bw.write("\n");
		            bw.close();

		            fw.close();

		        } catch (Exception e) {

		            // TODO Auto-generated catch block

		            e.printStackTrace();

		        }
			  
		  }
	  


}

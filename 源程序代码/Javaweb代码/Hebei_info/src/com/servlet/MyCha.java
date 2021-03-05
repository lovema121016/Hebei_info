package com.servlet;

public class MyCha {  
	  
    public static void main(String[] args) {  
        //要比较的两个字符串  
        String str1 = "互联网+概念于2012年11月易观国际董事长兼首席执行官于扬首次提出，并于2015年7月4日，国务院印发了《国务院关于积极推进“互联网+”行动的指导意见》。“互联网+”作为知识社会创新2.0推动下的互联网形态演进，成为了中国互联网发展的新形态和新业态。“互联网+”既是互联网的移动与泛在，促进了传统行业与互联网的融合及应用，将数据、计算、知识三者融合，使创新渗入到各行各业，从而开创了创新驱动发展的“新常态”" + 
        		"";  
        String str2 = "虚拟现实（英语：virtual reality，缩写VR），易观国际董事长兼首席执行官于扬首次提出，简称虚拟技术，也称虚拟环境，通过计算机模拟构造一个三维空间，在这个三维空间中虚拟世界，从而向用户推送关于视觉等感官的模拟，让用户感觉身在虚拟世界。VR可以支持即时、无限制地观察虚拟空间内的事物。当用户移动位置时，计算机可以马上通过复杂运算，然后向用户传送精确的三维世界影像，从而使用户产生临场感。虚拟技术利用了人工智能、计算机图形、计算机仿真、显示及网络并行处理等技术，是一种高新技术模拟系统。\r\n" + 
        		"";  
        levenshtein(str1,str2);  
    }  
  
    /** 
     * 　　DNA分析 　　拼字检查 　　语音辨识 　　抄袭侦测 
     *  
     * @createTime 2012-1-12 
     */  
    public static void levenshtein(String str1,String str2) {  
        //计算两个字符串的长度。  
        int len1 = str1.length();  
        int len2 = str2.length();  
        //建立上面说的数组，比字符长度大一个空间  
        int[][] dif = new int[len1 + 1][len2 + 1];  
        //赋初值，步骤B。  
        for (int a = 0; a <= len1; a++) {  
            dif[a][0] = a;  
        }  
        for (int a = 0; a <= len2; a++) {  
            dif[0][a] = a;  
        }  
        //计算两个字符是否一样，计算左上的值  
        int temp;  
        for (int i = 1; i <= len1; i++) {  
            for (int j = 1; j <= len2; j++) {  
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {  
                	//System.out.print(str1.charAt(i-1));
                    temp = 0;  
                } else {  
                    temp = 1;  
                }  
                //取三个值中最小的  
                dif[i][j] = min(dif[i - 1][j - 1] + temp, dif[i][j - 1] + 1,  
                        dif[i - 1][j] + 1);  
            }  
        }  
        System.out.println();
        //System.out.println("字符串\""+str1+"\"与\""+str2+"\"的比较");  
        //取数组右下角的值，同样不同位置代表不同字符串的比较  
        System.out.println("差异步骤："+dif[len1][len2]);  
        //计算相似度  
        float similarity =1 - (float) dif[len1][len2] / Math.max(str1.length(), str2.length());  
        System.out.println("相似度："+similarity);  
    }  
  
    //得到最小值  
    private static int min(int... is) {  
        int min = Integer.MAX_VALUE;  
        for (int i : is) {  
            if (min > i) {  
                min = i;  
            }  
        }  
        return min;  
    }  
  
}  

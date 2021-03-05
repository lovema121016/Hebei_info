#encoding=utf-8
import jieba
import jieba.analyse
import pymysql
#全模式
jieba.load_userdict("it.txt")
jieba.analyse.set_stop_words('except.txt')
db = pymysql.connect(host='localhost', user='root', passwd='root', db='dazuoye', port=3306, charset='utf8')
conn = db.cursor()  # 获取指针以操作数据库
conn.execute('set names utf8')
conn.execute("select * from info_tech")
result=conn.fetchall()
list=[]
x=0
for data in result:
  text = data[4]
#精确模式
  keywords = jieba.analyse.extract_tags(text, topK=20, withWeight=True, allowPOS=('n', 'nr', 'ns'))
  seg_list = jieba.cut(text, cut_all=False)
  # 获取关键词
  tags = jieba.analyse.extract_tags(text,topK=20)
  #print(u"关键词:")
  keyw=""
  for item in keywords:
    #print(item[0], item[1])
    list.append(item[0])
    keyw=keyw+item[0]+" "
  x=x+1
List_set = set(list) #List_set是另外一个列表，里面的内容是List里面的无重复 项
dic=[]
for item in List_set:
  #print(item,list.count(item))
  lis=[item,list.count(item)]
  dic.append(lis)
dic = sorted(dic, key = lambda item:item[1],reverse=True)
y=0
conn.execute("select * from info_tech")
result=conn.fetchall()
lei=[]
for item in dic:
    #print(item[0])
    lei.append(item[0])
    y=y+1
    if y==6:
        break
j=0
for item in lei:
 for data in result:
   str=data[5].split(" ")

   if item in data[5]:
     print(item)
     t1=(int(data[0]))
     #print(t1)
     sql1="select * from info_tech where `index` = %d and type ='' " % t1
     print(sql1)
     conn.execute(sql1)
     re = conn.fetchall()
     print(len(re))
     if len(re):
       t=(item,int(data[0]))
       sql = "update info_tech set type = '%s' where `index` = %d" % t
       print(sql)
       #conn.execute(sql)
       db.commit()

sql2="select * from info_tech where type = '' "
     #print(sql1)
conn.execute(sql2)
consult = conn.fetchall()
for con in consult:
    t=(int(con[0]))
    sq4 = "update info_tech set type = '其他' where `index` = %d" % t
    print(sq4)
    conn.execute(sq4)
    db.commit()




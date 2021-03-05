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
for data in result:
  text = data[4]
#精确模式
  keywords = jieba.analyse.extract_tags(text, topK=20, withWeight=True, allowPOS=('n', 'nr', 'ns'))
  seg_list = jieba.cut(text, cut_all=False)
  # 获取关键词
  tags = jieba.analyse.extract_tags(text,topK=20)
  print(u"关键词:")
  keyw=""
  for item in keywords:
    print(item[0], item[1])
    keyw=keyw+item[0]+" "
  t=(keyw,int(data[0]))
  sql = "update info_tech set keyword = '%s' where `index` = %d" % t
  print(sql)
  conn.execute(sql)
  db.commit()
  #keyword=" ".join(tags)
  #t=(keyword,int(data[0]))

  #sql = "update info_tech set keyword = '%s' where `index` = %d" % t
  #print(sql)
  #conn.execute(sql)
  #db.commit()
#默认是精确模式
  #seg_list = jieba.cut(text)
  #print(u"[默认模式]: ", "/ ".join(seg_list))

#新词识别 “杭研”并没有在词典中,但是也被Viterbi算法识别出来了
  #seg_list = jieba.cut("他来到了网易杭研大厦")
  #print(u"[新词识别]: ", "/ ".join(seg_list))

 #搜索引擎模式
  #seg_list = jieba.cut_for_search(text)
  #print(u"[搜索引擎模式]: ", "/ ".join(seg_list))
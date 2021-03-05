from bs4 import BeautifulSoup
import pymysql

conn = pymysql.connect(host='localhost', user='root', passwd='root', db='weather', port=3306, charset='utf8')
cursor = conn.cursor()
# 数据时从本地文件里来
def read_file(path):
    # 注意编码格式可能会出错
    with open(path, 'r+', encoding='ANSI') as f:
        str = f.read()
    return str.strip().replace('\ufeff', '')


# 解析目录数据
def parse_data(data):
    # 读取第一个MsoToc1和第二个MsoToc1之间的所有数据
    for str1 in data.split('class=MsoToc1')[1:]:
        bs = BeautifulSoup(str1, 'lxml')
        index = 0
        title1 = ""
        title2 = ""
        title3 = ""
        try:
            for tag in bs.select('a'):
                strs = tag.get_text().split(' ')[0].rstrip()
                if ('第' in strs and '篇' in strs):
                    title1 = tag.get_text().split(' ')[1].replace('.', '')

                elif ('第' in strs and '章' in strs):
                    title2 = tag.get_text().split(' ')[1].replace('.', '')
                else:
                    index = strs;
                    title3 = tag.get_text().split(' ')[1].replace('.', '')
                    save(index, title1, title2, title3)
        except:
            print("数据有误，跳过执行")
    bigdiv = data.split('class=WordSection3')[1]
    for str1 in bigdiv.split('class=3132020')[1:]:
        soup = BeautifulSoup('<p class=3132020 ' + str1, 'lxml')
        content = ""
        index = int(soup.find('p', {'class': '3132020'}).get_text().split(' ')[0])
        for tag in soup.find_all('p', {'class': '4'}):
            content += tag.get_text() + '\r\n'
        update(index, content)
    return


# 保存到数据库
def save(index, title1, title2, title3):
    db = pymysql.connect(host='localhost', user='root', passwd='root', db='dazuoye', port=3306, charset='utf8')
    conn = db.cursor()  # 获取指针以操作数据库
    conn.execute('set names utf8')
    t = (int(index), title1, title2, title3)

    sql = "INSERT INTO info_tech values(%d,'%s','%s','%s',' ','','')" % t
    print(sql)
    try:
        conn.execute(sql)
        # 执行sql语句
        db.commit()
    except:
        # 发生错误时回滚
        db.rollback()
    # 关闭数据库连接
    db.close()
    return


# 修改到数据库
def update(index, content):
    db = pymysql.connect(host='localhost', user='root', password='root', db='dazuoye')
    conn = db.cursor()  # 获取指针以操作数据库
    conn.execute('set names utf8')
    t = (content, int(index))
    sql = "update info_tech set content = '%s' where `index` = %d" % t
    print(sql)
    try:
        conn.execute(sql)
        # 执行sql语句
        db.commit()
    except:
        # 发生错误时回滚
        db.rollback()
    # 关闭数据库连接
    db.close()
    return


if __name__ == '__main__':
    str = read_file('information.htm')
    parse_data(str)
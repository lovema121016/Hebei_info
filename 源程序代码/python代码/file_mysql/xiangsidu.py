import urllib.request
import urllib.parse
from bs4 import BeautifulSoup
import re

pattern=re.compile(r'/view/\d+.html?')
ulrs_ed=set([])
queue=[]
MAX=100

def spider(url):
    url_full='http://baike.baidu.com'+url
    if url_full in ulrs_ed:
        spider(queue.pop())
        return
    if len(ulrs_ed)>=MAX:
        return
    ulrs_ed.add(url_full)

    try:
        res=urllib.request.urlopen(url_full)
        doc=str(res.readall().decode('utf8',errors='replace'))
        soup=BeautifulSoup(doc,'html.parser',from_encoding='utf-8')
        print(soup.find('title').get_text())
        for adds in soup.find_all('a',href=pattern):
            queue.append(adds['href'])
        spider(queue.pop())
    except IndexError:
        return
    except:
        spider(queue.pop())
        return

    spider("")

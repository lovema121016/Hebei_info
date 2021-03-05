from collections import OrderedDict


x0= input()#输入一串字符串
x1=input().split(" ")
x2=input().split(" ")
x3=input().split(" ")
x4=input()
lis= "".join(OrderedDict.fromkeys(x0))
s_sp = list(x1)
while "" in s_sp:
    s_sp.remove("")
lis2 = list(set(s_sp))
lis2.sort(key=s_sp.index)
ress= len(lis) + len(lis2)
print("Total: "+str(ress))
while "" in x2:
    x2.remove("")
r3= list(x2) + x3
st_split2 = list(x3)
liss2=list(set(lis2 + list(lis)).difference(set(r3)))
l2 = list(set(r3))
l2.sort(key=r3.index)
lis4=[]
lis4=list(set(l2) & set(x2))
lis41=list(set(lis4) & set(x3))
lis5=list(set(l2).difference(set(x2)))
lis6=list(set(l2).difference(set(x3)))
lis7=list(set(lis5).union(set(lis6)))


#输出结果#
print('Not in race: {0}, num: {1}'.format(sorted(liss2), str(len(liss2))))
print('All racers: {0}, num: {1}'.format(sorted(l2),str(len(l2))))
print('ACM + English: {0}, num: {1}'.format(sorted(lis41), str(len(lis41))))
print('Only ACM: {0}'.format(sorted(lis6)))
print('Only English: {0}'.format(sorted(lis5)))
print('ACM Or English: {0}'.format(sorted(lis7)))
##
li_4=list(lis)
if x4 in li_4:
	li_4.remove(x4)
	print (sorted(li_4))#对列表4进行排序
if x4 in lis2:
	lis2.remove(x4)
	print (sorted(lis2))#对列表2进行排序

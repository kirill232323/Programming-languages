enAlpha = ['a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z']
amount = [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0]

d = {}

for i in range(26):
    d[enAlpha[i]] = amount[i]

file = open('C:\\Users\\Kirill\\Downloads\\lorem.txt')
data = file.read()
data = data.lower()


for i in range(len(data)):
    if(d.get(data[i]) != None):
        d[data[i]] = d.get(data[i]) + 1

print(d)

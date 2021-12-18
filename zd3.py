file = open("C:\Users\Kirill\Downloads\words.txt", "t")
data = file.read().split()
longest = max(data, key=len)
print(longest, len(longest))
file.close()


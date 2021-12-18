import random
import math

numbers = []
p = False
t = 0.0

for i in range(10):
    numbers.append(round(random.uniform(-100,100),3))
    if(numbers[-1] - int(numbers[-1]) != 0):
        p = True

while(p == False):
    numbers[-1] = round(random.uniform(-100,100),3)
    if((numbers[-1] - int(numbers[-1])) != 0):
        p = True

for i in range(10):
    for j in range(10):
        if(math.fabs(numbers[i] - int(numbers[i])) < math.fabs(numbers[j] - int(numbers[j]))):
            t = numbers[i]
            numbers[i] = numbers[j]
            numbers[j] = t

print(numbers)

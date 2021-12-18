x = float(input("введите x = "))
y = float(input("введите y = "))
s = input("выберите знак (+,-,*,/): ")

if s == '+':
    print(x+y)
elif s == '-':
    print(x-y)
elif s == '*':
    print(x*y)
elif s == '/':
    if y != 0:
        print(x/y)
    else:
        print("Деление на ноль!")
else:
    print("Неверный знак операции!")
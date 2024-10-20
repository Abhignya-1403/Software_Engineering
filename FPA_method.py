ar = [[0 for _ in range(4)] for _ in range(6)]
categories = ["Input", "File", "Query", "Interface", "Output"]
w1 = float(input("Enter weighing factor of Simple (default 0.5): ") or 0.5)
w2 = float(input("Enter weighing factor of Average (default 1.0): ") or 1.0)
w3 = float(input("Enter weighing factor of Complex (default 1.5): ") or 1.5)
for i, category in enumerate(categories):
    ar[i][0] = int(input(f"Enter Initial value of '{category}': "))
    inputt = input(f"Enter Input Ratio of '{category}' (use 'simple:average:complex' or one of these): ")
    if ":" in inputt:
        temp = list(map(int, inputt.split(":")))
        total = sum(temp)
        for j in range(3):
            ar[i][j+1] = temp[j] * ar[i][0] // total
    else:
        idx = ["simple", "average", "complex"].index(inputt.lower())
        ar[i][idx+1] = ar[i][0]
for j in range(1, 4):
    ar[5][j] = sum(ar[i][j] for i in range(5))
arr = []
while (temp := input("Enter TDI (Type '00' to finish): ")) != "00":
    arr.append(temp)
ufp = sum(w * ar[5][i+1] for i, w in enumerate([w1, w2, w3]))
print(f"Unadjusted Function Point: {ufp}")
tdi = sum(float(i[:-1]) / 100 if "%" in i else float(i) for i in arr)
print(f"Total Degree of Influence: {tdi}")
vaf = 0.01 * tdi + 0.65
print(f"Value Added Factor: {vaf}")
afp = vaf * ufp
print(f"Adjusted Function Point: {afp}")

#Enter weighing factor of Simple (default 0.5): 
#Enter weighing factor of Average (default 1.0):
#Enter weighing factor of Complex (default 1.5):
#Enter Initial value of 'Input': 60
#Enter Input Ratio of 'Input' (use 'simple:average:complex' or one of these): 1:6:3
#Enter Initial value of 'File': 25
#Enter Input Ratio of 'File' (use 'simple:average:complex' or one of these): 1:2:2
#Enter Initial value of 'Query': 35
#Enter Input Ratio of 'Query' (use 'simple:average:complex' or one of these): Average
#Enter Initial value of 'Interface': 20
#Enter Input Ratio of 'Interface' (use 'simple:average:complex' or one of these): Complex
#Enter Initial value of 'Output': 25
#Enter Input Ratio of 'Output' (use 'simple:average:complex' or one of these): 2:2:1
#Enter TDI (Type '00' to finish): 00
#Unadjusted Function Point: 181.0
#Total Degree of Influence: 0
#Value Added Factor: 0.65
#Adjusted Function Point: 117.65



                
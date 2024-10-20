#text.txt
#z=0
#while x>0
#z=z+y
#x=x-1
#end while,
#print z

import math
from collections import Counter
file = open("text.txt", "r")
a = file.readlines()
temp = []
for i in a:
    temp.extend(i.split())
d = Counter(temp)
operator_list = ["=", "+", "-", "*", "/", ";", ",", "<", ">", "<=", ">=", "%", "!", "(", "{", "["]
for char in [")", "}", "]"]:
    if char in d:
        del d[char]
operands = 0
operand_number = 0
operators = 0
operator_number = 0
for k, v in d.items():
    if k in operator_list:
        operators += 1
        operator_number += v
    else:
        operands += 1
        operand_number += v
n = operators + operands
N = operator_number + operand_number
print(f"Number of operators(n1): {operators}")
print(f"Number of operands(n2): {operands}")
print(f"Token number (n): {n}")
print(f"Total number of Operators (N1): {operator_number}")
print(f"Total number of Operands (N2): {operand_number}")
print(f"Total number of Tokens (N): {N}")
print(f"1. Length of Program (N): (N1 + N2) = {N}")
print(f"2. Estimated Length: n * log(n) = {n * math.log(n, 2)}")
print(f"3. Volume (V): N * log(N) = {N * math.log(N, 2)}")
file.close()

#Number of operators(n1): 0
#Number of operands(n2): 9
#Token number (n): 9
#Total number of Operators (N1): 0
#Total number of Operands (N2): 9
#Total number of Tokens (N): 9
#1. Length of Program (N): (N1 + N2) = 9
#2. Estimated Length: n * log(n) = 28.529325012980813
#3. Volume (V): N * log(N) = 28.529325012980813

#Erick Mikoshi
#Problem 3 - Final Project (All binary trees)

usr = input("Enter a number for all binary trees: ")
arr = [ ]
for i in range(1,usr+1):
    arr.append(i)

#logic to make the binary trees
def MakeBinTree(x):
    #base cases
    if len(x) == 1:
        return [x]
    if len(x) == 2:
        temp_arr = [ ]
        temp_arr.append(x[0])
        temp_arr.append(x[1])
        return [temp_arr]
        
    ret = [ ]
    
    #getting the left and right of the tree recursively
    for i in range(len(x)-1):
        left = MakeBinTree(x[0:i+1])
        right = MakeBinTree(x[i+1:])
        
        #appending the lefts and rights to the final list(ret)
        for j in range(len(left)):            
            for k in range(len(right)):
                temp_arr_2 = [ ]
                temp_arr_2.append(left[j])
                temp_arr_2.append(right[k])
                ret.append(temp_arr_2)
    return ret

#printing out the binary trees
def PHT(tree):
    if type(tree) == int:
        return str(tree)
    if len(tree) == 1:
        return PHT(tree[0])
    if len(tree) == 2:
        return "(" + PHT(tree[0]) + " " + PHT(tree[1]) + ")"

printOut = MakeBinTree(arr)

#looping through the list, correctly formatting and printing out all trees
for i in range(len(printOut)):
    print PHT(printOut[i])

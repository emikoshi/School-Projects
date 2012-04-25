#Erick Mikoshi
#Problem 2 - Final Project (All subsets, specfying the set)

arr = [ ]
usr_arr = [ ]

usr = input("Please enter a number to see all of its subsets: \n")

for i in range(usr):
    usr_arr.append(input("Please enter number" + "[" + str(i) + "]:"))

#function to return the temporary subset
def tempArr(x, i):
    temp_arr = [ ]
    for k in range(len(x)):
        temp_arr.append(x[k])
    temp_arr.append(i)
    return temp_arr
    
#the logic used to append the temporary subset to the list  
for i in range(usr):
    for j in range(len(arr)):
        temp_val = tempArr(arr[j], usr_arr[i])
        arr.append(temp_val)
    arr.append([usr_arr[i]])

#fuction to print the array(all subsets)
def printArr():
    print "{}"
    for i in range(len(arr)):
        print "{",
        for j in range(len(arr[i])):
            print arr[i][j],
        print "}"

printArr()

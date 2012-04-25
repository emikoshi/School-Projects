#Erick Mikoshi
#This is the program for pretty printing

#setting a global for the fixed width of the line
L = 78

#opening the file, reading in its lines, and making a new file
new_f = open('newout.txt', 'w')
f = open("words.txt")
words = f.readlines()

#stripping down each word
for i in range(len(words)):
    words[i] = words[i].strip()

#creating a cache of the number of words * width and filling it with -1's
OPT = []
for i in range(len(words)):
    OPT.append([])
    for j in range(L):
        OPT[i].append(-1)
 
#implementing the recursive relation for pretty print
def best(w, l):
    #if all the words have been gone through
    if (w >= len(words)):
        #return the final sum of squares
        return (L - l)**2
    
    #setting a variable to the current length of the word 
    cur_l = len(words[w])

    #since the current words length + space + running sum is greater,
    #must go to a new line
    if L < l + 1 + cur_l:
        return (L - l) ** 2 + best(w+1, cur_l)

    #if the spot in the cache hasn't been filled
    if OPT[w][l] == -1:
        #set the cache to be the min of the best with the word on the current line
        #or the best of the word on a new line
        OPT[w][l] = min(best(w+1, l + 1 + cur_l), (L - l)**2 + best(w+1, cur_l))

    return OPT[w][l]

sum_of_sq = best(0, -1)

#traversing through the cache and ouputting it to a file,
#with the same idea as best
def print_pretty (w, l):
    
    if (w >= len(words)):
        return (L-l)**2

    cur_l = len(words[w])
    
    if L < l + 1 + cur_l:
        new_f.write("\n")
        new_f.write(words[w])
        return (L - l) ** 2 + print_pretty(w+1, cur_l)
    
    if best(w+1, l + 1 + cur_l) < (L - l)**2 + best(w+1, cur_l):
        if (w != 0):
            new_f.write(" ")
        new_f.write(words[w])
        return print_pretty(w+1, l + 1 + cur_l)
    else:
        new_f.write("\n" + words[w])
        return (L - l) ** 2 + print_pretty(w+1, cur_l) + 1
            
print_pretty(0,-1)
new_f.write("\n\n"+str(sum_of_sq))
new_f.close()
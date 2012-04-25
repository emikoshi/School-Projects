#Erick Mikoshi - Algorithms Project 1
#Implemented algorithm to measure the validity of butterfly data

#Declaring and initializing variables
user_input = raw_input('What file do you want to use?')
raw_G = [ ]
G = [ ]
colors = [ ]
related = [ ]
raw_list = [ ]
seen = [ ]
count = 0
final = 0
f = open(user_input, 'r')

#Reads the first line of the file
first_line = f.readline()

#Splits the line into an array so the values can be stored as int variables
first_split = first_line.split()
butterflies = int(first_split[0])
measurements = int(first_split[1])

#Creating place holders for the appropriate arrays
for i in range(measurements):
    raw_G.append([0, 0, ''])

for i in range(butterflies):
    G.append([ ])
    related.append([ ])
    colors.append(0)
    seen.append(0)

other_lines = f.readlines()

#Read in the rest of the file, split it, and append to the raw graph.
for i in other_lines:
    sp = i.split()
    raw_list.append(sp)
    
for i in raw_list:
    raw_G[count][0] = int(i[0]) 
    raw_G[count][1] = int(i[1])
    raw_G[count][2] = i[2]
    count += 1

#Logic for creating the actual graph with connectivities, as well as an array
#that corresponds to it with labeled edges.
for i in raw_G:
    if not i[0] in G[i[1]]:
        G[i[1]].append(i[0])
        related[i[1]].append(i[2])
    if not i[1] in G[i[0]]:
        G[i[0]].append(i[1])
        related[i[0]].append(i[2])


#print "Raw Graph: ", raw_G
#print "Connectivity Graph: ", G
#print "Relativity: ", related

#Function to set the colors of each node (3 and 7 are used)
def set_colors(u, v):
    #print "u: ",u, "v: ", v, related[v][G[v].index(u)]
    if related[v][G[v].index(u)] == 'same':
        colors[u] = colors[v]
    if related[v][G[v].index(u)] == 'different':
        if colors[v] == 7:
            colors[u] = 3
        if colors[v] == 3:
            colors[u] = 7

#Declaring and initializing vairables for DFS. Setting the first node to
#arbitrary color.
start = 0
tbs = [ start ]
colors[0] = 3  

#DFS
while len(tbs) > 0:
    #print("tbs=", tbs)
    v = tbs.pop()
    if seen[v] == 0:
        seen[v] = 1
        #print("JUST SAW", v)
        for u in G[v]:
            tbs.append(u)
            set_colors(u, v)

#print colors

#Butterfly verification. Used cases that would apply to find bad data,
#else it is good data.
for i in range(len(G)):
    for j in range(len(G[i])):
        #print "label:", related[i][j], ",colori: ", colors[i], "other color: ", colors[G[i][j]]
        #print "i: ",i, "j :", G[i][j]
        if related[i][j] == 'same' and colors[i] != colors[G[i][j]]:
            final = 1
        if related[i][j] == 'different' and colors[i] == colors[G[i][j]]:
            final = 1

if final == 1:
    print "BAD DATA"
else:
    print "GOOD DATA"
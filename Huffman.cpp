/*
 Erick Mkoshi 
 
 -Project that implements the concept of Huffman Coding (this is just the compressor)
*/

#include <stdio.h>
#include <errno.h>
#include <algorithm>
#include <vector>
#include <iostream>
#include <bitset>

using namespace std;

// evil global variables
int fileSize = 0;
vector<bitset<32> > the_bits;
int depths[256];


// treeNode used in creating huffman tree
struct treenode {
	struct treenode *left;
	struct treenode *right;
	char data;
	int frequency;
	bitset<32> nbit;
	int ndepth;
	int marked;
};

typedef struct treenode node;

// making a compare operator for the sorting based on frequency
bool compare(node *v1, node *v2) { return (v1->frequency < v2->frequency); }

// this takes care of printing the tree in the correct format
void printNodeTree(node *node) {
	if (node == NULL) return;
		printf("(");
	if(node->left){
		printNodeTree(node->left);
		printNodeTree(node->right);
	}
	else{
		printf("%d", node->data);
	}
	printf(")");
}


// finds the filesize of the inputted file
void makeFilesize(node *node, int ndepth, int arr[256]){
	node->ndepth = ndepth;
	if (!node->left && !node-> right){
		 fileSize += (node->ndepth * node->frequency);
	}
	if (node->left) {
		makeFilesize(node->left, ndepth+1, arr);
	}
	if (node->right) {
		makeFilesize(node->right, ndepth+1, arr);
	}
}

// traverses the huffman tree and creates a look up table for each character in the file
void makeBits(node *node, int ndepth, int arr[256] ) {
        node->ndepth = ndepth;
        if (!node->left && !node-> right) {

			for (int i = 31; i > 31-ndepth; i--) 
				the_bits[node->data][i] = node->nbit[i];
				depths[node->data] = node->ndepth;
        }
        if (node->left) {
                for (int i = 0; i <= ndepth; i++)
                        node->left->nbit[32-i] = node->nbit[32-i];
                node->left->nbit[31-(ndepth)] = 0;
                makeBits(node->left, ndepth+1, arr);
        }
        if (node->right) {
                for (int i = 0; i <= ndepth; i++)
                        node->right->nbit[32-i] = node->nbit[32-i];
                node->right->nbit[31-(ndepth)] = 1;
                makeBits(node->right, ndepth+1, arr);
        }
}

void createList(int arr[256]) {
	// this creates a list of nodes with respective data, frequencies, and etc. 
	vector<node*> nodeVec;
	node *final_node;
	int i = 0;
	for (i = 0; i < 256; i++) {
		if (arr[i] > 0) {
			node *temp;
			temp = (node *)malloc(sizeof(node));
			temp->left = NULL;
			temp->right = NULL;
			temp->data = i;
			temp->frequency = arr[i];
			for (int k = 0; k < 32; k++) 
				temp -> nbit[k] = 0;
			nodeVec.push_back(temp);
		}
	}

	sort(nodeVec.begin(), nodeVec.end(), &compare);

	// this takes the first two nodes off the vector, combines them, and pops the combined node back on
	while (nodeVec.size() > 1) {
		node *new_node;
		new_node = (node *)malloc(sizeof(node));
		new_node -> left = nodeVec[0];
		nodeVec.erase(nodeVec.begin());
        new_node -> right = nodeVec[0];
        nodeVec.erase(nodeVec.begin());
		new_node -> frequency = new_node -> left -> frequency + new_node -> right -> frequency;
		nodeVec.push_back(new_node);
		sort(nodeVec.begin(), nodeVec.end(), &compare);
		if (nodeVec.size() == 1) 
			final_node = new_node;
	}
	// prints out the part of the header which includes the tree and filesize
	the_bits.resize(256);
	makeFilesize(final_node, 0, arr);
	printNodeTree(final_node);
	printf("%010d", fileSize);
	makeBits(final_node, 0, arr);
}

int main(int argc, char* argv[])
{
    FILE *in;
    char* data;
    int compressable[256];
    int dsize = 0;
    int arraysize = 1;
    char c;
    int i;
	
	for (i = 0; i < 256; i++) 
		compressable[i] = 0;

	if (argc > 1) {
        in = fopen(argv[1], "r");
        if (in == NULL) {
            perror(argv[1]);
            exit(errno);
        }
    } else {
        in = stdin;
    }
    
	data = (char*)malloc(arraysize * sizeof(char));
    
	while (!feof(in)) {
	data[dsize++] = fgetc(in);
        if (dsize == arraysize) {
            arraysize *= 2;
            data = (char*)realloc(data, arraysize*sizeof(char));
        }  
    }
    
	dsize--;


    for (i = 0; i < dsize; i++) 
		compressable[data[i]]++;

    createList(compressable);

    int eighth;
    eighth = 0;
    vector<int> trans;
    bitset<8> buffer;
  
	// this pushes the binary data into a large vector for translating 
    for (int x = 0; x < dsize; x++) {
    	if (data[x] >= 0) {
		for (int j = 31; j > 31-depths[data[x]]; j--) {
			trans.push_back(the_bits[data[x]][j]);
		}
	}
	
    }
    int num;
    int count = 1;

	// pulls in 8 bits at a time from the vector and translates it into an ascii representation
    for (int g = 0; g < trans.size(); g++){
		if (eighth <  8){
			buffer[7-eighth] = trans[g];
			eighth++;	
		}
		else{
			eighth = 0;
			num  = buffer.to_ulong();
			count++;
			g--;
			printf("%c", num);
			buffer.reset();
		}
    }
    num  = buffer.to_ulong();
	printf("%c", num);

	return 0;
}

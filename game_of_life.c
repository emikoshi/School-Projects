/*/////////////////////////////////////////////
// Erick Mikoshi     CMPT353-01              //
//      C Class Project #4                   //
//                                           //
// This program simulates the game of life   //
// on a 12x12 checkerboard 2D array for the  //
// first 10 generations.                     //
//                                           //
// Variable Directory:                       //
//                                           //
// x,y  rows & columns                       //
// width,height  size of 2D array            //
// cur,fut  current and future array         //
// count counter for generations             //
// a  counter for specific checks            //
//                                           //
/////////////////////////////////////////////*/
 
# include <stdio.h>

int main() {
	
	/* Initializing the variables used througout the program */
	int x = 0;
	int y = 0;
	int width = 12;
	int height = 12;
	char cur[12][12];
	char fut[12][12];
	
	printf("Initial Generation \n");
	
	/* A nested for-loop to traverse through and add blanks to 
	   the 2D array */
	for(x = 0; x<width; x++){
		for(y = 0; y<height; y++){
			cur[x][y] = ' ';
			fut[x][y] = ' ';
		}
	}
	/* Adding the initial input to the 2D array cur */
	cur[3][4] = '*';
	cur[3][7] = '*';
	cur[4][4] = '*';
	cur[4][5] = '*';
	cur[4][6] = '*';
	cur[4][7] = '*';
	cur[5][3] = '*';
	cur[5][8] = '*';
	cur[6][3] = '*';
	cur[6][5] = '*';
	cur[6][6] = '*';
	cur[6][8] = '*';
	cur[7][3] = '*';
	cur[7][8] = '*';
	cur[8][4] = '*';
	cur[8][5] = '*';
	cur[8][6] = '*';
	cur[8][7] = '*';
	
	/* Printing out cur, showing the initial input */
	for(x = 0; x<width; x++){
		printf("\n");
		for(y = 0; y<height; y++){
			printf("%c",cur[x][y]);
		}
	}
	
	/* Initializing the counter for generations */
	int count;
	
	/* Printing out what number generation it is */
	for (count = 1; count < 11; count++) {
		printf("\n");
		printf("\nGeneration %i \n",count);
		
		/* Traversing through each spot in the 2D array,
		   checking the bounds, and using 'a' to count for
		   the appropriate situations */
		for(x = 0;x<width;x++){
			for(y = 0; y<height; y++){
				
				int a = 0;
				
				if (y > 0) {
					if(cur[x][y-1] == '*')
						a++;
				}
				if (x > 0 && y >0) {
					if(cur[x-1][y-1] == '*')
						a++;
				}
				if (x > 0) {
					if(cur[x-1][y] == '*')
						a++;
				}
				if (x > 0 && y < height - 1) {
					if(cur[x-1][y+1] == '*')
						a++;
				}
				if (y < height -1) {
					if(cur[x][y+1] == '*')
						a++;
				}
				if (x < width - 1) {
					if(cur[x+1][y] == '*')
						a++;
				}
				if (x < width - 1 && y > 0) {
					if(cur[x+1][y-1] == '*')
						a++;
				}
				if (x < width - 1 && y < height - 1) {
					if(cur[x+1][y+1] == '*')
						a++;
				}
				
				/* Adding blanks or * to the future generations */
				if(a < 2)
					fut[x][y] = ' ';
				else if(cur[x][y] == ' ' && a == 3)
					fut[x][y] = '*';
				else if (cur[x][y] == '*' && (a == 2 || a == 3))
					fut[x][y] = '*';
				else
					fut[x][y] = ' ';
			}
		}
		/* Printing out each of the new 2D arrays based off the last,
		   and copying the future array to the current one */
		for(x = 0; x<width; x++){
			printf("\n");
			for(y = 0; y<height; y++){
				printf("%c",fut[x][y]);
				cur[x][y] = fut[x][y];
			}
		}
	}
	printf("\n");
}


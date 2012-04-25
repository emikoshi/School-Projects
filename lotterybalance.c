/*////////////////////////////////////////
//                                      //
// Erick Mikoshi            CMPT 312-01 //
//                                      //         
// Program #1b -  Lottery Scheduler     //
//                                      //
// A new process called sub2 was added  //
// to the lottery program. Now the      //
// purpose of this program is to        //
// balance the processes so g stays     //
// around 0. It still uses the methods  //
// of giving each process a random      //
// number then "calling" that number to //
// be executed. It has two mock         //
// processes adder and subtractor. Both //
// of which will be executed            //
// accordingly.                         //
//                                      //
// Variable Directory:                  //
// adpc - Counter for adder             //
// supc - Counter for subtractor        //
// g - Global counter for processes     //
// i - Loop counter                     //
//                                      //
////////////////////////////////////////*/
#include <stdio.h>
#include <stdlib.h>
#include <time.h>

/* Declating and initializing values/functions */
void adder();
void sub();
void sub2();
int adpc = 1;
int supc = 1;
int g = 0;

int main () {
	
	/* Initializing the random numbers assigned to adder and subtractor */
	int addlot,sublot,sub2lot;
	
	/* Allows for different random numbers to be generated every time */
	srand(time(NULL));
	
	/* Assiging lottery numbers to adder and subtractor, between 0 and 99 */
	addlot = rand() % 100;
	sublot = rand() % 100;
	
	/* A large weight is given to sub2, where the numbers range from 0 to 9999 */
	sub2lot = rand() % 10000;
	
	int i;
	/* Counter to go through 100000 steps */
	for (i = 0; i < 100000 ; i++) {
		
		/* Assigning a random number to the lottery "picker" */
		int r;
		r = rand() % 100; 
		
		/* If the random number is picked execute adder, subtractor or sub2 */
		if (addlot == r) {
			adder();
		}
		if (sublot == r) {
			sub();
		}
		if (sub2lot == r) {
			sub2();
		}
		
		/* Every 1000 steps, print the value of g */
		if (i % 1000 == 0) {
			printf("%d: g is %d\n", i, g);
		}
		
	}
	return 0;
}

/*//////////////////////////////////
//                                //
//        Adder Function          //
//                                //
// The purpose of this function   //
// is to recreate a process that  //
// adds to g. The value that is   //
// added to g depends on the      //
// specific case. It is important //
// to note that once adpc reaches //
// case 6 it loops back around to //
// case 1. Therefore, it keeps    //
// running until the desired      //
// number of steps are completed. //
// Which is denoted by i.         //
//                                //
//////////////////////////////////*/

void adder(){
	/* adpc triggers a specific case depending on its value */
	switch (adpc){
		case 1: 
			g++;
			adpc = 2;
			break;
		case 2:
			g++;
			adpc = 3;
			break;
		case 3:
			g += 2;
			adpc = 4;
			break;
		case 4: 
			g += 3;
			adpc = 5;
			break;
		case 5:
			g++;
			adpc = 6;
			break;
		case 6:
			g += 2;
			adpc = 1;
			break;
		default:
			printf("Error");
	}
}

/*////////////////////////////////////
//                                  //
//      Subtractor Function         //
//                                  //
// The purpose of this function     //
// is to recreate a process that    //
// subtracts from g. The value      //
// that is subtracted to g depends  // 
// on the specific case. It is      //
// important to note that once supc //
// reaches case 6 it loops back     //
// around to case 1. Therefore, it  //
// keeps running until the desired  //
// number of steps are completed.   //
// Which is denoted by i.           //
//                                  //
////////////////////////////////////*/

void sub(){
	/* supc triggers a specific case depending on its value */
	switch (supc){
		case 1: 
			g--;
			supc = 2;
			break;
		case 2:
			g--;
			supc = 3;
			break;
		case 3:
			g -= 2;
			supc = 4;
			break;
		case 4: 
			g -= 3;
			supc = 5;
			break;
		case 5:
			g--;
			supc = 6;
			break;
		case 6:
			g -= 2;
			supc = 1;
			break;
		default:
			printf("Error");
	}
}

/*////////////////////////////////////
//                                  //
//         Sub2 Function            //
//                                  //
// The purpose of this function     //
// is to throw off the balance of   //
// the lottery scheduler. It simply //
// is just another process that     //
// subtracts 1 from g.              //
//                                  //
////////////////////////////////////*/

void sub2(){
	g--;
}

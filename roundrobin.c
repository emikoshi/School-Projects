/*////////////////////////////////////////
//                                      //
// Erick Mikoshi            CMPT 312-01 //
//                                      //         
// Program #1a -  Round Robin Scheduler //
//                                      //
// The purpose of this program is to    //
// show how the Round Robin scheduler   //
// is executed in the computer, which   //
// basically switches between the two   //
// processes. It has two mock processes //
// adder and subtractor. Both of which  //
// will be executed accordingly.        //
//                                      //
// Variable Directory:                  //
// adpc - Counter for adder             //
// supc - Counter for subtractor        //
// g - Global counter for processes     //
// i - Loop counter                     //
//                                      //
////////////////////////////////////////*/

#include <stdio.h>

/* Declating and initializing values/functions */
void adder();
void sub();
int apc = 1;
int spc = 1;
int g = 0;

int main () {
	
	int i;
	/* Counter to go through 100000 steps */
	for (i = 0; i < 100000 ; i++) {
		adder();
		sub();
		
		/* Every 1000 steps, print the value of g */
		if (i % 1000 == 0) {
			printf("g is %d", g);
			printf("\n");
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
			g = g + 2;
			adpc = 4;
			break;
		case 4: 
			g = g + 3;
			adpc = 5;
			break;
		case 5:
			g = g + 1;
			adpc = 6;
			break;
		case 6:
			g = g + 2;
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
			g = g - 2;
			supc = 4;
			break;
		case 4: 
			g = g - 3;
			supc = 5;
			break;
		case 5:
			g = g - 1;
			supc = 6;
			break;
		case 6:
			g = g - 2;
			supc = 1;
			break;
		default:
			printf("Error");
	}
}

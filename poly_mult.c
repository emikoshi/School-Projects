/*///////////////////////////////////////////////////////////////////////////
// Erick Mikoshi                                               CMPT 353-01 //
//                                                                         //
//                       C Class Project #8                                //
//                                                                         //
// This purpose of this program is to take in two polynomials of           //
// increasing power from the user and add them together.                   //
//                                                                         // 
// Variable Directory:                                                     //
//                                                                         //
// term   node of the user defined data structure                          //
// coeff  component of term; int variable for the coefficient of the       //
//        polynomial                                                       //
// pow   component of term; int variable for the power                     //
// *next  component of term; pointer to the next node                      //
// n int variable; size of the char arrays (30)                            //
// p1, p2  char arrays for the polynomial of size n                        //
// p3c, p4c  char array for the coefficient of size n                      //
// p3e, p4e   char arrays for the exponents of size n                      //
// i, c, e   int variables                                                 //
// TERM   a new name equivalent to the user defined data structure         //
// *head1  linked list, represents first polynomial entered by user        //
// *head2  linked list, represents second polynomial entered by user       //
// *head3  linked list which represents the output of the sum of the two   //
//         polynomials entered by the user                                 //
//////////////////////////////////////////////////////////////////////////*/


#include <stdio.h>
#include <stdlib.h>
#include <string.h>

/* Declaration of user defined data structure */
struct term
{
	int coeff;
	int pow;
	struct term *next;
};

/* Declaration of new instance of the struct term */
typedef struct term TERM;

/* Function Declarations */
void insertpoly(TERM** p, int c, int e); 
void addpoly(TERM* p1, TERM* p2, TERM** p3);
void displaypoly(TERM* p);

int main()
{
	int n = 30;
	char p1[n];
	char p2[n];
	char p3c[n];
	char p3e[n];
	char p4c[n];
	char p4e[n];
	int i, c, e;
	TERM *head1, *head2, *head3;
	
	/* Prompts user to enter their first 
	 polynomial, then scans the input and 
	 calls the function insertend to input
	 the data into head1 */
	printf("Enter your first polynomial!\n");
	scanf("%s",p1);
	
	for (i = 0; p1[i] != '\0'; i++)
	{
		if (p1[i] == 'X') {
			p3c[0] = p1[i-3];
			p3c[1] = p1[i-2];
			p3c[2] = '\0';
			p3e[0] = p1[i+2];
			p3e[1] = '\0';
			/* Converting the string array to an integer */
			c = atoi(p3c);
			e = atoi(p3e);
			insertpoly(&head1,c,e);
		}
	}
	
	/* Prompts user to enter in a second 
	 polynomial, then scans the input and 
	 calls the function insertend to input
	 the data into head2 */
	printf("Enter your second polynomial!\n");
	scanf("%s",p2);
	
	for (i = 0; p2[i] != '\0'; i++)
	{
		if (p2[i] == 'X') {
			p4c[0] = p2[i-3];
			p4c[1] = p2[i-2];
			p4c[2] = '\0';
			p4e[0] = p2[i+2];
			p4e[1] = '\0';
			/* Converting the string array to an integer */
			c = atoi(p4c);
			e = atoi(p4e);
			insertpoly(&head2,c,e);
		}
	}
			
	/* Function call to polyadd. Adds the first
	 and second polynomials and puts the
	 output sum in head3*/		
	addpoly(head1,head2,&head3);
	
	/* Displays the sum of the two polynomials
	 which is stored in head3*/
	displaypoly(head3);
}
	

/*///////////////////////////////////////////
//                                         //
// This purpose of this polyadd function   //
// is to simply add the two inputted       //
// polynomials together.                   //
//                                         //
// Variable Directory:                     //
//                                         //
// c the integer for the coefficient       //
//   of the polynomial                     //
// e the integer for the exponent          //
//   of the polynomial                     //
// *p1 pointer to term in first poly       //
// *p2 pointer to term in second poly      //
// **p3 pointer to first and second poly   //
// coeff component of the node term        //
// pow component of the node term          //
// next pointer to the next node of term   //
//                                         //
///////////////////////////////////////////*/

void addpoly(TERM *p1,TERM *p2,TERM **p3)
{
	int c, e; /* Initializing integers c and e */
	
	while(p1 != NULL && p2 != NULL) //While the term p1 and p2 are not equal to null
	{
		/* If the power of p1 is greater than the power of p2, then assign c the value of p1's
		   coefficient and e the value of p1's exponent. Then insertend is used to make a new node
		   in p3 with c and e, and finally p1 is set equal to the next node. */
		if(p1->pow > p2->pow) 
		{
			c = p1->coeff; 
			e = p1->pow; 
			insertpoly(p3,c,e);
			p1 = p1->next;
		}
		/* Else if the power of p2 is greater than the power of p1, then assign c the value of p2's
		 coefficient and e the value of p2's exponent. Then insertend is used to make a new node
		 in p3 with c and e, and finally p2 is set equal to the next node. */
		else if(p2->pow > p1->pow)
		{
			c = p2->coeff;
			e = p2->pow;
			insertpoly(p3,c,e);
			p2 = p2->next;
		}
		/* Else if the addition of coefficient p1 and coefficient p2 is equal to 0, then point p1
		   to the next node and point p2 to the next node. It does not store the terms in a 
		   new node. */
		else if(p1->coeff + p2->coeff == 0)
		{
			p1 = p1->next;
			p2 = p2->next;
		}
		/* Finally if none of the other cases are met, then c is equal to the addition of the coefficient
		   of p1 and p2, also e is set to the exponent of p1. Then insertend is used to make a new node
		   in p3 with c and e. p1 and p2 are set to equal to the next node. */
		else
		{
			c = p1->coeff + p2->coeff;
			e = p1->pow;
			insertpoly(p3,c,e);
			p1 = p1->next;
			p2 = p2->next;
		}
	}
	/* While p1 is not null, make a new node in p3 with the coefficient and power of p1. */
	while(p1 != NULL)
	{
		insertpoly(p3, p1->coeff, p1->pow);
		p1 = p1->next;
	}
	/* While p2 is not null, make a new node in p3 with the coefficient and power of p2. */
	while(p2 != NULL)
	{
		insertpoly(p3, p2->coeff, p2->pow);
		p2 = p2->next;
	}
}

/*///////////////////////////////////////////////////
//                                                 //
// This function steps through the polynomial      //
// and creates a linked list of nodes.             //
//						                           //
// Variable Directory				               //
//						                           //
// **p	 the linked list		                   //
// c	 integer for the coefficent                //
// e	 integer for the exponent	               //
// *n	 the node pointer		                   //
// *cur  the current node		                   //
//						                           //
///////////////////////////////////////////////////*/

void insertpoly(TERM **p, int c, int e)
{
	/* Create our node pointer and assign it values */
	TERM *n, *cur;
	n=(TERM*)malloc(sizeof(TERM));
	n->coeff = c;
	n->pow = e;
	n->next = NULL;
	cur = *p;
	
	/* If *p is null make it equal to n */
	if(*p == NULL)
		*p = n;
	
	/* Else go through the list and asssign each node to n */
	else
	{
		while(cur->next!=NULL)
			cur=cur->next;
		cur->next=n;
	}
}

/*///////////////////////////////////////////////////
//                                                 //
// This small function is used to display the      //
// resulting polynomial after the two are added.   //
// Variable Directory				               //
//						                           //
// TERM *p	  the pointer of the poly. to display  //
// cur	      temp. placeholder of pointer p       //
//						                           //
///////////////////////////////////////////////////*/


void displaypoly(TERM *p)
{
	TERM *cur;
	cur = p;
	printf("The final polynomial is: \n");
	while(cur != NULL)
	{
		printf("+%d*X^%d",cur->coeff,cur->pow);
		cur = cur->next;
	}
}

	
	
	
	
	
	
	
	
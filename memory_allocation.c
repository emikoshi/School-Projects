/*///////////////////////////////////////////////////////////
// Erick Mikoshi                               CMPT 312-01 //
//               Operating Systems - Project 2             //
//                                                         //
// The purpose of this program is to simulate the two      //
// different memory allocation schemes: first fit and best //
// fit. The user is first prompted to enter a certain      //
// amount of processes and queues. Then they can select    //
// which memory allocation scheme they can run. The        //
// implementation is done using linked lists.              //
//                                                         //
// Variable Lookup:                                        //
//                                                         //
// partition - linked list with nodes that include info    //
// about the partitions.                                   //
// --part_size - the node that contains the partition size //
// --part_waste - the node that contains the waste size    //
// --flag - used to mark which partition has been seen     //
// --*next - pointer to the next partition node            //
// --*pcurr - pointer to the current partition node        //
// --*phead - pointer to the top of partition linked list  //
// --p - assigning an alternative name to struct partition //
// queue - linked list with nodes that include info about  //  
// the processes.                                          //
// --process - the node that contains the process size     //
// --flag - used to mark which processes have been seen    //
// --*next - pointer to the next proces node               //
// --*qcurr - pointer to the current queue process         //
// --*qhead - pointer to the top of queue linked list      //
// --*qtemp - temporary pointer (used for best fit)        //
// --q - assigning an alternative name to struct process   // 
// -i,j - for loop counters used for input                 //
// -n - number of processes and queues fromt the user      //
// -e,f - counters to show what process or queues the user //
// is entering in.                                         //
//                                                         //
///////////////////////////////////////////////////////////*/

#include <stdio.h>
#include <stdlib.h>

/* Declaring the partition linked list with part_size, part_waste, flag, and *next */
struct partition {
	int part_size;
	int part_waste;
	int flag;
	struct partition *next;
};

/* Declaring the queue linked list with process, flag, and *next */
struct queue {
	int process;
	int flag;
	struct queue *next;
};

/* Assigning alternative names to the two linked lists */
typedef struct partition p;
typedef struct queue q;

int main () {
	
   /* Declaring various pointers for each linked list */
   p *pcurr, *phead;
   q *qcurr, *qhead, *qtemp;
	
   /* Declaring/initializing various counters and input variables */
   int i, j, n, part_input, queue_input, user_input;
   int e = 1;
   int f = 1;

   /* Initializing the both heads to point to NULL */
   phead = NULL;
   qhead = NULL;
	
   /* Prompting the user to enter the number of processes and queues */
   printf("Please enter the number of processes and queues: \n");
   scanf("%d", &n);
	
   /* For loop to add each partition by the user into the linked list */
   for (i = 0; i < n; i++) {
      printf("Enter partition size #%d: \n",e);
      scanf("%d", &part_input);
      e++;
      pcurr = (p *)malloc(sizeof(p));
      pcurr->part_size = part_input;
      pcurr->part_waste = 0;
      pcurr->next = phead;
      phead = pcurr;
		
   }
	
   /* For loop to add each process by the user into the linked list */
   for (j = 0; j < n; j++) {
      printf("Enter queue size #%d: \n",f);
      scanf("%d", &queue_input);
      f++;
      qcurr = (q *)malloc(sizeof(q));
      qcurr->process = queue_input;
      qcurr->next = qhead;
      qhead = qcurr;
   }
	
   /* Diplaying the original values of the partitions and linked lists */
   printf("\nThe original fixed partition sizes are: ");
   while (pcurr) {
      printf("%d ", pcurr->part_size);
      pcurr = pcurr->next;
   }
	
   printf("\nThe original process queue is: ");
   while (qcurr) {
      printf("%d ", qcurr->process);
      qcurr = qcurr->next;
   }

   /* Asking user which memory scheme they want to use */
   printf("\nChoose your memory scheme:\n\nFor first fit, press 1\nFor best fit, press 2\n");
   scanf("%d", &user_input);
	
   /* First Fit Scheme */
   if (user_input == 1) {
      /* Setting the current pointers to the head pointers */
      pcurr = phead;
      qcurr = qhead;
      /* Traverse through the queue linked list until qcurr is NULL */
      while (qcurr) {
         /* Nested - traverse through the process linked list until pcurr is NULL */
         while (pcurr) {
            printf("I want to fit my %d process into a space of %d\n",qcurr->process, pcurr->part_size);
            /* If the current process is less than or equal to the partition and it is not flagged
               then set the currents partitions waste to the partition minus the process. Display
			   what is going on, then set the partitons flag to 1 showing it was used. Finally,
			   advance to the next process. */
            if (qcurr->process <= pcurr->part_size & pcurr->flag != 1) {
               pcurr->part_waste = (pcurr->part_size - qcurr->process);
               printf("The waste is %d.\n\n", pcurr->part_waste);
               pcurr->flag = 1;
               pcurr = phead;
               qcurr = qcurr->next;
            }
            /* If the process greater than the size or if the partition flag it set to 1,
			   then display it could not be fit or used and go on to the next partition. */
            if (qcurr->process > pcurr->part_size || pcurr->flag == 1) {
               printf("Sorry, your %d process could not be fit into the %d partition or it may have been used.\n",qcurr->process, pcurr->part_size);
               pcurr = pcurr->next;
            }
            /* If the current partition happens to reach NULL, then go to the next process */
            if (pcurr == NULL){
               qcurr = qcurr->next;

            }
         }
      }
      /* Setting the current pointer to the head, traversing through the linked list,
	     and printing out each partiton with its waste. */
      pcurr = phead;
      printf("\nThe Results:\n");
      while (pcurr) {
         printf("The partition %d has a waste of %d.\n",pcurr->part_size,pcurr->part_waste);
         printf("---------------------------------------\n");
         pcurr = pcurr->next;
      }
   }
	
   /* Best Fit Scheme */
   if (user_input == 2) {
      /* Setting the current pointers to the head pointers */
      pcurr = phead;
      qcurr = qhead;
      /* Traverse through the partition linked list until pcurr is NULL */
      while (pcurr) {
         qcurr = qhead;
         /* Setting the partition waste to an arbitrary value in order for a minimum value to be found */
         pcurr->part_waste = 1000;
         printf("I'm looking for a process to fit into my %d space.\n", pcurr->part_size);
         /* Nested - traverse through the queue linked list until qcurr is NULL */
         while(qcurr) {
            /* If the partition size is greater than or equal to the process and the flag is not set
               to 1, then go to the nested if */
            if (pcurr->part_size >= qcurr->process && qcurr->flag != 1){
               /* If the partition size minus the process is less than the partition waste, set the
                  waste equal to it. Then set the temporary pointer equal to the current pointer */
               if ((pcurr->part_size - qcurr->process) < pcurr->part_waste){
                  pcurr->part_waste = (pcurr->part_size - qcurr->process);
                  qtemp = qcurr;
               }
            }
            /* If the partition size is 1000 (meaning it was untouched) or if the queue was flagged to 1,
               then display it could not be fit or was used. */
            if (pcurr->part_waste == 1000 || qcurr->flag == 1) {
               printf("Sorry, your %d process could not be fit into the %d partition or it may have been used.\n",qcurr->process, pcurr->part_size);
            }
            /* Continuing to the next queue */
            qcurr = qcurr->next;
         }
         /* Displaying the best fit possible for the partition. The current queue pointer is set to the
		    temporary (best fit) pointer, and it is given a flag of 1. The temporary pointer moves to it's
            position to avoid being overwritten and the partition advances next as well. */
         printf("The best possible fit I found for my %d partition was a %d process.\n\n\n",pcurr->part_size,qtemp->process);
         qcurr = qtemp;
         qcurr->flag = 1;
         qtemp = qtemp->next;
		 pcurr = pcurr->next;
      }
      /* Setting the current pointer to the head, traversing through the linked list,
         and printing out each partiton with its waste. */
      pcurr = phead;
      printf("\nThe Results:\n");
      while (pcurr) {
         printf("The partition %d has a waste of %d.\n",pcurr->part_size,pcurr->part_waste);
         printf("---------------------------------------\n");
         pcurr = pcurr->next;
      }
   }
}



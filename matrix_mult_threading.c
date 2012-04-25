/*/////////////////////////////////////////////////////////////
// Erick Mikoshi                               CMPT 312-01   //
//               Operating Systems - Project 3               //
//                                                           //
// The purpose of this program is to perform multiplication  //
// on the two given matrices. This program is implemented    //
// to show how threading works. There will be two different  //
// implementation methods. The first is putting both the     //
// thread creation and join in the same loop. The second     //
// places the creation in one loop and thread in the other.  //
//                                                           //
// Variable Lookup:                                          //
//                                                           //
// M - number of rows for matrix A and matrix C              //
// K - number of rows for matrix B and columns for A         //
// N - number of columns for matrix B and matrix C           //
// THREAD_MAX - # of threads to be used                      //
// V - struct with needed arguments (i & j) as members and   //
//     pass a pointer to the struct                          //
// *thread - pointer to the created thread                   //
// *arg - argument for the created thread                    //
// i,j - counters for the rows and columns respectively      //
// count - counter to show how many threads have been made   //
// *data - pointer used for struct V so values can be        //
//         assigned to i and j                               //
// tid - paramter to get the thread ID                       //
// attr - parameter to get the attributes of the thread      //
//                                                           //
/////////////////////////////////////////////////////////////*/

#include <pthread.h>
#include <stdio.h>
#include <stdlib.h>

#define M 3
#define K 2
#define N 3
#define THREAD_MAX 10

int A [M][K] ;
int B [K][N] ;
int C [M][N];

/* Creating the struct with rows and columns */
struct V {
   /* Value for row */
   int i;
   /* Value for column */
   int j;
};

/* Creating the thread with an argument */
void *thread(void *arg); 

int main() {
	
   /* Initializing counters */
   int i,j, count = 1, input;
	
   /* Letting user choose the method */
   printf("Enter 0 for serial execution of threads\n");
   printf("Enter 1 for parallel execution of threads\n");
   scanf("%d", &input);
	
	
   /* Getting the thread ID and its attributes */
   pthread_t tid;
   pthread_attr_t attr;
   pthread_attr_init(&attr);

   if (input == 0) {
	
      /* Getting user input for the matrix values */
      printf("Input matrix elements for A:\n");
      for (i = 0; i < M; i++) {
         for (j = 0; j < K; j++) {
            printf("Input element [%d][%d] : ", i, j);
            scanf("%d", &A[i][j]);
         }
      }
      printf("\n");

      printf("Input matrix elements for B:\n");
      for (i = 0; i < K; i++) {
         for (j = 0; j < N; j++) {
            printf("Input element [%d][%d] : ", i, j);
            scanf("%d", &B[i][j]);
         }
      }
	
      /* Printing out the original A and B matrices */
      printf("\n\nMatrix A: \n");
      for (i = 0; i < M; i++) {
         for (j = 0; j < K; j++) {
            printf("%3d", A[i][j]);
         }
         printf("\n");
      }
	
      printf("\n\nMatrix B: \n");
      for (i = 0; i < K; i++) {
         for (j = 0; j < N; j++) {
            printf("%3d", B[i][j]);
         }
         printf("\n");
      }
      printf("\n");
	
	
      /* Double for-loop, first traverse through M then through N */
      for(i = 0; i < M; i++) {
         for(j = 0; j < N; j++) {
            /* This creates a row and column for each thread */
            struct V *data = (struct V *) malloc(sizeof(struct V));
            /* Putting the values of i and j into the struct */
            data->i = i;
            data->j = j;
            /* Printing out the thread number */
            printf("Thread #%d:\n",count);
            /* Creating the thread and passing it values */
            pthread_create(&tid,&attr,thread,data);
            /* Joining the threads */
            pthread_join(tid, NULL);
            /* Increasing the count as the thread number */
            count++;
         }
      }
	
      /* Printing out matrix C */
      printf("\nMatrix C was used with serial programming:\n");
      for(i = 0; i < M; i++) {
         for(j = 0; j < N; j++) {
            printf("%d ", C[i][j]);
         }
         printf("\n");
      }
   }
	
   if (input == 1) {
		
      /* Getting user input for the matrix values */
      printf("Input matrix elements for A:\n");
      for (i = 0; i < M; i++) {
         for (j = 0; j < K; j++) {
            printf("Input element [%d][%d] : ", i, j);
            scanf("%d", &A[i][j]);
         }
      }
      printf("\n");
	   
      printf("Input matrix elements for B:\n");
      for (i = 0; i < K; i++) {
         for (j = 0; j < N; j++) {
            printf("Input element [%d][%d] : ", i, j);
            scanf("%d", &B[i][j]);
         }
      }
		
      /* Printing out the original A and B matrices */
      printf("\n\nMatrix A: \n");
      for (i = 0; i < M; i++) {
         for (j = 0; j < K; j++) {
            printf("%3d", A[i][j]);
         }
         printf("\n");
      }
		
      printf("\n\nMatrix B: \n");
      for (i = 0; i < K; i++) {
         for (j = 0; j < N; j++) {
            printf("%3d", B[i][j]);
         }
         printf("\n");
      }
      printf("\n");
		
      /* Double for-loop, first traverse through M then through N */
      for(i = 0; i < M; i++) {
         for(j = 0; j < N; j++) {
            /* This creates a row and column for each thread */
            struct V *data = (struct V *) malloc(sizeof(struct V));
            /* Putting the values of i and j into the struct */
            data->i = i;
            data->j = j;
            /* Printing out the thread number */
            printf("\nThread #%d:\n",count);
            /* Creating the thread and passing it values */
            pthread_create(&tid,&attr,thread,data);
            /* Increasing the count as the thread number */
            count++;
         }
      }
		
      /* Double for-loop specifically for joining the threads */
      for(i = 0; i < M; i++) {
         for(j = 0; j < N; j++) {
            /* Joining the threads */
            pthread_join(tid, NULL);
         }
      }
		
      /* Printing out matrix C */
      printf("\nMatrix C was used with parallel programming\n");
      for(i = 0; i < M; i++) {
         for(j = 0; j < N; j++) {
            printf("%d ", C[i][j]);
         }
         printf("\n");
      }
   }
}

/* Defining what function of what the thread will do */
void *thread(void *arg) {
   /* The struct with the data of the matrices */
   struct V *data = arg;
   /* Initializing n, which is the row or column identifier & total 
      which is the sum */
   int n, total = 0; 
	
   /* Multiplying the row by the column, assigning it to total, &
      printing out the values of i and j */
   for(n = 0; n < K; n++){
      total += A[data->i][n] * B[n][data->j];
      printf("The value of i: %d\n", A[data->i][n]);
      printf("The value of j: %d\n", B[n][data->j]);
   }
   printf("\n");
   /* Giving the sum to the new spot in the final matrix */
   C[data->i][data->j] = total;
	
   /* Leaving the thread */
   pthread_exit(0);
}

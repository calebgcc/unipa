// INTERFACE - ADT - QUEUE BIN
typedef struct queue *Q;

Q initQ();
int isEmpty(Q q);
void enQ(Q q, int day,int month,int year);
char *deQ(Q q);


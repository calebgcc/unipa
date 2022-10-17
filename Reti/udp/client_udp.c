#include <stdio.h> 
#include <stdlib.h> 
#include <unistd.h> 
#include <string.h> 
#include <time.h>
#include <sys/types.h> 
#include <sys/socket.h> 
#include <arpa/inet.h> 
#include <netinet/in.h> 
    
#define PORT     8080 
#define MAXLINE 256 
    
int main() {

    /*
        - Bomb Client - 
        Send a number x to the server
        Listen for response y
        if y > 0:
            send(y-1)
            repeat
        else
            BOOM!
    */


    int sockfd; 
    char buffer[MAXLINE]; 
    struct sockaddr_in server_addr = {0}; // per non dover utilizzare memset
        
    if ( (sockfd = socket(AF_INET, SOCK_DGRAM, 0)) < 0 ) { 
        perror("socket creation failed"); 
        exit(EXIT_FAILURE); 
    } 
        
    server_addr.sin_family    = AF_INET; // IPv4 
    server_addr.sin_addr.s_addr = INADDR_ANY; 
    server_addr.sin_port = htons(PORT); 
        
    int len, n, x;

    srand(time(NULL));
    x = (rand() % 50) + 1;

    while(1){
        sprintf(buffer,"%d", x-1);
        sendto(sockfd, (char *)buffer, strlen(buffer),  
            0, (const struct sockaddr *) &server_addr, 
            sizeof(server_addr)); 

        n = recvfrom(sockfd, (char *)buffer, MAXLINE,  
                MSG_WAITALL, ( struct sockaddr *) &server_addr, 
                &len);           

        buffer[n] = '\0';
        x = atoi(buffer);

        printf("Ricevuto dal server %s\n", buffer);

        if(x == 0){
            printf(" BOOOM!!!\n");
            strcpy(buffer, "Client has exploded :(");
            sendto(sockfd, (char *)buffer, strlen(buffer),  
                0, (const struct sockaddr *) &server_addr, 
                len);   
            break;
        }
    }

    close(sockfd);
    return 0; 
}
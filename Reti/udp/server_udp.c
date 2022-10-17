#include <stdio.h> 
#include <stdlib.h> 
#include <unistd.h> 
#include <string.h> 
#include <sys/types.h> 
#include <sys/socket.h> 
#include <arpa/inet.h> 
#include <netinet/in.h> 
    
#define PORT     8080 
#define MAXLINE 256 
    
int main() {

    /*
        - Bomb server - 
        Get a number x from the client
        if x > 0:
            send(x-1)
            repeat
        else
            BOOM!
    */


    int sockfd; 
    char buffer[MAXLINE]; 
    struct sockaddr_in server_addr = {0}; // per non dover utilizzare memset
    struct sockaddr_in client_addr = {0};
        
    if ( (sockfd = socket(AF_INET, SOCK_DGRAM, 0)) < 0 ){ 
        perror("socket creation failed"); 
        exit(EXIT_FAILURE); 
    } 
        
    server_addr.sin_family    = AF_INET; // IPv4 
    server_addr.sin_addr.s_addr = INADDR_ANY; 
    server_addr.sin_port = htons(PORT); 
        
    if (bind(sockfd, (const struct sockaddr *)&server_addr, sizeof(server_addr)) < 0 ){ 
        perror("bind failed"); 
        exit(EXIT_FAILURE); 
    } 
        
    int len, n, x; 
    while(1){
        n = recvfrom(sockfd, (char *)buffer, MAXLINE,  
                MSG_WAITALL, ( struct sockaddr *) &client_addr, 
                &len);           
        buffer[n] = '\0';
        x = atoi(buffer);

        printf("Ricevuto dal client :: %s\n", buffer);

        if(x == 0){    
            printf(" BOOOM!!!\n");
            strcpy(buffer, "Server has exploded :(");
            sendto(sockfd, (char *)buffer, strlen(buffer),  
                0, (const struct sockaddr *) &client_addr, 
                len);   
            break;
        }
        
        sprintf(buffer,"%d", x-1);
        sendto(sockfd, (char *)buffer, strlen(buffer),  
            0, (const struct sockaddr *) &client_addr, 
            len); 
    }

    close(sockfd);
    return 0; 
}
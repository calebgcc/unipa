#include <stdio.h> 
#include <stdlib.h> 
#include <unistd.h> 
#include <string.h> 
#include <sys/types.h> 
#include <sys/socket.h> 
#include <arpa/inet.h> 
#include <netinet/in.h>

#define PORT 9973
#define MAX_LINE 256

int main(){
    int sockfd;
    struct sockaddr_in server_addr = {0};
    char buffer[MAX_LINE];
    
    if((sockfd = socket(AF_INET, SOCK_STREAM, 0)) < 0){
        perror("Socket creation failed :(");
        exit(EXIT_FAILURE);
    }
    
    server_addr.sin_family = AF_INET;
    server_addr.sin_addr.s_addr = inet_addr("127.0.0.1");
    server_addr.sin_port = htons(PORT);
    
    if(connect(sockfd, (struct sockaddr*)&server_addr, sizeof(server_addr)) < 0){
        perror("Connection failed :(");
        exit(EXIT_FAILURE);
    }

    int n; 
    while(1){
        printf("::: ");
        gets(buffer);
        send(sockfd, buffer, strlen(buffer), 0);
        
        if(strcmp(buffer,"close") == 0)
            break; 
        
        n = recv(sockfd, buffer, MAX_LINE, 0);
        buffer[n] = '\0';
        
        if(strcmp(buffer,"close") == 0){
            printf("[Server says Bye Bye]\n");
            break;
        }

        printf(">>> %s\n", buffer);
    }

    // Close the socket:
    close(sockfd);
    
    return 0;
}
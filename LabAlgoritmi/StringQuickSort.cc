#include <iostream>
#include <string>
#include <vector>
#include <limits.h>

template <class T>
void print_vector(std::vector<T> &vec){
    for(T& x : vec){
        std::cout << x << "\n";
    }
}

// stirng quick sort
std::vector<std::string> sqs(std::vector<std::string> vec, int l = 0){
    
    if(vec.size() <= 1) return vec;

    std::vector<std::string> lvec; // element that are equal to l
    std::vector<std::string> mvec; // minor then pivot
    std::vector<std::string> gvec; // greater then pivot
    std::vector<std::string> evec; // equal then pivot
    
    bool pivot = false;
    std::string pv = "";

    for(std::string& s : vec){
        if(s.size() == l){
            lvec.push_back(s);
            continue;
        }
        
        if(!pivot){
            pv = s;
            pivot = true;
        }

        if(s[l] == pv[l]) evec.push_back(s);
        else if(s[l] < pv[l]) mvec.push_back(s);
        else gvec.push_back(s); 
    }
    

    mvec = sqs(mvec, l);
    evec = sqs(evec, l+1);
    gvec = sqs(gvec, l); 

    lvec.insert(lvec.end(), mvec.begin(), mvec.end());
    lvec.insert(lvec.end(), evec.begin(), evec.end());
    lvec.insert(lvec.end(), gvec.begin(), gvec.end());
    return lvec;
}

int main(){
    std::vector<std::string> res;

    res = msd({"zio", "zia", "nano", "nana", "nano", "cocco", ""});
    
    print_vector(res);
    return 0;
}
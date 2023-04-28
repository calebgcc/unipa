#include <iostream>
#include <string>
#include <vector>
#define DEBUG


template <class T>
void print_vector(std::vector<T> &vec){
    std::cout << "[ ";
    for(T& x : vec){
        std::cout << "\"" << x << "\" ";
    }
    std::cout << "]\n";
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


// Helper Quicksort
// vector, level, start, end
void hsq(std::vector<std::string> &vec, int l, int s, int e){
    // moving in-front all strings with len == l
    int p = s;
    for(int i=s; i<=e; ++i){
        if(vec[i].size() == l){
            swap(vec[i], vec[p]);
            ++p;
        }
    }

    // p is now pointing to the first string
    // with len > l  --  this string is our pivot
    if(p > e) return;

    char pivot = vec[p][l];
    int ls = p; // less then *
    int gr = p+1; // greater then *

    for(size_t c=p+1; c<=e; ++c){
        
        if(vec[c][l] == pivot){
            swap(vec[c], vec[gr]);
            ++gr;
        }

        if(vec[c][l] < pivot){
            swap(vec[c], vec[ls]);
            swap(vec[c], vec[gr]);
            ++ls; ++gr;
        }

    }

    hsq(vec,l,p,ls-1);    
    hsq(vec,l+1,ls,gr-1);    
    hsq(vec,l,gr,e);    
}


// in-place String Quicksort
void sq(std::vector<std::string> &vec){
   hsq(vec, 0, 0, vec.size()-1);
}


int main(){
    std::vector<std::string> vec{"","c1", "aa","a1", "a2", "", "r2", "g4", "c2", "e1","e2" ,"b", "f", "a3", "c3"};
    auto res = sqs(vec);
    sq(vec);
    print_vector(vec);
    print_vector(res);
    return 0;
}
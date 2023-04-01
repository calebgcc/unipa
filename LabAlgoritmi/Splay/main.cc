#include <iostream>
#include <vector>
#include <tuple>
#include <string>
#include "splay.hpp"

using namespace std;

int main(){

    vector<tuple<int, string>> actions{
        {5, "add"},
        {2, "add"},
        {4, "add"},
        {0, "add"},
        {1, "add"},
        {3, "add"},
    };
    Splay<int> tree;

    for(auto [value, action] : actions){
        if(action == "add")
            tree.add(value);
        tree.print();
    }
    return 0;
}
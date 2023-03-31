#include <iostream>
#include <vector>
#include <tuple>
#include <string>
#include "splay.hpp"

using namespace std;

int main(){

    vector<tuple<int, string>> actions{
        {1, "add"},
        {4, "add"},
        {5, "add"},
        {6, "add"},
        {3, "add"},
        {0, "add"},
        {10, "add"},
    };
    Splay<int> tree;

    for(auto [value, action] : actions){
        if(action == "add")
            tree.add(value);
    }
    return 0;
}
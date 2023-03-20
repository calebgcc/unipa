#include <iostream>
#include <vector>
#include <tuple>
#include <string>
#include <optional>
#include "avl.hpp"

using namespace std;

int main(){

  AVL tree;

  vector<tuple<int,string, string>> test{
    {0, "test", "insert"},
    {2, "test", "insert"},
    {3, "test", "insert"},
    {5, "test", "insert"},
    {2, "test", "erase"},
    {-2, "test", "insert"},
    {-1, "test", "insert"},
    {-3, "test", "insert"},
    {5, "test", "erase"},
    {-4, "test", "insert"},
    {-10, "test", "insert"},
    {-3, "test", "erase"},
  };

  for(auto [key,value,action] : test){
    if(action == "insert") tree.insert(key, value);
    else if(action == "erase") tree.erase(key);
    tree.print();
  }

  return 0;
}
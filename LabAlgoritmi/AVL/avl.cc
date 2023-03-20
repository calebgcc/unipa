#include "avl.hpp"
#include <string>
#include <optional>
#include <iostream>
#include <deque>
#include <cmath>

AVL::AVL(){ // constructor
  mroot = nullptr;
}

// * * * - - * * *
void AVL::rotate_helper(AVL::NodePtr *node, bool is_left){
  if(*node == nullptr) return;

  AVL::NodePtr temp;

  if(is_left){
    temp = (*node)->lt;
    (*node)->lt = temp->rt;
    (*node)->lh = temp->rh;

    temp->rt = *node;
    *node = temp;
    (*node)->rh = (*node)->rt->height()+1;
    return;
  }

  temp = (*node)->rt;
  (*node)->rt = temp->lt;
  (*node)->rh = temp->lh;
  
  temp->lt = *node;
  *node = temp;
  (*node)->lh = (*node)->lt->height()+1;
  
}


void AVL::balance_helper(AVL::NodePtr *node){
  if(*node == nullptr) return;

  int bf = (*node)->bf();

  // std::cout << "***" << (*node)->k << " " << bf << "***\n";
  if(bf > -2 && bf < 2) return;

  if(bf == 2){
    if((*node)->lt->bf() == 1) rotate_helper(node, true);
    else{
      rotate_helper(&(*node)->lt, false);
      rotate_helper(node, true);
    }
    return;
  }

  if((*node)->rt->bf() == -1) rotate_helper(node, false);
  else{
    rotate_helper(&(*node)->rt, true);
    rotate_helper(node, false);        
  }
}


// * * * - - * * *

int AVL::insert_helper(AVL::NodePtr *node, int key, std::string value){
  if(*node == nullptr){
    *node = new Node(key, value);
    return 1;
  }

  int past_height = (*node)->height();

  if(key > (*node)->k){
    (*node)->rh += insert_helper(&((*node)->rt), key, value);
    balance_helper(node);
    return (*node)->height() - past_height;
  }
  
  if(key < (*node)->k) {
    (*node)->lh += insert_helper(&((*node)->lt), key, value);
    balance_helper(node);
    return (*node)->height() - past_height;
  }    
  
  (*node)->v = value; // update value if key exist
  return 0;
}

void AVL::insert(int key, std::string value){ // public
  insert_helper(&mroot, key, value);
}


// * * *  - - * * *

AVL::NodePtr* AVL::search_helper(AVL::NodePtr* node, int key){
  if(*node == nullptr) return nullptr;
  else if(key > (*node)->k) return search_helper(&(*node)->rt, key);
  else if(key < (*node)->k) return search_helper(&(*node)->lt, key);
  else return node;
}

std::optional<std::string> AVL::search(int key){ // public
  NodePtr* node = search_helper(&mroot, key);
  rotate_helper(node, true);
  if(*node == nullptr) return {};
  return (*node)->v;
}

// * * * - - * * *

AVL::NodePtr AVL::max_helper(AVL::NodePtr node){
  if(node == nullptr) return nullptr;

  if(node->rt == nullptr) return node;
  return max_helper(node->rt);
}

int AVL::erase_helper(AVL::NodePtr *node, int key){
  if(*node == nullptr) return 0; // the node to delete doesn't exist

  int past_height = (*node)->height();

  if(key > (*node)->k) {
    (*node)->rh += erase_helper(&(*node)->rt, key);
    balance_helper(node);
    return (*node)->height() - past_height;
  }

  if(key < (*node)->k) {
    (*node)->lh += erase_helper(&(*node)->lt, key);
    balance_helper(node);
    return (*node)->height() - past_height;
  }

  // found
  AVL::NodePtr temp;
  
  if((*node)->lt == nullptr && (*node)->rt == nullptr){
    delete (*node);
    (*node) = nullptr;
    return -1;
  }
  
  if((*node)->lt == nullptr){
    temp = (*node)->rt;
    delete (*node);
    (*node) = temp;
    return -1;
  }
  
  if((*node)->rt == nullptr){
    temp = (*node)->lt;
    delete (*node);
    (*node) = temp;
    return -1;
  }
    
  temp = max_helper((*node)->lt);
  int k = temp->k;
  std::string v = temp->v;
  erase_helper(&(*node)->lt, k); 
  (*node)->k = k;
  (*node)->v = v;
  (*node)->lh -= 1;
  balance_helper(node);
  return (*node)->height() - past_height; 
}

void AVL::erase(int key){ // helper
  erase_helper(&mroot, key);
}


// * * * - - * * * 
void AVL::print_helper(AVL::NodePtr node, int liv){
  if(node == nullptr) return;

  print_helper(node->rt, liv+1);

  for(int i=0; i<abs(liv); ++i) std::cout << "-- ";

    std::cout << node->k << "\n";

  print_helper(node->lt, liv+1);
}


void AVL::print(){ // print
  print_helper(mroot, 0);
  std::cout << "\n\n";
}
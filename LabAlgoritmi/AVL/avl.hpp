#pragma once

#include <string>
#include <vector>
#include <optional>
#include <stdexcept>

class AVL {

  struct Node {
    Node *lt; // left node
    Node *rt; // right node
    int lh;   // left height
    int rh;   // right height
    int k;    // node key
    std::string v;   // node value
    Node(int key, std::string value) : lt{nullptr}, rt{nullptr}, lh{0}, rh{0}, k{key}, v{value} {}
    int height() {return std::max(lh, rh);}
    int bf() {return lh - rh;}
  };
  typedef struct Node* NodePtr;

  // * * * * * * * * * * * * *  

  NodePtr mroot = nullptr; // root of the tree

  void rotate_helper(NodePtr *node, bool is_left);
  void balance_helper(NodePtr *node);
  void heealth_helper();
  int insert_helper(NodePtr *node, int key, std::string value);
  NodePtr* search_helper(NodePtr* node, int key);
  NodePtr max_helper(NodePtr node);
  int erase_helper(NodePtr *node, int key);
  void print_helper(NodePtr node, int liv);

public:
  AVL();
  void insert(int key, std::string value);
  std::optional<std::string> search(int key);
  void erase(int key);
  void print();
  std::vector<int> visit(); 

};
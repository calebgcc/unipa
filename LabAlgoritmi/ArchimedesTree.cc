#include <iostream>
#include <tuple>
#include <unordered_map>
#include <vector>
#include <deque>

using namespace std;

struct TreeNode {
  int val;
  TreeNode *left;
  TreeNode *right;
  TreeNode(int v) : val(v), left(nullptr), right(nullptr) {}
};

typedef struct TreeNode *TreeNodePtr;

int min(vector<int> &seq, int s, int e) {
  if (s > e or s < 0 or e >= seq.size()) return -1;
  if (s == e) return s;
  int m = s;
  for (int i = s; i <= e; ++i) m = seq[m] < seq[i] ? m : i;
  return m;
}

void archimede(TreeNodePtr root, int start, int end, int i, vector<int> &seq) {
  if (start > end) return;

  int j; TreeNodePtr node;

  // left
  j = min(seq, start, i - 1);
  if (j != -1) {
    node = new TreeNode(seq[j]);
    root->left = node;
    archimede(node, start, i-1, j, seq);
  }

  // right
  j = min(seq, i + 1, end);
  if (j != -1) {
    node = new TreeNode(seq[j]);
    root->right = node;
    archimede(node, i+1, end, j, seq);
  }
}

TreeNodePtr larchimede(vector<int> &seq){
  vector<TreeNodePtr> stack;
  int count = 0;
  TreeNodePtr node, temp;
  while(count < seq.size()){
    node = new TreeNode(seq[count++]);
    temp = nullptr;
    while(not stack.empty() && node->val < stack.back()->val){
      temp = stack.back();  
      stack.pop_back();
    } 
    node->left = temp;
    if(not stack.empty()) stack.back()->right = node;
    stack.push_back(node);
  } 
  return stack[0];
}

// void print(TreeNodePtr root, int liv = 1) {
//   if (root == nullptr) return;
//   print(root->right, liv + 1);
//   for (int i = 1; i < liv; ++i) cout << "-";
//   cout << "|" << root->val << "\n";
//   print(root->left, liv + 1);
// }

void print(TreeNodePtr root){
  deque<TreeNodePtr> queue;
  queue.push_front(root);
  TreeNodePtr node;
  int size;
  while(not queue.empty()){
    size = queue.size();
    for(size_t i=0; i<size; ++i){
      node = queue.back();
      queue.pop_back();

      if(node != nullptr){
        cout << node->val << " ";        
        queue.push_front(node->left);
        queue.push_front(node->right);
      } 
      else cout << "_ ";
    }
    cout << "\n";
  }
}

int main() {
  vector<int> seq{9, 3, 7, 1, 8, 12, 10, 20, 15, 18, 5};
  // int index_root = min(seq, 0, seq.size()-1);
  // TreeNodePtr root = new TreeNode(seq[index_root]);
  // archimede(root, 0, seq.size()-1, index_root, seq);
  print(larchimede(seq));
  return 0;
}

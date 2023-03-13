#include <iostream>
#include <tuple>
#include <unordered_map>
#include <vector>

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

void print(TreeNodePtr root, int liv = 1) {
  if (root == nullptr) return;
  print(root->right, liv + 1);
  for (int i = 1; i < liv; ++i) cout << "-";
  cout << "|" << root->val << "\n";
  print(root->left, liv + 1);
}

int main() {
  vector<int> seq{9, 3, 7, 1, 8, 12, 10, 20, 15, 18, 5};
  int index_root = min(seq, 0, seq.size()-1);
  TreeNodePtr root = new TreeNode(seq[index_root]);
  archimede(root, 0, seq.size()-1, index_root, seq);
  print(root);
  return 0;
}

#pragma once
#include <optional>
#include <stdexcept>
#include <deque>
#include <vector>
#include <cmath>

template <class T>
class Splay{

    struct Node{
        T val;
        Node* left;
        Node* right;

        Node(T v): val{v}, left{nullptr}, right{nullptr} {}
    }; 
    
    Node* mroot;

    int add_helper(Node* &root, T val); 
    void rotate_left(Node* &root);
    void rotate_right(Node* &root);
    void print_helper(Node* root);

public:
    Splay();
    void add(T val);
    T remove(T val);
    std::optional<T> search(T val);
    void print();
};


template <class T>
Splay<T>::Splay(): mroot{nullptr} {
}

template <class T>
void Splay<T>::add(T val){
    add_helper(mroot, val);
}

template <class T>
T Splay<T>::remove(T val){
    return T();
}

template <class T>
std::optional<T> Splay<T>::search(T val){
    return std::optional<T>();
}

template <class T>
int Splay<T>::add_helper(Splay<T>::Node* &root, T val){
    if(root == nullptr){
      root = new Node(val);
      return 0;
    }

    if(val > root->val){
        int result = add_helper(root->right, val)+1;
        if(result == 0){ // Zig Zag
            rotate_right(root->right);
            rotate_left(root);
            return 0;
        }

        if(result == 2){ // Zig Zig
            rotate_left(root);
            rotate_left(root);
            return 0;
        }

        if(root == mroot) rotate_left(root);

        return result;
    }

    if(val < root->val){
        int result = add_helper(root->left, val)-1;
        if(result == 0){ // Zag Zig
            rotate_left(root->left);
            rotate_right(root);
            return 0;
        }

        if(result == -2){ // Zag Zag
            rotate_right(root);
            rotate_right(root);
            return 0;
        }

        if(root == mroot) rotate_right(root);

        return result;
    }

    return 0;
}

template <class T>
void Splay<T>::rotate_left(Splay<T>::Node* &root){
    Splay<T>::Node* temp = root->right;
    root->right = temp->left;
    temp->left = root;
    root = temp;
}

template <class T>
void Splay<T>::rotate_right(Splay<T>::Node* &root){
    Splay<T>::Node* temp = root->left;
    root->left = temp->right;
    temp->right = root;
    root = temp;
}

template <class T>
void Splay<T>::print(){
    print_helper(mroot);
}

template <class T>
void Splay<T>::print_helper(Splay<T>::Node* root){
    std::deque<Splay<T>::Node *> queue;
    std::vector<std::vector<Splay<T>::Node*>> layers;
    queue.push_front(root);
    bool all_null = false;
    while(not all_null){
        int size = queue.size();

        std::vector<Splay<T>::Node*> layer;
        all_null = true;
        for(int i=0; i<size; ++i){
            Splay<T>::Node* temp = queue.back();
            queue.pop_back();

            if(temp == nullptr){
                queue.push_front(nullptr);
                queue.push_front(nullptr);
                layer.push_back(temp);
                continue;
            }

            queue.push_front(temp->left);
            queue.push_front(temp->right);
            layer.push_back(temp);
            all_null = false;
        }
        layers.push_back(layer);
    }
    layers.pop_back();

    for(int i=0; i<layers.size(); ++i){
        int liv = layers.size() - i;
        int ispace = pow(2, liv-1)-1;
        int mspace = pow(2, liv)-1;

        // init space
        for(int s=0; s < ispace; ++s) std::cout << " ";
        for(auto& x : layers[i]){
            if(x == nullptr) std::cout << "_";
            else std::cout << x->val;
            for(int s=0; s < mspace; ++s) std::cout << " ";
        }
        std::cout << "\n";
    }
    std::cout << "\n. . . . . . . . . . . . .\n\n";
}


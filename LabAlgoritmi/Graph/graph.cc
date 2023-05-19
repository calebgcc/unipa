#include "graph.hpp"
#include <stdexcept>

void graph::add_edge(size_t v, size_t w){
    if(v >= this->mtable.size() || w >= this->mtable.size())
        throw std::out_of_range("Nodes intext out of range");
    this->mtable[v].push_front(w);
}
#pragma once
#include <vector>
#include <list>

class graph{

    graph(size_t n_nodes): mtable(n_nodes) {}
    void add_edge(size_t v, size_t w);



private:
    std::vector<std::list<size_t>> mtable;
};


class undirected_graph:graph{

};


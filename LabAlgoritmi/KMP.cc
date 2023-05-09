#include <iostream>
#include <vector>
#include <unordered_map>
#include <string>

namespace KMP {
    using std::string;
    using std::unordered_map;
    using std::vector;

    unordered_map<char, vector<size_t>> make_dfa(string text, string pattern){
        if(pattern.size() > text.size() || pattern == "") return {}; 
        
        unordered_map<char, vector<size_t>> dfa;

        for(char& ch : pattern)
            dfa[ch] = vector<size_t>(pattern.size()+1, 0);

        size_t x=0;
        dfa[pattern[0]][0] = 1;
        for(size_t i=1; i<pattern.size(); ++i){
            for(const auto& [key, value] : dfa)
                dfa[key][i] = dfa[key][x];

            dfa[pattern[i]][i] = i+1;
            x = dfa[pattern[i]][x];
        }

        for(const auto& [key, value] : dfa)
            dfa[key][pattern.size()] = dfa[key][x];
    
        return dfa;
    }

    int match(string text, string pattern){
        unordered_map<char, vector<size_t>> dfa = make_dfa(text, pattern);
        if(dfa.size() == 0) return -1;

        size_t state = 0;
        for(size_t i=0; i<text.size(); ++i){
            char ch = text[i];

            if(dfa.find(ch) == dfa.end()) state = 0;
            else state = dfa[ch][state];
            
            if(state == pattern.size()) return i-pattern.size()+1;
        }

        return -1;
    }
    
    vector<size_t> find_all(string text, string pattern){
        unordered_map<char, vector<size_t>> dfa = make_dfa(text, pattern);
        if(dfa.size() == 0) return {};

        vector<size_t> positions;
        size_t state = 0;
        for(size_t i=0; i<text.size(); ++i){
            char ch = text[i];

            if(dfa.find(ch) == dfa.end()) state = 0;
            else state = dfa[ch][state];
            
            if(state == pattern.size()) positions.push_back(i-pattern.size()+1);
        }

        return positions;
    }
    
    vector<vector<size_t>> match_all(string text, vector<string> patterns){
        vector<unordered_map<char, vector<size_t>>> dfas;
        for(const string& pattern : patterns)
            dfas.push_back(make_dfa(text, pattern));
        
        vector<size_t> states(patterns.size(), 0);
        vector<vector<size_t>> positions(patterns.size());
        for(size_t i=0; i<text.size(); ++i){
            char ch = text[i];

            for(size_t j=0; j<patterns.size(); ++j){
                if(dfas[j].find(ch) == dfas[j].end()) states[j] = 0;
                else states[j] = dfas[j][ch][states[j]];
            
                if(states[j] == patterns[j].size()) positions[j].push_back(i-patterns[j].size()+1);
            }
        }

        return positions;
    }

}

void test_match(std::string text, std::string pattern){
    std::cout << text << "  " << pattern << "  " << KMP::match(text,pattern) << "\n";
}

template <class T>
void print(const std::vector<T>& vec){
    std::cout << "[ ";
    for(const T& x : vec)
        std::cout << x << " ";
    std::cout << "] ";
}

void test_find_all(std::string text, std::string pattern){
    std::cout << text << "  " << pattern << "  ";
    print(KMP::find_all(text, pattern));
    std::cout << "\n";
}

void test_match_all(std::string text, std::vector<std::string> patterns){
    std::vector<std::vector<size_t>> result = KMP::match_all(text, patterns);

    std::cout << text << "  ";
    print(patterns); 
    std::cout << "\n";

    for(std::vector<size_t>& res : result)
        print(res);
    std::cout << "\n";
}

int main(){
    test_match("ABC", "ABC");
    test_match("", "");
    test_match("ABCABC", "ABC");
    test_match("ABAAC", "AA");
    test_match("ABC", "C");
    test_match("AaBbCc", "ABC");
    test_match("AaBbCc", "AaBbCc");

    std::cout << "\n"; 

    test_find_all("AAA", "A");
    test_find_all("ababab", "ab");
    test_find_all("abababa", "aba");
    test_find_all("bacbacbdaba", "ba");
    
    std::cout << "\n";

    test_match_all("abcabbabc", {"ab", "abc", "abb", "b"});
    return 0;
}
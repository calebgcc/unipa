#include <iostream>



// template<class T,size_t N>
// int binary_search(const T (&arr)[N], const int low, const int high, const T target){
//     if(high < low)
//         return -1;
//     size_t mid = (low+high)/2;
//     if(arr[mid] == target)
//         return mid;
//     else if(arr[mid] > target)
//         return binary_search(arr,low,mid-1,target);
//     return binary_search(arr,mid+1,high,target);
// }


template<class T,size_t N>
int binary_search(const T (&arr)[N], const T target){
    int low = 0, high = N-1;
    int mid;
    while(high >= low){
        mid = (low+high)/2;
        if(arr[mid] == target)
            return mid;
        else if(arr[mid] > target)
            high = mid-1;
        else
            low = mid+1;
    }
    return -1;
}

int main(){

    int array[] = {0,1,2,3,4,5,6,7,8,9};
    std::cout << binary_search(array,-3) << "\n";

    return 0;
}
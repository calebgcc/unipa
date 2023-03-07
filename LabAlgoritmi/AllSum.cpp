#include <iostream>
#include <cstdlib>
#include <vector>

#define MAX 100
#define MIN -100
#define SIZE 20

#define PRINT
#define TEST

using namespace std;


int find_sum(vector<int> nums){
  sort(nums.begin(), nums.end());

  int i = 0, count = 0;
  while(i<nums.size()-3){
    int left = i+1, right = nums.size()-1;

    if(nums[i] > 0) break;

    while(left < right){
      if( (nums[i] + nums[left] + nums[right]) == 0){
        ++count;
        #ifdef PRINT 
          cout << nums[i] << ", " << nums[left] << ", " << nums[right] << "\n"; 
        #endif
      }
     
      int sum = nums[left]+nums[right];

      if(sum <= -nums[i]){
        ++left;
        while(left<right && nums[left-1]==nums[left]) ++left;
      } else if(sum >= -nums[i]){
        --right;
        while(left<right && nums[right+1]==nums[right]) --right;
      }
    }

    ++i;
    while(i < nums.size()-3 && nums[i-1]==nums[i]) ++i;
  }

  return count;
}

void print(vector<int>& nums){
  cout <<"[ ";
  for(int& x : nums)
    cout << x << " ";
  cout << "]\n";
}

int main(){
  
  srand(time(NULL));

  vector<int> nums;
  nums.reserve(SIZE);
  
  for(size_t i=0; i<SIZE; ++i)
    nums.push_back(rand() % (MAX-MIN+1) + MIN);

  #ifdef PRINT
    print(nums);
  #endif

  int result = find_sum(nums);
  cout << "Found :: " << result << "\n"; 

  #ifdef TEST
    cout << "\n*** T E S T ***\n";
    vector<int> t1{-2,-1,-1,-1,-1,0,1,1,2,2};
    print(t1);
    find_sum(t1);
    
    cout << "-----------------\n";
    
    vector<int> t2{-3,-3,-2,-2,-1,-1,0,0,0,0,1,1,2,2,3,3};
    print(t2);
    find_sum(t2);
    cout << "-----------------\n";

    vector<int> t3{-3,-2,-1,0,0,1,2,3};
    print(t3);
    find_sum(t3);
    cout << "-----------------\n";
  #endif

  return 0;
}

#include <algorithm>
#include <bitset>
#include <complex>
#include <deque>
#include <exception>
#include <fstream>
#include <functional>
#include <iomanip>
#include <ios>
#include <iosfwd>
#include <iostream>
#include <istream>
#include <iterator>
#include <limits>
#include <list>
#include <locale>
#include <map>
#include <memory>
#include <new>
#include <numeric>
#include <ostream>
#include <queue>
#include <set>
#include <sstream>
#include <stack>
#include <stdexcept>
#include <streambuf>
#include <string>
#include <typeinfo>
#include <utility>
#include <valarray>
#include <vector>

using namespace std;

int appearsNBy3(int arr[], int n)
{
    int count1 = 0, count2 = 0;
    int first = INT_MAX, second = INT_MAX;

    for (int i = 0; i < n; i++)
    {

        // if this element is previously seen,
        // increment count1.
        if (first == arr[i])
            count1++;

        // if this element is previously seen,
        // increment count2.
        else if (second == arr[i])
            count2++;

        else if (count1 == 0)
        {
            count1++;
            first = arr[i];
        }

        else if (count2 == 0)
        {
            count2++;
            second = arr[i];
        }

        // if current element is different from
        // both the previously seen variables,
        // decrement both the counts.
        else
        {
            count1--;
            count2--;
        }
    }

    cout << first << endl;
    cout << second << endl;
    cout << count1 << endl;
    cout << count2 << endl;

    count1 = 0;
    count2 = 0;

    // Again traverse the array and find the
    // actual counts.
    for (int i = 0; i < n; i++)
    {
        if (arr[i] == first)
            count1++;

        else if (arr[i] == second)
            count2++;
    }

    if (count1 > n / 3)
        return first;

    if (count2 > n / 3)
        return second;

    return -1;
}

int main()
{
    int arr[] = {8, 8, 2, 8, 3, 8, 8, 4, 5, 9, 9, 9};
    int n = sizeof(arr) / sizeof(arr[0]);
    cout << appearsNBy3(arr, n) << endl;
    return 0;
}
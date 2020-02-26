#ifndef LAB1_UTILS_H
#define LAB1_UTILS_H

#include <iostream>
#include <sstream>
#include <string>

using namespace std;

template<class T>
string valueToString(T value) {
    ostringstream oss;
    oss << value;
    return oss.str();
}

template<class T>
T stringToValue(string str) {
    istringstream iss(str);
    T value;
    iss >> value;
    return value;
}

int ignoreWhiteMarks(istream& in) {
    int howMany = 0;
    while (in.peek() == 10 || in.peek() == 32)
        in.ignore();
    return howMany;
}

#endif //LAB1_UTILS_H

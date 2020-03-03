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
    while (in.peek() == 10 || in.peek() == 32) {
        in.ignore();
    }
    return howMany;
}

void saveToBinFile(string file_name, float from, float to, float step) {
    ofstream stream;
    stream.open(file_name, std::ios::out | std::ios::binary);
    for (; from < to ; from += step) {
        stream.write(reinterpret_cast<const char*>(&from), sizeof(float));
    }
    stream.close();
}

#endif //LAB1_UTILS_H

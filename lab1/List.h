#ifndef LAB1_LIST_H
#define LAB1_LIST_H

#include <iostream>
#include <cassert>
#include <limits>

using namespace std;

struct el {
//    double TMP[INT32_MAX];
    float v;
    el *next;
    el *previous;

    el();
    el(float v);
    virtual ~el();
};

class List {
private:
    el *head;
    el *tail;
    el *addHead(float a);
    el *addTail(float a);
    void removeHead();
    void removeTail();

public:
    List();
    virtual~List();
    el *add(float a);
    void removeList();
    void showRightToLeft();
    void showLeftToRight();
};

#endif //LAB1_LIST_H

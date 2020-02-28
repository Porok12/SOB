#ifndef LAB1_LIST_H
#define LAB1_LIST_H

#include <iostream>
#include <cassert>

using namespace std;

struct el {
    int v;
    el *next;
    el *previous;

    el();
    el(int v);
    virtual ~el();
};

class List {
private:
    el *head;
    el *tail;
    el *addHead(int a);
    el *addTail(int a);
    void removeHead();
    void removeTail();

public:
    List();
    virtual~List();
    el *add(int a);
    void removeList();
    void showRightToLeft();
    void showLeftToRight();
};

#endif //LAB1_LIST_H

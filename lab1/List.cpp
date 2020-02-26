#include "List.h"

el::el() {
    el(0);
}

el::el(int v)
        : next(nullptr), previous(nullptr), v(v) {}

el::~el() {

}

el *List::addHead(int a) {
    el* elem = new el(a);
    if (head == nullptr) {
        head = elem;
        tail = elem;
    } else {
        elem->next = head;
        head->previous = elem;
        head = elem;
    }

    assert(elem != nullptr);
    assert(elem == head);
    return elem;
}

el *List::addTail(int a) {
    el* elem = new el(a);
    if (tail == nullptr) {
        head = elem;
        tail = elem;
    } else {
        elem->previous = tail;
        tail->next = elem;
        tail = elem;
    }

    assert(elem != nullptr);
    assert(elem == tail);
    return elem;
}

el *List::removeHead() {
    if (head == tail) {
        delete head;
        head = nullptr;
        tail = nullptr;
    } else {
        el* tmp = head->next;
        delete head;
        head = tmp;
    }
    return nullptr;
}

el *List::removeTail() {
    if (head == tail) {
        delete head;
        head = nullptr;
        tail = nullptr;
    } else {
        el* tmp = tail->previous;
        delete tail;
        tail = tmp;
    }
    return nullptr;
}

List::List() {
    head = nullptr;
    tail = nullptr;
}

List::~List() {
    removeList();
}

el *List::add(int a) {
    el* elem = new el(a);

    if (head == nullptr) {
        head = elem;
        tail = elem;
    } else if (head->v > elem->v) {
        elem->next = head;
        head->previous = elem;
        head = elem;
    } else if (tail->v < elem ->v) {
        elem->previous = tail;
        tail->next= elem;
        tail = elem;
    } else {
        el* current = head;
        while (current->next != nullptr &&
               current->next->v < elem->v) {
            current = current->next;
        }

        elem->next = current->next;
        elem->previous = current;
        if (current->next != nullptr)
            current->next->previous = elem;
        current->next = elem;
    }

    assert(elem != nullptr);
    return elem;
}

el *List::removeValue(int a) {
    return nullptr;
}

void List::removeList() {
    el* ptr = head;
    el* next = nullptr;
    while(ptr != nullptr) {
        next = ptr->next;
        delete ptr;
        ptr = next;
    }
    head = nullptr;
    tail = nullptr;
}

void List::showRightToLeft() {
    el* ptr = this->tail;
    while(ptr != nullptr) {
        cout << ptr->v << " ";
        ptr = ptr->previous;
    }
    cout << endl;
}

void List::showLeftToRight() {
    el* ptr = this->head;
    while(ptr != nullptr) {
        cout << ptr->v << " ";
        ptr = ptr->next;
    }
    cout << endl;
}

void List::swap(el *&tmp, el *&k) {

}

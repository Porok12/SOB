#include "List.h"

el::el() {
    el(0);
}

el::el(float v)
        : next(nullptr), previous(nullptr), v(v) { }

el::~el() { }

el *List::addHead(float a) {
    el* elem = new el(a);
    assert(elem != nullptr);

    if (head == nullptr) {
        head = elem;
        tail = elem;
    } else {
        elem->next = head;
        head->previous = elem;
        head = elem;
    }

    assert(elem == head);
    assert(elem != nullptr
           && elem->v == a);
    return elem;
}

el *List::addTail(float a) {
    el* elem = new el(a);
    assert(elem != nullptr);

    if (tail == nullptr) {
        head = elem;
        tail = elem;
    } else {
        elem->previous = tail;
        tail->next = elem;
        tail = elem;
    }

    assert(elem == tail);
    assert(elem != nullptr
           && elem->v == a);
    return elem;
}

void List::removeHead() {
    if (head == tail) {
        delete head;
        head = nullptr;
        tail = nullptr;
    } else {
        el* tmp = head->next;
        delete head;
        head = tmp;
    }
}

void List::removeTail() {
    if (head == tail) {
        delete head;
        head = nullptr;
        tail = nullptr;
    } else {
        el* tmp = tail->previous;
        delete tail;
        tail = tmp;
    }
}

List::List() {
    head = nullptr;
    tail = nullptr;
}

List::~List() {
    removeList();
}

el *List::add(float a) {
    el* elem = new (std::nothrow) el(a);
    assert(elem != nullptr);

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

    assert(elem != nullptr && elem->v == a);
    return elem;
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
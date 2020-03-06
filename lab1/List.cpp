#include "List.h"

el::el() {
    el(0);
}

el::el(float v)
        : next(nullptr), previous(nullptr), v(v) { }

el::~el() { }

el *List::addHead(el* elem) {
    assert(elem != nullptr);
    elem->next = head;
    head->previous = elem;
    head = elem;
    return elem;
}

el *List::addTail(el* elem) {
    assert(elem != nullptr);
    elem->previous = tail;
    tail->next= elem;
    tail = elem;
    return elem;
}

/*wywolujemy tylko gdy wiemy ze lista nie jest pusta*/
void List::removeHead() {
    assert(head != nullptr); /**/
    if (head == tail) {  /*Czy jest to jedyny element*/
        delete head;
        head = nullptr;
        tail = nullptr;
    } else { /*Przynajmniej dwa elementy*/
        el* tmp = head->next;
        delete head;
        head = tmp;
        assert(head != nullptr);
    }
}

/*wywolujemy tylko gdy wiemy ze lista nie jest pusta*/
void List::removeTail() {
    assert(tail != nullptr);
    if (head == tail) { /*Czy jest to ostatni element*/
        delete head;
        head = nullptr;
        tail = nullptr;
    } else { /*Przynajmniej dwa elementy*/
        el* tmp = tail->previous;
        delete tail;
        tail = tmp;
        assert(tail != nullptr);
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
        addHead(elem);
    } else if (tail->v < elem ->v) {
        addTail(elem);
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
    assert(elem->v == a);
    return elem;
}

void List::removeList() {
    while(head != nullptr) {
        removeHead();
    }
    tail = nullptr;
    assert(this->size() == 0);
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

int List::size() {
    int i = 0;
    el* ptr = this->head;
    while (ptr != nullptr) {
        ptr = ptr->next;
        i++;
    }
    return i;
}

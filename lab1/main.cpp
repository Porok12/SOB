#include <iostream>
#include <fstream>
#include <cassert>
#include "utils.h"
#include "List.h"

using namespace std;

int main(int argc, char** argv) {
    if (argc == 4) {
        string file_name(argv[1]);
        auto min = stringToValue<int>(string(argv[2]));
        auto max =  stringToValue<int>(string(argv[3]));

        int value;
        char what;
        string str;

        filebuf* fb = new filebuf();
        fb->open("../" + file_name, ios::in);
        istream input(fb);

        assert(fb->is_open());
        assert(input.good());

        auto* list = new List();

        while (!input.eof()) {
            ignoreWhiteMarks(input);
            what = input.peek();
            if (isdigit(what)) {
                input >> value;
                cout << "Liczba: " << value <<endl;
                assert(value >= min && value <= max);
                assert(list->add(value) != nullptr);
            } else {
                input >> str;
                cerr <<"Nie liczba: " << str <<";" <<endl;
            }
        }

        list->showLeftToRight();
        list->showRightToLeft();
        list->removeList();

        fb->close();
        delete list, fb;
    }

    return 0;
}
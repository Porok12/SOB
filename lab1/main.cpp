//#define NDEBUG

#include <iostream>
#include <fstream>
#include <cassert>
#include <zconf.h>
#include "utils.h"
#include "List.h"

using namespace std;

int main(int argc, char** argv) {

//    saveToBinFile("./input.bin", 1, 10, 0.5);
//    return EXIT_SUCCESS;
    
    if (argc == 4) {
        string file_name(argv[1]);
        auto min = stringToValue<float>(string(argv[2]));
        auto max =  stringToValue<float>(string(argv[3]));

        auto* list = new (nothrow) List();
        assert(list != nullptr);
        auto * value = new (nothrow) float;
        assert(list != nullptr);
        ifstream* fb;

//        file_name = "input.bin";
        auto bios = ios::in;

        if (file_name.find(".txt") != std::string::npos) {
            cout << "odczyt w trybie tekstowym" << endl;

        } else if (file_name.find(".bin") != std::string::npos) {
            cout << "odczyt w trybie binarnym" << endl;
            bios |= ios::binary;

        } else {
            cerr << "nieobslugiwany format" << endl;
            return EXIT_FAILURE;
        }

        fb = new (nothrow) ifstream("../" + file_name, bios);
        assert(fb != nullptr);
        assert(fb->is_open());
        while (!fb->eof()) {
            if (bios & ios::binary) {
                fb->read((char*)value, sizeof(float));
            } else {
                fb->operator>>(*value);
            }
            assert(*value <= max);
            assert(*value >= min);
            list->add(*value);
        }

//        while (1) {
//            list->add(1.0f);
//        }

        list->showLeftToRight();
        list->showRightToLeft();
        list->removeList();
        assert(list->size() == 0);

        fb->close();
        delete fb, list, value;
    } else {
        cerr << "Nieprawidlowa ilosc argumentow" << endl;
        return EXIT_FAILURE;
    }

    return EXIT_SUCCESS;
}
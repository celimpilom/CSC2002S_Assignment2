# assignment 2 make file
# Celimpilo manqele
# 19 April 2021

JAVAC=/usr/bin/javac
.SUFFIXES: .java .class
SRCDIR=src
BINDIR=bin
FILESDIR=files
PYTHON=/usr/bin/python3

$(BINDIR)/%.class:$(SRCDIR)/%.java
		$(JAVAC) -d $(BINDIR)/ src/*.java


CLASSES= ReadFile.class MedianFilter.class	sequential.class	Parallel.class

CLASS_FILES=$(CLASSES:%.class=$(BINDIR)/%.class)

default: $(CLASS_FILES)

runparallel:
		java -cp bin Parallel $(file) $(size)
		
runsequential:
		java -cp bin sequential $(file) $(size)

docs:
		javadoc -d docs/ src/*.java

experiment:
		make clean
		$(PYTHON) script/test.py

clean:
		rm -f bin/*
		rm -f Tests/*

graphs:
		$(PYTHON) script/graphs.py

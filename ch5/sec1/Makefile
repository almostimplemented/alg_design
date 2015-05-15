JFLAGS = -g
JC     = javac

.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = Mergesort.java MergesortTest.java

default: classes

classes: $(CLASSES:.java=.class)

clean:
	$(RM) *.class


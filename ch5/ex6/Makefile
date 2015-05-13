JFLAGS = -g
JC     = javac

.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = Node.java LocalMin.java

default: classes

classes: $(CLASSES:.java=.class)

clean:
	$(RM) *.class


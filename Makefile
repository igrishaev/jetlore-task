
all: compile run

compile:
	scalac Formatter.scala

run:
	scala Formatter

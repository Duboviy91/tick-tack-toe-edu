export JAVA_HOME="/cygdrive/c/Program Files/Java/jdk-9.0.1/"
export PATH="$PATH:$JAVA_HOME/bin"

rm -rf ./bin
mkdir -p bin

javac -d bin -sourcepath src src/org/pribyshchuk/ttt/TickTackToeMain.java
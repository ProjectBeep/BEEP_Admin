CHECK_OS="`uname -s`"

if [[ "$CHECK_OS" = "Darwin"* ]]; then
    # Do something under Mac OS X platform
    THIS_OS="MAC"
elif [[ "$CHECK_OS" = "Linux"* ]]; then
    # Do something under GNU/Linux platform
    THIS_OS="LINUX"
elif [[ "$CHECK_OS" = "MINGW32"* ]]; then
    # Do something under Windows NT platform
    THIS_OS="WIN"
elif [[ "$CHECK_OS" = "MINGW64"* ]]; then
    # Do something under Windows NT platform
    THIS_OS="WIN"
elif [[ "$CHECK_OS" = "CYGWIN"* ]]; then
    # Do something under Windows NT platform
    THIS_OS="WIN"
fi

if [[ "$THIS_OS" = "MAC"]]; then
    ./gradlew packageDmg
elif [[ "$THIS_OS" = "LINUX" ]]; then
    ./gradlew packageDeb
elif [[ "$THIS_OS" = "WIN" ]]; then
    ./gradlew packageMsi
if

echo "build/compose/binaries/main 폴더를 확인해주세요"
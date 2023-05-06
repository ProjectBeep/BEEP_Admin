#!/bin/bash

CHECK_OS="`uname -s`"

if [[ "$CHECK_OS" = "Darwin"* ]]; then
    THIS_OS="MAC"
elif [[ "$CHECK_OS" = "Linux"* ]]; then
    THIS_OS="LINUX"
elif [[ "$CHECK_OS" = "MINGW32"* ]]; then
    THIS_OS="WIN"
elif [[ "$CHECK_OS" = "MINGW64"* ]]; then
    THIS_OS="WIN"
elif [[ "$CHECK_OS" = "CYGWIN"* ]]; then
    THIS_OS="WIN"
fi

sudo chmod +x ./gradlew
if [[ "$THIS_OS" == "MAC" ]]; then
    sudo ./gradlew packageDmg
elif [[ "$THIS_OS" == "LINUX" ]]; then
    sudo ./gradlew packageDeb
elif [[ "$THIS_OS" == "WIN" ]]; then
    sudo ./gradlew packageMsi
fi

echo "build/compose/binaries/main 폴더를 확인해주세요"
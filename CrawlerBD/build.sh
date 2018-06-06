#!/bin/bash

shopt -s globstar

mkdir bin

javac -classpath src/:lib/jsoup-1.11.3.jar -d bin/ src/**/*.java

jar -cvfm crowler.jar Manifest.txt -C bin .

jar -uvf crowler.jar -C src .
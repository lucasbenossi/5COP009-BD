#!/bin/bash

for i in home bitbucket github; do
	git fetch $i
done

#!/bin/bash

for i in home bitbucket github; do
	git push $i ${1:-'+master'}
done

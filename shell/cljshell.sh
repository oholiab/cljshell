#!/bin/bash

c () {
	echo "$@" | nc 127.0.0.1 7888
}

cl () {
	c $@ | sed -E 's/[\(|\)]//g' | sed -E 's/\s+/\n/g'
}

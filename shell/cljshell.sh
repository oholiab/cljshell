#!/bin/bash

c () {
	echo "($@)" | nc 127.0.0.1 7888
}

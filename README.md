# cljshell

A basic clojure server which listens on a port and evaluates any text that you
send it, returning the result and closing the connection.

This comes with an accompanying bash function so the commands can be sent and
piped like native bash commands!

## Installation

Requires [leiningen](https://github.com/technomancy/leiningen) for now

	git clone https://github.com/oholiab/cljshell

If you want to run directly from the jar you will need to run

	lein uberjar

to build

## Usage

Either, in the project directory

	lein run

or

	java -jar cljshell-0.1.0-standalone.jar

and then in another shell

	source cljshell/shell/cljshell.sh

Then you should be able to run any clojure function like:

	$ c '(+ 1 1)'
	2

All function calls currently have to be enclosed in quotes of some kind so that
bash does not interpret the parentheses. I'm still thinking about that one.

Exceptions are caught and returned as single line strings:

	$ c '(+ - 1)'
	#<ClassCastException java.lang.ClassCastException: clojure.core$_ cannot be cast to java.lang.Number>

Results can be piped around like any normal bash command. For a slightly more
usefully formatted output, there's also `cl` which will parse a list (although
sadly not a nested one yet) into a newline separated list, so you can do:

	$ cl '(range 3)'
	0
	1
	2

And thus

	$ for i in $(cl '(range 3)'); do echo $i stuff; done
	0 stuff
	1 stuff
	2 stuff

Which is obviously entirely useless compared to bash's inbuilts, but once you've
added some functions to your server it might be useful. Might being the
operative word.

## Options

Currently none but I'll at least be making it possible to specify a port other
than 7888.

You can easily customise the bash function to be named something else and I'd be
interested to see what people do with it

### Bugs

Probably loads. Pull requests welcome.

### Disclaimer

I make no guarantee that this code will not adversely affect anything, least of
all your computer. Please proceed with caution, as always YMMV

Copyright © 2013 Matt Carroll

# cljs-basic

A simple app for you to play around with.

## Installation

You need [leiningen](http://leiningen.org/) and Java.

Clone this repo and stick it in the location of you choice.

## Structure

`src-cljs/` contains Clojurescript code, which will be compiled to Javascript and run on the browser. `src/` contains Clojure code, which will be run as a .jar.

## Usage

This project includes a webserver which serves static assets and a Clojurescript app.

To start the server:

`lein ring server 3000` (or whatever port)

To compile the code automatically (it will recompile when you save files):

`lein cljsbuild auto`

To fire up a REPL:

`lein trampoline cljsbuild repl-rhino`

Visit the site, and open Devtools.  You should see

    Lisp syntax
    JS syntax

in the console.

Now start reading/hacking around in the source code. I would read `src-cljs/cljs_basic/app.cljs`, then `src-cljs/cljs_basic/components.cljs`, then `src/cljs_basic/server.clj`, and finally `project.clj`.

## Other Resources

["Clojure for the Brave and True"](http://www.braveclojure.com/)
[Clojure syntax cheatsheet](http://clojure.org/cheatsheet)
[Clojurescript reference + web REPL](http://himera.herokuapp.com/index.html)

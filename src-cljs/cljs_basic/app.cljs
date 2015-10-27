;; Welcome to Clojure!  Comments start with ;

;; Statements in Clojure are contained in parentheses.
;;
;; `ns` defines a namespace.  If this were Ruby, we would write `module CljsBasic::App`.
(ns cljs-basic.app
  ;; We can require other namespaces (modules) this way.  We defined `cljs-basic.components`,
  ;; `reagent.core` comes from a separate library.
  ;;
  ;; The `reagent` library wraps React, and provides a JSX equivalent.
  (:require [cljs-basic.components :as components]
            [reagent.core :as reagent]))

;; This is what defining a function looks like.  If this were Javascript we would write
;; `function logStuff() {}`.
;;
;; The empty array (called a vector in Clojure) stores the arguments to the function.
(defn log-stuff []
  ;; This is how you call native Javascript functions.
  ;;
  ;; You can use the syntax that Clojure uses `(function argument1 argument2 ...)`  Native
  ;; Javascript functions get a period in front of their name.
  (.log js/console "Lisp syntax")

  ;; Or you can use a more familiar syntax.
  (js/console.log "JS syntax"))

(defn init! []
  ;; If you check devtools in your browser of choice, you should see
  ;;
  ;; Lisp syntax
  ;; JS syntax
  ;;
  ;; In the console when the page loads.
  (log-stuff)

  ;; This will render React components in <div id="mnt"></div>.  Right now it's commented out.
  ;;
  ;; `#_` will comment out the entire statement in parentheses.  It's often more convenient than
  ;; adding ; to each line you want to comment out.
  ;;
  ;; Uncomment this when you have things loading (i.e. you see the stuff that is supposed to be
  ;; printed to the console) and want to play around with React.
  #_(reagent/render-component [components/app] (js/document.getElementById "mnt")))

;; Start the app.
(init!)

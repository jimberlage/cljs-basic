;; We have a few React components defined here.  The function named `app` will be the root of our
;; React components.
(ns cljs-basic.components
  (:require [clojure.string :as string]
            [reagent.core :as reagent]))

;; mdsol-list-item is a React component.  In Reagent, React components are functions that return a
;; vector representing html (they can also return another function; we'll get to that later).  This
;; renders a <li></li>.
(defn mdsol-list-item
  ;; If you want to add some documentation about the purpose of your function, you can do it here.
  ;; This docstring goes before the arguments to the function.
  "Prepends \"mdsol-\" to the text of this <li>.  Gotta protect the brand."
  ;; Here are the function's arguments.
  [text]
  ;; This is Reagent's equivalent of JSX; html in components looks like this.  If this were JSX we
  ;; would write `return <li>{["mdsol", this.props.text].join("-")}</li>;`
  ;;
  ;; Just like Ruby, functions return the result of the last statement they contain.  Unlike Ruby,
  ;; there is no explicit `return`.
  [:li (string/join "-" ["mdsol" text])])

;; Another component.  `items` is an atom (http://clojuredocs.org/clojure.core/atom), which is
;; a variable that keeps track of state.  Calling `@items` gets the current state of items.  Here,
;; we expect it to be a vector.
(defn mdsol-list [items]
  ;; `let` is a way to define local variables (bindings) in Clojure.  They're immutable.
  ;; (http://clojuredocs.org/clojure.core/let)
  (let [list-items (map (fn [i]
                          [mdsol-list-item i]) @items)
        ;; However, nothing stops us from redefining the binding again by calling a function with
        ;; its old value.  It's just not the same data structure.
        list-items (vec list-items)]
    ;; This should expand to
    ;;
    ;; <ul>
    ;;   <li>...</li>
    ;;   <li>...</li>
    ;; </ul>
    (vec (concat [:ul] list-items))))

;; This component does some interactive stuff.  It will allow us to add a new list item, with a
;; user-submitted value.
(defn add-item-button [items]
  ;; A reagent/atom is a Clojure atom that triggers React to rerender the page when it is updated.
  (let [item (reagent/atom "")]
    ;; This component returns a function instead of a vector.  This new function is what React will
    ;; rerender.  This means that `item` will not be set to an empty string every time the page is
    ;; rendered.
    (fn []
      [:div
       ;; This `{}` is a Clojure map.  It's like a map in Ruby or an object in Javascript, but you
       ;; don't have to separate pairs with commas.  In fact, Clojure treats commas as whitespace.
       ;;
       ;; The keys correspond to DOM attributes.  You can view the DOM attributes for the input
       ;; element here: https://html.spec.whatwg.org/multipage/forms.html#the-input-element.
       ;;
       ;; The IDL section defines all the attributes.  React uses these attribute names, not
       ;; necessarily the ones you would type in an HTML tag.  For example, it will complain if you
       ;; use the key :readonly.  The value is :readOnly or :read-only.  You can insert dashes
       ;; instead of using camelCase if you want.
       [:input {:type "text"
                :value @item
                ;; You can modify atoms by calling `reset!` which replaces the atom with the new
                ;; value.
                ;;
                ;; Typing something in this input will trigger React to rerender the document, since
                ;; a reagent/atom changed.
                :on-change #(reset! item (.-value (.-target %)))}]
       ;; `swap!` is a little different from `reset!`.  It takes the current state of the atom, and
       ;; passes to another function.  It then sets the value of the atom with the result of
       ;; applying the function.
       ;;
       ;; In the example below, we're setting items to be `(conj @items @item)`, appending the new
       ;; item to the list.
       [:button {:on-click (fn [_]
                             (swap! items conj @item)
                             ;; We should clear the text from the input too.
                             (reset! item ""))} "Add"]])))

;; That's pretty much all of Reagent that you need to get up and running.
;;
;; * State is set and retrieved through atoms.
;; * Components are A.) a function that returns a vector representing an HTML element OR B.) a
;; function that returns A.
;; * You can render a component (and all its children) initially with `reagent/render-component`.

(defn app []
  ;; Our initial list of items.
  (let [items (reagent/atom ["hello" "world"])]
    [:section#app
     ;; We nest components by putting them in a vector, with the arguments we want to call them
     ;; with.
     [mdsol-list items]
     [add-item-button items]]))

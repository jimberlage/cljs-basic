;; This defines a server to serve static files.  We use `ring`, which is a popular way to build
;; webservers.  It would be like building a Rack app in Ruby.
(ns cljs-basic.server
  ;; You can `refer` values, which in practical terms just means that you can leave off their
  ;; namespace when calling them.
  ;;
  ;; `*cwd*` gives the current working directory.
  ;; `file-response` is a function which returns the appropriate HTTP response for the file you
  ;; point it at.
  (:require [me.raynes.fs :refer [*cwd*]]
            [ring.util.response :refer [file-response]]))

;; Ring responses are just a map, with some common HTTP things for keys.
(defn app-page-response []
  {:status 200
   :headers {"Content-Type" "text/html"}
   :body (slurp (str *cwd* "/resources/html/app.html"))})

;; This will serve our js files.
(defn js-file-response [url]
  (file-response url {:root (str *cwd*)}))

;; Ring expects an app handler, which is the first point of entry for any request sent to the
;; webserver.  You can filter the requests as you see fit.
(defn handle [request]
  (let [url (:uri request)]
    ;; `#"resources/js" defines a regex.
    ;;
    ;; `re-find` takes a regex and a string, and returns the matches or nil (when the string doesn't
    ;; match).
    ;;
    ;; Just like Ruby, falsey statements are `false` and `nil`.  You can use `nil` as false in
    ;; conditionals.
    (if (re-find #"resources/js" url)
      (js-file-response url)
      (app-page-response))))

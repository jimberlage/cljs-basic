(defproject cljs-basic "0.1.0-SNAPSHOT"
  ;; We use lein-cljsbuild, which is a useful plugin that makes compiling Clojurescript easy.
  ;;
  ;; :source-paths contains all the directories in which we keep our Clojurescript.
  :cljsbuild {:builds {:dev {:source-paths ["src-cljs"]
                             ;; Compiler options go here.
                             ;;
                             ;; :output-to will specify the root of our Javascript.
                             :compiler {:output-to "resources/js/app.js"
                                        ;; There are several options, ranging from :none to
                                        ;; :advanced.
                                        :optimizations :none
                                        ;; Source maps are a useful browser devtools feature, which
                                        ;; allows you to map generated Javascript to the original
                                        ;; Clojurescript function.
                                        :source-map true
                                        ;; Since we're not optimizing at all, our compiled
                                        ;; Javascript will live in a directory with the same
                                        ;; structure as `src-cljs`.  If we were using more advanced
                                        ;; optimizations, everything would be shoved into `app.js`.
                                        :output-dir "resources/js"}}}}
  :description "A small Clojurescript project for JS/Ruby developers."
  ;; Specify the libraries you need here.  Leiningen will grab them all for you.
  :dependencies [[me.raynes/fs "1.4.6"]
                 [org.clojure/clojure "1.7.0"]
                 [org.clojure/clojurescript "0.0-3308"]
                 [reagent "0.5.1"]
                 [ring/ring-core "1.4.0"]]
  :hooks [leiningen.cljsbuild]
  :main ^:skip-aot cljs-basic.core
  :plugins [[lein-cljsbuild "1.0.6"]
            [lein-ring "0.9.6"]]
  :profiles {:uberjar {:aot :all}}
  ;; This defines our webserver.
  :ring {:handler cljs-basic.server/handle}
  :target-path "target/%s")

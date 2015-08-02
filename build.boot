(set-env!
  :source-paths   #{"src"}
  :resource-paths #{"html"}
  :dependencies
  '[[org.clojure/clojure        "1.7.0"          :scope "provided"]
    [org.clojure/clojurescript  "0.0-3123"       :scope "provided"]
    [rum                        "0.2.7"]

    [adzerk/boot-cljs           "0.0-3308-0"     :scope "test"]
    [cljsjs/boot-cljsjs         "0.5.0"          :scope "test"]
    [adzerk/boot-cljs-repl      "0.1.9"          :scope "test"]
    [adzerk/boot-reload         "0.3.1"          :scope "test"]
    [pandeiro/boot-http         "0.6.3"          :scope "test"]])

(require
  '[adzerk.boot-cljs      :refer [cljs]]
  '[cljsjs.boot-cljsjs    :refer [from-cljsjs]]
  '[adzerk.boot-cljs-repl :refer [cljs-repl start-repl]]
  '[adzerk.boot-reload    :refer [reload]]
  '[pandeiro.boot-http    :refer [serve]])

(deftask dev
  "Start the dev env..."
  []
  (comp
    (watch)
    (serve :dir "target" :port 3000)
    (cljs-repl)
    (from-cljsjs :profile :development)
    (cljs :unified true
          :source-map true
          :optimizations :none)
    (reload)))

(deftask prod
  "Make the production build"
  []
  (comp
    (from-cljsjs :profile :production)
    (cljs :unified true
          :optimizations :advanced)))

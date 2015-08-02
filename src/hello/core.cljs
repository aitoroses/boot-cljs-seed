(ns hello.core
  (:require [rum :as r]))

(def post
  {:text "First Post" :author "Jane"})

(r/defc render-post [{:keys [:text :author]}]
  [:blockquote (+ text " ") [:cite author]])

(r/mount (render-post post)
  (.getElementById js/document "app"))

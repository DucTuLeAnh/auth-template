(ns auth-template.domain.endpoint-handler.home
  (:require
   [compojure.response :refer [render]]
   [clojure.java.io :as io]))

(defn home-handler
  [request]
  (let [content (slurp (io/resource "index.html"))]
    (render content request)))


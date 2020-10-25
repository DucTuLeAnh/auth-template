(ns auth-template.domain.endpoint-handler.login
  (:require
   [compojure.response :refer [render]]
   [clojure.java.io :as io]))

(defn login-handler
  [request]
  (let [content (slurp (io/resource "login.html"))]
    (render content request)))


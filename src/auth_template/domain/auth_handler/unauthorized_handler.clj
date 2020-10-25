(ns auth-template.domain.auth_handler.unauthorized-handler

  (:require
   [compojure.core :refer :all]
   [compojure.response :refer [render]]
   [clojure.java.io :as io]
   [ring.util.response :refer [redirect]]
   [buddy.auth :refer [authenticated?]]))

(defn unauthorized-handler
  [request metadata]
  (cond
    ;; If request is authenticated, raise 403 instead
    ;; of 401 (because user is authenticated but permission
    ;; denied is raised).
    (authenticated? request)
    (-> (render (slurp (io/resource "error.html")) request)
        (assoc :status 403))
    ;; In other cases, redirect the user to login page.
    :else
    (let [current-url (:uri request)]
      (redirect (format "/login?next=%s" current-url)))))


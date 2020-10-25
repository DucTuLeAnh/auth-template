(ns auth-template.domain.endpoint-handler.logout
  (:require [ring.util.response :refer [redirect]]))

(defn logout-handler
  [request]
  (-> (redirect "/login")
      (assoc :session {})))


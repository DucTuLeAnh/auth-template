(ns auth-template.core
  (:require [ring.middleware.session :refer [wrap-session]]
            [ring.middleware.params :refer [wrap-params]]
            [ring.adapter.jetty :as jetty]
            [ring.middleware.basic-authentication :refer [wrap-basic-authentication]]
            [buddy.auth.backends.session :refer [session-backend]]
            [buddy.auth.middleware :refer [wrap-authentication wrap-authorization]]
            [auth-template.routes :refer [app-routes]]
            [auth-template.domain.auth_handler.unauthorized-handler :refer [unauthorized-handler]])

  (:gen-class))

(def auth-backend
  (session-backend {:unauthorized-handler unauthorized-handler}))

(defn -main
  [& args]
  (as-> app-routes $
    (wrap-authorization $ auth-backend)
    (wrap-authentication $ auth-backend)
    (wrap-params $)
    (wrap-session $)
    (jetty/run-jetty $ {:port 3000})))

(ns auth-template.domain.endpoint-handler.login-authenticate
  (:require
   [compojure.response :refer [render]]
   [clojure.java.io :as io]
   [ring.util.response :refer [redirect]]
   [auth-template.mock-user-db :refer [authdata authorized-users]]
   [auth-template.domain.endpoint-handler.login :refer [login-handler]]
   [ring.middleware.json :refer [json-body-request]]
   ))

(defn login-authenticate-handler
  "Check request username and password against authdata
  username and passwords.
  On successful authentication, set appropriate user
  into the session and redirect to the value of
  (:next (:query-params request)). On failed
  authentication, renders the login page."
  [request]
  (let [body-map (get (json-body-request request {}) :body)
        username (get body-map "username")
        password (get body-map "password")
        session (:session request)
        found-password (get authdata (keyword username))]
    (if (and found-password (= found-password password))
      (let [next-url (get-in request [:query-params :next] "/")
            updated-session (assoc session :identity (keyword username))]
        (-> (redirect next-url)
            (assoc :session updated-session)))
      (let [content (slurp (io/resource "login.html"))]
        (render content request)))))


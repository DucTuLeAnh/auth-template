(ns auth-template.routes
  (:require
   [compojure.core :refer :all]
   [auth-template.domain.auth-handler.secure-endpoint-handler :refer [secure-endpoint]]
   [auth-template.domain.endpoint-handler.login :refer [login-handler]]
   [auth-template.domain.endpoint-handler.login-authenticate :refer [login-authenticate-handler]]

   [auth-template.domain.endpoint-handler.logout :refer [logout-handler]]
   [auth-template.domain.endpoint-handler.home :refer [home-handler]]))

(defroutes app-routes "Defines endpoint functions for each route. Endpoint functions take one parameter - the request. If you want to call a function with more than one parameter create an anonymous function around the multivariant function and pass the request to the multivariant function. E.g #(secure-endpoint % some-endpoint-fn)"
  (GET "/" [] #(secure-endpoint % home-handler))
  (GET "/login" [] login-handler)
  (POST "/login" [] login-authenticate-handler)
  (GET "/logout" [] logout-handler))

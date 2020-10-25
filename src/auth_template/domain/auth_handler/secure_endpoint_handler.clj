(ns auth-template.domain.auth-handler.secure-endpoint-handler
  (:require
   [buddy.auth :refer [authenticated? throw-unauthorized]]
   [auth-template.mock-user-db :refer [authorized-users]]))

(defn secure-endpoint "Checks if the request is authenticated and authorized by reading the :identity in the :session of the request. If the check fails, an unauthorized exception will be thrown. That unauthorized exception should then be handled by an unauthorized handler." [request endpoint-fn]
  (if-not (and (authenticated? request) (get authorized-users (get-in request [:session :identity])))

    (throw-unauthorized)
    (endpoint-fn request)))

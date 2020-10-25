(ns auth-template.domain.endpoint-handler.login-authenticate-test
  (:require  [clojure.test :refer :all]
             [auth-template.domain.endpoint-handler.login-authenticate :refer [login-authenticate-handler]]
             [ring.mock.request :as mock]
             [clojure.java.io :as io]))

(deftest test-login-authenticate
  (testing "with known username" (is (= (login-authenticate-handler (-> (mock/request :post "/login")
                                                                        (mock/json-body {"username" "admin" "password" "secret"})))
                                        {:status  302
                                         :headers {"Location" "/"}
                                         :body    ""
                                         :session {:identity :admin}})))

  (testing "with unknown username" (is (= (login-authenticate-handler (-> (mock/request :post "/login")
                                                                          (mock/json-body {"username" "unknown" "password" "secret"})))
                                          {:status  200
                                           :headers {"Content-Type" "text/html; charset=utf-8"}
                                           :body    (slurp (io/resource "login.html"))}))))

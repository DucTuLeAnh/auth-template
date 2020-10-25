(ns auth-template.domain.auth-handler.secure-endpoint-handler-test
  (:require  [clojure.test :refer :all]
             [auth-template.domain.auth-handler.secure-endpoint-handler :refer [secure-endpoint]]
             [ring.mock.request :as mock]
             [clojure.java.io :as io]))

(defn stub-request-handler [request]
  {:msg "I got executed!"})

(deftest test-secure-endpoint-handler
  (testing "with authorized username" (is (= (secure-endpoint  {:identity :admin}
                                                               stub-request-handler)
                                             {:msg "I got executed!"})))

  (testing "with unauthorized username" (is (thrown? Exception (secure-endpoint  {:identity :test}
                                                                                 stub-request-handler))))

  (testing "without identity" (is (thrown? Exception (secure-endpoint  {}
                                                                       stub-request-handler)))))

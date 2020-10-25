(defproject auth-template "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.10.0"]
                 [compojure "1.6.2"]
                 [ring/ring-core "1.8.2"]
                 [ring/ring-jetty-adapter "1.8.2"]
                 [ring-basic-authentication "1.1.0"]
                 [buddy/buddy-auth "2.2.0"]
                 [ring/ring-json "0.5.0"]
                 [ring/ring-mock "0.4.0"]
                 ]
  :main ^:skip-aot auth-template.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})

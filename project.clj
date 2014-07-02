(defproject doit "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [com.datomic/datomic-free "0.9.4699"
                  :exclusions [org.slf4j/jul-to-slf4j
                               org.slf4j/slf4j-nop]]
                 [io.pedestal/pedestal.service "0.3.0"]
                 [io.pedestal/pedestal.service-tools "0.3.0"]
                 [io.pedestal/pedestal.jetty "0.3.0"]])

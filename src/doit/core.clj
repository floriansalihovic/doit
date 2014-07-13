(ns doit.core
  (:require [io.pedestal.http.route.definition :refer [defroutes]]
            [io.pedestal.http.route :as route :refer [router]]
            [io.pedestal.http :as http]
            [ring.handler.dump :refer [handle-dump]]
            [ns-tracker.core :refer [ns-tracker]]
            [doit.todo :as todo :refer [index]]))

(defroutes routes
  [[["/"
     ["/todos" {:get [:todos todo/index]}]]]])

(def modified-namespaces (ns-tracker "src"))

(def service {::http/interceptors [http/log-request
                                   http/not-found
                                   route/query-params
                                   (router (fn []
                                             (doseq [ns-sym (modified-namespaces)]
                                               (require ns-sym :reload))
                                             routes))]
              ::http/port 8080})

(defn -main [& args]
  (-> service
      http/create-server
      http/start))

(ns doit.core
  (:require [io.pedestal.http.route.definition :refer [defroutes]]
            [io.pedestal.http.route :as route :refer [router]]
            [io.pedestal.http :as http]))

(defn hello-world [req]
  {:status 200
   :body "Hello, world!"
   :headers {}})

(defroutes routes
  [[["/" ["/hello" {:get hello-world}]]]])

(def modified-namespaces (ns-tracker "src"))

(def service {::http/interceptors [(router (fn []
                                             (doseq [ns-sym (modified-namespaces)]
                                               (require ns-sym :reload))
                                             routes))]
              ::http/port 8080})

(defn -main [& args]
  (-> service
      http/create-server
      http/start))

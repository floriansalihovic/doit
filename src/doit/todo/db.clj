(ns todoit.todo.db
    (:require [datomic.api :as d]))

(defonce uri (str "datomic:mem://" (gensym "todos")))
(d/create-database uri)
(def conn (d/connect uri))

(def schema-tx (->> "todos.edn"
                    clojure.java.io/resource
                    slurp
                    (clojure.edn/read-string {:readers *data-readers*})))

@(d/transact conn schema-tx)

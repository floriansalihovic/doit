(ns doit.todo.db-test
  (:require [clojure.test :refer :all]
            [datomic.api :as d]
            [doit.todo.db :as db]))

(deftest create-todo-test
  (testing "Creating a todo."
    (let [title "The title"
          description "The description"]
      (db/create-todo title description)
      (is (not= 0 (count (db/all-todos (d/db db/conn))))))))

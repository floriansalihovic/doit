(ns doit.todo.db-test
  (:require [clojure.test :refer :all]
            [datomic.api :as d]
            [doit.todo.db :as db]))

(def title "The title")

(def description "The description")

(deftest create-todo-test
  (testing "Creating a todo."
    (db/create-todo title description)
    (is (not= 0 (count (db/all-todos (d/db db/conn)))))
    (let [todo (first (db/all-todos (d/db db/conn)))
          id (:db/id todo)]
      (is title (:todo/title todo))
      (is description (:todo/description todo))
      (db/toggle-status id true))))

(deftest toggle-status-test
  (testing "Toogling the :todo/completed? status of a todo."
    (let [todo (first (db/all-todos (d/db db/conn)))]
      (is true (:todo/completed? todo)))))

(deftest delete-todo-tests
  (testing "Deleting a todo."
    (let [todo (first (db/all-todos (d/db db/conn)))
          id (:db/id todo)]
      (db/delete-todo id)
      (is 0 (db/all-todos (d/db db/conn))))))

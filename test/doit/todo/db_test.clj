(ns doit.todo.db-test
  (:require [clojure.test :refer :all]
            [datomic.api :as d]
            [doit.todo.db :as db]))

(def title "The title")
(def description "The description")

(deftest todo-livecycle-test
  (testing "if a todo can be stored properly."
    (db/create-todo title description)
    (is (not= 0 (count (db/all-todos (d/db db/conn)))))
    (let [todo (first (db/all-todos (d/db db/conn)))
          id (:db/id todo)]
      (is (= title (:todo/title todo)))
      (is (= description (:todo/description todo)))
      (db/toggle-status id true)))
  (testing "if the status of a todo can be changed."
    (let [todo (first (db/all-todos (d/db db/conn)))
          id (:db/id todo)]
      (is (= true (:todo/completed? todo)))))
  (testing "if the completed todo can be found.."
    (is (= 1 (count (db/completed-todos (d/db db/conn))))))
  (testing "if a todo can be delted."
    (let [todo (first (db/all-todos (d/db db/conn)))
          id (:db/id todo)]
      (db/delete-todo id)
      (is (= 0 (count (db/all-todos (d/db db/conn))))))))

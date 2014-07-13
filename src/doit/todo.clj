(ns doit.todo
  (:require [io.pedestal.interceptor :refer [defhandler]]
            [ring.handler.dump :refer [handle-dump]]
            [doit.todo.db :as db]))

(defhandler index [req]
  (handle-dump req))

(ns rtc-chat.test.core
  (:require [cemerick.cljs.test :as t]
            [dommy.core :as dommy]
            [dommy.core :refer-macros [sel sel1]]
            [om.core :as om :include-macros true]
            [rtc-chat.core :as core]
            )
  (:require-macros [cemerick.cljs.test :refer (is deftest)]))

(enable-console-print!)

(defn new-id 
  ([]
   (str "container-" (gensym)))
  ([id]
   (str "container-" id)))

(defn new-node [id]
  (-> (dommy/create-element "div")
      (dommy/set-attr! "id" id)))

(defn append-node [node]
  (dommy/append! (sel1 js/document :body) node))

(defn container!
  ([]
   (container! (new-id)))
  ([id]
   (-> id
       new-node
       append-node)))

(deftest test-container
  (let [c (container! "container-1")]
    (is (sel1 :#container-1))
    (is (container!))
    ))

(deftest video-element
  (let [c (container!)]
    (om/root core/video {} {:target c})
    (is (sel1 :video))))

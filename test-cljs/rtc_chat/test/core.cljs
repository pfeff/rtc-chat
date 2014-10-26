(ns rtc-chat.test.core
  (:require [cemerick.cljs.test :as t]
            [dommy.core :refer-macros [sel sel1]]
            )
  (:require-macros [cemerick.cljs.test :refer (is deftest)]))

(deftest hello
  (let [h1 (sel1 [:h1])]
    (is (= "Hello world!" h1))))

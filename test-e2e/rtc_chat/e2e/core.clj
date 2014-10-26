(ns rtc-chat.e2e.core
  (:require [clojure.test :refer :all]
            [clj-webdriver.taxi :as t]
            )
  )

(defn base-url []
  "http://localhost:8080/")

(defn start-server [f]
  ;; app start code here
  (f)
  )

(defn start-browser-fixture
  [f]
  (t/set-driver! {:browser :firefox} (base-url))
  (f))

(defn reset-browser-fixture
  [f]
  (t/to (base-url))
  (f))

(defn quit-browser-fixture
  [f]
  (f)
  (t/quit))

(use-fixtures :once start-server start-browser-fixture quit-browser-fixture)
(use-fixtures :each reset-browser-fixture)

(deftest test-browser-configured
  (is (= (class t/*driver*) clj_webdriver.driver.Driver))
  (is (= (t/current-url) (base-url)))
  (is (= (re-find #"(?i)html>" (t/page-source)))))


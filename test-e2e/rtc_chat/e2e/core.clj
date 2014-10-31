(ns rtc-chat.e2e.core
  (:require [clojure.test :refer :all]
            [clj-webdriver.taxi :as t]
            [rtc-chat.server :as server]
            [rtc-chat.core :as c]
            )
  )

(def app (atom nil))

(defn base-url []
  "http://localhost:8080/")

(defn start-server [f]
  ;; app start code here
  (let [app-component (c/start-app)]
    (reset! app app-component))
  (f)
  )

(defn start-browser-fixture
  [f]
  (t/set-driver! {:browser :phantomjs} (base-url))
  (f))

(defn reset-browser-fixture
  [f]
  (t/to (base-url))
  (f))

(defn quit-browser-fixture
  [f]
  (f)
  (t/quit))

(defn stop-server
  [f]
  (f)
  (c/stop-app @app))

(use-fixtures :once start-server start-browser-fixture quit-browser-fixture stop-server)
(use-fixtures :each reset-browser-fixture)

(deftest test-browser-configured
  (is (= (class t/*driver*) clj_webdriver.driver.Driver))
  (is (= (t/current-url) (base-url)))
  (is (= (re-find #"Hello world" (t/page-source)))))


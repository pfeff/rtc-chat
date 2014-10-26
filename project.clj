(defproject rtc-chat "0.1.0-SNAPSHOT"
  :description "FIXME: write this!"
  :url "http://example.com/FIXME"

  :dependencies [[org.clojure/clojure "1.6.0"]
                 [org.clojure/clojurescript "0.0-2311"]
                 [org.clojure/core.async "0.1.267.0-0d7780-alpha"]
                 [om "0.7.1"]
                 [prismatic/dommy "1.0.0"]
                 ]

  :plugins [[lein-cljsbuild "1.0.4-SNAPSHOT"]
            [com.cemerick/clojurescript.test "0.2.3"] ]

  :source-paths ["src"]

  :cljsbuild { 
    :builds [{:id "rtc-chat"
              :source-paths ["src-cljs" "test-cljs"]
              :compiler {
                :output-to "rtc_chat.js"
                :output-dir "out"
                :optimizations :whitespace
                :source-map "rtc_chat.js.map"}}]
    :test-commands {"unit-tests" ["phantomjs" :runner
                                  "this.literal_js_eval=true"
                                  "rtc_chat.js"
                                  ]}
    }
  :profiles {:dev {:dependencies [[clj-webdriver "0.6.0"]
                                  [com.cemerick/clojurescript.test "0.3.1"]
                                  ]}}
  )

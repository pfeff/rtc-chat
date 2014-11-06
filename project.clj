(defproject rtc-chat "0.1.0-SNAPSHOT"
  :description "FIXME: write this!"
  :url "http://example.com/FIXME"

  :dependencies [[org.clojure/clojure "1.6.0"]
                 [org.clojure/clojurescript "0.0-2311"]
                 [org.clojure/core.async "0.1.267.0-0d7780-alpha"]
                 [com.stuartsierra/component "0.2.2"]
                 [om "0.7.1"]
                 [prismatic/dommy "1.0.0"]
                 [http-kit "2.1.16"]
                 [compojure "1.2.1"]
                 [enlive "1.1.5"]
                 ]

  :node-dependencies [[slimerjs "0.9.2"]]

  :plugins [[lein-cljsbuild "1.0.4-SNAPSHOT"]
            [com.cemerick/clojurescript.test "0.2.3"]
            [lein-npm "0.4.0"]]

  :source-paths ["src"]
  :test-paths ["test" "test-cljs" "test-e2e"]

  :cljsbuild { 
    :builds [{:id "dev"
              :source-paths ["src-cljs"]
              :compiler {
                :output-to "rtc_chat.js"
                :output-dir "out"
                :optimizations :none
                :source-map "rtc_chat.js.map"}}
             {:id "test"
              :source-paths ["src-cljs" "test-cljs"]
              :compiler {:pretty-print true
                         :output-dir "resources/private/js"
                         :output-to "resources/private/js/unit-test.js"
                         :preamble ["react/react.js"]
                         :externs ["react/externs/react.js"]
                         :optimizations :whitespace } } ]
    :test-commands {"unit-tests" ["node_modules/slimerjs/bin/slimerjs" :runner
                                  "this.literal_js_eval=true"
                                  "resources/private/js/unit-test.js" ]}
    }
  :profiles {:dev {:dependencies [[clj-webdriver "0.6.1"]
                                  [com.cemerick/clojurescript.test "0.3.1"]
                                  ]}}
  )

(ns rtc-chat.server
  (:require [org.httpkit.server :as http]
            [compojure.core :refer [defroutes GET]]
            [compojure.handler :refer [site]]
            [net.cgrand.enlive-html :as html]
            [com.stuartsierra.component :as component]
            ))

(html/deftemplate show-index "templates/index.html"
  [_req])

(defroutes routes
  (GET "/" [] show-index))

(defrecord WebServer []
  component/Lifecycle
  (start [component]
    (let [stop-fn (http/run-server (site #'routes) {:port 8080})]
      (assoc component :stop-fn stop-fn)))
  (stop [component]
    ((:stop-fn component))))

(defn start-server []
    (WebServer.))


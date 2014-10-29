(ns rtc-chat.server
  (:require [org.httpkit.server :as server]
            [compojure.core :refer [defroutes GET]]
            [compojure.handler :refer [site]]
            [net.cgrand.enlive-html :as html]
            ))

(html/deftemplate show-index "templates/index.html"
  [_req])

(defroutes routes
  (GET "/" [] show-index))

(defn start-server []
  (server/run-server (site #'routes) {:port 8080}))

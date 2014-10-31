(ns rtc-chat.core
  (:require [com.stuartsierra.component :as component]
            [rtc-chat.server :as server]))

(defn chat-system []
  (component/system-map
    :webserver (server/start-server)))

(defn start-app []
  (let [system (chat-system)]
    (component/start system)))

(defn stop-app [app-component]
  (component/stop app-component))

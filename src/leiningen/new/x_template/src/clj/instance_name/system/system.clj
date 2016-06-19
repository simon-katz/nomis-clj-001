(ns {{ns-name}}.system.system
  (:require [com.stuartsierra.component :as component]
            [{{ns-name}}.services.provided.web.handlers.handler :as handler]
            [{{ns-name}}.services.provided.web.server :as web.server]
            [taoensso.timbre :as timbre]))

;;;; ___________________________________________________________________________

(defn make-system
  "Returns a new instance of the whole application."
  [config]
  (component/system-map

   :handler-maker
   (component/using (handler/make-handler-maker)
                    [])

   :port (get-in config [:http :port])

   :webserver (component/using (web.server/make-webserver)
                               [:port
                                :handler-maker])))

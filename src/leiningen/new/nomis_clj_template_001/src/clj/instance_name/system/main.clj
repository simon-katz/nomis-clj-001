(ns {{ns-name}}.system.main
  (:require [com.stuartsierra.component :as component]
            [environ.core :as env]
            [{{ns-name}}.system.system :as system])
  (:gen-class))

;;;; ___________________________________________________________________________

(defn ^:private http-port-from-env []
  (when-let [port-as-string (env/env :{{ns-name}}-http-port)]
    (try (Integer/parseInt port-as-string)
         (catch NumberFormatException e
           (throw (RuntimeException.
                   (str ":{{ns-name}}-http-port in environment cannot be parsed: "
                        e)))))))

(def ^:private config
  {:http {:port (or (http-port-from-env)
                    12345)}}) ; FIXME: Change port no (need a config file)

;;;; ___________________________________________________________________________

(defn -main
  [& args]
  (assert (= (count args) 0)
          (str "Expected no args but got: " args))
  (component/start (system/make-system config)))

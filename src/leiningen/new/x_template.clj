(ns leiningen.new.x-template
  (:use [leiningen.new.templates :only [renderer raw-resourcer name-to-path sanitize-ns project-name ->files]]))

(def render (renderer "x-template"))

(def raw (raw-resourcer "x-template"))

(defn x-template
  [name]
  (let [data {:name name
              :project-name (project-name name)
              :ns-name (sanitize-ns name)
              :sanitized (name-to-path name)}]
    (letfn [(render' [f]
              (render f data))]
      (->files data
               [".gitignore"                                                       (render' ".gitignore")]
               ["project.clj"                                                      (render' "project.clj")]
               ["_scripts/run-tests"                                               (render' "_scripts/run-tests")]
               ["dev/user.clj"                                                     (render' "dev/user.clj")]
               
               ["src/clj/{{sanitized}}/system/main.clj"                            (render' "src/clj/instance_name/system/main.clj")]
               ["src/clj/{{sanitized}}/system/system.clj"                          (render' "src/clj/instance_name/system/system.clj")]
               ["src/clj/{{sanitized}}/services/provided/web/server.clj"           (render' "src/clj/instance_name/services/provided/web/server.clj")]
               ["src/clj/{{sanitized}}/services/provided/web/handlers/handler.clj" (render' "src/clj/instance_name/services/provided/web/handlers/handler.clj")]
               
               ["src/cljs/{{sanitized}}/main.cljs"                                 (render' "src/cljs/instance_name/main.cljs")]
               ["src/cljs/{{sanitized}}/render.cljs"                               (render' "src/cljs/instance_name/render.cljs")]
               ["src/cljs/{{sanitized}}/play/re_com_play.cljs"                     (render' "src/cljs/instance_name/play/re_com_play.cljs")]
               
               ["resources/public/assets/css/material-design-iconic-font.min.css"  (render' "resources/public/assets/css/material-design-iconic-font.min.css")]
               ["resources/public/assets/css/re-com.css"                           (render' "resources/public/assets/css/re-com.css")]
               ["resources/public/assets/css/bootstrap.css"                        (render' "resources/public/assets/css/bootstrap.css")]
               ["resources/public/css/style.css"                                   (render' "resources/public/css/style.css")]
               ["resources/public/{{project-name}}.html"                           (render' "resources/public/instance-name.html")]
               
               ["resources/public/assets/css/chosen-sprite.png"                    (raw "resources/public/assets/css/chosen-sprite.png")]
               ["resources/public/assets/css/chosen-sprite@2x.png"                 (raw "resources/public/assets/css/chosen-sprite@2x.png")]
               ["resources/public/assets/fonts/Material-Design-Iconic-Font.eot"    (raw "resources/public/assets/fonts/Material-Design-Iconic-Font.eot")]
               ["resources/public/assets/fonts/Material-Design-Iconic-Font.ttf"    (raw "resources/public/assets/fonts/Material-Design-Iconic-Font.ttf")]
               ["resources/public/assets/fonts/Material-Design-Iconic-Font.woff"   (raw "resources/public/assets/fonts/Material-Design-Iconic-Font.woff")]
               ["resources/public/assets/fonts/Material-Design-Iconic-Font.woff2"  (raw "resources/public/assets/fonts/Material-Design-Iconic-Font.woff2")]
               ["resources/public/assets/fonts/Material-Design-Iconic-Font.svg"    (raw "resources/public/assets/fonts/Material-Design-Iconic-Font.svg")]))))

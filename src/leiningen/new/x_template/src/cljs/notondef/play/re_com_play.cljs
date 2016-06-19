(ns {{ns-name}}.play.re-com-play)

(defn make-re-com-stuff []
  (time
   (let [text          "Rhubarb"
         approach      1
         nesting-level (case 2
                         1   4
                         2 620)]
     (letfn [[nestify [elements]
              (case approach
                1 (into [:div] elements)
                2 [re/v-box :children elements])]
             (stuff [n]
               (nestify (repeat 5 [:p (str n " " text)])))
             (nested-structure [n]
               (letfn [(r [cnt]
                         (if (> cnt n)
                           nil
                           (nestify [(r (inc cnt))
                                     (stuff cnt)])))]
                 (r 1)))]
       [:div
        [:h1 "{{ns-name}}"]
        (nested-structure nesting-level)]))))
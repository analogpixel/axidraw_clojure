(ns axidraw-clojure.svg-out
  (:require [dali.io :as io])
  (:use [axidraw-clojure.proto])
  (:use [axidraw-clojure.utils])
  (:use [axidraw-clojure.macros])
  )

(defrecord SvgOut [api-url svg-width svg-height virtual-width virtual-height]
  Output
  Svg

  (svg-document [this inputs]
  ;(println "svg-document" inputs)

    (let [document [:dali/page {
                                :width 1800 
                                :height 1800 
                                }  (into [:g {}] inputs)  ]]
      (io/render-svg document "test.svg")
    )
  )

  (line [this p1 p2]
    (let [p1t {:x (map-range (:x p1) 0 virtual-width 0 svg-width) :y (map-range (:y p1) 0 virtual-height 0 svg-height)}
          p2t {:x (map-range (:x p2) 0 virtual-width 0 svg-width) :y (map-range (:y p2) 0 virtual-height 0 svg-height)}
          ]
          [:line {:stroke :indigo :stroke-width 1 :fill :darkorange} (p2v p1t 1) (p2v p2t 1)]
          )
    )

  (triangle [this p1 p2 p3]
    [:g {}
        (line this p1 p2)
        (line this p2 p3)
        (line this p3 p1)
        ]
    )
  )


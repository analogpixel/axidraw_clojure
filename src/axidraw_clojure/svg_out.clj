(ns axidraw-clojure.svg-out
  (:require [dali.io :as io])
  (:use [axidraw-clojure.proto])
  )

; point to vector 
(defmacro p2v [point]
  `[(:x ~point) (:y ~point)])

(defmacro p2v [point scale]
  `[(* (:x ~point) ~scale) (* (:y ~point) ~scale)])

(defrecord SvgOut [api-url]
  Output
  Svg

  (svg-document [this inputs]
  (println "svg-document" inputs)

    (let [document [:dali/page {
                                :width 400 
                                :height 400 
                                } inputs ]]
      (io/render-svg document "test.svg")
    )
  )

  (line [this p1 p2]
    [:line {:stroke :indigo :stroke-width 4 :fill :darkorange} (p2v p1 100) (p2v p2 100)]
    )

  (triangle [this p1 p2 p3]
    [:g {}
        (line this p1 p2)
        (line this p2 p3)
        (line this p3 p1)
        ]
    )
  )


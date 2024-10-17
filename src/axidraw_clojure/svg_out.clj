(ns axidraw-clojure.svg-out
  (:require [dali.io :as io])
  (:use [axidraw-clojure.proto])
  )

(defrecord SvgOut [api-url]
  Output
  Svg

  (svg-document [this inputs]
  (println "svg-document" inputs)

    (let [document [:dali/page {:width 100 :height 100} inputs ]]
      (io/render-svg document "test.svg")
    )
  )

  (line [this p1 p2]
    (println "line" p1 p2)
    )

  (triangle [this p1 p2 p3]
        [:circle {:stroke :indigo :stroke-width 4 :fill :darkorange} [30 30] 20]
    )
  )


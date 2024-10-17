(ns axidraw-clojure.core
  (:require [axidraw-clojure.plotter-out :as plotter-out]
            [axidraw-clojure.svg-out :as svg-out ]
            )
  (:use [axidraw-clojure.proto])
  (:gen-class))

(def api-url "http://axidraw.local:9090/cmd/")

(defn -main [& args]
  ;(def p (plotter-out/->PlotterOut api-url)  )
  (def p (svg-out/->SvgOut api-url)  )

  ; use apply to pass the vector as arguments
  (svg-document p  (triangle p [0 0] [1 1] [2 0])  )
  )


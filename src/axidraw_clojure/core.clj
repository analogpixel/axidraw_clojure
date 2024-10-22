(ns axidraw-clojure.core
  (:require [axidraw-clojure.plotter-out :as plotter-out]
            [axidraw-clojure.svg-out :as svg-out ]
            )
  (:use [axidraw-clojure.proto])
  (:gen-class))

(def api-url "http://axidraw.local:9090/cmd/")

(defn -main [& args]
  ;(def p (plotter-out/->PlotterOut api-url)  )
  ; (triangle p {:x 0 :y 0} {:x 1 :y 1} {:x 2 :y 2})  
  ;(triangle-fill p {:x 0 :y 0} {:x 1 :y 2} {:x 2 :y 0}) 
  ;(triangle p {:x 0 :y 0} {:x 1 :y 2} {:x 2 :y 0}) 
  
  (def p (svg-out/->SvgOut api-url)  )

  ; use apply to pass the vector as arguments
  (svg-document p  (triangle p {:x 0 :y 0} {:x 1 :y 2} {:x 2 :y 0})) 
  )


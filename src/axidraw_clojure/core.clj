(ns axidraw-clojure.core
  (:require [axidraw-clojure.plotter-out :as plotter-out]
            [axidraw-clojure.svg-out :as svg-out ]
            )
  (:use [axidraw-clojure.proto])
  (:use [axidraw-clojure.utils])
  (:use [axidraw-clojure.macros])
  (:gen-class))

(def api-url "http://axidraw.local:9090/cmd/")

(defn -main [& args]
  
  (def p (svg-out/->SvgOut api-url)  )
  ;(def p (plotter-out/->PlotterOut api-url)  )

  ; (def drawing  [  
  ;               (triangle-fill p {:x 0 :y 0} {:x 1 :y 2} {:x 2 :y 0})
  ;               (triangle-fill p {:x 0 :y 2} {:x 1 :y 0} {:x 2 :y 2} )
  ;               ]
  ;   )
 

  (def p1 {:x 1 :y 0}) 
  (def p2 {:x 1 :y 1})
  (def cp (center-point p1 p2))

  ;(reset-plotter p)
  (def drawing [
               (line p p1 p2)
               (do-range #(line p (rotate-point p1 cp %) (rotate-point p2 cp %)) 0 3 0.5)
               ]
   ) 

  ;(reset-plotter p)

  ; use apply to pass the vector as arguments
  (svg-document p drawing ) 
  )


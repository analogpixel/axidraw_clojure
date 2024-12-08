(ns axidraw-clojure.core
  (:require [axidraw-clojure.plotter-out :as plotter-out]
            [axidraw-clojure.svg-out :as svg-out ]
            )
  (:use [axidraw-clojure.proto])
  (:use [axidraw-clojure.utils])
  (:use [axidraw-clojure.macros])
  (:use [axidraw-clojure.grid])
  (:gen-class))

(def api-url "http://axidraw.local:9090/cmd/")

(defn -main [& args]
 
  ; create a canvas 200x200 with a virtual size of 100x100 
  ;(def p (svg-out/->SvgOut api-url 200 200 100 100)  )

  ; create a plot 8inches x 8inches with a virtual size of 100x100
  (def p (plotter-out/->PlotterOut api-url 8 8 100 100)  )

  ; (def drawing  [  
  ;               (triangle-fill p {:x 0 :y 0} {:x 1 :y 2} {:x 2 :y 0})
  ;               (triangle-fill p {:x 0 :y 2} {:x 1 :y 0} {:x 2 :y 2} )
  ;               ]
  ;   )
 

  (def pixels (load-pixels "low_polly_skull_small.jpg"))

  ; grid from pixels willcreate a grid x cells wide and y cells high
  ; with the cell size being calculated from the pixels dimensions
  ; so if you have 10 columns in your grid, and you image is 200pixels wide
  ; then each cell will be 20 pixels wide
  (def g (grid-from-pixels pixels 40 40))

  ; (def p1 {:x 1 :y 0}) 
  ; (def p2 {:x 1 :y 1})
  ; (def cp (center-point p1 p2))
  
  (defn make-lines [g c r]
    (let [p1 (grid-center-w g c r)
          p2 (grid-center-e g c r)
          cp (grid-center g c r)
          c1 (map-range (pixel-at pixels cp 0) 0 255 0 (* Math/PI 2) )
          ]
      (line p (rotate-point p1 cp c1) (rotate-point p2 cp c1))
      )
    )

  ;(grid-do g make-lines)

  (reset-plotter p)
  (def drawing [
               (grid-do g make-lines)
               ;(line p p1 p2)
               ;(do-range #(line p (rotate-point p1 cp %) (rotate-point p2 cp %)) 0 3 0.5)
               ]
   ) 

  (reset-plotter p)

  ; use apply to pass the vector as arguments
  ; (svg-document p drawing ) 
  )


(ns axidraw-clojure.utils
  (:use axidraw-clojure.macros) 
  )

; rotate point  p1 around center point c by th radians
(defn rotate-point [p1 c th]
  (let [x (- (:x p1) (:x c))
        y (- (:y p1) (:y c))
        xr (- (* x (Math/cos th)) (* y (Math/sin th)))
        yr (+ (* x (Math/sin th)) (* y (Math/cos th)))
        nx (+ xr (:x c))
        ny (+ yr (:y c))
        ]
    {:x nx :y ny}
    )
  )

(defn center-point [p1 p2]
 {
  :y (/ (+ (:y p1) (:y p2)) 2)
  :x (/ (+ (:x p1) (:x p2)) 2)
  }
 )
 

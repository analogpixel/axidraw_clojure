(ns axidraw-clojure.plotter-out
  (:use [axidraw-clojure.proto]))

; get the slope of a line defined by points p1 and p2
(defn slope [p1 p2] 
  (/ (- (:y p2) (:y p1)) (- (:x p2) (:x p1)))
  )


; given a point, a slope, and a y value (height)
; return where the x would be
(defn x-point-on-line [slope p1 y2]
  (/ 
    (+ (- y2 (:y p1))  (* slope (:x p1))) 
    slope
  )
)

(defrecord PlotterOut [api-url]
  Output
  Plotter

  (send-command [this cmd options] 
    (if options
      (let [response (slurp (str (:api-url this) cmd "/" options))]
        (println response))
      (let [response (slurp (str (:api-url this) cmd))]
        (println response))
      )
    )

  ;; Plotter
  (pen-up   [this] (send-command this "pu" nil) )
  (pen-down [this] (send-command this "pd" nil) )

  ;; Output
  (line [this p1 p2]
     (send-command this "mt" (str (:x p1) "," (:y p1)))
     (send-command this "lt" (str (:x p2) "," (:y p2)))
    )

  ; draw a triangle
  (triangle [this p1 p2 p3]
    (line this p1 p2)
    (line this p2 p3)
    (line this p3 p1)
    (pen-up this)
    )

  ; draw a filled triangle
  (triangle-fill [this p1 p2 p3]
    (let [
          s1 (slope p1 p2)
          s2 (slope p3 p2)
          max_y (apply max (map #(:y %) [p1 p2 p3]))
          min_y (apply min (map #(:y %) [p1 p2 p3]))
          r (range min_y max_y 0.1)  
          pts (map #(vector 
              {:x (x-point-on-line s1 p1 %) :y %} 
              {:x (x-point-on-line s2 p2 %) :y %} 
              ) r)
          ]
        (mapv #(apply line this %) pts)
        (pen-up this)
      )
    )


  )



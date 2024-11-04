(ns axidraw-clojure.plotter-out
  (:use [axidraw-clojure.proto])
  (:use [axidraw-clojure.utils])
  )

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

(defrecord PlotterOut [api-url plotter-width plotter-height virtual-width virtual-height]
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

  (reset-plotter [this] 
    (pen-up this)
     (send-command this "mt" "0,0")
     )
     
  ;; Output
  (line [this p1 p2]
     (send-command this "mt" (str 
                               (map-range (:x p1) 0 virtual-width 0 plotter-width)  "," 
                               (map-range (:y p1) 0 virtual-height 0 plotter-height)
                               ))

     (send-command this "lt" (str 
                               (map-range (:x p2) 0 virtual-width 0 plotter-width)  "," 
                               (map-range (:y p2) 0 virtual-height 0 plotter-height)
                               ))

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



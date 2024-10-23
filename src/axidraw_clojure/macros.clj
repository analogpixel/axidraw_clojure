(ns axidraw-clojure.macros)

; convert point {:x :y} to vector [x y]
(defmacro p2v [point]
  `[(:x ~point) (:y ~point)])

; convert point {:x :y} to vector [x y] and scale it
(defmacro p2v [point scale]
  `[(* (:x ~point) ~scale) (* (:y ~point) ~scale)])


; doall is there so the plotter will actually plot 
; otherwise clojure's laziness takes over
(defmacro do-range [f start stop step]
  `(doall 
     (map ~f 
          (range ~start ~stop ~step))))


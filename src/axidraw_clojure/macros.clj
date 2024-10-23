(ns axidraw-clojure.macros)

; point to vector 
(defmacro p2v [point]
  `[(:x ~point) (:y ~point)])

(defmacro p2v [point scale]
  `[(* (:x ~point) ~scale) (* (:y ~point) ~scale)])


(defmacro do-range [f start stop step]
  `(doall 
     (map ~f 
          (range ~start ~stop ~step))))


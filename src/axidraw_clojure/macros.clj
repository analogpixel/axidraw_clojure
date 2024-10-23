(ns axidraw-clojure.macros)

; point to vector 
(defmacro p2v [point]
  `[(:x ~point) (:y ~point)])

(defmacro p2v [point scale]
  `[(* (:x ~point) ~scale) (* (:y ~point) ~scale)])


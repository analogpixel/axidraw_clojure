(ns axidraw-clojure.grid)

(defrecord Grid [cols rows w h])

(defn grid-from-pixels [pixels cols rows]
  (Grid. cols rows (/ (count pixels) cols) (/ (count (first pixels)) rows))
  )

(defn grid-nw [grid col row]
  {:x (* col (:w grid))
   :y (* row (:h grid))
   }
  )

(defn grid-ne [grid col row]
  {:x (+ (* col (:w grid)) (:w grid))
   :y (* row (:h grid))
   }
  )

(defn grid-sw [grid col row]
  {:x (* col (:w grid))
   :y (+ (* row (:h grid)) (:h grid))
   }
  )

(defn grid-se [grid col row]
  {:x (+ (* col (:w grid)) (:w grid))
   :y (+ (* row (:h grid)) (:h grid))
   }
  )

(defn grid-center [grid col row]
  {:x (+ (* col (:w grid)) (/ (:w grid) 2))
   :y (+ (* row (:h grid)) (/ (:h grid) 2))
   }
  )


(defn grid-center-w [grid col row]
  {:x (* col (:w grid))
   :y (+ (* row (:h grid)) (/ (:h grid) 2))
   }
  )

(defn grid-center-e [grid col row]
  {:x (+ (* (+ col 1) (:w grid))) 
   :y (+ (* row (:h grid)) (/ (:h grid) 2))
   }
  )

(defn grid-center-n [grid col row]
  {:x (+ (* col (:w grid)) (/ (:w grid) 2))
   :y (* row (:h grid))
   }
  )

(defn grid-center-s [grid col row]
  {:x (+ (* col (:w grid)) (/ (:w grid) 2))
   :y (+ (* row (:h grid)) (:h grid))
   }
  )

(defn grid-do [grid f]
  (doall
  (for [row (range (:cols grid))
        col (range (:rows grid))
        ]
    (f grid col row)
    )
  )
  )

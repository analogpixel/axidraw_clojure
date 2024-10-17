(ns axidraw-clojure.plotter-out
  (:use [axidraw-clojure.proto]))

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
    (send-command this "mt" (str (p1 0) "," (p1 1)))
    (send-command this "lt" (str (p2 0) "," (p2 1)))
    )

  (triangle [this p1 p2 p3]
    (line this p1 p2)
    (line this p2 p3)
    (line this p3 p1)
    (pen-up this)
    )

  )



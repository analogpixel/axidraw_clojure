(ns axidraw-clojure.proto)

(defprotocol Output
   (line [this p1 p2])
   (triangle [this p1 p2 p3])
   )

(defprotocol Svg
  (svg-document [this inputs])
  )

(defprotocol Plotter
  (send-command [this cmd options]) ; send a command to the plotter 
  (pen-up [this]) ; pen down
  (pen-down [this]) ; pen up
  )

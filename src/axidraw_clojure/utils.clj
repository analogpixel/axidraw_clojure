(ns axidraw-clojure.utils
  (:use axidraw-clojure.macros) 
  (:use mikera.image.core)
  (:use mikera.image.colours)
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

(defn map-range
  "Maps a value from one range to another range."
  [value from-min from-max to-min to-max]
  (let [scale (/ (- to-max to-min) (- from-max from-min))]
    (+ (* scale (- value from-min)) to-min))
)

(defn load-pixels [filename]
  (->> filename
       load-image
       get-pixels
       (map color)
       (mapv #(vector (.getRed %) (.getGreen %) (.getBlue %) (.getAlpha %)))
       (partition 100 )  ; TODO fix needs to be width, not just a static number
       (mapv vec)
       )
)

(defn pixel-at [pixels p1]
  (let [
        x (:x p1)
        y (:y p1)
        ]
    (nth (nth pixels y) x)
    )
  )

(defn pixel-at [pixels p1 idx]
  (let [
        x (:x p1)
        y (:y p1)
        ]
    (nth (nth (nth pixels y) x) idx)
    )
  )

(defn grid-avg-pixels [pixels xstart xend ystart yend]
  "return the average pixel value given a block of pixels start->upto"

  (let [
        subrange (mapv #(subvec % xstart xend) (subvec pixels ystart yend))
        values   (map #(reduce + (map first %)) subrange)
        cnt      (* (- xend xstart) (- yend ystart)) 
        total    (reduce + values)
        avg      (/ total cnt)
       ] 
    avg
    )

  )


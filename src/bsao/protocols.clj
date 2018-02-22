(ns bsao.protocols)

; Protocols are a little easier to understand and they might feel more similar to Java interfaces.
; Define a protocol and it is called Shape and everything that implements this protocol
; implement the following two functions: perimeter and area.
(defprotocol Shape
  "This is a protocol for Shapes"
  (perimeter [this] "Calculate the perimeter")
  (area [this] "Calculate the area"))

; There are a number of ways to implement a protocol;
; One interesting feature is that you can even extend Java classes to implement a protocol without an
; access to the Java source and without having to recompile anything.


; Records work exactly like maps, but they are much faster if you stick to the predefined keys.
; Clojure knows beforehand about the fields that the record will have, so it can generate byte code on the
; fly and the code that uses the records is much faster.

(defrecord Square [size]
  Shape
  (perimeter [{:keys [size]}] (* 4 size))
  (area [{:keys [size]}] (* size size)))

(def square
  (Square. 5))

(println square)

(println
  (let [{size :size} square] size))

(println
  (let [{:keys [size]} square] size))

(doseq [[k v] (Square. 5)] (println k v))

; it works exactly like a map, you can even associate things to it
(assoc (Square. 5) :hello :world)

(println
  (perimeter square))

(println
  (area square))

(defrecord Circle [radius]
  Shape
  (perimeter [{:keys [radius]}] (* Math/PI 2 radius))
  (area [{:keys [radius]}] (* Math/PI radius radius)))

(def circle (Circle. 5))

(println
  (perimeter circle))

(println
  (area circle))

; We will be able to extend our existing records and types without having to touch the current code

(defprotocol ShapeProperties
  (num-sides [this] "How many sides a shape has"))

(extend-type Square ShapeProperties
  (num-sides [this] 4))

(extend-type Circle ShapeProperties
  (num-sides [this] Double/POSITIVE_INFINITY))

(println
  (num-sides square))

(println
  (num-sides circle))

; Protocols become much more interesting when you extend them for Java types

(defprotocol ListOps
  (positive-values [list])
  (negative-values [list])
  (non-zero-values [list]))

(extend-type java.util.List ListOps
  (positive-values [list]
    (filter #(> % 0) list))
  (negative-values [list]
    (filter #(< % 0) list))
  (non-zero-values [list]
    (filter #(not= % 0) list)))

(println
  (positive-values [-1 0 1]))

(println
  (negative-values [-1 0 1]))

(println
  (non-zero-values [-1 0 1]))

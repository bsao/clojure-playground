(ns bsao.repl)

"Hello World"

(println "Hello World")

(defn some-function [times parameter]
  "Prints a string"
  (dotimes [x times]
    (println parameter)))

(* 1 2 3)
(+ 5 9 7)
(/ 4 5)
(- 2 3 4)
(map inc [1 2 3 4 5 6])

(defn hello
  ([] hello "Clojure")
  ([name] (str "Hello" name)))


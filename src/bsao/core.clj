(ns bsao.core
  (:gen-class))

;; :gen-class was added here in order to create a Java class and allow the Java interpreter
;; to start a static main method. It is not needed if you don't intend to create a standalone program.
(defn -main
  [& args]
  (println "Hello, World!!"))
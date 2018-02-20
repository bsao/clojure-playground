(ns bsao.ns
  (:import [java.util Date Properties])
  (:require [clojure.java.io :as io]))

;; Keep in mind that a namespace in Clojure is normally represented by a single file.

;; The require option uses a similar syntax and then builds some more on it.
;; Let's check requiring a single function from a namespace
;; (:require [some.package :refer [a-function another-function]])

;; As you can see, it is familiar and the interesting part is when you start importing everything:
;; (:require [some.package :refer [:all]]):

;; You can also use a custom name for everything inside your package:
;; (:require [some.package :as s])
;; And then use everything in the package like this:
;; (s/a-function 5)
;; Or you could even combine different keywords:
;; (:require [some.package :as s :refer [a-function]])

; Require
; require loads a Clojure library so that you can use it in your current file or REPL.
; This is the normal way to access functions and definition in a Clojure library.
;
; Use
; use brings in a Clojure namespace in the same way as require,
; but in addition it refers to the definitions in the loaded namespace from the current
; namespace (i.e. it creates a convenient alias in the current namespace).
; Don't over-use it (pun intended) - it can easily cause namespace conflicts!
;
; Import
; import is for importing Java classes and interfaces only.


(def addition +)

(defn current-date []
  "Returns the current Date"
  (new Date))

(defn <3
  "Creates a sequence of all the {loved-ones} {loved} loves"
  [love & loved-ones]
  (for [loved-one loved-ones]
    (str love " love " loved-one)))

(defn sum-something
  "Adds something to all the remaining parameters"
  [something & nums]
  (apply addition something nums))

(def sum-one (partial sum-something 1))

;; You must have noticed the & operator in the arguments of the <3 and sum-something functions;
;; this allows those functions to receive any number of arguments and we can call them,
;; as shown: (sum-something 1 2 3 4 5 6 7 8) or (sum-something)
;; They are called variadic functions. In Java you will call this feature varargs.

(defn read-properties
  "docstring"
  [path]
  (let [resource (io/resource path)
        is (io/input-stream resource)
        props (Properties.)]
    (.load props is)
    (.close is)
    props))

;; The let form lets us create local 'variables', instead of using the (io/resource path) directly
;; in the code. We can create a reference once and use it through the code.
;; It allows us to use simpler code and to have a single reference to an object.
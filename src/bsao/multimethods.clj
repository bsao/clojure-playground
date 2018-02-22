(ns bsao.multimethods)

; Keywords are symbolic identifiers that evaluate to themselves.
; (defmulti name docstring? attr-map? dispatch-fn& options)

; The dispatch function gets called for every piece of content; it generates a dispatch key that is later checked
; against its function implementation. When the dispatch key and the key in the function implementation match,
; the function is called.

;(defmulti function-name dispatch-function)

; In this case, the multimethod is called area and the dispatch function is the :shape keyword.
(defmulti area "Calculate an Area" :shape)

; define a multimethod
; Each implementation function must define a dispatch key, if it matches with the dispatch function's result,
; then this function is executed.

; (defmethod function-name dispatch-key [params] function-body)
(defmethod area :square [{:keys [side]}]
  (* side side))
; Note that the dispatch-key is always the result of invoking the dispatch-function with params as parameters.


; The first thing that happens is that we call the dispatch function :shape
; The :square function is now the dispatch key, we need to look for the method that has that dispatch key;
; In this case, the only method that we defined works.
(println
  (area {:shape :square :side 5}))

; It is pretty simple to add the area and perimeter for both square and circle.
; Now, we have defined how to calculate the perimeter and area of circles and squares with very little effort and
; without having to define a very strict object hierarchy. However, we are just starting to uncover
; the power of multimethods.


; Keywords can be namespaced, it allows you to keep your code better organized. There are two ways to define a
; namespaced keyword, such as :namespace/keyword and ::keyword. When using the :: notation, the used namespace is the
; current namespace. So if you write ::test in the REPL, you will be defining :user/test.
(defmethod area :circle [{:keys [radius]}]
  (* Math/PI radius radius))

(defmulti perimeter :shape)

(defmethod perimeter :square [{:keys [side]}]
  (* side 4))

(defmethod perimeter :circle [{:keys [radius]}]
  (* 2 Math/PI radius))

(println
  (perimeter {:shape :square :side 5})
  (perimeter {:shape :circle :radius 5}))

; Let's try another example
(defmulti walk :type)

(defmethod walk ::animal [_]
  "Just Walk")

(defmethod walk ::primate [_]
  "Primate Walk")

(println
  (walk {:type ::animal})
  (walk {:type ::primate}))

; You can declare that a keyword derives from another keyword and then respond to other dispatch keys,
; for that you can use the derive function:
(derive ::hominid ::primate)

; Parent must be a namespace-qualified symbol or keyword and
; child can be either a namespace-qualified symbol or keyword or a class
(println
  (walk {:type ::primate})
  (walk {:type ::hominid}))

(derive ::hominid ::animal)
; (walk {:type ::hominid})
;; java.lang.IllegalArgumentException: Multiple methods in multimethod 'walk' match dispatch value:
;; :boot.user/hominid -> :boot.user/animal and :boot.user/primate, and neither is preferred

(prefer-method walk ::hominid ::primate)
;(println
;  (walk {:type ::hominid}))

(defmethod walk ::hominid [_] "Walk in two legs")
(println
  (walk {:type ::hominid}))


; DERIVATIONS

; The isa function checks if a type derives from some other type,
; it works with Java classes as well as Clojure keywords.
(println
  (isa? java.util.ArrayList java.util.List)
  (isa? ::hominid ::animal)
  (isa? ::animal ::primate))


; The parent function returns a set of a type's parents, they might be Java or Clojure keywords:
(println
  (parents java.util.ArrayList)
  (parents ::hominid))


; The descendants function, as you can imagine, returns a set of descendants of the passd keyword;
; it is important to keep in mind that in this case only Clojure keywords are allowed
(println
  (descendants ::animal))

; The underive function breaks the relation between two types,
; as you can imagine it only works with the Clojure keywords
(underive ::hominid ::animal)
(println
  (isa? ::hominid ::animal))

; A LA CARTE DISPATCH FUNCTIONS

; we have used a keyword as a dispatch function but you can use any function
; you like with as many arguments as you want.
;   This is a simple function, but it shows two important facts:
;     - The dispatch function can receive more than one argument
;     - The dispatch key can be anything, not just a keyword

(defn get-race [& ages]
  (if (> (/ (apply + ages) (count ages)) 120)
    :timelord
    :human))

(defmulti travel get-race)

(defmethod travel :timelord [& ages]
  (str (count ages) " timelords travelling by tardis"))

(defmethod travel :human [& ages]
  (str (count ages) " humans travelling by car"))

(println
  (travel 2000 1000 100 200))

(println
  (travel 80 20 100 40))

(defn multisports-fn [sport1 sport2]
  [sport1 sport2])

(defmulti multisports
          "Define the multisports for passed args"
          multisports-fn)

(defmethod multisports [:swin :running] [swin running] [:aquathlon running swin])
(defmethod multisports [:cycle :running] [cycle running] [:duathlon cycle running])

(println
  (multisports :swin :running))

(println
  (multisports :cycle :running))


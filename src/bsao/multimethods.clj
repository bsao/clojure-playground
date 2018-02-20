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
(area {:shape :square :side 5})

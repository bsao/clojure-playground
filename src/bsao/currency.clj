(ns bsao.currency)

(defn as-currency
  "Money amounts are transmitted as \"$2.44\".
 Parse this and return a numeric type."
  [currency-amount]
  ;; strip off the leading "$"
  (-> (subs currency-amount 1)
      (bigdec)))

(defn sum-transactions [transactions]
  (let [amounts (map :amount transactions)
        currencies (map as-currency amounts)]
    (reduce + currencies)))

(sum-transactions [ "$1.22" "$2.22"])
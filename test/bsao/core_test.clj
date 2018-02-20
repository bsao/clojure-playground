(ns bsao.core-test
  (:import [java.util Date])
  (:require [clojure.test :refer :all]
            [bsao.core :refer :all]
            [bsao.ns :as hello :refer [<3]]))

(deftest a-test
  (testing "FIXME, I fail."
    (is (= 1 1))))

(defn- lazy-contains? [seq element]
  (not (empty? (filter #(= element %) seq))))

(deftest current-date-is-date
  (testing "Testing that the current date is a date"
    (is (instance? Date (hello/current-date)))))


(deftest check-loving-collection
  (testing "Check that I love clojure and you"
    (let [loving-seq (hello/<3 "I" "Clojure" "you" "doggies" "chocolate")]
      (is (not (lazy-contains? loving-seq "I love Vogons")))
      (is (lazy-contains? loving-seq "I love Clojure"))
      (is (lazy-contains? loving-seq "I love doggies"))
      (is (lazy-contains? loving-seq "I love chocolate"))
      (is (lazy-contains? loving-seq "I love you")))))

;; Interactive programming means, telling every step of how something should be done to a computer.
;; Declarative programming just asks for a result and doesn't give details of how to achieve it.


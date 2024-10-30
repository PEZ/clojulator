(ns clojulator.calculator.core
  (:require
   [clojulator.calculator.evaluator :refer [evaluate]]
   [clojulator.calculator.history :refer [update-history]]
   [clojulator.calculator.parser :refer [parse]]
   [clojulator.calculator.scanner :refer [tokenize]]))

(defn calculate
  "Given an expressions as a string, attempts to parse
  the string and return the result. Updates the given
  history object with the result."
  [expression state]
  (try
    (let [value (-> expression tokenize parse (evaluate (:history state)))]
      (-> state
          (assoc :value value)
          (update-history value)
          (dissoc :error)))
    (catch #?(:clj Exception
              :cljs js/Error) e
      (assoc state :error (ex-message e)))))

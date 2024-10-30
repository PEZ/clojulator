(ns clojulator.calculator.history)

(defn clear-history
  [state]
  (update state :history empty))

(defn update-history
  [{:keys [history] :as state} value]
  (let [[p1 p2] history]
    (assoc state :history [value p1 p2])))

(defn repl1
  [history]
  (first history))

(defn repl2
  [history]
  (second history))

(defn repl3
  [history]
  (nth history 2))

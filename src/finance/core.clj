(ns finance.core
  (:use [finance chart]))

(defn average-income [age profession])

(defn rendering [{age :age profession :profession} [w h]]
  (let [income (average-income age profession)
        chart-title (str "Average American " age "-year-old " profession)]
    (retirement-chart chart-title age 32000)))
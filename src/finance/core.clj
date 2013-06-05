(ns finance.core)

(defn average-income [age profession])
(defn retirement-chart [title start-age start-income])
(defn rendering [{age :age profession :profession}]
  (let [income (average-income age profession)
        chart-title (str "Average American " age "-year-old " profession)]
    (retirement-chart chart-title age 32000)))
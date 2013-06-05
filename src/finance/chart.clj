(ns finance.chart
  (:use [fui drawing]))


(defn chart-x-axis
  ([label x-min x-max maj-step] (chart-x-axis label x-min x-max maj-step maj-step))
  ([label x-min x-max maj-step min-step]))
(defn chart-y-axis [label y-min y-max maj-step])
(defn chart-line-plot [[x-min x-max] [y-min y-max] data])

(defn retirement-data [start-age start-income end-age])
(defn max-y-value [numbers])
(defn round-number [number])
(defn nice-step [number])

(defn retirement-chart [title start-age start-income]
  (let [data (retirement-data start-age start-income 100)
        max-y (round-number (max-y-value data))]
    [(draw-text title 0 10)
     (chart-x-axis "Age" start-age 100 1)
     (chart-y-axis "Retirement assets (USD)" 0 max-y (nice-step max-y))
     (chart-line-plot [start-age 100] [0 max-y] data)]))
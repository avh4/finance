(ns finance.chart
  (:require [deft [color :as color] [color-basic :as c]])
  (:use [deft drawing]))

(def axis-color c/black)
(defn chart-x-axis
  ([label x-min x-max maj-step] (chart-x-axis label x-min x-max maj-step maj-step))
  ([label x-min x-max maj-step min-step]
    [(solid-rect [10 100 300 1] axis-color)]))
(defn chart-y-axis [label y-min y-max maj-step])
(defn chart-line-plot [[x-min x-max] [y-min y-max] data])

(defn apply-inflation [x & _] (* x (Math/exp 0.04)))
(defn apply-interest [x] (* x (Math/exp 0.07)))
(defn simulate-year [{start-assets :assets income :income} [age expenses]]
  (let [withdrawal (if (> age 65) expenses 0)
        deposit (if (> age 65) 0 income)]
    {:assets (-> (- start-assets withdrawal) (apply-interest) (+ deposit))
     :income (-> income (apply-inflation))
      }))
(defn retirement-data [start-age start-income end-age]
  (let [ages (range start-age end-age)
        tax-rate 0.25
        savings-rate 0.10
        salary 32000
        start-assets 73500 ; for a 30-year-old :: http://mymoneycounselor.com/net-worth-how-are-you-doing
        expenses (reductions apply-inflation (* salary (- 1 tax-rate savings-rate)) ages)]
    (map vector ages (map :assets (reductions simulate-year {:assets start-assets :income (* salary savings-rate)} (map vector ages expenses))))))
(defn max-y-value [numbers])
(defn round-number [number])
(defn nice-step [number])

(defn retirement-chart [title start-age start-income]
  (let [data (retirement-data start-age start-income 100)
        max-y (round-number (max-y-value data))]
    [(drawn-text title 0 10 (color/rgb 0 0 0) "Lucida Grande" 13)
     (chart-x-axis "Age" start-age 100 1)
     (chart-y-axis "Retirement assets (USD)" 0 max-y (nice-step max-y))
     (chart-line-plot [start-age 100] [0 max-y] data)]))
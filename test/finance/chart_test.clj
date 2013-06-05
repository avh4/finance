(ns finance.chart-test
  (:use [finance chart])
  (:use [fui drawing])
  (:use [midje.sweet]))

(def max-age 100)

(facts "about retirement chart renderings"
  (fact "it has a chart title"
    (retirement-chart ..title.. .age. .income.) => (contains ..title-rendering..)
    (provided
      (draw-text ..title.. 0 10) => ..title-rendering..))
  (fact (str "it has an x-axis showing age up to " max-age)
    (retirement-chart .title. ..age.. .income.) => (contains ..x-axis-rendering..)
    (provided
      (chart-x-axis "Age" ..age.. max-age 1) => ..x-axis-rendering..))
  (fact "it has a y-axis showing dollar amounts"
    (retirement-chart .title. ..age.. ..income..) => (contains ..y-axis-rendering..)
    (provided
      (max-y-value (retirement-data ..age.. ..income.. max-age)) => ..max-dollars..
      (round-number ..max-dollars..) => ..max-y..
      (nice-step ..max-y..) => ..y-step..
      (chart-y-axis "Retirement assets (USD)" 0 ..max-y.. ..y-step..) => ..y-axis-rendering..))
  (fact "it shows retirement assets vs age"
    (retirement-chart .title. ..age.. ..income..) => (contains ..assets-plot..)
    (provided
      (max-y-value (retirement-data ..age.. ..income.. max-age)) => ..max-dollars..
      (round-number ..max-dollars..) => ..max-y..
      (chart-line-plot [..age.. max-age] [0 ..max-y..] (retirement-data ..age.. ..income.. max-age)) => ..assets-plot..)))
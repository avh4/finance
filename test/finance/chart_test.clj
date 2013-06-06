(ns finance.chart-test
  (:use [finance chart])
  (:use [fui drawing])
  (:use [midje.sweet]))

(def max-age 100)
(defn round [x] (Math/round (double x)))

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

(facts "About retirement data"
  (fact (str "it has a data point for each year from the start age to age " max-age)
    (map first (retirement-data 30 32000 max-age)) => (range 30 max-age))
  (fact "it shows the start-of-year retirement assets"
    (map round (map second (retirement-data 30 32000 max-age))) => [73500 82029 91308 101395 112355 124257 137175 151189 166385 182857 200702 220028 240951 263593 288088 314579 343219 374174 407622 443752 482770 524896 570368 619439 672383 729494 791087 857500 929099 1006274 1089445 1179063 1275613 1379614 1491627 1612250 1742127 1774290 1804942 1833817 1860623 1885040 1906719 1925276 1940293 1951316 1957846 1959341 1955213 1944819 1927461 1902381 1868756 1825691 1772216 1707279 1629739 1538361 1431804 1308621 1167242 1005970 822969 616253 383677 122922 -168516 -493344 -854482 -1255084]))

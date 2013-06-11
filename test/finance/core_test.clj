(ns finance.core-test
  (:use [finance core chart])
  (:use [midje.sweet]))

(facts "about the Average American screen"
  (fact "shows a retirement chart for the selected age and profession"
    (rendering [400 640] {:age 30 :profession "Average Joe"}) => (contains ..chart-rendering..)
    (provided
      (average-income 30 "Average Joe") => 32000
      (retirement-chart "Average American 30-year-old Average Joe" 30 32000) => ..chart-rendering..)))

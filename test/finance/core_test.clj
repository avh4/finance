(ns finance.core-test
  (:require [clojure.test :refer :all]
            [finance.core :refer :all]))
(ns finance.core-test
  (:use [finance.core])
  (:use [midje.sweet]))

(facts "about failing tests"
  (fact "they fail"
    (= 0 1) => true ))

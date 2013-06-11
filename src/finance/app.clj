(ns finance.app
  (:use [finance core])
  (:use [lamina core])
  (:use [deft widgets drawing]))

(def model {:age 30 :profession "Average Joe"})
(def main-screen (CustomComponent [400 640] rendering))
(def app
  (application
    "Retirement planning"
    model
    (display main-screen)))

(defn -main []
  (run app))
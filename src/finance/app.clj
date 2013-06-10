(ns finance.app
  (:use [finance core])
  (:use [lamina core])
  (:use [deft widgets drawing]))

(def model {:age 30 :profession "Average Joe"})
(def graphics (rendering model))
(def app (window (component 400 640 (channel graphics) (channel)) (channel "Retirement planning")))

(defn -main []
  (show app))
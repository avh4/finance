(defproject finance "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [lamina "0.5.0-beta9"]
                 [net.avh4.clj/deft "0.3.0-SNAPSHOT"]]
  :main finance.app
  :profiles {
    :dev {
      :plugins [[lein-midje "3.0.0"]]
      :dependencies [[midje "1.5.0"]] }} )

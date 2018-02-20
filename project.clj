(defproject clojure-spark "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://bsao.me/clojure"
  :license {:name "Eclipse Public License"
            :url  "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.9.0"]
                 [org.imgscalr/imgscalr-lib "4.2"]]
  ;:repositories [["jcenter" "http://jcenter.bintray.com/"]]
  :main ^:skip-aot bsao.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})

;; Leiningen supports a wide array of options to configure your project,
;; for more information you can check the sample at Leiningen's official repository:
;; https://github.com/technomancy/leiningen/blob/master/sample.project.clj.

;; lein deps :tree -< check dependencies


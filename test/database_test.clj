(ns database-test
  (:require [clojure.test :refer :all]
            [logical-interpreter :refer :all]))

(def incomplete-database "
	varon(juan).
	varon
")

(deftest fact-test
  (testing "varon(juan) should be nil"
    (evaluate-query incomplete-database "nada")
  ))


(ns fact-rule-test
  (:require [clojure.test :refer :all]
            [logical-interpreter :refer :all]))

(def incomplete-database "
	varon(juan).
	varon
")

(def complete-database "
	varon(juan).
	varon(pepe).
	padre(juan,pepe).
	madre(ana,juan).
	padres(X,Y,Z):-padre(X,Z),madre(Y,Z).
")

(deftest fact-rule-separation-test
  (testing "separating facts and rules in differents lists"
    (is (= (evaluate-query complete-database "nothing") nil))
 ))
